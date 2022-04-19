package org.whistlepost.model

import org.apache.sling.models.factory.ModelFactory
import org.apache.sling.testing.mock.sling.junit5.SlingContext
import spock.lang.Specification

class LayoutTest extends Specification {

    SlingContext context

    def setup() {
        context = []
        context.addModelsForPackage('org.whistlepost.model')
//        context.registerService(ModelFactory, new ModelAdapterFactory())
    }

    def 'test header configuration'() {
        given: 'a layout resource'
        context.load().json('/layout.json', '/layout')

        when: 'the resource is retrieved'
        def resource = context.resourceResolver().getResource('/layout')

        and: 'and converted to a model'
        def layout = context.getService(ModelFactory).createModel(resource, Layout)

        then: 'header configuration is as expected'
        layout.header != null
    }
}
