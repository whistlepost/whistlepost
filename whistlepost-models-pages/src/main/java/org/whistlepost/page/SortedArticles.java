package org.whistlepost.page;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Model(adaptables = {Resource.class, SlingHttpServletRequest.class})
public class SortedArticles extends AbstractFilteredList<DefaultArticle> {

    private final Comparator<DefaultArticle> comparator;

    public SortedArticles() {
        super("nt:unstructured[published=true]", DefaultArticle.class);
        comparator = Comparator.comparing(o -> o.getMetadata().getPublishedDate());
    }

    public Iterable<DefaultArticle> getSorted() {
        List<DefaultArticle> all = toList();
        Collections.sort(all, comparator.reversed());
        return all;
    }

    public Iterable<DefaultArticle> getSortedPage() {
        List<DefaultArticle> all = toList();
        Collections.sort(all, comparator.reversed());
        return all.subList((getCurrentPage() - 1) * getPageSize(),
                (getCurrentPage() - 1) * getPageSize() + Math.min(getPageSize(), all.size()));
    }
}
