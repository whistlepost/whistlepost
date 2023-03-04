package org.whistlepost.model.view;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
import java.util.List;

@Model(adaptables = {Resource.class})
public class ResourceListView implements ListView<Resource> {

    @Inject
    private List<Resource> list;

    @Override
    public List<Resource> getListItems() {
        return list;
    }
}
