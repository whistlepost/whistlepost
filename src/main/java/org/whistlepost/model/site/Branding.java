package org.whistlepost.model.site;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
import javax.inject.Named;

@Model(adaptables = {Resource.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public interface Branding {

    @Inject
    String getImage();

    @Inject
    String getLogo();

    @Inject @Named("alttext")
    String getAltText();

    @Inject @Named("favicon")
    String getFavIcon();
}
