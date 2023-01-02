package org.whistlepost.config.site;

import org.apache.sling.caconfig.annotation.Configuration;

/**
 * When publishing websites on the Internet it is recommended to include some
 * legal protections such as copyright, terms of service, usage license, etc.
 * This site configuration supports both inline text and links to detailed pages.
 */
@Configuration(name = "site.legal")
public @interface LegalConfig {

    String copyright();

    String license();

    String privacy();

    String tos();
}
