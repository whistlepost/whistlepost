package org.whistlepost.servlet.keycloak;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "Keycloak OIDC Filter", description = "Servlet Filter configuration")
@interface KeycloakFilterConfiguration {

    @AttributeDefinition(name = "osgi.http.whiteboard.filter.pattern", description = "Servlet Filter pattern")
    String osgi_http_whiteboard_filter_pattern() default "/auth/*";

    @AttributeDefinition(name = "osgi.http.whiteboard.context.select", description = "Servlet Context filter")
    String osgi_http_whiteboard_context_select() default "(osgi.http.whiteboard.context.name=*)";

    @AttributeDefinition(name = "filter.init.keycloak.config.path", description = "Keycloak configuration path")
    String keycloak_config_path() default "/keycloak.json";

    @AttributeDefinition(name = "filter.init.keycloak.config.skipPattern", description = "Keycloak bypass pattern")
    String keycloak_config_skipPattern() default "";
}
