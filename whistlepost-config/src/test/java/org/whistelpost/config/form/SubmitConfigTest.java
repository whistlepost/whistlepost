package org.whistelpost.config.form;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.caconfig.ConfigurationBuilder;
import org.apache.sling.testing.mock.sling.junit5.SlingContextExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.whistelpost.config.AbstractConfigTest;
import org.whistlepost.config.form.SubmitConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SlingContextExtension.class)
public class SubmitConfigTest extends AbstractConfigTest {

    @Override
    @BeforeEach
    public void setup() {
        super.setup();

        context.load().json("/SLING-INF/content/wp.json", "/conf/wp");
        context.load().json("/SLING-INF/content/wp/form.json", "/conf/wp/form");

        context.currentResource(context.create().resource("/content/example/form",
                "sling:configRef", "/conf/wp/form"));
    }

    @Test
    public void testGetConfig() {
        Resource resource = context.request().getResource();
        SubmitConfig config = resource.adaptTo(ConfigurationBuilder.class).as(SubmitConfig.class);
        assertNotNull(config);
        assertEquals("multipart/form-data", config.encoding());
    }
}
