package org.whistlepost.model;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;

@Model(adaptables = {Resource.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public interface Legal {

    @Inject
    String getCopyright();

    @Inject
    String getLicense();

    @Inject
    String getPrivacyPolicy();

    @Inject
    String getTermsOfService();
}
