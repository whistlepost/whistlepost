package org.whistlepost.model.list;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Via;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Support for result paging.
 */
public interface PagingSupport<T> {

    int DEFAULT_PAGE_SIZE = 6;

    /**
     * Returns the configured number of items per page.
     * @return a positive integer
     */
    @Inject @Via("resource")
    @Default(intValues = DEFAULT_PAGE_SIZE)
    int getPageSize();

    int getPageCount();

    /**
     * Returns the current page index based on the request.
     * @return a positive integer between 1 and {@link #getPageCount()}
     */
    int getCurrentPage();

    Iterable<T> getPageItems();

    static int getPageNumber(SlingHttpServletRequest request) {
        int currentIndex = -1;
        if (request != null && request.getRequestPathInfo().getSuffix() != null) {
            try {
                String[] directives = request.getRequestPathInfo().getSuffix().substring(1).split("-");
                if (directives.length > 1) {
                    currentIndex = Integer.parseInt(directives[0]);
//                    limit = Integer.parseInt(directives[1]);
                } else {
                    currentIndex = Integer.parseInt(directives[0]);
//                    limit = -1;
                }
            } catch (RuntimeException e) {
//                currentPage = 1;
//                limit = -1;
            }
        } else {
            currentIndex = 1;
        }
        return currentIndex;
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
}
