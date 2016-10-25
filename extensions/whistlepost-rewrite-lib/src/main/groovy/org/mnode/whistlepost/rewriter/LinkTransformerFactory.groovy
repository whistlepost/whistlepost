package org.mnode.whistlepost.rewriter

import org.apache.felix.scr.annotations.Activate
import org.apache.felix.scr.annotations.Component
import org.apache.felix.scr.annotations.Property
import org.apache.felix.scr.annotations.Service
import org.apache.sling.rewriter.Transformer
import org.apache.sling.rewriter.TransformerFactory
import org.osgi.service.component.ComponentContext

/**
 * Created by fortuna on 30/05/2016.
 */
@Component(metatype = true, label = 'Whistlepost Link Transformer', description = 'Rewrites links in response data')
@Service(value = TransformerFactory.class)
class LinkTransformerFactory extends RegexLinkTransformer implements TransformerFactory {

    @Property(value = 'wp-link-rewriter', propertyPrivate = true)
    private static final String PIPELINE_TYPE = 'pipeline.type';

    @Property(label = 'Link Pattern', description = 'Regular expression for matching links', value='^/content(/wp)?(/\\w+)?(/.*)$')
    private static final String LINK_PATTERN = "pattern.link";

    @Property(label = "Replacement link", description = "Replacement string for links", value='$1$3')
    private static final String REPLACEMENT_STRING = "replacement.string";

    @Activate
    protected void activate(ComponentContext ctx) {
        tags = [a: 'href', link: 'href', img: 'src', form: 'action']
        linkPattern = ~ ctx.properties[LINK_PATTERN]
        replacement = ctx.properties[REPLACEMENT_STRING];
    }

    @Override
    Transformer createTransformer() {
        this
    }
}
