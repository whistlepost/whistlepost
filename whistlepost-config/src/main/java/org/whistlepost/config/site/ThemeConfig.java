package org.whistlepost.config.site;

import org.apache.sling.caconfig.annotation.Configuration;

/**
 * Theme configuration for managing a consistent theme across all pages.
 */
@Configuration(name = "theme")
public @interface ThemeConfig {

    String name() default "wp-pure";
}
