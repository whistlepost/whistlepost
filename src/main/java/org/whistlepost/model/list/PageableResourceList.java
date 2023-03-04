package org.whistlepost.model.list;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.query.SlingQuery;

import static org.apache.sling.query.SlingQuery.$;

@Model(adaptables = {SlingHttpServletRequest.class})
public interface PageableResourceList<T> extends ListContainer<T>, PagingSupport<T> {

    /**
     * Returns the total number of page indexes based on items per page.
     * @return a positive integer greater than zero
     */
    default int getPageCount() {
        return (int) Math.ceil((float) getCount() / getPageSize());
    }

    default Iterable<T> getPageItems() {
        SlingQuery query = $(getListResource()).children();
        int startIndex = (getCurrentPage() - 1) * getPageSize();
        int endIndex = startIndex + Math.min(getPageSize(), getCount() - startIndex) - 1;
        return query.slice(startIndex, endIndex).map(getMappedType());
    }
}
