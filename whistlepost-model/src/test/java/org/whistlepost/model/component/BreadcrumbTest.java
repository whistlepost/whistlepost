package org.whistlepost.model.component;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.factory.ModelFactory;
import org.apache.sling.testing.mock.sling.junit5.SlingContextExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.whistlepost.model.AbstractModelTest;

@ExtendWith(SlingContextExtension.class)
class BreadcrumbTest extends AbstractModelTest {

    @Test
    public void testModelMapping() {
        context.load().json("/content/component/breadcrumb.json", "/content/component/breadcrumb");

        Resource resource = context.currentResource("/content/component/breadcrumb/child1/child2/child3");

        Breadcrumb model = context.getService(ModelFactory.class).createModel(resource, Breadcrumb.class);
        Assertions.assertEquals(4, model.getPathSegments().size());
        Assertions.assertEquals("/content/component", model.getPathSegments().get(0).getPath());
        Assertions.assertEquals("component", model.getPathSegments().get(0).getName());
        Assertions.assertEquals("/content/component/breadcrumb/child1/child2", model.getPathSegments().get(3).getPath());
        Assertions.assertEquals("child2", model.getPathSegments().get(3).getName());
    }
}
