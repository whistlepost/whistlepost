package org.whistlepost.servlet.gateway

import org.apache.http.impl.client.HttpClientBuilder
import org.apache.http.osgi.services.HttpClientBuilderFactory
import spock.lang.Specification

class HttpClientServiceTest extends Specification {

    def 'test GET request'() {
        given: 'a http client service'
        HttpClientService service = [{ HttpClientBuilder.create()} as HttpClientBuilderFactory]

        expect: 'a GET request returns a result'
        def response = service.doGet(URI.create('https://google.com'), null)
        response != null
    }
}
