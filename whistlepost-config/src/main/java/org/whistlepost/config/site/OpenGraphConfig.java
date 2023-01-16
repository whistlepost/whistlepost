package org.whistlepost.config.site;

import org.apache.sling.caconfig.annotation.Configuration;

/**
 * Support for the <a href="https://ogp.me/">OpenGraph</a> protocol.
 */
@Configuration(name = "opengraph")
public @interface OpenGraphConfig {

    String title();

    String type();

    String url();

    String image();

    String siteName();

    String description();

    String determiner();

    String locale();

    String audio();

    String video();
}
