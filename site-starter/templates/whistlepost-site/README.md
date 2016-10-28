# ${projectName}

Description: This is a placeholder description for a [Whistlepost](http://whistlepost.org) site project.

## Subprojects

This project consists of three (3) sub-projects that comprise the entire site.

### ${projectId}-app

The site application code resides in this project. HTML, CSS and JavaScript customisations may be
implemented here.

### ${projectId}-config

Root-level site configuration can be found in this project. This will include things like mapping (/etc/map)

### ${projectId}-content

This project contains all the site content that is rendered using the application code.


## Build

This project uses gradle to build and bundles. The following tasks are the most commonly used:

    build # rebuild and package bundle jars
    
    installBundle # upload bundle jars to the configured OSGi (e.g. Apache Sling) environment
    
    startBundle # start installed bundles in the configured OSGi environment

The following project properties may also be overridden:

    sling_url # the host environment url

    sling_username # the host environment user
    
    sling_password # the host environment password
    
## Docker

Through containerisation of the deployment stack development and testing productivity is greatly improved. The following
commands are most commonly used when using Docker:

    docker-compose up -d # start Apache Sling and install bundles
    
    docker-compose build && docker-compose run --rm ${projectId} # reinstall any changes into the running Apache Sling environment
