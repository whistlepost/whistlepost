package org.mnode.whistlepost.model;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;

import java.util.Collections;
import java.util.stream.Collectors;

import static org.apache.sling.query.SlingQuery.$;

@Model(adaptables = {Resource.class, SlingHttpServletRequest.class})
public class SectionList extends Page {

    @ChildResource @Optional
    private Resource sections;

    public Iterable<Section> getSections() {
        if (sections != null) {
            return $(sections).children().asList().stream().map(r -> r.adaptTo(Section.class)).collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }
}
