package org.mnode.whistlepost.page;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.mnode.whistlepost.model.api.Advertisement;

@Model(adaptables = {Resource.class})
public class PublishedAdvertisements extends AbstractFilteredList<Advertisement> {

    public PublishedAdvertisements() {
        super("nt:unstructured[published=true]", Advertisement.class);
    }
}
