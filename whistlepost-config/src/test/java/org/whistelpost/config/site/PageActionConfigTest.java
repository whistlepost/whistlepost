package org.whistelpost.config.site;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.caconfig.ConfigurationBuilder;
import org.apache.sling.testing.mock.sling.junit5.SlingContextExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.whistelpost.config.AbstractConfigTest;
import org.whistlepost.config.site.PageActionConfig;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SlingContextExtension.class)
public class PageActionConfigTest extends AbstractConfigTest {

    @Override
    @BeforeEach
    public void setup() {
        super.setup();

        context.load().json("/SLING-INF/content/wp.json", "/conf/wp");
        context.load().json("/SLING-INF/content/wp/folder.json", "/conf/wp/folder");
        context.load().json("/SLING-INF/content/wp/folder/subscription.json", "/conf/wp/folder/subscription");

        context.create().resource("/content/subscriptions",
                "sling:configRef", "/conf/wp/folder/subscription");
        context.create().resource("/content/subscriptions/one");

    }

    @Test
    public void testGetPageActions() {
        context.currentResource("/content/subscriptions/one");

        Resource resource = context.request().getResource();
        Collection<PageActionConfig> config = resource.adaptTo(ConfigurationBuilder.class).asCollection(PageActionConfig.class);
        assertNotNull(config);
        assertEquals(4, config.size());
        assertEquals("edit", config.iterator().next().path());
    }
}
