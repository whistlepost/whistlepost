package org.whistlepost.servlet.gateway;

import org.apache.http.Header;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.osgi.services.CachingHttpClientBuilderFactory;
import org.apache.http.util.EntityUtils;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;

import java.io.IOException;
import java.net.URI;
import java.util.Arrays;

@Component(service = CachingHttpClientService.class, property = {
        "service.description=Whistlepost HTTP Client",
        "service.vendor=Micronode"
})
@Designate(ocd = CachingHttpClientServiceConfiguration.class)
public class CachingHttpClientService {

    @Reference
    private CachingHttpClientBuilderFactory httpClientBuilder;

    public CachingHttpClientService(CachingHttpClientBuilderFactory httpClientBuilder) {
        this.httpClientBuilder = httpClientBuilder;
    }

    public String doGet(URI uri, CredentialsProvider credentialsProvider, Header... defaultHeaders) throws IOException {
        try (CloseableHttpClient httpClient =
                     httpClientBuilder.newBuilder().setDefaultCredentialsProvider(credentialsProvider)
                             .setDefaultHeaders(Arrays.asList(defaultHeaders)).build()) {

            HttpGet httpGet = new HttpGet(uri);

            try (CloseableHttpResponse httpResponse = httpClient.execute(httpGet)) {
                return EntityUtils.toString(httpResponse.getEntity());
            }
        }
    }
}
