package org.whistlepost.model.schema.list;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.whistlepost.model.schema.CreativeWork;
import org.whistlepost.model.schema.ItemList;

@Model(adaptables = {Resource.class, SlingHttpServletRequest.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class CreativeWorkList extends ItemList<CreativeWork> {

    @Override
    public Class<CreativeWork> getResourceType() {
        return CreativeWork.class;
    }
}
