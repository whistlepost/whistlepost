package org.whistlepost.model;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.factory.ModelFactory;
import org.apache.sling.testing.mock.sling.junit5.SlingContextExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(SlingContextExtension.class)
class PageTest extends AbstractModelTest {

    @Test
    public void testPage() {
        context.load().json("/content/site/index.json", "/content/site/index");
        context.load().json("/content/defaults.json", "/content/defaults");
        context.load().json("/content/navigation.json", "/content/navigation");

        Resource resource = context.currentResource("/content/site/index");
        Page page = context.getService(ModelFactory.class).createModel(resource, Page.class);
        Assertions.assertEquals("My Example Site", page.getTitle());
        Assertions.assertEquals("Site Defaults", page.getMetadata().title());
        Assertions.assertNotNull(page.getBranding());
        Assertions.assertEquals("/assets/wnews/favicon.ico", page.getBranding().favicon());
        Assertions.assertEquals(2, page.getSections().size());
    }
}
