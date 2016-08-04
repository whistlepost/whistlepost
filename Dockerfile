FROM java:openjdk-8-jdk

# Set the WORKDIR. All following commands will be run in this directory.
WORKDIR /app

# Copying all gradle files necessary to install gradle with gradlew
COPY gradlew build.gradle settings.gradle gradle.properties ./
COPY gradle gradle

COPY sling-bootstrap-lib sling-bootstrap-lib
COPY sling-analytics-lib sling-analytics-lib
COPY sling-comments-lib sling-comments-lib
COPY whistlepost-rewrite-lib whistlepost-rewrite-lib
COPY whistlepost-rss-lib whistlepost-rss-lib
COPY wp-reactjs-lib wp-reactjs-lib
COPY whistlepost-app whistlepost-app
COPY whistlepost-content whistlepost-content
COPY whistlepost-config whistlepost-config

ENTRYPOINT ["/app/gradlew"]

CMD ["-q", "tasks", "--all"]
