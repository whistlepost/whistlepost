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
class ArticleListTest extends AbstractModelTest {

    public ArticleListTest() {
        super(ResourceResolverType.JCR_OAK);
    }

    @Test
    public void testModelLoading() {
        context.load().json("/content/articles.json", "/content/articles");
        context.load().json("/content/articles/list.json", "/content/articles/list");
        context.load().json("/content/articles/list/one.json", "/content/articles/list/one");
        context.load().json("/content/articles/list/two.json", "/content/articles/list/two");
        context.load().json("/content/articles/list/sidebar.json", "/content/articles/list/sidebar");
        context.load().json("/content/defaults.json", "/content/defaults");

        Resource resource = context.resourceResolver().getResource("/content/articles");
        ArticleList model = context.getService(ModelFactory.class).createModel(resource, ArticleList.class);
        Assertions.assertEquals(3, model.getCount());
        Assertions.assertEquals(2, model.getFilteredCount());
    }
}
