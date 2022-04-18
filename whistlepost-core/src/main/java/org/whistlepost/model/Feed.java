package org.whistlepost.model;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Required;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Support for <a href="https://validator.w3.org/feed/docs/atom.html">Atom</a> feed generation.
 */
@Model(adaptables = {Resource.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Feed {

    @Inject @Required
    private String id;

    @Inject @Required
    private String title;

    @Inject @Required
    private String updated;

    @Inject
    private String link;

    @Inject
    private String author;

    @SlingObject
    protected Resource resource;

    @SlingObject
    protected ResourceResolver resolver;

    private List<FeedEntry> entries = new ArrayList<>();

    @PostConstruct
    protected void init() {
        PageableList entryList = resolver.resolve(resource.getValueMap().get("feedEntries", String.class))
                .adaptTo(PageableList.class);
        // todo: iterate pages and add feed entries..
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getUpdated() {
        return updated;
    }

    public String getLink() {
        return link;
    }

    public String getAuthor() {
        return author;
    }

    public List<FeedEntry> getEntries() {
        return entries;
    }
}
