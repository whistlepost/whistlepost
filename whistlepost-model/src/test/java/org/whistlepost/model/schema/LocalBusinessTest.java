package org.whistlepost.model.schema;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.factory.ModelFactory;
import org.apache.sling.testing.mock.sling.junit5.SlingContextExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.whistlepost.model.AbstractModelTest;

@ExtendWith(SlingContextExtension.class)
class LocalBusinessTest extends AbstractModelTest {

    @Test
    public void testModelLoading() {
        context.load().json("/content/advertisements/list/balloonsandstuff.json", "/content/advertisements/list/balloonsandstuff");

        Resource resource = context.resourceResolver().getResource("/content/advertisements/list/balloonsandstuff");
        LocalBusiness model = context.getService(ModelFactory.class).createModel(resource, LocalBusiness.class);
        Assertions.assertEquals("Balloonsandstuff Shop 21", model.getTitle());
        Assertions.assertNotNull(model.getImage());
    }

}