package org.whistlepost.model.list;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.factory.ModelFactory;
import org.apache.sling.testing.mock.sling.ResourceResolverType;
import org.apache.sling.testing.mock.sling.junit5.SlingContextExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.whistlepost.model.AbstractModelTest;

@ExtendWith(SlingContextExtension.class)
class ListContainerTest extends AbstractModelTest {

    public ListContainerTest() {
        super(ResourceResolverType.JCR_OAK);
    }

    @Test
    public void testResourceList() {
        context.load().json("/content/list/resourceList.json", "/content/list/resourceList");

        Resource resource = context.currentResource("/content/list/resourceList");
        ListContainer<?> resourceList = context.getService(ModelFactory.class).createModel(resource, ListContainer.class);
        Assertions.assertEquals("/content/list/resourceList/list", resourceList.getListResource().getPath());
    }
}
