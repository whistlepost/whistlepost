package org.whistlepost.model.schema;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ResourcePath;

import java.util.List;

@Model(adaptables = {Resource.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Recipe extends CreativeWork {

    @ResourcePath
    private Recipe sidebar;

    @ResourcePath
    private List<Recipe> related;

//    @Override @PostConstruct
//    protected void init() {
//        super.init();
//        if (request != null) {
//            sidebar = resolver.resolve(request, sidebarRef).adaptTo(Recipe.class);
//            related = relatedRef.stream().map(ref -> resolver.resolve(request, ref)
//                    .adaptTo(Recipe.class)).collect(Collectors.toList());
//        } else {
//            sidebar = resolver.resolve(sidebarRef).adaptTo(Recipe.class);
//            related = relatedRef.stream().map(ref -> resolver.resolve(ref)
//                    .adaptTo(Recipe.class)).collect(Collectors.toList());
//        }
//    }

    public Recipe getSidebar() {
        return sidebar;
    }

    public List<Recipe> getRelated() {
        return related;
    }
}
