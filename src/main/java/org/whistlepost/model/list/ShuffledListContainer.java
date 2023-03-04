package org.whistlepost.model.list;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.whistlepost.model.transform.ShuffleTransformer;

@Model(adaptables = {Resource.class})
public interface ShuffledListContainer<T> extends ListContainer<T> {

    default Iterable<T> getShuffledList() {
        return new ShuffleTransformer<T>().transform(getListItems());
    }
}
