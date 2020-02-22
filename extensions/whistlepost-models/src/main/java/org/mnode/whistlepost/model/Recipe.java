package org.mnode.whistlepost.model;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = {Resource.class, SlingHttpServletRequest.class})
public class Recipe extends Article {

    public String[] getIngredients() {
        return resource.getValueMap().get("ingredients", new String[] {});
    }

    public String[] getMethod() {
        return resource.getValueMap().get("method", new String[] {});
    }

    public Recipe getSidebar() {
        String sidebar = resource.getValueMap().get("sidebar", String.class);
        if (sidebar != null) {
            return resourceResolver.getResource(resource.getParent(), sidebar).adaptTo(Recipe.class);
        } else {
            return null;
        }
    }
}
