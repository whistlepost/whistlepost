package org.whistelpost.config.site;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.caconfig.ConfigurationBuilder;
import org.apache.sling.testing.mock.caconfig.MockContextAwareConfig;
import org.apache.sling.testing.mock.sling.junit5.SlingContextExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.whistelpost.config.AbstractConfigTest;
import org.whistlepost.config.site.LayoutConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SlingContextExtension.class)
public class LayoutConfigTest extends AbstractConfigTest {

    @Override
    @BeforeEach
    public void setup() {
        super.setup();

        context.create().resource("/content/example", "sling:configRef", "/conf/site");

        MockContextAwareConfig.writeConfiguration(context, "/content/example", LayoutConfig.class,
                "header", "/content/header", "footer", "/content/footer",
                "sidebar", "/content/sidebar", "navigation", "/content/navigation");
    }

    @Test
    public void testGetConfig() {
        context.currentResource("/content/example");

        Resource resource = context.request().getResource();
        LayoutConfig config = resource.adaptTo(ConfigurationBuilder.class).as(LayoutConfig.class);
        assertNotNull(config);
        assertEquals("/content/header", config.header());
        assertEquals("/content/footer", config.footer());
        assertEquals("/content/sidebar", config.sidebar());
        assertEquals("/content/navigation", config.navigation());
    }
}
