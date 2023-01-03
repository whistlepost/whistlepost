package org.whistlepost.model;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.factory.ModelFactory;
import org.apache.sling.testing.mock.sling.junit5.SlingContextExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(SlingContextExtension.class)
class FolderTest extends AbstractModelTest {

    @Override
    @BeforeEach
    public void setup() {
        super.setup();

        context.load().json("/conf/site/folder/subscription.json", "/conf/site/folder/subscription");
        context.create().resource("/content/subscriptions",
                "sling:configRef", "/conf/site/folder/subscription");
        context.create().resource("/content/subscriptions/one", "title", "Test One");
    }

    @Test
    public void testSelect() {
        Resource resource = context.currentResource("/content/subscriptions/one");
        Page folder = context.getService(ModelFactory.class).createModel(resource, Page.class);

        Assertions.assertEquals(4, folder.getActions().size());
        Assertions.assertEquals("edit", folder.getActions().get(0).path());
    }
}