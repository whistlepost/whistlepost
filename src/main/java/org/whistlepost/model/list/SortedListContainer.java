package org.whistlepost.model.list;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import java.util.Comparator;
import java.util.List;

@Model(adaptables = {Resource.class})
public interface SortedListContainer<T extends Comparable<? super T>> extends ListContainer<T>, SortingSupport<T> {

    default List<T> getSortedList() {
        Comparator<T> comparator = getComparator();
        if (comparator != null) {
            List<T> items = getListItems();
            items.sort(comparator);
            return items;
        } else {
            return getListItems();
        }
    }
}
