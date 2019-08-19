package org.mnode.whistlepost.rewriter;

import groovy.lang.Reference;
import groovy.util.logging.Slf4j;
import org.apache.sling.rewriter.DefaultTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by fortuna on 24/05/2016.
 */
public class RegexLinkTransformer extends DefaultTransformer {

    private static final Logger LOG = LoggerFactory.getLogger(RegexLinkTransformer.class);

    private Map<String, String> tags = new LinkedHashMap<();

    private Pattern linkPattern;

    private String replacement;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (tags.keySet().contains(localName.toLowerCase())) {
            super.startElement(uri, localName, qName, transform(attributes, tags.get(localName.toLowerCase())));
        } else {
            super.startElement(uri, localName, qName, attributes);
        }

    }

    private Attributes transform(final Attributes attributes, String key) {
        LOG.info("Transforming [" + key + "]");
        final AttributesImpl transformedAttrs = (AttributesImpl) new ArrayList<Attributes>(Arrays.asList(attributes));
        for (final Reference<Integer> i = new Reference<int>(0); ; i.get() < attributes.getLength() ;){
            if (attributes.getLocalName(i.get()).equals(key)) {
                Matcher matcher = linkPattern.matcher(attributes.getValue(i.get()));
                if (matcher.matches()) {
                    transformedAttrs.setValue(i.get(), matcher.replaceFirst(replacement));
                    LOG.info("Transformed [" + attributes.getValue(i.get()) + "] -> [" + transformedAttrs.getValue(i.get()) + "]");
                }

            }

        }

        return transformedAttrs;
    }

    public Map<String, String> getTags() {
        return tags;
    }

    public void setTags(Map tags) {
        this.tags = tags;
    }

    public Pattern getLinkPattern() {
        return linkPattern;
    }

    public void setLinkPattern(Pattern linkPattern) {
        this.linkPattern = linkPattern;
    }

    public String getReplacement() {
        return replacement;
    }

    public void setReplacement(String replacement) {
        this.replacement = replacement;
    }
}
