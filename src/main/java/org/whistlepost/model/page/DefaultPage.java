package org.whistlepost.model.page;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

import static org.apache.sling.models.annotations.injectorspecific.InjectionStrategy.OPTIONAL;
import static org.apache.sling.query.SlingQuery.$;

@Model(adaptables = {Resource.class})
public class DefaultPage implements Page {

    @SlingObject
    private Resource resource;

    @SlingObject
    private ResourceResolver resourceResolver;

    @SlingObject(injectionStrategy = OPTIONAL)
    protected SlingHttpServletRequest request;

//    @Inject @Named("resource")
//    private Metadata metadata;

    @Override
    public Metadata getMetadata() {
        return resource.adaptTo(Metadata.class);
    }

    @Override
    public Resource getResource() {
        return resource;
    }

    @Override
    public TableOfContents getToc() {
        return resource.adaptTo(TableOfContents.class);
    }

    @Override
    public Iterable<Page> getParents() {
        return $(resource).parents("wp/page").map(DefaultPage.class);
    }

    @Override
    public Iterable<Page> getChildren() {
        return $(resource).children().map(DefaultPage.class);
    }

    @Override
    public Iterable<Page> getSiblings() {
        return $(resource).siblings().not($(resource)).map(DefaultPage.class);
    }

    public Page getPage(String resourceRef) {
//        return Resources.getResource(resourceRef, page.getResource()).adaptTo(Page.class);
        if (resourceRef.startsWith("/")) {
            return $(resource).closest(resourceRef).first().map(Page.class).iterator().next();
        } else {
            return $(resource).siblings(resourceRef).first().map(Page.class).iterator().next();
        }
    }

//    public static Iterable<Page> getPages(Page page) {
//        return page.getPageRefs().stream().map(r -> getPage(r, page)).collect(Collectors.toList());
//    }
}
