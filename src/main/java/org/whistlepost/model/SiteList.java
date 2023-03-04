package org.whistlepost.model;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.whistlepost.model.list.AbstractItemList;

@Model(adaptables = {Resource.class})
public class SiteList extends AbstractItemList<SiteDefaults> {

    @Override
    public Class<SiteDefaults> getMappedType() {
        return SiteDefaults.class;
    }
}
