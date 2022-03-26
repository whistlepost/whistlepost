package org.whistlepost.model.impl;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;

import javax.inject.Inject;

@Model(adaptables = {Resource.class, SlingHttpServletRequest.class})
public class Section extends SectionList {

    @Inject @Optional
    private String[] pars;

    @Inject @Optional
    private String media;

    public String[] getPars() {
        return pars;
    }

    public String getMedia() {
        return media;
    }
}
