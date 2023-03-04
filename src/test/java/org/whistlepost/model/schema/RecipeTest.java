package org.whistlepost.model.schema;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.factory.ModelFactory;
import org.apache.sling.testing.mock.sling.junit5.SlingContextExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.whistlepost.model.AbstractModelTest;

@ExtendWith(SlingContextExtension.class)
class RecipeTest extends AbstractModelTest {

    @Test
    public void testModelLoading() {
        context.load().json("/content/recipes/list/cheesey-corn-muffins.json", "/content/recipes/list/cheesey-corn-muffins");

        Resource resource = context.resourceResolver().getResource("/content/recipes/list/cheesey-corn-muffins");
        Recipe model = context.getService(ModelFactory.class).createModel(resource, Recipe.class);
        Assertions.assertEquals("Cheesey Corn Muffins", model.getTitle());
        Assertions.assertNull(model.getSidebar());
        Assertions.assertNull(model.getRelated());
    }

}