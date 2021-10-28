package org.mnode.whistlepost.model.request;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static org.apache.sling.query.SlingQuery.$;

@Model(adaptables = {SlingHttpServletRequest.class})
public class Paging {

    @SlingObject
    private SlingHttpServletRequest request;
//    private RequestPathInfo requestPathInfo;

    @ChildResource @Via("resource")
    private Resource list;

    @Inject @Via("resource")
    private int pageSize;

    private List<Resource> publishedResources;

    private int currentPage;

    private int limit = -1;

    @PostConstruct
    protected void init() {
        try {
            String[] directives = request.getRequestPathInfo().getSuffix().substring(1).split("-");
            if (directives.length > 1) {
                currentPage = Integer.parseInt(directives[0]);
                limit = Integer.parseInt(directives[1]);
            } else {
                currentPage = Integer.parseInt(directives[0]);
            }
        } catch (RuntimeException e) {
            currentPage = 1;
        }
//        pageSize = 6; //resource.getChild("paging").getValueMap().get("pageSize", 6);

        publishedResources = $(list).children("nt:unstructured[published=true]").asList();
    }

    /**
     * Returns the current page index based on the request.
     * @return a positive integer between 1 and {@link #getPageCount()}
     */
    public int getCurrentPage() {
        return currentPage;
    }

    /**
     * Convenience method to indicate if there are page indexes prior to the current page.
     * @return true if the current page is greater than 1
     */
    public boolean hasPrevious() {
        return currentPage > 1;
    }

    /**
     * Convenience method to indicate if there are page indexes after to the current page.
     * @return true if the current page is less than {{@link #getPageCount()}}
     */
    public boolean hasNext() {
        return currentPage < getPageCount();
    }

    /**
     * Returns the total number of page indexes based on items per page.
     * @return a positive integer greater than zero
     */
    public int getPageCount() {
        return (int) Math.ceil((float) publishedResources.size() / pageSize);
    }

    /**
     * Returns the configured number of items per page.
     * @return a positive integer
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * Returns a list of page indexes based on the total published resources.
     * @return a non-empty list of integers (starting with 1)
     */
    public Iterable<Integer> getPageNumbers() {
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
