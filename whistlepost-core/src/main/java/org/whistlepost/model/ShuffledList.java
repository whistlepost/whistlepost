package org.whistlepost.model;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import java.util.Collections;
import java.util.List;

@Model(adaptables = {Resource.class})
public interface ShuffledList<T> extends PageableList<T> {

    default Iterable<T> getShuffledItems() {
        List<T> items = toList(getItems());
        Collections.shuffle(items);
        return items;
    }

    default Iterable<T> getShuffledPage() {
        List<T> items = toList(getItems());
        Collections.shuffle(items);
        return items.subList((getCurrentPage() - 1) * getPageSize(),
                (getCurrentPage() - 1) * getPageSize() + Math.min(getPageSize(), items.size()));
    }
}
