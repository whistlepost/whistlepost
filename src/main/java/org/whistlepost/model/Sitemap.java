package org.whistlepost.model;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.caconfig.annotations.ContextAwareConfiguration;
import org.whistlepost.caconfig.page.SitemapConfig;

import javax.inject.Inject;
import java.util.List;

/**
 * Support for sitemap generation.
 * See <a href="https://www.sitemaps.org/protocol.html">sitemaps.org</a> for details.
 */
@Model(adaptables = {Resource.class})
public interface Sitemap {

    @ContextAwareConfiguration
    SitemapConfig getConfig();

    @Inject @Default(values = {})
    List<String> getUris();
}
