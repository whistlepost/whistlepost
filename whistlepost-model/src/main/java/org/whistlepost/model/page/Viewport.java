package org.whistlepost.model.page;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * @deprecated use {@link org.whistlepost.caconfig.site.ViewportConfig}
 */
@Deprecated
@Model(adaptables = {Resource.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Viewport {

    @Inject
    private String width;

    @Inject @Named("initial-scale")
    private String initialScale;

    String getWidth() {
        return width;
    }

    String getInitialScale() {
        return initialScale;
    }
}
