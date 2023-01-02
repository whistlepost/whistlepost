package org.whistlepost.config.form;

import org.apache.sling.caconfig.annotation.Configuration;

@Configuration(name = "options", collection = true)
public @interface OptionConfig {

    String value();

    boolean booleanValue();

    String text();
}
