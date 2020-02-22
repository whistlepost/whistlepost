package org.mnode.whistlepost.model;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import java.util.List;
import java.util.stream.Collectors;

@Model(adaptables = {Resource.class, SlingHttpServletRequest.class})
public class PageList extends Page {

    @ValueMapValue
    private List<String> list;

    public Iterable<Page> getPages() {
        return list.stream().map(p -> getResource(p).adaptTo(Page.class)).collect(Collectors.toList());
    }
}
