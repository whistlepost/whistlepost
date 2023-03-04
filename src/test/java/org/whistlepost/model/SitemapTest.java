package org.whistlepost.model;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.factory.ModelFactory;
import org.apache.sling.testing.mock.sling.junit5.SlingContextExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(SlingContextExtension.class)
class SitemapTest extends AbstractModelTest {

    @Test
    public void testPage() {
        context.load().json("/content/site/sitemap.json", "/content/site/sitemap");

        Resource resource = context.currentResource("/content/site/sitemap");
        Sitemap model = context.getService(ModelFactory.class).createModel(resource, Sitemap.class);
        Assertions.assertEquals("https://www.example.com/", model.getConfig().urlBase());
        Assertions.assertEquals("weekly", model.getConfig().changeFrequency());
    }
}
