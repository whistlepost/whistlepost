package org.whistlepost.model;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.factory.ModelFactory;
import org.apache.sling.testing.mock.sling.ResourceResolverType;
import org.apache.sling.testing.mock.sling.junit5.SlingContextExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(SlingContextExtension.class)
class PageListTest extends AbstractModelTest {

    public PageListTest() {
        super(ResourceResolverType.JCR_OAK);
    }

    @Test
    public void testPageList() {
        context.load().json("/content/page-list.json", "/content/page-list");
        context.load().json("/content/defaults.json", "/content/defaults");

        Resource resource = context.resourceResolver().getResource("/content/page-list");
        PageList model = context.getService(ModelFactory.class).createModel(resource, PageList.class);
        Assertions.assertNotNull(model.getListItems());
        Assertions.assertEquals(1, model.getCount());
        Assertions.assertEquals("Child One", model.getListItems().get(0).getTitle());
    }
}
