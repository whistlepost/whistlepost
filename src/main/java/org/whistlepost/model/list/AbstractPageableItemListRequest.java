package org.whistlepost.model.list;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.caconfig.annotations.ContextAwareConfiguration;
import org.whistlepost.caconfig.site.ListConfig;

import javax.inject.Inject;

public abstract class AbstractPageableItemListRequest<T extends Comparable<? super T>> implements PageableFilteredList<T>,
        PageableSortedList<T>, PageableShuffledList<T> {

    @SlingObject
    private SlingHttpServletRequest request;

    @ChildResource(name = "list") @Via("resource")
    private Resource listResource;

    @Inject @Optional @Via("resource")
    private String filter;

    @ContextAwareConfiguration
    private ListConfig listConfig;

    @Override
    public Resource getListResource() {
        return listResource;
    }

    @Override
    public String getFilter() {
        return filter;
    }

    @Override
    public String getItemListOrder() {
        return listConfig.itemListOrder();
    }

    @Override
    public int getPageSize() {
        return listConfig.pageSize();
    }

    @Override
    public int getCurrentPage() {
        return PagingSupport.getPageNumber(request);
    }
}
