package org.whistlepost.config.site;

import org.apache.sling.caconfig.annotation.Configuration;

/**
 * Site layout configuration for managed a consistent layout across all site pages.
 */
@Configuration(name = "layout")
public @interface LayoutConfig {

    String header();

    String footer();

    String sidebar();

    String navigation();
}
