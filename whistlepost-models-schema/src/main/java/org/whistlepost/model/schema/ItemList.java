package org.whistlepost.model.schema;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.*;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.whistlepost.model.SortedList;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.Comparator;

import static org.apache.sling.models.annotations.injectorspecific.InjectionStrategy.OPTIONAL;

/**
 * Sling models implementation of <a href="https://schema.org/ItemList">Schema.org ItemList</a>.
 */
@Model(adaptables = {Resource.class, SlingHttpServletRequest.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public abstract class ItemList<T extends Thing> extends Thing implements SortedList<T> {

    @Inject
    private String itemListOrder;

    @Inject @Optional
    private String filter;

    @Inject @Default(intValues = DEFAULT_PAGE_SIZE)
    private int pageSize;

    private int currentPage;

    @ChildResource(name = "list") @Required
    private Resource listResource;

    @SlingObject(injectionStrategy = OPTIONAL)
    protected SlingHttpServletRequest request;

    @PostConstruct
    protected void init() {
        if (request != null) {
            try {
                String[] directives = request.getRequestPathInfo().getSuffix().substring(1).split("-");
                if (directives.length > 1) {
                    currentPage = Integer.parseInt(directives[0]);
//                    limit = Integer.parseInt(directives[1]);
                } else {
                    currentPage = Integer.parseInt(directives[0]);
//                    limit = -1;
                }
            } catch (RuntimeException e) {
//                currentPage = 1;
//                limit = -1;
            }
        } else {
            currentPage = 1;
        }
    }

    @Override
    public Resource getListResource() {
        return listResource;
    }

    public Iterable<T> getItemListElements() {
        return getItems();
    }

    public String getItemListOrder() {
        return itemListOrder;
    }

    public int getNumberOfItems() {
        return getItemCount();
    }

    @Override
    public String getFilter() {
        return filter;
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    @Override
    public int getCurrentPage() {
        return currentPage;
    }

    @Override
    public Comparator<T> getComparator() {
        Comparator<T> comparator = null;
        if ("Ascending".equals(itemListOrder)) {
            comparator = Comparator.naturalOrder();
        } else if ("Descending".equals(itemListOrder)) {
            comparator = Comparator.reverseOrder();
        }
        return comparator;
    }
}
