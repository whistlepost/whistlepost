package org.whistlepost.model.api;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.injectorspecific.ResourcePath;

import javax.inject.Inject;
import java.util.List;

/**
 * An article represents content that can be rendered as a published media-rich post.
 * An article extends from a page to add a mandatory abstract, article body, published date,
 * optional list of media, an optional sidebar article, and optional related articles.
 */
@Model(adaptables = {Resource.class})
public interface Article extends Page {

    @Inject @Optional
    String getAbstract();

    @Inject @Optional
    BlockResource getBody();

    @Inject @Optional
    Iterable<String> getMedia();

    @ResourcePath(name = "sidebar") @Optional
    Article getSidebar();

    @Inject @Optional
    List<Reference> getRelated();
}
