package org.whistlepost.config.site;

import org.apache.sling.caconfig.annotation.Configuration;

/**
 * Page actions are used to add and maintain site content. This configuration is used to
 * specify actions for different page and folder types.
 */
@Configuration(name = "actions", collection = true)
public @interface PageActionConfig {

    String name();

    String label();

    String path();

    boolean isDefault() default false;
}
