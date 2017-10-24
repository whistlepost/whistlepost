#!/usr/bin/env bash
./gradlew -q currentVersion | awk '{ print $3 }' | tr -d '\n' > site-starter/templates/whistlepost-site/VERSION
./gradlew :site-starter:packageAllTemplates :site-starter:publishAllTemplates
