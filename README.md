# Whistlepost - Web Publishing Platform

[Apache Sling]: http://sling.apache.org/
[Jekyll]: https://jekyllrb.com/
[Wordpress]: https://wordpress.org/
[Adobe Experience Manager]: https://docs.adobe.com/

[Webpack]: https://webpack.github.io
[Docker]: https://www.docker.com

[Lazybones]: https://github.com/pledbrook/lazybones
[SDKMAN]: http://sdkman.io/

[Introduction]: #introduction

[Features]: #features

[Getting Started]: #getting-started


#### Table of Contents

1. [Introduction - What is Whistlepost?][Introduction]
2. [Features][Features]
2. [Getting Started][Getting Started]

## Introduction

Whistlepose is content management system (CMS) designed to be as simple as [Jekyll], as flexible as [Adobe Experience Manager],
and as robust as [Wordpress].

Whistlepost is built on the [Apache Sling] platform, which applies the mantra that everything is a resource.
The benefit of this approach is that is separates the structure of the content from the structure of the site itself, 
which allows both (UX/UI) designers and (content) developers to focus on what is most important to them.

## Features

Whistlepost includes default support for a selection of features popular with Web
developers today, such that the overhead of configuring the development environment
is significantly reduced.

### Enterprise-grade Content Management

Whistlepost is built on the same engine used to power [Adobe Experience Manager], meaning the all the benefits of
a rock-solid Content Management System are realised.

### Static Asset Management

One of the challenges of the modern Web developer is managing all the dependencies and potential conflicts in
static assets. The Whistlepost site template includes [Webpack] support by default making it simple to define
such dependencies and reduce the chance of conflicts. 

### Reusable Code Library

Whistlepost provides an extensive list of code snippets that may be incorporated in a site with very little
effort. These extensions use well-known patterns and third-party libraries to add support for a wide range
of common website features. 

### Containerisation

A modern approach to testing, and increasingly deployment of websites includes containerisation frameworks
such as [Docker]. Whistlepost provides Docker containers for both the platform and websites built using the
site template, offering the flexibility to decide how to deploy your site.


## Getting Started

Whistlepost provides a [Lazybones] project template that is probably the simplest way to create a new Whistlepost site.
The following steps outline how to use it:

1. Install [Lazybones] via [SDKMAN]:

	```$ curl -s "https://get.sdkman.io" | bash && sdk install lazybones```

1. Include the Whistlepost repository in configuration:

	```$ lazybones set bintrayRepositories = [micronode/whistlepost, pledbrook/lazybones-templates]```

1. Create a new site skeleton using the Whistlepost template:

	```$ lazybones create whistlepost-site <site directory>```
	  

# Build

Whistlepost uses gradle to build and bundles. The following tasks are the most commonly used:

    build # rebuild and package bundle jars
    
    deployBundle # upload bundle jars to the configured OSGi (e.g. Apache Sling) environment
    
    startBundle # start installed bundles in the configured OSGi environment

## Project properties

The following project properties may also be overridden:

    sling_host # the host environment name
    
    sling_port # the host environment port
    
    sling_username # the host environment user
    
    sling_password # the host environment password

A common approach is to use the `GRADLE_OPTS` environment variable to override project build settings. The following
values are useful when building Whistlepost:

* -Dorg.gradle.parallel (execute build in parallel)
* 

## Docker

Through containerisation of the deployment stack development and testing productivity is greatly improved. The following
commands are most commonly used when using Docker:

    docker-compose up -d # start Apache Sling and deploy bundles
    
    docker-compose build && docker-compose run --rm whistlepost # re-deploy any changes into the running Apache Sling environment
