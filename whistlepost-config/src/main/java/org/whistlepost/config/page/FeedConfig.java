package org.whistlepost.config.page;

import org.apache.sling.caconfig.annotation.Configuration;

/**
 * Configuration for a site feed supporting popular syndication formats.
 */
@Configuration(name = "feed")
public @interface FeedConfig {

    String format();
}
