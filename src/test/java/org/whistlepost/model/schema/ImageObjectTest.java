package org.whistlepost.model.schema;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.factory.ModelFactory;
import org.apache.sling.testing.mock.sling.junit5.SlingContextExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.whistlepost.model.AbstractModelTest;

@ExtendWith(SlingContextExtension.class)
class ImageObjectTest extends AbstractModelTest {

    @Test
    public void testModelLoading() {
        context.load().json("/content/gallery/list/2022-05-26.json", "/content/gallery/list/2022-05-26");

        Resource resource = context.resourceResolver().getResource("/content/gallery/list/2022-05-26");
        ImageObject model = context.getService(ModelFactory.class).createModel(resource, ImageObject.class);
        Assertions.assertEquals("2022 05 26", model.getTitle());
        Assertions.assertNotNull(model.getImage());
    }
}