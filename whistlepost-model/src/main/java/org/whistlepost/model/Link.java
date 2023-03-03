package org.whistlepost.model;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ResourcePath;

import javax.inject.Inject;

/**
 * Model an internal hyperlink.
 */
@Model(adaptables = {Resource.class})
public interface Link {

    @ResourcePath
    Resource getTarget();

    @Inject
    String getValueKey();
}
