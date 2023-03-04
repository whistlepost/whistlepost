package org.whistlepost.model.list;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;

import javax.inject.Inject;
import java.util.List;

import static org.apache.sling.query.SlingQuery.$;

@Model(adaptables = {Resource.class})
public interface FilteredListContainer<T> extends ListContainer<T> {

    @Inject @Optional
    String getFilter();

    default int getFilteredCount() {
        return getFilteredCount(getFilter());
    }

    default int getFilteredCount(String filter) {
        if (filter != null) {
            return (int) $(getListResource()).children(filter).stream().count();
        } else {
            return ListContainer.super.getCount();
        }
    }

    default List<T> getFilteredList() {
        return getFilteredList(getFilter());
    }

    default List<T> getFilteredList(String filter) {
        if (filter != null) {
            return ListContainer.toList($(getListResource()).children(filter).map(getMappedType()));
        } else {
            return ListContainer.super.getListItems();
        }
    }
}
