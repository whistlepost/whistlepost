package org.whistlepost.model;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.caconfig.annotations.ContextAwareConfiguration;
import org.whistlepost.caconfig.site.ThemeConfig;

@Model(adaptables = {Resource.class})
public interface Themeable {

    @ContextAwareConfiguration
    ThemeConfig getTheme();
}
