package org.whistlepost.model.schema;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.factory.ModelFactory;
import org.apache.sling.testing.mock.sling.ResourceResolverType;
import org.apache.sling.testing.mock.sling.junit5.SlingContextExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.whistlepost.model.AbstractModelTest;

@ExtendWith(SlingContextExtension.class)
class ImageObjectListTest extends AbstractModelTest {

    public ImageObjectListTest() {
        super(ResourceResolverType.JCR_OAK);
    }

    @Test
    public void testModelLoading() {
        context.load().json("/content/gallery.json", "/content/gallery");
        context.load().json("/content/gallery/list.json", "/content/gallery/list");
        context.load().json("/content/gallery/list/2022-05-26.json", "/content/gallery/list/2022-05-26");
        context.load().json("/content/gallery/list/2022-06-23.json", "/content/gallery/list/2022-06-23");
        context.load().json("/content/defaults.json", "/content/defaults");

        Resource resource = context.resourceResolver().getResource("/content/gallery");
        ImageObjectList model = context.getService(ModelFactory.class).createModel(resource, ImageObjectList.class);
        Assertions.assertEquals(2, model.getCount());
        Assertions.assertEquals(1, model.getFilteredCount());
    }
}
