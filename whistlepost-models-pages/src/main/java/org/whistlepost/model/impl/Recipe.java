package org.whistlepost.model.impl;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;

@Model(adaptables = {Resource.class, SlingHttpServletRequest.class})
public class Recipe extends AbstractModel {

    @Inject
    private String[] ingredients;

    @Inject
    private String[] method;

    public Recipe getSidebar() {
        String sidebar = resource.getValueMap().get("sidebar", String.class);
        if (sidebar != null) {
            return resourceResolver.getResource(resource.getParent(), sidebar).adaptTo(Recipe.class);
        } else {
            return null;
        }
    }
}
