package org.mnode.whistlepost.headers;

import groovy.lang.Closure;
import org.apache.sling.commons.osgi.PropertiesUtil;
import org.codehaus.groovy.runtime.DefaultGroovyMethods;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

@Component(metatype = true, label = "Whistlepost Headers Filter", description = "Applies response headers where applicable")
@Service(value = Filter.class)
@Property(name = "service.description", value = "Whistlepost Headers Filter", propertyPrivate = true)
@Property(name = "service.vendor", value = "Micronode", propertyPrivate = true)
@Property(name = "sling.filter.scope", value = "request", propertyPrivate = true)
public class HeadersFilter implements Filter {
    @Activate
    protected void activate(ComponentContext ctx) {
        uriPattern = ((Pattern) (~DefaultGroovyMethods.getAt(ctx.properties, URI_PATTERN)));
        headers = DefaultGroovyMethods.collect(DefaultGroovyMethods.toList(PropertiesUtil.toStringArray(DefaultGroovyMethods.getAt(ctx.properties, HEADER_LIST))), new Closure<String[]>(this, this) {
            public String[] doCall(String it) {
                return it.split(":");
            }

            public String[] doCall() {
                return doCall(null);
            }

        });
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;
        if (uriPattern.matcher(request.getRequestURI()).matches()) {
            DefaultGroovyMethods.each(headers, new Closure<Object>(this, this) {
                public void doCall(Object header) {
                    response.addHeader(DefaultGroovyMethods.getAt(header, 0), DefaultGroovyMethods.getAt(header, 1));
                }

            });
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

    public Pattern getUriPattern() {
        return uriPattern;
    }

    public void setUriPattern(Pattern uriPattern) {
        this.uriPattern = uriPattern;
    }

    public Object getHeaders() {
        return headers;
    }

    public void setHeaders(Object headers) {
        this.headers = headers;
    }

    @Property(label = "URI Pattern", description = "Regular expression for matching request URI", value =/^\/some\/path\/.*$/)
    private static final String URI_PATTERN = "pattern.uri";
    @Property(unbounded = PropertyUnbounded.ARRAY, label = "Header List", cardinality = 50, description = "A list of headers to apply to the response", value = {"X-Accel-Expires:60"})
    private static final String HEADER_LIST = "headers.response";
    private Pattern uriPattern;
    private Object headers;
}
