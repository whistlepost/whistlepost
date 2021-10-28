package org.whistlepost.model.schema;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Model(adaptables = {Resource.class})
public class Recipe extends CreativeWork {

    @Inject
    @Named("sidebar") @Optional
    private String sidebarRef;

    private Recipe sidebar;

    @Inject @Named("related") @Optional
    private List<String> relatedRef;

    private List<Recipe> related;

    @Override @PostConstruct
    protected void init() {
        super.init();
        if (request != null) {
            sidebar = resolver.resolve(request, sidebarRef).adaptTo(Recipe.class);
            related = relatedRef.stream().map(ref -> resolver.resolve(request, ref)
                    .adaptTo(Recipe.class)).collect(Collectors.toList());
        } else {
            sidebar = resolver.resolve(sidebarRef).adaptTo(Recipe.class);
            related = relatedRef.stream().map(ref -> resolver.resolve(ref)
                    .adaptTo(Recipe.class)).collect(Collectors.toList());
        }
    }

    public Recipe getSidebar() {
        return sidebar;
    }

    public List<Recipe> getRelated() {
        return related;
    }
}
