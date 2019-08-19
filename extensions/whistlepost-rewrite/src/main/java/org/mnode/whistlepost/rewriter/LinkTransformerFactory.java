package org.mnode.whistlepost.rewriter;

import org.apache.sling.rewriter.Transformer;
import org.apache.sling.rewriter.TransformerFactory;
import org.codehaus.groovy.runtime.DefaultGroovyMethods;

import java.util.LinkedHashMap;

/**
 * Created by fortuna on 30/05/2016.
 */
@Component(metatype = true, label = "Whistlepost Link Transformer", description = "Rewrites links in response data")
@Service(value = TransformerFactory.class)
public class LinkTransformerFactory extends RegexLinkTransformer implements TransformerFactory {
    @Activate
    protected void activate(ComponentContext ctx) {
        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>(4);
        map.put("a", "href");
        map.put("link", "href");
        map.put("img", "src");
        map.put("form", "action");
        setTags(map);
        setLinkPattern(~DefaultGroovyMethods.getAt(ctx.properties, LINK_PATTERN));
        setReplacement(DefaultGroovyMethods.getAt(ctx.properties, REPLACEMENT_STRING));
    }

    @Override
    public Transformer createTransformer() {
        return this;
    }

    @Property(value = "wp-link-rewriter", propertyPrivate = true)
    private static final String PIPELINE_TYPE = "pipeline.type";
    @Property(label = "Link Pattern", description = "Regular expression for matching links", value = "^/content(/wp)?(/\\w+)?(/.*)$")
    private static final String LINK_PATTERN = "pattern.link";
    @Property(label = "Replacement link", description = "Replacement string for links", value = "$1$3")
    private static final String REPLACEMENT_STRING = "replacement.string";
}
