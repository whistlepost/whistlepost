package org.whistlepost.config.form;

import org.apache.sling.caconfig.annotation.Configuration;

/**
 * Defines a form submit configuration.
 */
@Configuration(name = "submit")
public @interface SubmitConfig {

    /**
     * The form method (i.e. GET or POST).
     * @return
     */
    String method() default "POST";

//    String action();

    String target();

    String encoding();
}
