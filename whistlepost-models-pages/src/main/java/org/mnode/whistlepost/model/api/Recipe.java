package org.mnode.whistlepost.model.api;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.injectorspecific.ResourcePath;

import javax.inject.Inject;

@Model(adaptables = {Resource.class})
public interface Recipe extends Article {

    @Inject
    String[] getIngredients();

    @Inject
    String[] getMethod();

    @ResourcePath(name = "sidebar") @Optional
    Recipe getSidebar();
}
