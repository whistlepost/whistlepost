package org.whistelpost.config.page;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.caconfig.ConfigurationBuilder;
import org.apache.sling.testing.mock.caconfig.MockContextAwareConfig;
import org.apache.sling.testing.mock.sling.junit5.SlingContextExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.whistelpost.config.AbstractConfigTest;
import org.whistlepost.config.page.SitemapConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SlingContextExtension.class)
public class SitemapConfigTest extends AbstractConfigTest {

    @Override
    @BeforeEach
    public void setup() {
        super.setup();

        context.create().resource("/content/example", "sling:configRef", "/conf/site");

        context.currentResource(context.create().resource("/content/example/en"));

        MockContextAwareConfig.writeConfiguration(context, "/content/example", SitemapConfig.class,
                "urlBase", "https://www.example.com/", "changeFrequency", "weekly");
    }

    @Test
    public void testGetConfig() {
        Resource resource = context.request().getResource();
        SitemapConfig config = resource.adaptTo(ConfigurationBuilder.class).as(SitemapConfig.class);
        assertNotNull(config);
        assertEquals("https://www.example.com/", config.urlBase());
        assertEquals("weekly", config.changeFrequency());
    }
}
