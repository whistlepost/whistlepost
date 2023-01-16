package org.whistlepost.config.form;

import org.apache.sling.caconfig.annotation.Configuration;

@Configuration(name = "input")
public @interface InputConfig {

    String type();

    String pattern();

    String placeholder();

    String title();

    String min();

    String max();
}
