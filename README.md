Whistlepost
===========

A content management system (CMS) designed to be as simple as Jekyll, as flexible as Adobe AEM, and as robust as Wordpress.

# Architecture

Whistlepost is built on the Apache Sling platform, which applies the mantra that everything is a resource. The benefit of this approach is that is separates the structure of the content from the structure of the site itself, which allows both (UX/UI) designers and (content) developers to focus on what is most important to them.

# Build

Whistlepost uses gradle to build and bundles. The following tasks are the most commonly used:

* build # rebuild and package bundle jars
* deployBundle # upload bundle jars to the configured OSGi (e.g. Apache Sling) environment
* startBundle # start installed bundles in the configured OSGi environment

## Docker

Through containerisation of the deployment stack development and testing productivity is greatly improved. The following
commands are most commonly used when using Docker:

* docker-compose up -d # start Apache Sling and deploy bundles
* docker-compose build # rebuild container images to include local changes
* docker-compose run --rm whistlepost # re-deploy any changes into the running Apache Sling environment
