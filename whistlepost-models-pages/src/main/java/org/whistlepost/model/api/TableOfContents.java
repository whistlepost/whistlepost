package org.whistlepost.model.api;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.injectorspecific.ResourcePath;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Model(adaptables = {Resource.class})
public interface TableOfContents {

    @Inject @Optional @Named("toc")
    List<Reference> getPages();

    @ResourcePath(name = "defaultContentsPage") @Optional
    Page getDefaultPage();
}
