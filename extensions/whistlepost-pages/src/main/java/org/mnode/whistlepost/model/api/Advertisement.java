package org.mnode.whistlepost.model.api;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;

import javax.inject.Inject;

@Model(adaptables = {Resource.class})
public interface Advertisement extends Page {

    @Inject @Optional
    String getMedia();
}
