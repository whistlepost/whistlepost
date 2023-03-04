package org.whistlepost.model.form;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.injectorspecific.ResourcePath;

import javax.inject.Inject;
import java.util.List;

@Model(adaptables = {Resource.class})
public interface FieldSet {

    @ResourcePath
    List<Resource> getFields();

    @Inject @Optional
    String getLegend();
}
