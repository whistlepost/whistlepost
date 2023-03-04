package org.whistlepost.model;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.whistlepost.model.schema.Article;
import org.whistlepost.model.schema.ArticleList;

import javax.inject.Inject;

@Model(adaptables = {Resource.class})
public interface FeaturePage extends Page {

    @Inject
    Article getFeature();

    @Inject
    ArticleList getArticles();
}
