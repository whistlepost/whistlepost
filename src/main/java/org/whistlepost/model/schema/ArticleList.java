package org.whistlepost.model.schema;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.whistlepost.model.list.AbstractItemList;

@Model(adaptables = {Resource.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ArticleList extends AbstractItemList<Article> {

    @Override
    public Class<Article> getMappedType() {
        return Article.class;
    }
}
