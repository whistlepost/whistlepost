package org.mnode.whistlepost.rewriter

import groovy.util.logging.Slf4j
import org.apache.sling.rewriter.DefaultTransformer
import org.xml.sax.Attributes
import org.xml.sax.SAXException
import org.xml.sax.helpers.AttributesImpl

import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * Created by fortuna on 24/05/2016.
 */
@Slf4j
class RegexLinkTransformer extends DefaultTransformer {

    Map tags = [:]
    Pattern linkPattern
    String replacement

    @Override
    void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (tags.keySet().contains(localName.toLowerCase())) {
            super.startElement(uri, localName, qName, transform(attributes, tags[localName.toLowerCase()]))
        } else {
            super.startElement(uri, localName, qName, attributes)
        }
    }

    private Attributes transform(Attributes attributes, String key) {
        log.info "Transforming [$key]"
        AttributesImpl transformedAttrs = [attributes]
        for (int i = 0; i < attributes.length; i++) {
            if (attributes.getLocalName(i) == key) {
                Matcher matcher = linkPattern.matcher(attributes.getValue(i))
                if (matcher.matches()) {
                    transformedAttrs.setValue(i, matcher.replaceFirst(replacement))
                    log.info "Transformed [${attributes.getValue(i)}] -> [${transformedAttrs.getValue(i)}]"
                }
            }
        }
        transformedAttrs
    }
}
