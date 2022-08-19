package org.whistlepost.servlet.cors;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "Cors Filter", description = "Servlet Filter configuration")
@interface CorsFilterConfiguration {

    @AttributeDefinition(name = "osgi.http.whiteboard.filter.pattern", description = "Servlet Filter pattern")
    String osgi_http_whiteboard_filter_pattern() default "/auth/*";

    @AttributeDefinition(name = "osgi.http.whiteboard.context.select", description = "Servlet Context filter")
    String osgi_http_whiteboard_context_select() default "(osgi.http.whiteboard.context.name=*)";

    @AttributeDefinition(name = "pattern.origin", description = "Cors origin pattern")
    String cors_pattern_origin() default "^.*\\.example\\.com$";
}
