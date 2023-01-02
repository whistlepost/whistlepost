package org.whistelpost.config.site;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.caconfig.ConfigurationBuilder;
import org.apache.sling.testing.mock.caconfig.MockContextAwareConfig;
import org.apache.sling.testing.mock.sling.junit5.SlingContextExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.whistelpost.config.AbstractConfigTest;
import org.whistlepost.config.site.MetadataConfig;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SlingContextExtension.class)
public class MetadataConfigTest extends AbstractConfigTest {

    @Override
    @BeforeEach
    public void setup() {
        super.setup();

        context.create().resource("/content/example", "sling:configRef", "/conf/site");
        context.create().resource("/content/example/en", "sling:configRef", "/conf/site/en");
        context.create().resource("/content/example/fr");

        MockContextAwareConfig.writeConfiguration(context, "/content/example", MetadataConfig.class,
                "title", "My Example Site", "description", "A fictional site for testing configuration");

        MockContextAwareConfig.writeConfiguration(context, "/content/example/en", MetadataConfig.class,
                "title", "My English Site", "description", "A fictional site for testing configuration");
    }

    @Test
    public void testGetConfigEn() {
        context.currentResource("/content/example/en");

        Resource resource = context.request().getResource();
        MetadataConfig config = resource.adaptTo(ConfigurationBuilder.class).as(MetadataConfig.class);
        assertNotNull(config);
        assertEquals("My English Site", config.title());
        assertNotNull(config.description());
        assertNull(config.keywords());
    }

    @Test
    public void testGetConfigFr() {
        context.currentResource("/content/example/fr");

        Resource resource = context.request().getResource();
        MetadataConfig config = resource.adaptTo(ConfigurationBuilder.class).as(MetadataConfig.class);
        assertNotNull(config);
        assertEquals("My Example Site", config.title());
        assertNotNull(config.description());
        assertNull(config.keywords());
    }
}
