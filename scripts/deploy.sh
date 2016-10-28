#!/usr/bin/env bash
: ${SLING_HOST?"Need to set SLING_HOST variable"}
: ${SLING_PORT?"Need to set SLING_PORT variable"}
./gradlew build bundleInstall -Psling_host=$SLING_HOST -Psling_port=$SLING_PORT
