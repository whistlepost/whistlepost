package org.whistlepost.model;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

import javax.servlet.http.HttpServletRequest;

/**
 * Models a Document Reference select type used in Forestry.io
 */
@Model(adaptables = {Resource.class})
public class DocumentRef {

    @Self
    private String filename;

    @SlingObject
    private ResourceResolver resourceResolver;

    public String getFilename() {
        return filename;
    }

    public Resource getResource(HttpServletRequest request) {
//        String path = "/" + getFilename().split("\\.json")[0];
        return resourceResolver.resolve(request, getFilename());
    }
}
