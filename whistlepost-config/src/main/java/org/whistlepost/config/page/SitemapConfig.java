package org.whistlepost.config.page;

import org.apache.sling.caconfig.annotation.Configuration;

/**
 * Configuration for site structure definition useful for search engines and other automated site crawlers.
 */
@Configuration(name = "sitemap")
public @interface SitemapConfig {

    String urlBase();

    String changeFrequency();
}
