# Whistlepost Site Starter

The Site Starter is a collection of [lazybones] templates used to bootstrap Whistlepost 
site projects and components.

## Prerequisites

To use the Site Starter you must first install [lazybones]. The easiest way to do this
is to install [sdkman] and run:

    $ sdk install lazybones

## Templates

The following templates are available for creating a new Whistlepost site.

**Whistlepost Site**
    
This template creates a [gradle] project structure that includes three (3) sub-projects.

## Publishing templates

To install locally:

	$ echo <version> > templates/whistlepost-site/VERSION && ../gradlew installAllTemplates

To publish to bintray:

	$ ../gradlew publishAllTemplates # or use the publish-site-starter.sh script    
    
[lazybones]: https://github.com/pledbrook/lazybones

[sdkman]: http://sdkman.io/

[gradle]: https://gradle.org/
