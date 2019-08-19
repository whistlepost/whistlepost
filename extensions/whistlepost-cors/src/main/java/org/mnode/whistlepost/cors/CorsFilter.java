package org.mnode.whistlepost.cors;

import org.codehaus.groovy.runtime.DefaultGroovyMethods;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

@Component(service = Filter.class, property = {"service.description=Whistlepost CORS Filter", "service.vendor=Micronode", "pattern.origin"})
public class CorsFilter implements Filter {
    @Activate
    protected void activate(BundleContext ctx) {
        originPattern = Pattern.compile(ORIGIN_PATTERN);
    }

    public void init(FilterConfig config) {
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String origin = request.getHeader("Origin");
        //if (origin ==~ originPattern) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        //}
        chain.doFilter(request, response);
    }

    public void destroy() {
    }

    public Pattern getOriginPattern() {
        return originPattern;
    }

    public void setOriginPattern(Pattern originPattern) {
        this.originPattern = originPattern;
    }

    @Property(label = "Origin Pattern", description = "Regular expression for matching request origin", value =/^.*\.example\.com$/)
    private static final String ORIGIN_PATTERN = "pattern.origin";
    private Pattern originPattern;
}
