package org.whistlepost.model;

import org.apache.sling.testing.mock.caconfig.ContextPlugins;
import org.apache.sling.testing.mock.caconfig.MockContextAwareConfig;
import org.apache.sling.testing.mock.sling.ResourceResolverType;
import org.apache.sling.testing.mock.sling.junit5.SlingContext;
import org.apache.sling.testing.mock.sling.junit5.SlingContextBuilder;
import org.junit.jupiter.api.BeforeEach;

public abstract class AbstractModelTest {

    protected final SlingContext context;

    public AbstractModelTest() {
        this(ResourceResolverType.RESOURCERESOLVER_MOCK);
    }

    public AbstractModelTest(ResourceResolverType resourceResolverType) {
//        context = new SlingContext(resourceResolverType);
        context = new SlingContextBuilder().resourceResolverType(resourceResolverType)
                .plugin(ContextPlugins.CACONFIG).build();
    }

    @BeforeEach
    public void setup() {
        context.addModelsForPackage("org.whistlepost.model");
        // register configuration annotation class
        MockContextAwareConfig.registerAnnotationPackages(context, "org.whistlepost.config");

        context.load().json("/conf/site.json", "/conf/site");
        context.create().resource("/content", "sling:configRef", "/conf/site");
    }
}
