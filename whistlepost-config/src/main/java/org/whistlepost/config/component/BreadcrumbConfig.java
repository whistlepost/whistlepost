package org.whistlepost.config.component;

import org.apache.sling.caconfig.annotation.Configuration;

/**
 * Configuration for breadcrumb navigation.
 */
@Configuration(name = "breadcrumb")
public @interface BreadcrumbConfig {

    String[] ignoredResourceNames() default {"content", "list"};
}
