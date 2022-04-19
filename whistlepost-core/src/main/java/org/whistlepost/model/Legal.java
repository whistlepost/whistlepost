package org.whistlepost.model;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;

/**
 * Defines legal terms and conditions for a website.
 */
@Model(adaptables = {Resource.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public interface Legal {

    /**
     * The copyright text typically displayed in the website footer section.
     * @return a string of copyright text
     */
    @Inject
    String getCopyright();

    /**
     * The default license granted for website content.
     * @return a resource path to a license configuration
     */
    @Inject
    String getLicense();

    /**
     * The privacy policy for a website.
     * @return a resource path to the website privacy policy
     */
    @Inject
    String getPrivacyPolicy();

    /**
     * The terms of service for a website.
     * @return a resource path to the website terms of service.
     */
    @Inject
    String getTermsOfService();
}
