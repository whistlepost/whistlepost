package org.mnode.whistlepost.model.impl;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.sling.models.annotations.injectorspecific.InjectionStrategy.OPTIONAL;
import static org.apache.sling.query.SlingQuery.$;

@Model(adaptables = {Resource.class, SlingHttpServletRequest.class})
public class AdvertisementList extends AbstractModel {

    @Inject
    private String title;

    @SlingObject(injectionStrategy = OPTIONAL)
    protected SlingHttpServletRequest request;

    @ChildResource
    private Resource list;

    private List<Resource> publishedResources;

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

        // make concrete list for shuffle..
        publishedResources = new ArrayList($(list).children("nt:unstructured[published=true]").asList());
        Collections.shuffle(publishedResources);
    }

    public String getTitle() {
        if (getCurrentPage() > 1) {
            return String.format("%s | %s", title, getCurrentPage());
        } else {
            return title;
        }
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getPageCount() {
        return (int) Math.ceil((float) publishedResources.size() / pageSize);
    }

    public boolean hasPrevious() {
        return currentPage > 1;
    }

    public boolean hasNext() {
        return currentPage < getPageCount();
    }

    public Iterable<Advertisement> getAdvertisements() {
        int offset = Math.min((currentPage - 1) * pageSize, publishedResources.size() - 1);

        if (!publishedResources.isEmpty()) {
            List<Resource> filtered;
            if (limit > 0) {
                filtered = publishedResources.subList(offset, Math.min(offset + limit, publishedResources.size()));
            } else {
                filtered = publishedResources.subList(offset, Math.min(offset + pageSize, publishedResources.size()));
            }
            return filtered.stream().map(r -> r.adaptTo(Advertisement.class)).collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }
}
