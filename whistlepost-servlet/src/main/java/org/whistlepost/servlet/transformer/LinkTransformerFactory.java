package org.whistlepost.servlet.transformer;

import org.apache.sling.rewriter.Transformer;
import org.apache.sling.rewriter.TransformerFactory;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.metatype.annotations.Designate;

import java.util.LinkedHashMap;
import java.util.regex.Pattern;

/**
 * Created by fortuna on 30/05/2016.
 */
@Component(service = TransformerFactory.class, property = {
        "service.description=Whistlepost Link Transformer",
        "service.vendor=Micronode"
})
@Designate(ocd = LinkTransformerFactoryConfiguration.class)
public class LinkTransformerFactory extends RegexLinkTransformer implements TransformerFactory {

    private LinkTransformerFactoryConfiguration configuration;

    private BundleContext bundleContext;

    @Activate
    protected void activate(LinkTransformerFactoryConfiguration configuration, BundleContext bundleContext) {
        this.configuration = configuration;
        this.bundleContext = bundleContext;
        configure(configuration);
    }

    @Modified
    private void modified(LinkTransformerFactoryConfiguration configuration) {
        this.configuration = configuration;
        configure(configuration);
    }

    @Deactivate
    private void deactivate() {
        bundleContext = null;
    }

    private void configure(LinkTransformerFactoryConfiguration configuration) {

        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>(4);
        map.put("a", "href");
        map.put("link", "href");
        map.put("img", "src");
        map.put("form", "action");
        setTags(map);

        setLinkPattern(Pattern.compile(configuration.pattern_link()));
        setReplacement(configuration.replacement_string());
    }

    @Override
    public Transformer createTransformer() {
        return this;
    }
}
