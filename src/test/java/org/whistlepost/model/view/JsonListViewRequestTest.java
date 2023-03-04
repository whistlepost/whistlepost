package org.whistlepost.model.view;

import org.apache.sling.models.factory.ModelFactory;
import org.apache.sling.testing.mock.sling.ResourceResolverType;
import org.apache.sling.testing.mock.sling.junit5.SlingContextExtension;
import org.apache.sling.testing.mock.sling.servlet.MockSlingHttpServletRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.whistlepost.model.AbstractModelTest;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@ExtendWith(SlingContextExtension.class)
class JsonListViewRequestTest extends AbstractModelTest {

    public JsonListViewRequestTest() {
        super(ResourceResolverType.JCR_OAK);
    }

    @Test
    public void testPageList() {
        context.load().json("/content/views/jsonpath.json", "/content/views/jsonpath");

        context.currentResource("/content/views/jsonpath");
        MockSlingHttpServletRequest request = context.request();
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("json", new String[]{"{\"child\":\"one\"}"});
        request.setParameterMap(parameters);
        JsonListViewRequest model = context.getService(ModelFactory.class).createModel(request, JsonListViewRequest.class);
        Assertions.assertNotNull(model.getListItems());
        Assertions.assertEquals(Collections.singletonList("one"), model.getListItems());
    }
}
