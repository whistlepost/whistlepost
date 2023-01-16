package org.whistlepost.config.site;

import org.apache.sling.caconfig.annotation.Configuration;

/**
 * Configure pagination options for lists.
 */
@Configuration(name = "paging")
public @interface ListConfig {

    int pageSize() default 6;

    String itemListOrder() default "Descending";
}
