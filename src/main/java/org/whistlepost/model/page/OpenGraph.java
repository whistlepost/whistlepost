package org.whistlepost.model.page;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;

/**
 * Support for the <a href="https://ogp.me/">OpenGraph</a> protocol.
 * @deprecated use {@link org.whistlepost.caconfig.site.OpenGraphConfig}
 */
@Deprecated
@Model(adaptables = {Resource.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public interface OpenGraph {

    @Inject
    String getTitle();

    @Inject
    String getType();

    @Inject
    String getURL();

    @Inject
    String getImage();

    @Inject
    String getSiteName();

    @Inject
    String getDescription();

    @Inject
    String getDeterminer();

    @Inject
    String getLocale();

    @Inject
    String getAudio();

    @Inject
    String getVideo();
}
