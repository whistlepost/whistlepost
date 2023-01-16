package org.whistlepost.config.site;

import org.apache.sling.caconfig.annotation.Configuration;

/**
 * User feedback and comments are supported via integration of third-party
 * tools. You can configure your preferred provider with this site configuration.
 */
@Configuration(name = "site.comments")
public @interface CommentsConfig {

    String provider();

    String shortcode();
}
