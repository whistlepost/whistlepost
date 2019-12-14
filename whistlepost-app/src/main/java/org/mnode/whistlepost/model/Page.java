package org.mnode.whistlepost.model;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

import static org.apache.sling.models.annotations.injectorspecific.InjectionStrategy.OPTIONAL;
import static org.apache.sling.query.SlingQuery.$;

@Model(adaptables = {Resource.class, SlingHttpServletRequest.class})
public class Page {

    @SlingObject
    protected Resource resource;

    @SlingObject(injectionStrategy = OPTIONAL)
    protected SlingHttpServletRequest request;

    public String getName() {
        return resource.getName();
    }

    public String getPath() {
        return resource.getPath();
    }

    public String getTitle() {
        return resource.getValueMap().get("title", String.class);
    }

    public String getDescription() {
        return resource.getValueMap().get("description", String.class);
    }

    public String getAuthor() {
        return resource.getValueMap().get("author", String.class);
    }

    public String getContent() {
        return resource.getValueMap().get("content", String.class);
    }

    public Iterable<Page> getParents() {
        return $(resource).parents("wp/page").map(Page.class);
    }

    public Iterable<Page> getChildren() {
        return $(resource).children().map(Page.class);
    }

    public Iterable<Page> getSiblings() {
        return $(resource).siblings().not($(resource)).map(Page.class);
    }
}
