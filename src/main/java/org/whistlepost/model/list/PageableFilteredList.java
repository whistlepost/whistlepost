package org.whistlepost.model.list;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.query.SlingQuery;

import static org.apache.sling.query.SlingQuery.$;

@Model(adaptables = {SlingHttpServletRequest.class})
public interface PageableFilteredList<T> extends FilteredListContainer<T>, PageableResourceList<T> {

    /**
     * Returns the total number of page indexes based on items per page.
     * @return a positive integer greater than zero
     */
    default int getFilteredPageCount() {
        return (int) Math.ceil((float) getFilteredCount() / getPageSize());
    }

    default Iterable<T> getFilteredPage() {
        SlingQuery query;
        if (getFilter() != null) {
            query = $(getListResource()).children(getFilter());
        } else {
            query = $(getListResource()).children();
        }
        return query.slice((getCurrentPage() - 1) * getPageSize(),
                (getCurrentPage() - 1) * getPageSize() + getPageSize() - 1).map(getMappedType());
    }
}
