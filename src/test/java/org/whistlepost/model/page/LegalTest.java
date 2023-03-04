package org.whistlepost.model.page;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.factory.ModelFactory;
import org.apache.sling.testing.mock.sling.junit5.SlingContextExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.whistlepost.model.AbstractModelTest;
import org.whistlepost.model.site.Legal;

@ExtendWith(SlingContextExtension.class)
class LegalTest extends AbstractModelTest {

    @Test
    public void testLayout() {
        context.load().json("/content/defaults.json", "/content/defaults");

        Resource resource = context.resourceResolver().getResource("/content/defaults");
        Legal model = context.getService(ModelFactory.class).createModel(resource, Legal.class);
        Assertions.assertEquals("Waranga News 2022", model.getCopyright());
    }
}
