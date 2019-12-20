package org.mnode.whistlepost.model;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.sling.query.SlingQuery.$;

@Model(adaptables = {Resource.class, SlingHttpServletRequest.class})
public class ArticleList extends Page {

    @ChildResource
    private Resource list;

    private int currentPage;

    @ValueMapValue @Default(intValues = 6)
    private int pageSize;

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
    }

    public String getTitle() {
        if (getCurrentPage() > 1) {
            return String.format("%s | %s", super.getTitle(), getCurrentPage());
        } else {
            return super.getTitle();
        }
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getPageCount() {
        return (int) Math.ceil((float) $(list).children().asList().size() / pageSize);
    }

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

    public boolean hasPrevious() {
        return currentPage > 1;
    }

    public boolean hasNext() {
        return currentPage < getPageCount();
    }

    public Iterable<Article> getArticles() {
        int offset = Math.min((currentPage - 1) * pageSize, $(list).children().asList().size() - 1);

        List<Resource> filtered;
        if (limit > 0) {
            filtered = $(list).children().asList().subList(offset, Math.min(offset + limit, $(list).children().asList().size()));
        } else {
            filtered = $(list).children().asList().subList(offset, Math.min(offset + pageSize, $(list).children().asList().size()));
        }
        return filtered.stream().map(r -> r.adaptTo(Article.class)).collect(Collectors.toList());
    }
}
