package org.whistlepost.model;

import org.apache.sling.api.adapter.Adaptable;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ResourcePath;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.whistlepost.model.site.Branding;
import org.whistlepost.model.site.Layout;
import org.whistlepost.model.site.Legal;

import javax.inject.Inject;

/**
 * Provides the default values for a site. This model should be used on most pages for rendering a consistent
 * layout and appearance across an entire site.
 * @deprecated use ca-config for defaults.
 */
@Deprecated
@Model(adaptables = {Resource.class})
public interface SiteDefaults extends Comparable<SiteDefaults>, Adaptable {

    /**
     * The site title. Use to populate tags such as <pre><TITLE></pre> on each page.
     * @return
     */
    @Inject
    String getTitle();

//    @ResourcePath
//    Resource getHomepage();

    /**
     * The site layout. Includes navigation, header and footer resources
     * @return
     */
    @ResourcePath
    Layout getLayout();

    /**
     * Site branding. Used for consistent appearance.
     * @return
     */
    @ResourcePath
    Branding getBranding();

    /**
     * Site legal information.
     * @return
     */
    @ResourcePath
    Legal getLegal();

    @Self
    Resource getResource();

    @Override
    default int compareTo(@NotNull SiteDefaults o) {
        return getTitle().compareTo(o.getTitle());
    }

    @Override
    default <AdapterType> @Nullable AdapterType adaptTo(@NotNull Class<AdapterType> type) {
        return getResource().adaptTo(type);
    }
}
