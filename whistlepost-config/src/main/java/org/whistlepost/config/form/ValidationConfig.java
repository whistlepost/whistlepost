package org.whistlepost.config.form;

import org.apache.sling.caconfig.annotation.Configuration;

@Configuration(name = "validation")
public @interface ValidationConfig {

    String type();

    String pattern();

    String placeholder();

    String title();
}
