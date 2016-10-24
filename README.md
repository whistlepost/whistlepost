Whistlepost
===========

A content management system (CMS) designed to be as simple as Jekyll, as flexible as Adobe AEM, and as robust as Wordpress.

# Architecture

Whistlepost is built on the Apache Sling platform, which applies the mantra that everything is a resource.
The benefit of this approach is that is separates the structure of the content from the structure of the site itself, 
which allows both (UX/UI) designers and (content) developers to focus on what is most important to them.

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

* --parallel (execute build in parallel)
* 

## Docker

Through containerisation of the deployment stack development and testing productivity is greatly improved. The following
commands are most commonly used when using Docker:

    docker-compose up -d # start Apache Sling and deploy bundles
    
    docker-compose build && docker-compose run --rm whistlepost # re-deploy any changes into the running Apache Sling environment
