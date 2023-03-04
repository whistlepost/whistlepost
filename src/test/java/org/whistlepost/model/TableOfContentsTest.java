package org.whistlepost.model;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.factory.ModelFactory;
import org.apache.sling.testing.mock.sling.junit5.SlingContextExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(SlingContextExtension.class)
class TableOfContentsTest extends AbstractModelTest {

    @Test
    public void testPage() {
        context.load().json("/content/advertisements.json", "/content/advertisements");
        context.load().json("/content/articles.json", "/content/articles");
        context.load().json("/content/gallery.json", "/content/gallery");
        context.load().json("/content/recipes.json", "/content/recipes");
        context.load().json("/content/toc.json", "/content/toc");

        Resource resource = context.resourceResolver().getResource("/content/toc");
        TableOfContents model = context.getService(ModelFactory.class).createModel(resource, TableOfContents.class);
        Assertions.assertEquals(4, model.getPages().size());
    }
}
