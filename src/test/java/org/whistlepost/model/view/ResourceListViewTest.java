package org.whistlepost.model.view;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.factory.ModelFactory;
import org.apache.sling.testing.mock.sling.junit5.SlingContextExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.whistlepost.model.AbstractModelTest;

import java.util.Arrays;
import java.util.stream.Collectors;

@ExtendWith(SlingContextExtension.class)
class ResourceListViewTest extends AbstractModelTest {

    @Test
    public void testResourceList() {
        context.load().json("/content/list/resourceList.json", "/content/list/resourceList");

        Resource resource = context.resourceResolver().getResource("/content/list/resourceList");
        ResourceListView resourceList = context.getService(ModelFactory.class).createModel(resource, ResourceListView.class);
        Assertions.assertEquals(Arrays.asList("one", "two"), resourceList.getListItems().stream().map(Resource::getName).collect(Collectors.toList()));
    }
}
