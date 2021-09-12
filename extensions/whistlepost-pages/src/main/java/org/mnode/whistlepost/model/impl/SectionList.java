package org.mnode.whistlepost.model.impl;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;

import javax.inject.Inject;
import java.util.Collections;
import java.util.stream.Collectors;

import static org.apache.sling.query.SlingQuery.$;

@Model(adaptables = {Resource.class, SlingHttpServletRequest.class})
public class SectionList extends AbstractModel {

    @Inject
    private String title;

    @ChildResource @Optional
    private Resource sections;

    public String getTitle() {
        return title;
    }

    public Iterable<Section> getSections() {
        if (sections != null) {
            return $(sections).children().asList().stream().map(r -> r.adaptTo(Section.class)).collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }
}
