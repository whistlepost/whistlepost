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
public class WebPage extends CreativeWork {

    @Inject
    @Named("related") @Optional
    private List<String> breadcrumbRef;

    private List<WebPage> breadcrumb;

    @Inject
    @Named("related") @Optional
    private List<String> relatedRef;

    private List<WebPage> related;

    @Override @PostConstruct
    protected void init() {
        super.init();
        if (request != null) {
            breadcrumb = breadcrumbRef.stream().map(ref -> resolver.resolve(request, ref)
                    .adaptTo(WebPage.class)).collect(Collectors.toList());
            related = relatedRef.stream().map(ref -> resolver.resolve(request, ref)
                    .adaptTo(WebPage.class)).collect(Collectors.toList());
        } else {
            breadcrumb = breadcrumbRef.stream().map(ref -> resolver.resolve(ref)
                    .adaptTo(WebPage.class)).collect(Collectors.toList());
            related = relatedRef.stream().map(ref -> resolver.resolve(ref)
                    .adaptTo(WebPage.class)).collect(Collectors.toList());
        }
    }

    public List<WebPage> getBreadcrumb() {
        return breadcrumb;
    }

    public List<WebPage> getRelated() {
        return related;
    }
}
