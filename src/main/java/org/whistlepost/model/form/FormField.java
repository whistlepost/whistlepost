package org.whistlepost.model.form;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Required;

import javax.inject.Inject;

/**
 * Base model for all form controls.
 */
@Model(adaptables = {Resource.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public interface FormField {

    @Inject @Required
    String getName();

    /**
     * The field identifier for the control.
     * @return a string
     */
    @Inject
    String getId();

    /**
     * The display name of the control.
     * @return a string
     */
    @Inject
    String getLabelKey();

    /**
     * Indicates a mandatory form control.
     * @return true if required otherwise false
     */
    @Inject
    boolean isRequired();

    /**
     * Indicates a disabled form control.
     * @return true if disabled otherwise false
     */
    @Inject
    boolean isDisabled();

    /**
     * Indicate if multiple values may be specified.
     * @return true if multiple values allowed otherwise false
     */
    @Inject
    boolean isMultipleAllowed();
}
