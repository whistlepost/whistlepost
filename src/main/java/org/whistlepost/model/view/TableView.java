package org.whistlepost.model.view;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.injectorspecific.ResourcePath;
import org.apache.sling.models.annotations.injectorspecific.Self;

import java.util.List;

import static org.apache.sling.query.SlingQuery.$;

public interface TableView {

    @Self
    Resource getResource();

    @ResourcePath
    List<TableColumn> getColumns();

    default List<Resource> getItems() {
        return $(getResource()).children().asList();
    }
}
