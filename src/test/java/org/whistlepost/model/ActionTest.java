package org.whistlepost.model;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.factory.ModelFactory;
import org.apache.sling.testing.mock.sling.junit5.SlingContextExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(SlingContextExtension.class)
class ActionTest extends AbstractModelTest {

    @Test
    public void testAction() {
        context.load().json("/content/form.json", "/content/form");
        context.load().json("/content/form/fieldsets/default.json", "/content/form/fieldsets/default");
        context.load().json("/content/form/select/gender.json", "/content/form/select/gender");

        context.create().resource("/content/example/index");
        Resource resource = context.currentResource(context.create().resource("/content/example/index/edit",
                "form", "/content/form"));

        Action model = context.getService(ModelFactory.class).createModel(resource, Action.class);
        Assertions.assertNotNull(model.getFieldSets());
        Assertions.assertEquals("/content/example/index", model.getTarget().getPath());
    }
}
