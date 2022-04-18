package org.whistlepost.model;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Required;

import javax.inject.Inject;
import javax.inject.Named;

@Model(adaptables = {Resource.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public interface Branding {

    @Inject @Required
    String getImage();

    @Inject
    String getLogo();

    @Inject @Named("alt-text")
    String getAltText();

    @Inject @Named("favicon")
    String getFavIcon();
}
