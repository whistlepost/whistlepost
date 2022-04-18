package org.whistlepost.model;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;

@Model(adaptables = {Resource.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public interface Layout extends Page {

    @Inject
    String getHeader();

    @Inject
    String getFooter();

    @Inject
    String getSidebar();

    @Inject
    String getNavigation();
}
