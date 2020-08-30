package org.mnode.whistlepost.page;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = {Resource.class, SlingHttpServletRequest.class})
public class PublishedArticles extends AbstractFilteredList<DefaultArticle> {

    public PublishedArticles() {
        super("nt:unstructured[published=true]", DefaultArticle.class);
    }
}
