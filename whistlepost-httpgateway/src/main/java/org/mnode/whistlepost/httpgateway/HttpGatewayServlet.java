package org.mnode.whistlepost.httpgateway;

import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.message.BasicHeader;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.*;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component(service = Servlet.class, property = {
        "service.description=Whistlepost Gateway Servlet",
        "service.vendor=Micronode"
})
@Designate(ocd = HttpGatewayServletConfiguration.class, factory = true)
public class HttpGatewayServlet extends SlingSafeMethodsServlet {

    private static final Logger LOG = LoggerFactory.getLogger(HttpGatewayServlet.class);

    @Reference
    private CachingHttpClientService httpClientService;

    private HttpGatewayServletConfiguration configuration;

    private BundleContext bundleContext;

    private URI uri;

    private CredentialsProvider credentialsProvider;

    private List<Header> defaultHeaders = new ArrayList<>();

    @Activate
    protected void activate(HttpGatewayServletConfiguration configuration, BundleContext bundleContext) {
        this.configuration = configuration;
        this.bundleContext = bundleContext;
        configure(configuration);
    }

    @Modified
    private void modified(HttpGatewayServletConfiguration configuration) {
        this.configuration = configuration;
        configure(configuration);
    }

    @Deactivate
    private void deactivate() {
        bundleContext = null;
    }

    private void configure(HttpGatewayServletConfiguration configuration) {
        uri = URI.create(configuration.target_uri());
        if ("basic".equals(configuration.authentication_type())) {
            credentialsProvider = new BasicCredentialsProvider();
            credentialsProvider.setCredentials(new AuthScope(configuration.target_fqdn(), configuration.target_port()),
                    new UsernamePasswordCredentials(configuration.basic_user(), configuration.basic_password()));
        } else if ("oauth".equals(configuration.authentication_type())) {
             defaultHeaders.add(new BasicHeader(HttpHeaders.AUTHORIZATION,
                     String.format("Bearer:%s", configuration.oauth_token())));
        }
    }

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {

        URIBuilder uriBuilder = new URIBuilder(uri);
        request.getParameterMap().forEach((k, v) -> Arrays.stream(v).forEach(v1 -> uriBuilder.setParameter(k, v1)));

        response.setContentType("application/json");

        try {
            String targetResponse = httpClientService.doGet(uriBuilder.build(), credentialsProvider,
                    defaultHeaders.toArray(new Header[] {}));
            response.getWriter().write(targetResponse);
        } catch (URISyntaxException use) {
            LOG.error("Unexpected error", use);
        }
    }
}
