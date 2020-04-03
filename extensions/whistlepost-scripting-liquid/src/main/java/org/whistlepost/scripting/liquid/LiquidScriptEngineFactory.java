package org.whistlepost.scripting.liquid;

import org.apache.sling.commons.classloader.DynamicClassLoaderManager;
import org.apache.sling.scripting.api.AbstractScriptEngineFactory;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.*;
import org.osgi.service.metatype.annotations.Designate;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;

@Component(service = ScriptEngineFactory.class, property = {
        "service.description=Liquid Script Engine",
        "service.vendor=Micronode"
})
@Designate(ocd = LiquidScriptEngineFactoryConfiguration.class)
public class LiquidScriptEngineFactory extends AbstractScriptEngineFactory {

    @Reference
    private DynamicClassLoaderManager dynamicClassLoaderManager;

    private LiquidScriptEngineFactoryConfiguration configuration;

    private BundleContext bundleContext;

    @Override
    public String getLanguageName() {
        return "Liquid Templates";
    }

    @Override
    public String getLanguageVersion() {
        return "1.0";
    }

    @Override
    public ScriptEngine getScriptEngine() {
        return new LiquidScriptEngine(this);
    }

    @Activate
    private void activate(LiquidScriptEngineFactoryConfiguration configuration, BundleContext bundleContext) {
        this.configuration = configuration;
        this.bundleContext = bundleContext;
        configure(configuration);
    }

    @Modified
    private void modified(LiquidScriptEngineFactoryConfiguration configuration) {
        this.configuration = configuration;
        configure(configuration);
    }

    @Deactivate
    private void deactivate() {
        bundleContext = null;
    }

    private void configure(LiquidScriptEngineFactoryConfiguration configuration) {
        setExtensions(configuration.extensions());
        setMimeTypes(configuration.mimeTypes());
        setNames(configuration.names());
    }
}
