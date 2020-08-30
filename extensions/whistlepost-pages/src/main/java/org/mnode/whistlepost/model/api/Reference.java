package org.mnode.whistlepost.model.api;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.injectorspecific.ResourcePath;

import javax.inject.Inject;
import java.net.URL;

@Model(adaptables = {Resource.class})
public interface Reference {

    @Inject @Optional
    String getTitle();

    @ResourcePath(name = "page") @Optional
    Page getPage();

    @Inject @Optional
    URL getLink();
}
