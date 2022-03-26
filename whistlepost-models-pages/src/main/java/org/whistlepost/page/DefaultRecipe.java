package org.whistlepost.page;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.injectorspecific.ResourcePath;
import org.whistlepost.model.api.Recipe;

import javax.inject.Inject;

@Model(adaptables = {Resource.class})
public class DefaultRecipe extends DefaultArticle implements Recipe {

    @Inject
    private String[] ingredients;

    @Inject
    private String[] method;

    @ResourcePath(name = "sidebar") @Optional
    private Recipe sidebar;

    @Override
    public String[] getIngredients() {
        return ingredients;
    }

    @Override
    public String[] getMethod() {
        return method;
    }

    @Override
    public Recipe getSidebar() {
        return sidebar;
    }
}
