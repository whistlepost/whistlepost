package org.whistlepost.model.site;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Required;
import org.apache.sling.models.annotations.injectorspecific.ResourcePath;

import javax.inject.Inject;
import java.net.URL;

@Model(adaptables = {Resource.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public interface License {

    @Inject @Required
    String getName();

    @Inject
    URL getURL();

    @ResourcePath
    Resource getLogo();
}
