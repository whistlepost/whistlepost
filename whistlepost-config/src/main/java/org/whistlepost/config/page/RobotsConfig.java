package org.whistlepost.config.page;

import org.apache.sling.caconfig.annotation.Configuration;

/**
 * Configuration for site crawling preferences to provide hints to search engines.
 */
@Configuration(name = "robots")
public @interface RobotsConfig {

    String provider();
}
