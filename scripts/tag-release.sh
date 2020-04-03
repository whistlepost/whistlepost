#!/usr/bin/env bash
# tag current commit and overwrite existing tag in origin
#git tag -f release/$RELEASE_NAME && git push origin :refs/tags/release/$RELEASE_NAME && git push origin refs/tags/release/$RELEASE_NAME

# Tag release and upload artifacts to bintray..
./gradlew -q -Prelease.customUsername=$GIT_USER -Prelease.customPassword=$GIT_PASSWORD -Prelease.disableChecks -Prelease.pushTagsOnly release \
  && ./gradlew build bintrayUpload
