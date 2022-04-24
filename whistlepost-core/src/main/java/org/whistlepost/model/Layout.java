package org.whistlepost.model;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ResourcePath;

import javax.inject.Inject;

/**
 * Defines the resources that make up a site layout. Adopting the typical "Holy Grail" layout of websites means
 * including header and footer sections, an optional sidebar and navigation elements.
 */
@Model(adaptables = {Resource.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public interface Layout extends Page {

    /**
     * Resource path defining the site header configuration.
     * @return a relative path used for resource resolution
     */
    @Inject
    String getHeader();

    /**
     * Resource path defining the site footer configuration.
     * @return a relative path used for resource resolution
     */
    @Inject
    String getFooter();

    /**
     * Resource path defining the site sidebar configuration.
     * @return a relative path used for resource resolution
     */
    @Inject
    String getSidebar();

    /**
     * Resource path defining the site navigation configuration.
     * @return a relative path used for resource resolution
     */
    @ResourcePath
    Navigation getNavigation();
}
