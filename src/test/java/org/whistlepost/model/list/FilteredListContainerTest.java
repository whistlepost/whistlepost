package org.whistlepost.model.list;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.factory.ModelFactory;
import org.apache.sling.testing.mock.sling.junit5.SlingContextExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.whistlepost.model.AbstractModelTest;

@ExtendWith(SlingContextExtension.class)
class FilteredListContainerTest extends AbstractModelTest {

    @Test
    public void testFeedLoader() {
        context.load().json("/content/filtered.json", "/content/filtered");

        Resource resource = context.resourceResolver().getResource("/content/filtered");
        FilteredListContainer<?> filteredList = context.getService(ModelFactory.class).createModel(resource, FilteredListContainer.class);
//        Assertions.assertEquals("Filtered Content", filteredList.getTitle());
//        Assertions.assertEquals(3, filteredList.getItemCount());
    }

}