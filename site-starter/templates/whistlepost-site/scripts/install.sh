#!/usr/bin/env bash
: ${SLING_URL?"Need to set SLING_URL variable"}
./gradlew build installBundle -Psling_url=$SLING_URL
