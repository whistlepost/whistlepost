package org.whistlepost.model.form;

import org.apache.sling.models.caconfig.annotations.ContextAwareConfiguration;
import org.whistlepost.caconfig.form.InputConfig;

import javax.inject.Inject;

public interface TextControl extends FormField {

    /**
     * The text value of the control.
     * @return a string
     */
    @Inject
    String getTextValue();

    /**
     * Indicates a read-only form control.
     * @return true if read-only otherwise false
     */
    @Inject
    boolean isReadOnly();

    @ContextAwareConfiguration
    InputConfig getValidation();
}
