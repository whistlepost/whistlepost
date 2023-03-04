package org.whistlepost.model.list;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.whistlepost.model.view.ListView;

import java.util.ArrayList;
import java.util.List;

import static org.apache.sling.query.SlingQuery.$;

@Model(adaptables = {Resource.class})
public interface ListContainer<T> extends ListView<T> {

    @ChildResource(name = "list")
    Resource getListResource();

    Class<T> getMappedType();

    default int getCount() {
        return (int) $(getListResource()).children().stream().count();
    }

    default List<T> getListItems() {
        return toList($(getListResource()).children().map(getMappedType()));
    }

    static <T> List<T> toList(Iterable<T> items) {
        List<T> result = new ArrayList<>();
        items.forEach(result::add);
        return result;
    }
}
