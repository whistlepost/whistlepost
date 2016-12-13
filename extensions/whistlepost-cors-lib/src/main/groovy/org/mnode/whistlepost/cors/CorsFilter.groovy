package org.mnode.whistlepost.cors

import java.util.regex.Pattern

import javax.servlet.Filter
import javax.servlet.FilterConfig
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.ServletException

import org.apache.felix.scr.annotations.Activate
import org.apache.felix.scr.annotations.Component
import org.apache.felix.scr.annotations.Properties
import org.apache.felix.scr.annotations.Property
import org.apache.felix.scr.annotations.Service
import org.osgi.service.component.ComponentContext

@Component(metatype = true, label = 'Whistlepost CORS Filter', description = 'Applies CORS headers where applicable')
@Service(value = Filter.class)
@Property(name = 'service.description', value = 'Whistlepost CORS Filter', propertyPrivate = true)
@Property(name = "service.vendor", value = "Micronode", propertyPrivate = true)
@Property(name = "sling.filter.scope", value = "request", propertyPrivate = true)
class CorsFilter implements Filter {

    @Property(label = 'Origin Pattern', description = 'Regular expression for matching request origin', value = /^.*\.example\.com$/)
    private static final String ORIGIN_PATTERN = "pattern.origin";
    
    Pattern originPattern

    @Activate
    protected void activate(ComponentContext ctx) {
        originPattern =~ ctx.properties[ORIGIN_PATTERN]
    }
    
    void init(FilterConfig config) {}
    
    void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String origin = request.getHeader("Origin");
        //if (origin ==~ originPattern) {
            response.addHeader("Access-Control-Allow-Origin", '*');
            response.addHeader("Access-Control-Allow-Methods", 'GET, POST, OPTIONS')
        //}
        chain.doFilter(request, response)
    }
    
    void destroy() {}
}
