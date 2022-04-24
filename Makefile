SHELL:=/bin/bash
include .env

NEXT_VERSION=$(wordlist 2,$(words $(MAKECMDGOALS)),$(MAKECMDGOALS))
CHANGE_JUSTIFICATION=$(wordlist 2,$(words $(MAKECMDGOALS)),$(MAKECMDGOALS))

.PHONY: all gradlew clean build changelog currentVersion markNextVersion listApiChanges approveApiChanges \
	verify release publish

all: test

gradlew:
	./gradlew wrapper --gradle-version=$(GRADLE_VERSION) --distribution-type=bin

clean:
	./gradlew clean #&& docker rmi $(REGISTRY)/$(IMAGE_NAME)

test:
	./gradlew test

build:
	./gradlew build

changelog:
	git log "$(CHANGELOG_START_TAG)...$(CHANGELOG_END_TAG)" \
    	--pretty=format:'* %s [View commit](http://github.com/ical4j/ical4j/commit/%H)' --reverse | grep -v Merge

currentVersion:
	./gradlew -q currentVersion

markNextVersion:
	./gradlew markNextVersion -Prelease.version=$(NEXT_VERSION)

verify:
	./gradlew verify

release: verify
	./gradlew release

install:
	./gradlew publishToMavenLocal

publish:
	./gradlew publish

tag:
	echo $(TAGS) | tr "/," "-\n" | xargs -n1 -I % docker tag $(REGISTRY)/$(IMAGE_NAME) $(REGISTRY)/$(IMAGE_NAME):%

push:
	echo $(TAGS) | tr "/," "-\n" | xargs -n1 -I % docker push $(REGISTRY)/$(IMAGE_NAME):%

run:
	docker run --rm -it -p8080:8080 micronode/whistlepost

up:
	docker compose up -d

down:
	docker compose down

karaf:
	#docker run --rm -it -p 8000:8101 -p 8080:8181 -v $(PWD)/etc/org.apache.karaf.features.cfg:/etc/org.apache.karaf.features.cfg apache/karaf
	docker run --rm -it -p 8000:8101 -p 8080:8181 \
		-e ORG_APACHE_KARAF_FEATURES_FEATURESREPOSITORIES='${featuresRepositories},mvn:org.apache.sling/org.apache.sling.karaf-features/0.2.0-SNAPSHOT/xml/features' \
		-e ORG_APACHE_KARAF_FEATURES_FEATURESBOOT='${featuresBoot},felix-http,webconsole,sling-configs' \
		apache/karaf

