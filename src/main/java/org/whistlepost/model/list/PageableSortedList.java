package org.whistlepost.model.list;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import java.util.Comparator;
import java.util.List;

@Model(adaptables = {Resource.class})
public interface PageableSortedList<T extends Comparable<? super T>> extends SortedListContainer<T>,
        PageableResourceList<T> {

    default Iterable<T> getSortedPage() {
        Comparator<T> comparator = getComparator();
        if (comparator != null) {
            List<T> items = getListItems();
            items.sort(comparator);
            return items.subList((getCurrentPage() - 1) * getPageSize(),
                    (getCurrentPage() - 1) * getPageSize() + Math.min(getPageSize(), items.size()));
        } else {
            return getPageItems();
        }
    }
}
