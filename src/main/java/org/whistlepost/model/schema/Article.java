package org.whistlepost.model.schema;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ResourcePath;

import java.util.List;

@Model(adaptables = {Resource.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Article extends CreativeWork {

    @ResourcePath
    private Article sidebar;

    @ResourcePath
    private List<Article> related;

//    @Override @PostConstruct
//    protected void init() {
//        super.init();
//        if (request != null) {
//            sidebar = resolver.resolve(request, sidebarRef).adaptTo(Article.class);
//            related = relatedRef.stream().map(ref -> resolver.resolve(request, ref)
//                    .adaptTo(Article.class)).collect(Collectors.toList());
//        } else {
//            sidebar = resolver.resolve(sidebarRef).adaptTo(Article.class);
//            related = relatedRef.stream().map(ref -> resolver.resolve(ref)
//                    .adaptTo(Article.class)).collect(Collectors.toList());
//        }
//    }

    public Article getSidebar() {
        return sidebar;
    }

    public List<Article> getRelated() {
        return related;
    }
}
