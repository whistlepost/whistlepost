package org.whistlepost.config.page;

import org.apache.sling.caconfig.annotation.Configuration;

/**
 * Configuration for site crawling preferences to provide hints to search engines.
 */
@Configuration(name = "robots", collection = true)
public @interface RobotsConfig {

    String userAgent() default "*";

    String allow() default "/";
}
