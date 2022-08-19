package org.whistlepost.model.site;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ResourcePath;
import org.whistlepost.model.Page;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Model(adaptables = {Resource.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public interface Navigation {

    @ResourcePath @Named("header")
    List<Page> getHeaderNav();

    @Inject @Named("columns")
    List<Page> getColumnNav();

    @Inject @Named("footer")
    List<Page> getFooterNav();

    @Inject @Named("sitemap")
    List<Page> getSitemapNav();
}
