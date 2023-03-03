package org.whistlepost.model.form;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.caconfig.annotations.ContextAwareConfiguration;
import org.whistlepost.caconfig.form.OptionConfig;

import javax.inject.Inject;
import java.util.List;

@Model(adaptables = {Resource.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public interface Select extends FormField {

    /**
     * The default selected option of the control.
     * @return a string
     */
    @Inject
    String getDefaultValue();

    @ContextAwareConfiguration
    List<OptionConfig> getOptions();
}
