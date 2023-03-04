package org.whistlepost.model.form;

public interface Checkbox extends FormField {

    /**
     * The checked status of the control.
     * @return true if checked otherwise false
     */
    boolean isChecked();

    /**
     * The default status of the control.
     * @return true if checked otherwise false
     */
    boolean defaultValue();
}
