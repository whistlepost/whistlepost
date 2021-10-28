package org.whistlepost.model;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import java.util.Comparator;
import java.util.List;

@Model(adaptables = {Resource.class})
public interface SortedList<T> extends PageableList<T> {

    Comparator<T> getComparator();

    default Iterable<T> getSortedItems() {
        Comparator<T> comparator = getComparator();
        if (comparator != null) {
            List<T> items = toList(getItems());
            items.sort(comparator);
            return items;
        } else {
            return getItems();
        }
    }

    default Iterable<T> getSortedPage() {
        Comparator<T> comparator = getComparator();
        if (comparator != null) {
            List<T> items = toList(getItems());
            items.sort(comparator);
            return items.subList((getCurrentPage() - 1) * getPageSize(),
                    (getCurrentPage() - 1) * getPageSize() + Math.min(getPageSize(), items.size()));
        } else {
            return getPage();
        }
    }
}
