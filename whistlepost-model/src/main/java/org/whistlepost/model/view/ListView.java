package org.whistlepost.model.view;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Model(adaptables = {Resource.class})
public interface ListView<T> {

    @Inject @Named("list")
    List<T> getListItems();
}
