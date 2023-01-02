package org.whistlepost.config.extension;

import org.apache.sling.caconfig.annotation.Configuration;

/**
 * Configuration for a service integration that provides site content from external sources.
 */
@Configuration(name = "extension.service")
public @interface ServiceConfig {

    String name();

    String description();

    String url();
}
