package org.whistlepost.model;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Required;
import org.apache.sling.models.annotations.injectorspecific.ResourcePath;
import org.apache.sling.models.caconfig.annotations.ContextAwareConfiguration;
import org.whistlepost.caconfig.form.SubmitConfig;
import org.whistlepost.model.form.FieldSet;

import javax.inject.Inject;
import java.util.List;

/**
 * @deprecated use {@link Action}
 */
@Deprecated
@Model(adaptables = {Resource.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public interface Form {

    @ContextAwareConfiguration
    SubmitConfig getSubmit();

    @ResourcePath @Required
    List<FieldSet> getFieldSets();

    @Inject @Default(values = "submit")
    String getSubmitValueKey();
}
