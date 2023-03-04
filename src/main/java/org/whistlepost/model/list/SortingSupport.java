package org.whistlepost.model.list;

import java.util.Comparator;

public interface SortingSupport<T extends Comparable<? super T>> {

    String getItemListOrder();

    default Comparator<T> getComparator() {
        Comparator<T> comparator = null;
        if ("Ascending".equals(getItemListOrder())) {
            comparator = Comparator.naturalOrder();
        } else if ("Descending".equals(getItemListOrder())) {
            comparator = Comparator.reverseOrder();
        }
        return comparator;
    }
}
