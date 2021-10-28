package org.whistlepost.model;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Support for sitemap generation.
 * See <a href="https://www.sitemaps.org/protocol.html">sitemaps.org</a> for details.
 */
@Model(adaptables = {Resource.class})
public interface Sitemap {

    @Inject @Named("url-base")
    String getUrlBase();

    @Inject @Named("change-frequency")
    String getChangeFrequency();

    @Inject @Default(values = {})
    List<String> getUris();
}
