package org.whistlepost.model;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.factory.ModelFactory;
import org.apache.sling.testing.mock.sling.ResourceResolverType;
import org.apache.sling.testing.mock.sling.junit5.SlingContextExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(SlingContextExtension.class)
class PageDefaultsTest extends AbstractModelTest {

    public PageDefaultsTest() {
        super(ResourceResolverType.JCR_OAK);
    }

    @Test
    public void testPage() {
        context.load().json("/content/navigation.json", "/content/navigation");
        context.load().json("/content/advertisements.json", "/content/advertisements");
        context.load().json("/content/advertisements/list.json", "/content/advertisements/list");

        Resource resource = context.currentResource(context.create().resource("/content/site/en"));
        PageDefaults model = context.getService(ModelFactory.class).createModel(resource, PageDefaults.class);
        Assertions.assertNotNull(model.getLayout().getSidebar().getPath());
        Assertions.assertNotNull(model.getLayout().getNavigation().getPath());
        Assertions.assertNotNull(model.getBranding());
        Assertions.assertNotNull(model.getLegal());
        Assertions.assertEquals("Foo Bar", model.getMetadata().getAuthor());
    }
}
