package org.whistlepost.model.schema;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.factory.ModelFactory;
import org.apache.sling.testing.mock.sling.ResourceResolverType;
import org.apache.sling.testing.mock.sling.junit5.SlingContextExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.whistlepost.model.AbstractModelTest;

@ExtendWith(SlingContextExtension.class)
class RecipeListTest extends AbstractModelTest {

    public RecipeListTest() {
        super(ResourceResolverType.JCR_OAK);
    }

    @Test
    public void testModelLoading() {
        context.load().json("/content/recipes.json", "/content/recipes");
        context.load().json("/content/recipes/list.json", "/content/recipes/list");
        context.load().json("/content/recipes/list/cheesey-corn-muffins.json", "/content/recipes/list/cheesey-corn-muffins");
        context.load().json("/content/recipes/list/easy-one-cup-slice.json", "/content/recipes/list/easy-one-cup-slice");

        Resource resource = context.resourceResolver().getResource("/content/recipes");
        RecipeList model = context.getService(ModelFactory.class).createModel(resource, RecipeList.class);
        Assertions.assertEquals(2, model.getCount());
        Assertions.assertEquals(1, model.getFilteredCount());
    }
}
