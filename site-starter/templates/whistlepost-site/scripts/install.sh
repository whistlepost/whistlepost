#!/usr/bin/env bash
: ${SLING_URL?"Need to set SLING_URL variable"}
./gradlew build bundleInstall -Psling_url=$SLING_URL
