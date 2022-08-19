package org.whistlepost.servlet.keycloak;

import org.keycloak.adapters.servlet.KeycloakOIDCFilter;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.metatype.annotations.Designate;

import javax.servlet.Filter;

@Component(service = Filter.class, property = {
        "service.description=Whistlepost Keycloak Servlet Filter",
        "service.vendor=Micronode"
})
@Designate(ocd = KeycloakFilterConfiguration.class, factory = true)
public class KeycloakFilter extends KeycloakOIDCFilter {

    private KeycloakFilterConfiguration configuration;

    private BundleContext bundleContext;

    @Activate
    protected void activate(KeycloakFilterConfiguration configuration, BundleContext bundleContext) {
        this.configuration = configuration;
        this.bundleContext = bundleContext;
        configure(configuration);
    }

    @Modified
    private void modified(KeycloakFilterConfiguration configuration) {
        this.configuration = configuration;
        configure(configuration);
    }

    @Deactivate
    private void deactivate() {
        bundleContext = null;
    }

    private void configure(KeycloakFilterConfiguration configuration) {
    }
}
