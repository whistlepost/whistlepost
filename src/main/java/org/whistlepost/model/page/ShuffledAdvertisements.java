package org.whistlepost.model.page;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import java.util.Collections;
import java.util.List;

@Model(adaptables = {Resource.class})
public class ShuffledAdvertisements extends AbstractFilteredList<Advertisement> {

    public ShuffledAdvertisements() {
        super("nt:unstructured[published=true]", Advertisement.class);
    }

    public ShuffledAdvertisements(String filter) {
        super(filter, Advertisement.class);
    }

    public Iterable<Advertisement> getShuffled() {
        List<Advertisement> all = toList();
        Collections.shuffle(all);
        return all;
    }

    public Iterable<Advertisement> getShuffledPage() {
        List<Advertisement> all = toList();
        Collections.shuffle(all);
        return all.subList((getCurrentPage() - 1) * getPageSize(),
                (getCurrentPage() - 1) * getPageSize() + Math.min(getPageSize(), all.size()));
    }
}
