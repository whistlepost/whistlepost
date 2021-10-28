package org.whistlepost.model;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static org.apache.sling.query.SlingQuery.$;

@Model(adaptables = {Resource.class})
public interface FilteredList<T> extends Page {

    @ChildResource(name = "list")
    Resource getListResource();

    @Inject @Optional
    String getFilter();

    Class<T> getResourceType();

    default int getItemCount() {
        if (getFilter() != null) {
            return (int) $(getListResource()).children(getFilter()).stream().count();
        } else {
            return (int) $(getListResource()).children().stream().count();
        }
    }

    default Iterable<T> getItems() {
        if (getFilter() != null) {
            return $(getListResource()).children(getFilter()).map(getResourceType());
        } else {
            return $(getListResource()).children().map(getResourceType());
        }
    }

    default List<T> toList(Iterable<T> items) {
        List<T> result = new ArrayList<>();
        items.forEach(result::add);
        return result;
    }
}
