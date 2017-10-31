#!/usr/bin/env bash
: ${SLING_URL?"Need to set SLING_URL variable"}
: ${SLING_PASSWORD?"Need to set SLING_PASSWORD variable"}
./gradlew build bundleInstall -Psling_url=$SLING_URL -Psling_password=$SLING_PASSWORD
