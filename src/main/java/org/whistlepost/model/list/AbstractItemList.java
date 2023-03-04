package org.whistlepost.model.list;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.caconfig.annotations.ContextAwareConfiguration;
import org.whistlepost.caconfig.site.ListConfig;

import javax.inject.Inject;

public abstract class AbstractItemList<T extends Comparable<? super T>> implements FilteredListContainer<T>,
        SortedListContainer<T>, ShuffledListContainer<T> {

    @ChildResource(name = "list")
    private Resource listResource;

    @Inject @Optional
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
}
