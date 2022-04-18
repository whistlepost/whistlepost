package org.whistlepost.model;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;

@Model(adaptables = {Resource.class})
public interface Feature extends Page {

    @Inject
    String getFeature();

    @Inject
    String getArticles();
}
