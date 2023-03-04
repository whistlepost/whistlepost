package org.whistlepost.model;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Required;
import org.apache.sling.models.caconfig.annotations.ContextAwareConfiguration;
import org.whistlepost.model.page.Metadata;
import org.whistlepost.model.site.Branding;
import org.whistlepost.model.site.Layout;
import org.whistlepost.model.site.Legal;

/**
 * @deprecated use ca-config to configure defaults.
 */
@Deprecated
@Model(adaptables = {Resource.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public interface PageDefaults {

    @ContextAwareConfiguration(name = "layout")
    Layout getLayout();

    @ContextAwareConfiguration(name = "branding")
    Branding getBranding();

    @ContextAwareConfiguration(name = "legal")
    Legal getLegal();

    @ContextAwareConfiguration(name = "metadata") @Required
    Metadata getMetadata();
}
