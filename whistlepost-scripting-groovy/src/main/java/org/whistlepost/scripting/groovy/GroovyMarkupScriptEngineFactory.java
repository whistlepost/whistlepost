package org.whistlepost.scripting.groovy;

import groovy.text.TemplateEngine;
import groovy.text.markup.MarkupTemplateEngine;
import groovy.text.markup.TemplateConfiguration;
import org.apache.sling.commons.classloader.DynamicClassLoaderManager;
import org.apache.sling.scripting.api.AbstractScriptEngineFactory;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.*;
import org.osgi.service.metatype.annotations.Designate;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import java.util.Dictionary;
import java.util.Hashtable;

@Component(service = ScriptEngineFactory.class, property = {
        "service.description=Groovy Markup Script Engine",
        "service.vendor=Micronode"
})
@Designate(ocd = GroovyMarkupScriptEngineFactoryConfiguration.class)
public class GroovyMarkupScriptEngineFactory extends AbstractScriptEngineFactory {

    @Reference
    private DynamicClassLoaderManager dynamicClassLoaderManager;

    private GroovyMarkupScriptEngineFactoryConfiguration configuration;

    private BundleContext bundleContext;

    private TemplateEngine templateEngine;

    private ServiceRegistration<TemplateEngine> serviceRegistration;

    @Override
    public String getLanguageName() {
        return "Groovy Markup Templates";
    }

    @Override
    public String getLanguageVersion() {
        return "2.4";
    }

    @Override
    public ScriptEngine getScriptEngine() {
        return new GroovyMarkupScriptEngine(this);
    }

    TemplateEngine getTemplateEngine() { return templateEngine; }

    @Activate
    private void activate(GroovyMarkupScriptEngineFactoryConfiguration configuration, BundleContext bundleContext) {
        this.configuration = configuration;
        this.bundleContext = bundleContext;
        configure(configuration);

        TemplateConfiguration templateConfiguration = new TemplateConfiguration();
        templateEngine = new MarkupTemplateEngine(dynamicClassLoaderManager.getDynamicClassLoader(),
                templateConfiguration);
        registerTemplateEngine();
    }

    @Modified
    private void modified(GroovyMarkupScriptEngineFactoryConfiguration configuration) {
        this.configuration = configuration;
        configure(configuration);
    }

    @Deactivate
    private void deactivate() {
        unregisterTemplateEngine();
        templateEngine = null;
        bundleContext = null;
    }

    private void configure(GroovyMarkupScriptEngineFactoryConfiguration configuration) {
        setExtensions(configuration.extensions());
        setMimeTypes(configuration.mimeTypes());
        setNames(configuration.names());
    }

    private void registerTemplateEngine() {
        if (templateEngine != null) {
            Dictionary<String, String> properties = new Hashtable<>();
            properties.put(Constants.SERVICE_DESCRIPTION, "Groovy MarkupTemplateEngine");
            properties.put(Constants.SERVICE_VENDOR, "Micronode");
            serviceRegistration = bundleContext.registerService(TemplateEngine.class, templateEngine, properties);
        }
    }

    private void unregisterTemplateEngine() {
        if (serviceRegistration != null) {
            serviceRegistration.unregister();
            serviceRegistration = null;
        }
    }
}
