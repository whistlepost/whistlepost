package org.whistlepost.model.list;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import java.util.Collections;
import java.util.List;

@Model(adaptables = {Resource.class})
public interface PageableShuffledList<T> extends ShuffledListContainer<T>, PageableResourceList<T> {

    default Iterable<T> getShuffledPage() {
        List<T> items = ListContainer.toList(getListItems());
        Collections.shuffle(items);
        return items.subList((getCurrentPage() - 1) * getPageSize(),
                (getCurrentPage() - 1) * getPageSize() + Math.min(getPageSize(), items.size()));
    }
}
