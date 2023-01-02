package org.whistelpost.config.form;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.caconfig.ConfigurationBuilder;
import org.apache.sling.testing.mock.sling.junit5.SlingContextExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.whistelpost.config.AbstractConfigTest;
import org.whistlepost.config.form.RadioConfig;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SlingContextExtension.class)
public class RadioConfigTest extends AbstractConfigTest {

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
        RadioConfig config = resource.adaptTo(ConfigurationBuilder.class).as(RadioConfig.class);
        assertNotNull(config);
        assertArrayEquals(new String[] {"True", "False"}, config.values());
    }

    @Test
    public void testConfigOnoffOptions() {
        context.currentResource("/content/example/form/onoff");

        Resource resource = context.request().getResource();
        RadioConfig config = resource.adaptTo(ConfigurationBuilder.class).as(RadioConfig.class);
        assertNotNull(config);
        assertArrayEquals(new String[] {"On", "Off"}, config.values());
    }

    @Test
    public void testConfigYesnoOptions() {
        context.currentResource("/content/example/form/yesno");

        Resource resource = context.request().getResource();
        RadioConfig config = resource.adaptTo(ConfigurationBuilder.class).as(RadioConfig.class);
        assertNotNull(config);
        assertArrayEquals(new String[] {"Yes", "No"}, config.values());
    }

    @Test
    public void testConfigGenderOptions() {
        context.currentResource("/content/example/form/gender");

        Resource resource = context.request().getResource();
        RadioConfig config = resource.adaptTo(ConfigurationBuilder.class).as(RadioConfig.class);
        assertNotNull(config);
        assertArrayEquals(new String[] {"Not Specified", "Female", "Male"}, config.values());
    }

    @Test
    public void testConfigFolderTypeOptions() {
        context.currentResource("/content/example/form/folderType");

        Resource resource = context.request().getResource();
        RadioConfig config = resource.adaptTo(ConfigurationBuilder.class).as(RadioConfig.class);
        assertNotNull(config);
        assertArrayEquals(new String[] {"Article", "Recipe", "Subscription"}, config.values());
    }
}
