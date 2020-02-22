package org.mnode.whistlepost.model;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = {Resource.class, SlingHttpServletRequest.class})
public class Advertisement extends Page {

    public String getMedia() {
        return resource.getValueMap().get("media", String.class);
    }
}
