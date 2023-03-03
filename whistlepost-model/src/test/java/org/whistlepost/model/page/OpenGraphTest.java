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
class OpenGraphTest extends AbstractModelTest {

    public OpenGraphTest() {
        super(ResourceResolverType.JCR_OAK);
    }

    @Test
    public void testPage() {
        Resource defaults = context.load().json("/content/defaults.json", "/content/defaults");
        context.load().json("/content/site/index.json", "/content/site/index");

        Resource resource = context.resourceResolver().getResource("/content/site/index");
        OpenGraph page = context.getService(ModelFactory.class).createModel(resource, OpenGraph.class);
        Assertions.assertEquals("My Example Site", page.getTitle());
        Assertions.assertEquals("My amazing site for testing Whistlepost", page.getDescription());
    }
}
