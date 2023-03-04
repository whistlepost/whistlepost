package org.whistlepost.model.form;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.factory.ModelFactory;
import org.apache.sling.testing.mock.sling.junit5.SlingContextExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.whistlepost.model.AbstractModelTest;

@ExtendWith(SlingContextExtension.class)
class SelectTest extends AbstractModelTest {

    @Override
    @BeforeEach
    public void setup() {
        super.setup();

        context.load().json("/conf/site/form/select/gender.json", "/conf/site/form/select/gender");
        context.load().json("/content/form/select/gender.json", "/content/form/select/gender");
    }

    @Test
    public void testSelect() {
        Resource resource = context.currentResource("/content/form/select/gender");
        Select select = context.getService(ModelFactory.class).createModel(resource, Select.class);

        Assertions.assertEquals("gender", select.getLabelKey());
        Assertions.assertEquals(3, select.getOptions().size());
        Assertions.assertEquals("Not Specified", select.getOptions().get(0).text());
    }
}