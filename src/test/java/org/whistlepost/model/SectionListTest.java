package org.whistlepost.model;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.factory.ModelFactory;
import org.apache.sling.testing.mock.sling.junit5.SlingContextExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(SlingContextExtension.class)
class SectionListTest extends AbstractModelTest {

    @Test
    public void testPage() {
        context.load().json("/content/site/index.json", "/content/site/index");
        context.load().json("/content/defaults.json", "/content/defaults");
        context.load().json("/content/navigation.json", "/content/navigation");

        Resource resource = context.currentResource("/content/site/index");
        SectionList model = context.getService(ModelFactory.class).createModel(resource, SectionList.class);
        Assertions.assertEquals(2, model.getSections().size());
    }
}
