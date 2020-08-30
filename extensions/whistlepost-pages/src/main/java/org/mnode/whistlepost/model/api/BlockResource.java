package org.mnode.whistlepost.model.api;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;

@Model(adaptables = {Resource.class})
public interface BlockResource {

    @Self
    BlockEditor getEditor();

    String getRendered();
}
