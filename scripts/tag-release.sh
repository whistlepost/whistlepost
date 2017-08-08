#!/usr/bin/env bash
# tag current commit and overwrite existing tag in origin
git tag -f release/$RELEASE_NAME && git push origin :refs/tags/release/$RELEASE_NAME && git push origin refs/tags/release/$RELEASE_NAME
