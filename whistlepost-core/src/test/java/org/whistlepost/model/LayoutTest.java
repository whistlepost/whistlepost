package org.whistlepost.model;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.factory.ModelFactory;
import org.apache.sling.testing.mock.sling.junit5.SlingContext;
import org.apache.sling.testing.mock.sling.junit5.SlingContextExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(SlingContextExtension.class)
class LayoutTest {

    private final SlingContext context = new SlingContext();

    @BeforeEach
    public void setup() {
        context.addModelsForPackage("org.whistlepost.model");
    }

    @Test
    public void testLayout() {
        context.load().json("/content/layout.json", "/content/layout");
        context.load().json("/content/navigation.json", "/content/navigation");

        Resource resource = context.resourceResolver().getResource("/content/layout");
        Layout layout = context.getService(ModelFactory.class).createModel(resource, Layout.class);
        Assertions.assertNotNull(layout.getHeader());
    }
}