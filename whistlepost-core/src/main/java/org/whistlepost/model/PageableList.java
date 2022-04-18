package org.whistlepost.model;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.query.SlingQuery;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static org.apache.sling.query.SlingQuery.$;

@Model(adaptables = {Resource.class})
public interface PageableList<T> extends FilteredList<T> {

    int DEFAULT_PAGE_SIZE = 6;

    /**
     * Returns the configured number of items per page.
     * @return a positive integer
     */
    @Inject @Default(intValues = DEFAULT_PAGE_SIZE)
    int getPageSize();

    /**
     * Returns the current page index based on the request.
     * @return a positive integer between 1 and {@link #getPageCount()}
     */
    int getCurrentPage();

    /**
     * Returns the total number of page indexes based on items per page.
     * @return a positive integer greater than zero
     */
    default int getPageCount() {
        return (int) Math.ceil((float) getItemCount() / getPageSize());
    }

    /**
     * Convenience method to indicate if there are page indexes prior to the current page.
     * @return true if the current page is greater than 1
     */
    default boolean hasPrevious() {
        return getCurrentPage() > 1;
    }

    /**
     * Convenience method to indicate if there are page indexes after to the current page.
     * @return true if the current page is less than {{@link #getPageCount()}}
     */
    default boolean hasNext() {
        return getCurrentPage() < getPageCount();
    }

    default Iterable<T> getPage() {
        SlingQuery query;
        if (getFilter() != null) {
            query = $(getListResource()).children(getFilter());
        } else {
            query = $(getListResource()).children();
        }
        return query.slice((getCurrentPage() - 1) * getPageSize(),
                (getCurrentPage() - 1) * getPageSize() + getPageSize() - 1).map(getResourceType());
    }

    /**
     * Returns a list of page indexes based on the total published resources.
     * @return a non-empty list of integers (starting with 1)
     */
    default Iterable<Integer> getPageNumbers() {
        List<Integer> pageNumbers = new ArrayList<>();
        for (int i = 1; i <= getPageCount(); i++) {
            if (i == 1 || i == getPageCount() || (i > getCurrentPage() - 3 && i < getCurrentPage() + 3)) {
                pageNumbers.add(i);
            } else if (pageNumbers.lastIndexOf(-1) < pageNumbers.size() - 1) {
                // add separator..
                pageNumbers.add(-1);
            }
        }
        return pageNumbers;
    }
}
