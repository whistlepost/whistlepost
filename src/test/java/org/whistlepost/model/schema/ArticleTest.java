package org.whistlepost.model.schema;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.factory.ModelFactory;
import org.apache.sling.testing.mock.sling.junit5.SlingContextExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.whistlepost.model.AbstractModelTest;

@ExtendWith(SlingContextExtension.class)
class ArticleTest extends AbstractModelTest {

    @Test
    public void testModelLoading() {
        context.load().json("/content/articles/list/one.json", "/content/articles/list/one");
        context.load().json("/content/articles/list/two.json", "/content/articles/list/two");
        context.load().json("/content/articles/list/sidebar.json", "/content/articles/list/sidebar");
        context.load().json("/content/defaults.json", "/content/defaults");

        Resource resource = context.resourceResolver().getResource("/content/articles/list/one");
        Article model = context.getService(ModelFactory.class).createModel(resource, Article.class);
        Assertions.assertEquals("Article One", model.getTitle());
        Assertions.assertNotNull(model.getSidebar());
        Assertions.assertEquals(1, model.getRelated().size());
    }

}