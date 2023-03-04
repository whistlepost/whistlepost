package org.whistlepost.model.schema;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.whistlepost.model.list.AbstractItemList;

@Model(adaptables = {Resource.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class LocalBusinessList extends AbstractItemList<LocalBusiness> {

    @Override
    public Class<LocalBusiness> getMappedType() {
        return LocalBusiness.class;
    }
}
