package org.whistlepost.config.site;

import org.apache.sling.caconfig.annotation.Configuration;

@Configuration(name = "viewport")
public @interface ViewportConfig {

    String width();

    String initialScale();
}
