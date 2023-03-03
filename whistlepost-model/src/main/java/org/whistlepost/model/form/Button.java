package org.whistlepost.model.form;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;

@Model(adaptables = {Resource.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public interface Button extends FormField {

    @Inject
    String getValueKey();

    /**
     * Identifier of a predefined action to execute when button is clicked.
     * @return a string action identifier
     */
    @Inject
    String getActionId();
}
