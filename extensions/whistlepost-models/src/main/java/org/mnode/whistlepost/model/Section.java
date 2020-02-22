package org.mnode.whistlepost.model;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = {Resource.class, SlingHttpServletRequest.class})
public class Section extends SectionList {

    public String[] getPars() {
        return resource.getValueMap().get("pars", new String[] {});
    }

    public String getMedia() {
        return resource.getValueMap().get("media", String.class);
    }
}
