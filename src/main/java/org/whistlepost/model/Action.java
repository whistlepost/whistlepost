package org.whistlepost.model;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.caconfig.annotations.ContextAwareConfiguration;
import org.whistlepost.caconfig.form.SubmitConfig;
import org.whistlepost.model.form.FieldSet;

import java.util.List;

/**
 * Implementors are resource models that support actions via a form.
 */
@Model(adaptables = {Resource.class})
public interface Action {

    @ContextAwareConfiguration(name = "fieldSets")
    List<FieldSet> getFieldSets();

    @ContextAwareConfiguration
    SubmitConfig getSubmit();

    @Self @Via("parent")
    Resource getTarget();
}
