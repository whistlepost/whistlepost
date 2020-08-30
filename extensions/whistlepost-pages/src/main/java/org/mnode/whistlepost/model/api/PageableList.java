package org.mnode.whistlepost.model.api;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;

@Model(adaptables = {Resource.class})
public interface PageableList {

    /**
     * Returns the configured number of items per page.
     * @return a positive integer
     */
    @Inject
    int getPageSize();

    /**
     * Returns the total number of page indexes based on items per page.
     * @return a positive integer greater than zero
     */
    int getPageCount();

    /**
     * Convenience method to indicate if there are page indexes prior to the current page.
     * @return true if the current page is greater than 1
     */
    boolean hasPrevious();

    /**
     * Convenience method to indicate if there are page indexes after to the current page.
     * @return true if the current page is less than {{@link #getPageCount()}}
     */
    boolean hasNext();

    /**
     * Returns the current page index based on the request.
     * @return a positive integer between 1 and {@link #getPageCount()}
     */
    int getCurrentPage();

    /**
     * Returns a list of page indexes based on the total published resources.
     * @return a non-empty list of integers (starting with 1)
     */
    Iterable<Integer> getPageNumbers();
}
