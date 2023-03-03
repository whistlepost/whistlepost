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
class LocalBusinessListTest extends AbstractModelTest {

    public LocalBusinessListTest() {
        super(ResourceResolverType.JCR_OAK);
    }

    @Test
    public void testModelLoading() {
        context.load().json("/content/advertisements.json", "/content/advertisements");
        context.load().json("/content/advertisements/list.json", "/content/advertisements/list");
        context.load().json("/content/advertisements/list/balloonsandstuff.json", "/content/advertisements/list/balloonsandstuff");
        context.load().json("/content/advertisements/list/the-spin-doctor.json", "/content/advertisements/list/the-spin-doctor");

        Resource resource = context.resourceResolver().getResource("/content/advertisements");
        LocalBusinessList model = context.getService(ModelFactory.class).createModel(resource, LocalBusinessList.class);
        Assertions.assertEquals(2, model.getCount());
        Assertions.assertEquals(1, model.getFilteredCount());
    }
}
