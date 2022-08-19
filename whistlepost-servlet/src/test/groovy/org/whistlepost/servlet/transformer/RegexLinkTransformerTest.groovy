package org.whistlepost.servlet.transformer

import org.cyberneko.html.parsers.SAXParser
import org.xml.sax.*
import spock.lang.Specification

/**
 * Created by fortuna on 27/05/2016.
 */
class RegexLinkTransformerTest extends Specification {

    RegexLinkTransformer transformer

    def setup() {
        transformer = [
                tags       : [a: 'href', link: 'rel', img: 'src'],
                linkPattern: ~ '^/content/whistlepost(/.*)$',
                replacement: '$1'
        ]
    }

    def 'assert content is transformed'() {
        given: 'a SAX parser initialised with a transformer content handler'
        CapturingContentHandler resultHandler = []
        transformer.contentHandler = resultHandler
        SAXParser parser = []
        parser.contentHandler = transformer

        when: 'a text string is parsed'
        parser.parse(new InputSource(new StringReader('<html><a href="/content/whistlepost/index.html">Home</a></html>')))

        then:
        assert resultHandler.result == '<HTML><HEAD></HEAD><BODY><A href=/index.html>Home</A></BODY></HTML>'
    }

    static class CapturingContentHandler implements ContentHandler {

        def result = ''

        @Override
        void setDocumentLocator(Locator locator) {

        }

        @Override
        void startDocument() throws SAXException {

        }

        @Override
        void endDocument() throws SAXException {

        }

        @Override
        void startPrefixMapping(String prefix, String uri) throws SAXException {

        }

        @Override
        void endPrefixMapping(String prefix) throws SAXException {

        }

        @Override
        void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
            def attString = {
                def result = ''
                for (int i=0; i < atts.length; i++) {
                    result += " ${atts.getLocalName(i)}=${atts.getValue(i)}"
                }
                result
            }
            result += "<$localName${attString()}>"
        }

        @Override
        void endElement(String uri, String localName, String qName) throws SAXException {
            result += "</$localName>"
        }

        @Override
        void characters(char[] ch, int start, int length) throws SAXException {
            result += new String(ch, start, length)
        }

        @Override
        void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {

        }

        @Override
        void processingInstruction(String target, String data) throws SAXException {

        }

        @Override
        void skippedEntity(String name) throws SAXException {

        }
    }
}
