package org.whistlepost.model.site;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ResourcePath;
import org.whistlepost.model.Page;

import javax.inject.Inject;
import javax.inject.Named;

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
    @ResourcePath
    License getLicense();

    /**
     * The privacy policy for a website.
     * @return a resource path to the website privacy policy
     */
    @ResourcePath @Named("privacy")
    Page getPrivacyPolicy();

    /**
     * The terms of service for a website.
     * @return a resource path to the website terms of service.
     */
    @ResourcePath @Named("tos")
    Page getTermsOfService();
}
