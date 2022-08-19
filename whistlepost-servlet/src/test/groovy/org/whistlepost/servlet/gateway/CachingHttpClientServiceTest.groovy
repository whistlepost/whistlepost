package org.whistlepost.servlet.gateway


import org.apache.http.impl.client.cache.CachingHttpClientBuilder
import org.apache.http.osgi.services.CachingHttpClientBuilderFactory
import spock.lang.Specification

class CachingHttpClientServiceTest extends Specification {

    def 'test GET request'() {
        given: 'a cahing http client service'
        CachingHttpClientService service = [{ CachingHttpClientBuilder.create()} as CachingHttpClientBuilderFactory]

        expect: 'a GET request returns a result'
        def response = service.doGet(URI.create('https://google.com'), null)
        response != null
    }
}
