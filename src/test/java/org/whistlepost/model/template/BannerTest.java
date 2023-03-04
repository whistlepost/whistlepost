package org.whistlepost.model.template;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.factory.ModelFactory;
import org.apache.sling.testing.mock.sling.junit5.SlingContextExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.whistlepost.model.AbstractModelTest;
import org.whistlepost.model.ExternalImage;

@ExtendWith(SlingContextExtension.class)
class BannerTest extends AbstractModelTest {

    @Test
    public void testModelMapping() {
        context.load().json("/content/template/banner.json", "/content/template/banner");

        Resource resource = context.currentResource("/content/template/banner");

        ExternalImage model = context.getService(ModelFactory.class).createModel(resource, ExternalImage.class);
        Assertions.assertEquals("http://example.com/banner.png", model.getSource());
        Assertions.assertEquals("Example Banner image", model.getAltText());
        Assertions.assertEquals("Example Banner", model.getTitle());
        Assertions.assertEquals("wp-pure", model.getTheme().name());
    }

    @Test
    public void testModelMappingWithConfig() {
        context.load().json("/conf/template/banner.json", "/conf/template/banner");
        context.load().json("/content/template/banner.json", "/content/template/banner");

        Resource resource = context.currentResource("/content/template/banner");

        ExternalImage model = context.getService(ModelFactory.class).createModel(resource, ExternalImage.class);
        Assertions.assertEquals("http://example.com/banner.png", model.getSource());
        Assertions.assertEquals("Example Banner image", model.getAltText());
        Assertions.assertEquals("Example Banner", model.getTitle());
        Assertions.assertEquals("wp-bootstrap", model.getTheme().name());
    }
}
