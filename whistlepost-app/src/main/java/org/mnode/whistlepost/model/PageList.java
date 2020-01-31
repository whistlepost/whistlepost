package org.mnode.whistlepost.model;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import java.util.List;
import java.util.stream.Collectors;

@Model(adaptables = {Resource.class, SlingHttpServletRequest.class})
public class PageList extends Page {

    @SlingObject
    private ResourceResolver resourceResolver;

    @ValueMapValue
    private List<String> list;

    public Iterable<Page> getPages() {
        return list.stream().map(p -> resourceResolver.getResource(resource.getParent(), p).adaptTo(Page.class))
                .collect(Collectors.toList());
    }
}
