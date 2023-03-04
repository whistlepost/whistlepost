package org.whistlepost.model;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.factory.ModelFactory;
import org.apache.sling.testing.mock.sling.junit5.SlingContextExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(SlingContextExtension.class)
class SiteDefaultsTest extends AbstractModelTest {

    @Test
    public void testPage() {
        context.load().json("/content/defaults.json", "/content/defaults");
        context.load().json("/content/branding.json", "/content/branding");
        context.load().json("/content/navigation.json", "/content/navigation");
        context.load().json("/content/advertisements.json", "/content/advertisements");
        context.load().json("/content/legal.json", "/content/legal");

        Resource resource = context.resourceResolver().getResource("/content/defaults");
        SiteDefaults model = context.getService(ModelFactory.class).createModel(resource, SiteDefaults.class);
        Assertions.assertNotNull(model.getLayout().getHeader().getPath());
        Assertions.assertNotNull(model.getLayout().getNavigation().getPath());
        Assertions.assertNotNull(model.getBranding());
        Assertions.assertNotNull(model.getLegal());
    }
}
