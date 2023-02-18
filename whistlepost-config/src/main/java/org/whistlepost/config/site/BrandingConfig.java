package org.whistlepost.config.site;

import org.apache.sling.caconfig.annotation.Configuration;

/**
 * Site branding configuration used to provide a consistent look and feel across site pages.
 */
@Configuration(name = "branding")
public @interface BrandingConfig {

    String image();

    String logo();

    String alttext();

    String favicon() default "/assets/wp-pure/images/favicon.ico";
}
