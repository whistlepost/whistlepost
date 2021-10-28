package org.whistlepost.model.schema.list;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.whistlepost.model.schema.ItemList;
import org.whistlepost.model.schema.Thing;

@Model(adaptables = {Resource.class, SlingHttpServletRequest.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ThingList extends ItemList<Thing> {

    @Override
    public Class<Thing> getResourceType() {
        return Thing.class;
    }
}
