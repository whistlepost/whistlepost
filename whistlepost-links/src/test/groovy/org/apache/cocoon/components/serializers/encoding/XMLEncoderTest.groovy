package org.apache.cocoon.components.serializers.encoding


import spock.lang.Specification

/**
 * Created by fortuna on 19/08/2016.
 */
class XMLEncoderTest extends Specification {

    XMLEncoder encoder = []

    def 'verify encoding of emoji chars'() {
        given: 'an emoji character string'
        def emoji = '\uD83C\uDF40'

        when: 'the string is encoded'
        def encoded = emoji.chars.collect {encoder.encode(it)}.join('')

        then: 'the encoded form is as expected'
        encoded == '&#x1F340;'

        /*
         * Condition not satisfied:
         *
         * encoded == encoder.encode((char) 127808)
         * |       |  |       |
         * |       |  |       &#xF340;
         * |       |  org.apache.cocoon.components.serializers.encoding.XMLEncoder@71d44a3[X-W3C-XML]
         * |       false
         * &#xD83C;&#xDF40;
         */
    }
}
