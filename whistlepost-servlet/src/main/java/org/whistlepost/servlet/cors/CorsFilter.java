package org.whistlepost.servlet.cors;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

@Component(service = Filter.class, property = {
        "service.description=Whistlepost CORS Filter",
        "service.vendor=Micronode"
})
public class CorsFilter implements Filter {

    private Pattern originPattern;

    @Activate
    protected void activate(CorsFilterConfiguration configuration, BundleContext ctx) {
        configure(configuration);
    }

    @Modified
    private void modified(CorsFilterConfiguration configuration) {
        configure(configuration);
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

    private void configure(CorsFilterConfiguration configuration) {
        originPattern = Pattern.compile(configuration.cors_pattern_origin());
    }
}
