FROM openjdk:8-jre-alpine

LABEL maintainer="Ben Fortuna <fortuna@micronode.com>"

ARG SLING_VERSION=11

RUN mkdir -p /opt/sling
RUN wget https://repo1.maven.org/maven2/org/apache/sling/org.apache.sling.starter/${SLING_VERSION}/org.apache.sling.starter-${SLING_VERSION}.jar -O /opt/sling/org.apache.sling.starter.jar

# sha1sum.txt must be updated when switching to a different jar,
# or docker build will fail
# COPY sha1sum.txt /tmp
# RUN sha1sum -c /tmp/sha1sum.txt

WORKDIR /opt/sling/
EXPOSE 8080
VOLUME /opt/sling/sling

ENV JAVA_OPTS -XX:MaxRAM=768m -Xmx384m -XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap
ENV SLING_OPTS ''

CMD exec java $JAVA_OPTS -jar org.apache.sling.starter.jar $SLING_OPTS

HEALTHCHECK CMD wget -O- localhost:8080/system/health.txt?httpStatus=CRITICAL:503
