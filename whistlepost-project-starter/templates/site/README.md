# ${projectName}

A site for sharing common emoji phrases

## Build

This project uses gradle to build and bundles. The following tasks are the most commonly used:

    build # rebuild and package bundle jars
    
    deployBundle # upload bundle jars to the configured OSGi (e.g. Apache Sling) environment
    
    startBundle # start installed bundles in the configured OSGi environment

The following project properties may also be overridden:

    sling_url # the host environment url

    sling_username # the host environment user
    
    sling_password # the host environment password
    
## Docker

Through containerisation of the deployment stack development and testing productivity is greatly improved. The following
commands are most commonly used when using Docker:

    docker-compose up -d # start Apache Sling and deploy bundles
    
    docker-compose build && docker-compose run --rm ${projectId} # re-deploy any changes into the running Apache Sling environment
