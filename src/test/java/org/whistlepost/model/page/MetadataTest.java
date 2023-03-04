package org.whistlepost.model.page;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.factory.ModelFactory;
import org.apache.sling.testing.mock.sling.ResourceResolverType;
import org.apache.sling.testing.mock.sling.junit5.SlingContextExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.whistlepost.model.AbstractModelTest;

@ExtendWith(SlingContextExtension.class)
class MetadataTest extends AbstractModelTest {

    public MetadataTest() {
        super(ResourceResolverType.JCR_OAK);
    }

    @Test
    public void testPage() {
        context.load().json("/content/site/index.json", "/content/site/index");

        Resource resource = context.resourceResolver().getResource("/content/site/index");
        Metadata page = context.getService(ModelFactory.class).createModel(resource, Metadata.class);
        Assertions.assertEquals("My amazing site for testing Whistlepost", page.getDescription());
        Assertions.assertEquals("Foo Bar", page.getAuthor());
        Assertions.assertEquals(3, page.getKeywords().size());
        Assertions.assertNotNull(page.getViewport());
        Assertions.assertNotNull(page.getOpenGraph());
    }
}
