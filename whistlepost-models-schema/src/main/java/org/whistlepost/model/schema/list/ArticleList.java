package org.whistlepost.model.schema.list;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.whistlepost.model.schema.Article;
import org.whistlepost.model.schema.ItemList;

@Model(adaptables = {Resource.class, SlingHttpServletRequest.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ArticleList extends ItemList<Article> {

    @Override
    public Class<Article> getResourceType() {
        return Article.class;
    }
}
