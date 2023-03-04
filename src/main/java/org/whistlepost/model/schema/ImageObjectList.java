package org.whistlepost.model.schema;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.whistlepost.model.list.AbstractItemList;

@Model(adaptables = {Resource.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ImageObjectList extends AbstractItemList<ImageObject> {

    @Override
    public Class<ImageObject> getMappedType() {
        return ImageObject.class;
    }
}
