package org.whistlepost.servlet.gateway;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.osgi.service.metatype.annotations.Option;

@ObjectClassDefinition(name = "Whistlepost HTTP Gateway Servlet", description = "Configurable HTTP Gateway Servlet")
@interface HttpGatewayServletConfiguration {

    @AttributeDefinition(name = "target.fqdn", description = "FQDN for gateway target")
    String target_fqdn();

    @AttributeDefinition(name = "target.port", description = "Port for gateway target")
    int target_port();

    @AttributeDefinition(name = "target.uri", description = "URI for gateway target")
    String target_uri();

    @AttributeDefinition(name = "authentication.type", options = {
            @Option(label = "None", value = "none"),
            @Option(label = "Basic", value = "basic"),
            @Option(label = "OAuth", value = "oauth"),
    })
    String authentication_type() default "none";

    @AttributeDefinition(name = "basic.user", description = "Username for Basic authentication")
    String basic_user() default "";

    @AttributeDefinition(name = "basic.password", description = "Password for Basic authentication",
            type = AttributeType.PASSWORD)
    String basic_password() default "";

    @AttributeDefinition(name = "oauth.token", description = "Bearer Token for OAuth authentication",
            type = AttributeType.PASSWORD)
    String oauth_token() default "";

    @AttributeDefinition(name = "sling.servlet.resourceTypes", description = "Sling resource types to handle")
    String[] sling_servlet_resourceTypes() default {"wp-http/api"};

    @AttributeDefinition(name = "sling.servlet.methods", description = "HTTP methods to handle")
    String[] sling_servlet_methods() default {"GET"};

    @AttributeDefinition(name = "sling.servlet.extensions", description = "Sling request extensions to handle")
    String[] sling_servlet_extensions() default {"json"};

    @AttributeDefinition(name = "sling.servlet.selectors", description = "Sling request selectors to handle")
    String[] sling_servlet_selectors() default {};
}
