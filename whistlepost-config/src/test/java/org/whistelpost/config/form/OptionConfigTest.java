package org.whistelpost.config.form;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.caconfig.ConfigurationBuilder;
import org.apache.sling.testing.mock.sling.junit5.SlingContextExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.whistelpost.config.AbstractConfigTest;
import org.whistlepost.config.form.OptionConfig;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SlingContextExtension.class)
public class OptionConfigTest extends AbstractConfigTest {

    @Override
    @BeforeEach
    public void setup() {
        super.setup();

        context.load().json("/SLING-INF/content/wp.json", "/conf/wp");
        context.load().json("/SLING-INF/content/wp/form.json", "/conf/wp/form");
        context.load().json("/SLING-INF/content/wp/form/select/boolean.json", "/conf/wp/form/select/boolean");
        context.load().json("/SLING-INF/content/wp/form/select/onoff.json", "/conf/wp/form/select/onoff");
        context.load().json("/SLING-INF/content/wp/form/select/yesno.json", "/conf/wp/form/select/yesno");
        context.load().json("/SLING-INF/content/wp/form/select/gender.json", "/conf/wp/form/select/gender");
        context.load().json("/SLING-INF/content/wp/form/select/folderType.json", "/conf/wp/form/select/folderType");

        context.create().resource("/content/example/form/boolean", "sling:configRef", "/conf/wp/form/select/boolean");
        context.create().resource("/content/example/form/onoff", "sling:configRef", "/conf/wp/form/select/onoff");
        context.create().resource("/content/example/form/yesno", "sling:configRef", "/conf/wp/form/select/yesno");
        context.create().resource("/content/example/form/gender", "sling:configRef", "/conf/wp/form/select/gender");
        context.create().resource("/content/example/form/folderType", "sling:configRef", "/conf/wp/form/select/folderType");
    }

    @Test
    public void testConfigBooleanOptions() {
        context.currentResource("/content/example/form/boolean");

        Resource resource = context.request().getResource();
        Collection<OptionConfig> config = resource.adaptTo(ConfigurationBuilder.class).asCollection(OptionConfig.class);
        assertNotNull(config);
        assertEquals(2, config.size());
        assertTrue(config.iterator().next().booleanValue());
    }

    @Test
    public void testConfigOnoffOptions() {
        context.currentResource("/content/example/form/onoff");

        Resource resource = context.request().getResource();
        Collection<OptionConfig> config = resource.adaptTo(ConfigurationBuilder.class).asCollection(OptionConfig.class);
        assertNotNull(config);
        assertEquals(2, config.size());
        assertEquals("on", config.iterator().next().value());
    }

    @Test
    public void testConfigYesnoOptions() {
        context.currentResource("/content/example/form/yesno");

        Resource resource = context.request().getResource();
        Collection<OptionConfig> config = resource.adaptTo(ConfigurationBuilder.class).asCollection(OptionConfig.class);
        assertNotNull(config);
        assertEquals(2, config.size());
        assertEquals("Y", config.iterator().next().value());
    }

    @Test
    public void testConfigGenderOptions() {
        context.currentResource("/content/example/form/gender");

        Resource resource = context.request().getResource();
        Collection<OptionConfig> config = resource.adaptTo(ConfigurationBuilder.class).asCollection(OptionConfig.class);
        assertNotNull(config);
        assertEquals(3, config.size());
        assertEquals("X", config.iterator().next().value());
    }

    @Test
    public void testConfigFolderTypeOptions() {
        context.currentResource("/content/example/form/folderType");

        Resource resource = context.request().getResource();
        Collection<OptionConfig> config = resource.adaptTo(ConfigurationBuilder.class).asCollection(OptionConfig.class);
        assertNotNull(config);
        assertEquals(3, config.size());
        assertEquals("article", config.iterator().next().value());
    }
}
