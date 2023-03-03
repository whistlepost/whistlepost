package org.whistlepost.model.page;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.factory.ModelFactory;
import org.apache.sling.testing.mock.sling.junit5.SlingContextExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.whistlepost.model.AbstractModelTest;
import org.whistlepost.model.Page;

@ExtendWith(SlingContextExtension.class)
class LayoutTest extends AbstractModelTest {

    @Test
    public void testLayout() {
        context.load().json("/content/site/index.json", "/content/site/index");
        context.load().json("/content/defaults.json", "/content/defaults");
        context.load().json("/content/navigation.json", "/content/navigation");

        Resource resource = context.currentResource("/content/site/index");
        Page page = context.getService(ModelFactory.class).createModel(resource, Page.class);
        Assertions.assertEquals("/content/site/index", page.getLayout().header());
        Assertions.assertNotNull(page.getLayout().navigation());
    }
}