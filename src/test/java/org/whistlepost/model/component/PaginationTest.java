package org.whistlepost.model.component;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.factory.ModelFactory;
import org.apache.sling.testing.mock.sling.ResourceResolverType;
import org.apache.sling.testing.mock.sling.junit5.SlingContextExtension;
import org.apache.sling.testing.mock.sling.servlet.MockRequestPathInfo;
import org.apache.sling.testing.mock.sling.servlet.MockSlingHttpServletRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.whistlepost.model.AbstractModelTest;

@ExtendWith(SlingContextExtension.class)
class PaginationTest extends AbstractModelTest {

    public PaginationTest() {
        super(ResourceResolverType.JCR_OAK);
    }

    @Test
    public void testModelMapping() {
        context.load().json("/content/component/paging.json", "/content/component/paging");

        context.currentResource("/content/component/paging");
        SlingHttpServletRequest request = context.request();

        PageListRequest model = context.getService(ModelFactory.class).createModel(request, PageListRequest.class);
        Assertions.assertEquals(6, model.getPageSize());
        Assertions.assertEquals(4, model.getCount());
        Assertions.assertEquals(1, model.getPageCount());
    }

    @Test
    public void testModelMappingWithConfig() {
        context.load().json("/conf/component/paging.json", "/conf/component/paging");
        context.load().json("/content/component/paging.json", "/content/component/paging");

        context.currentResource("/content/component/paging");
        SlingHttpServletRequest request = context.request();

        PageListRequest model = context.getService(ModelFactory.class).createModel(request, PageListRequest.class);
        Assertions.assertEquals(3, model.getPageSize());
        Assertions.assertEquals(4, model.getCount());
        Assertions.assertEquals(2, model.getPageCount());
        Assertions.assertEquals(1, model.getCurrentPage());
        Assertions.assertEquals(true, model.hasNext());
        Assertions.assertEquals(false, model.hasPrevious());
    }

    @Test
    public void testPageList() {
        context.load().json("/content/articles.json", "/content/articles");
        context.load().json("/content/articles/list.json", "/content/articles/list");
        context.load().json("/content/articles/list/one.json", "/content/articles/list/one");
        context.load().json("/content/articles/list/two.json", "/content/articles/list/two");
        context.load().json("/content/articles/list/sidebar.json", "/content/articles/list/sidebar");
        context.load().json("/content/defaults.json", "/content/defaults");

        context.currentResource("/content/articles");
        MockSlingHttpServletRequest request = context.request();
        ((MockRequestPathInfo) request.getRequestPathInfo()).setSuffix("/1");
        PageListRequest articles = context.getService(ModelFactory.class).createModel(request, PageListRequest.class);
        Assertions.assertNotNull(articles.getListItems());
        Assertions.assertEquals(6, articles.getPageSize());
        Assertions.assertEquals(1, articles.getPageCount());
        Assertions.assertEquals(1, articles.getCurrentPage());
        Assertions.assertEquals(false, articles.hasNext());
        Assertions.assertEquals(false, articles.hasPrevious());
        Assertions.assertEquals("Article One", articles.getPageItems().iterator().next().getTitle());
    }
}
