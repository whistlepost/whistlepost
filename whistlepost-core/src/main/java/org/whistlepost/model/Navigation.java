package org.whistlepost.model;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Model(adaptables = {Resource.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public interface Navigation {

    @Inject @Named("header")
    List<String> getHeaderNav();

    @Inject @Named("columns")
    List<String> getColumnNav();

    @Inject @Named("footer")
    List<String> getFooterNav();
}
