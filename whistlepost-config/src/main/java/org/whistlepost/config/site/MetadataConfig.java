package org.whistlepost.config.site;

import org.apache.sling.caconfig.annotation.Configuration;

@Configuration(name = "metadata")
public @interface MetadataConfig {

    String title();

    String keywords();

    String description();
}
