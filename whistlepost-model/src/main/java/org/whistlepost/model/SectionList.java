package org.whistlepost.model;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ResourcePath;

import java.util.List;

/**
 * A model to support a group of resources that support the "section" selector.
 */
@Model(adaptables = {Resource.class})
public interface SectionList {

    @ResourcePath
    List<Resource> getSections();
}
