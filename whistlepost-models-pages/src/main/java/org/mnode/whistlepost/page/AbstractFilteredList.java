package org.mnode.whistlepost.page;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.mnode.whistlepost.model.api.PageableList;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static org.apache.sling.query.SlingQuery.$;

public abstract class AbstractFilteredList<T> extends DefaultPage implements PageableList {

    private final String filter;

    private final Class<T> resourceType;

//    @Inject @Via("resource")
    @ChildResource
    private Resource list;

    @Inject @Default(intValues = 6)
    private int pageSize;

    private int limit = -1;

    private int currentPage = 1;

    private int pageCount;

    private List<Integer> pageNumbers;

    @Inject @Optional
    private String title;

    public AbstractFilteredList(String filter, Class<T> resourceType) {
        this.filter = filter;
        this.resourceType = resourceType;
    }

    @PostConstruct
    protected void init() {
        if (request != null) {
            try {
                String[] directives = request.getRequestPathInfo().getSuffix().substring(1).split("-");
                if (directives.length > 1) {
                    currentPage = Integer.parseInt(directives[0]);
                    limit = Integer.parseInt(directives[1]);
                } else {
                    currentPage = Integer.parseInt(directives[0]);
//                    limit = -1;
                }
            } catch (RuntimeException e) {
//                currentPage = 1;
//                limit = -1;
            }
        }

        pageCount = (int) Math.ceil((float) $(list).children(filter).asList().size() / pageSize);

        pageNumbers = new ArrayList<>();
        for (int i = 1; i <= pageCount; i++) {
            if (i == 1 || i == pageCount || (i > getCurrentPage() - 3 && i < getCurrentPage() + 3)) {
                pageNumbers.add(i);
            } else if (pageNumbers.lastIndexOf(-1) < pageNumbers.size() - 1) {
                // add separator..
                pageNumbers.add(-1);
            }
        }
    }

    public String getTitle() {
        if (getCurrentPage() > 1) {
            return String.format("%s | %s", title, getCurrentPage());
        } else {
            return title;
        }
    }

    public Iterable<T> getAll() {
        return $(list).children(filter).map(resourceType);
    }

    public Iterable<T> getPage() {
        return $(list).children(filter).slice((currentPage - 1) * pageSize,
                (currentPage - 1) * pageSize + pageSize - 1).map(resourceType);
    }

    protected List<T> toList() {
        List<T> result = new ArrayList<>();
        $(list).children(filter).map(resourceType).forEach(result::add);
        return result;
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    @Override
    public int getPageCount() {
        return pageCount;
    }

    @Override
    public boolean hasPrevious() {
        return currentPage > 1;
    }

    @Override
    public boolean hasNext() {
        return currentPage < getPageCount();
    }

    @Override
    public int getCurrentPage() {
        return currentPage;
    }

    @Override
    public Iterable<Integer> getPageNumbers() {
        return pageNumbers;
    }
}
