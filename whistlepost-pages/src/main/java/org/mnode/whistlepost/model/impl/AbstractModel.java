package org.mnode.whistlepost.model.impl;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

import static org.apache.sling.models.annotations.injectorspecific.InjectionStrategy.OPTIONAL;

public abstract class AbstractModel {

    @SlingObject
    protected Resource resource;

    @SlingObject
    protected ResourceResolver resourceResolver;

    @SlingObject(injectionStrategy = OPTIONAL)
    protected SlingHttpServletRequest request;

    public String getName() {
        return resource.getName();
    }

    public String getPath() {
        return resource.getPath();
    }

}
