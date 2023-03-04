package org.whistlepost.model;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.factory.ModelFactory;
import org.apache.sling.testing.mock.sling.junit5.SlingContextExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(SlingContextExtension.class)
class FormTest extends AbstractModelTest {

    @Test
    public void testPage() {
        context.load().json("/content/form.json", "/content/form");
        context.load().json("/content/form/fieldsets/default.json", "/content/form/fieldsets/default");
        context.load().json("/content/site/index.json", "/content/site/index");
        context.load().json("/content/form/select/gender.json", "/content/form/select/gender");

        Resource resource = context.currentResource("/content/form");
        Form form = context.getService(ModelFactory.class).createModel(resource, Form.class);
        Assertions.assertEquals("get", form.getSubmit().method());
        Assertions.assertEquals(1, form.getFieldSets().size());
        Assertions.assertEquals("gender", form.getFieldSets().get(0).getFields().get(0).getName());
//        Assertions.assertNotNull(form.getSubmit().action());
    }
}
