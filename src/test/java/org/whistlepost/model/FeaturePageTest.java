package org.whistlepost.model;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.factory.ModelFactory;
import org.apache.sling.testing.mock.sling.ResourceResolverType;
import org.apache.sling.testing.mock.sling.junit5.SlingContextExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(SlingContextExtension.class)
class FeaturePageTest extends AbstractModelTest {

    public FeaturePageTest() {
        super(ResourceResolverType.JCR_OAK);
    }

    @Test
    public void testPage() {
        context.load().json("/content/site/index.json", "/content/site/index");
        context.load().json("/content/defaults.json", "/content/defaults");
        context.load().json("/content/branding.json", "/content/branding");
        context.load().json("/content/navigation.json", "/content/navigation");
        context.load().json("/content/articles.json", "/content/articles");
        context.load().json("/content/articles/list.json", "/content/articles/list");
        context.load().json("/content/articles/list/one.json", "/content/articles/list/one");
        context.load().json("/content/articles/list/two.json", "/content/articles/list/two");

        Resource resource = context.currentResource("/content/site/index");
        FeaturePage page = context.getService(ModelFactory.class).createModel(resource, FeaturePage.class);
        Assertions.assertEquals("My Example Site", page.getTitle());
        Assertions.assertNotNull(page.getFeature());
        Assertions.assertNotNull(page.getArticles());
        Assertions.assertEquals(2, page.getArticles().getCount());
    }
}
