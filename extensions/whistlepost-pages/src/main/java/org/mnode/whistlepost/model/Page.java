package org.mnode.whistlepost.model;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

import static org.apache.sling.models.annotations.injectorspecific.InjectionStrategy.OPTIONAL;
import static org.apache.sling.query.SlingQuery.$;

@Model(adaptables = {Resource.class, SlingHttpServletRequest.class})
public class Page {

    @SlingObject
    protected Resource resource;

    @SlingObject
    protected ResourceResolver resourceResolver;

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

    /**
     * Get a resource based on a relative or absolution path reference.
     * @param resourceRef a relative or absolute path
     * @return the resource at the specified path or null if no resource exists
     */
    protected Resource getResource(String resourceRef) {
        Resource r = null;
        if (resourceRef != null) {
            if (resourceRef.startsWith("/")) {
                r = resourceResolver.resolve(resourceRef);
            } else {
                r = resourceResolver.getResource(this.resource.getParent(), resourceRef);
            }
        }
        return r;
    }

    /**
     * Inspect value map to determine appropriate page type to adapt to.
     * @param resourceRef
     * @return
     */
    protected Page getPage(String resourceRef) {
        Resource r = getResource(resourceRef);
        if (r.getValueMap().containsKey("list")) {
            return r.adaptTo(PageList.class);
        } else {
            return r.adaptTo(Page.class);
        }
    }

    /**
     * Get a value from the resource value map.
     * @param name value name
     * @param type value type class
     * @param <T> value type
     * @return a value from the value map or null if no value exists
     */
    protected <T> T getValue(String name, Class<T> type) {
        return resource.getValueMap().get(name, type);
    }

    public String getBannerUrl() {
        return getValue("bannerUrl", String.class);
    }

    public String getBannerImage() {
        return getValue("bannerImage", String.class);
    }
}
