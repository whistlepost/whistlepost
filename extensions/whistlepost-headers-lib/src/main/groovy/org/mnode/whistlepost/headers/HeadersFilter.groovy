package org.mnode.whistlepost.headers

import org.apache.felix.scr.annotations.*
import org.apache.sling.commons.osgi.PropertiesUtil
import org.osgi.service.component.ComponentContext

import javax.servlet.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import java.util.regex.Pattern

@Component(metatype = true, label = 'Whistlepost Headers Filter', description = 'Applies response headers where applicable')
@Service(value = Filter.class)
@Property(name = 'service.description', value = 'Whistlepost Headers Filter', propertyPrivate = true)
@Property(name = "service.vendor", value = "Micronode", propertyPrivate = true)
@Property(name = "sling.filter.scope", value = "request", propertyPrivate = true)
class HeadersFilter implements Filter {

    @Property(label = 'URI Pattern', description = 'Regular expression for matching request URI', value = /^\/some\/path\/.*$/)
    private static final String URI_PATTERN = "pattern.uri";

    @Property(unbounded = PropertyUnbounded.ARRAY, label = "Header List", cardinality = 50, description = "A list of headers to apply to the response", value = ['X-Accel-Expires:60'])
    private static final String HEADER_LIST = "headers.response";

    Pattern uriPattern

    def headers

    @Activate
    protected void activate(ComponentContext ctx) {
        uriPattern = ~ ctx.properties[URI_PATTERN]
        headers = PropertiesUtil.toStringArray(ctx.properties[HEADER_LIST]).toList().collect { it.split(':') }
    }

    @Override
    void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        if (uriPattern.matcher(request.requestURI).matches()) {
            headers.each { header ->
                response.addHeader(header[0], header[1])
            }
        }
        chain.doFilter(request, response)
    }

    @Override
    void destroy() {}
}
