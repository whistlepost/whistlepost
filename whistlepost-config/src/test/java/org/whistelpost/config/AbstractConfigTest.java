package org.whistelpost.config;

import org.apache.sling.testing.mock.caconfig.ContextPlugins;
import org.apache.sling.testing.mock.caconfig.MockContextAwareConfig;
import org.apache.sling.testing.mock.sling.ResourceResolverType;
import org.apache.sling.testing.mock.sling.junit5.SlingContext;
import org.apache.sling.testing.mock.sling.junit5.SlingContextBuilder;
import org.junit.jupiter.api.BeforeEach;

public abstract class AbstractConfigTest {

    protected final SlingContext context;

    public AbstractConfigTest() {
        this(ResourceResolverType.RESOURCERESOLVER_MOCK);
    }

    public AbstractConfigTest(ResourceResolverType resourceResolverType) {
        context = new SlingContextBuilder().resourceResolverType(resourceResolverType)
                .plugin(ContextPlugins.CACONFIG).build();
    }

    @BeforeEach
    public void setup() {
        // register configuration annotation class
        MockContextAwareConfig.registerAnnotationPackages(context, "org.whistlepost.config");

        context.create().resource("/content", "sling:configRef", "/conf/wp");
    }
}
