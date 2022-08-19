package org.whistlepost.model;

import org.apache.sling.api.adapter.Adaptable;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.whistlepost.model.page.Metadata;

import javax.inject.Inject;

import static org.apache.sling.query.SlingQuery.$;

/**
 * Base model for all pages in Whistlepost. Any resource should conform with this model, as it
 * only requires a single mandatory property: title.
 */
@Model(adaptables = {Resource.class})
public interface Page extends Comparable<Page>, Adaptable {

    @Inject
    String getTitle();

    @Self
    Metadata getMetadata();

    @Self
    Resource getResource();

    /**
     * Returns a list of all parent pages in the content hierarchy.
     * @return an iterable list of pages
     */
    default Iterable<Page> getParents() {
        return $(getResource()).parents("wp/page").map(Page.class);
    }

    /**
     * Returns a list of all sibling pages in the content hierarchy.
     * @return an iterable list of pages
     */
    default Iterable<Page> getSiblings() {
        return $(getResource()).siblings().not($(getResource())).map(Page.class);
    }

    /**
     * Returns a list of all child pages in the content hierarchy.
     * @return an iterable list of pages
     */
    default Iterable<Page> getChildren() {
        return $(getResource()).children().map(Page.class);
    }

    @Override
    default int compareTo(@NotNull Page o) {
        return getTitle().compareTo(o.getTitle());
    }

    @Override
    default <AdapterType> @Nullable AdapterType adaptTo(@NotNull Class<AdapterType> type) {
        return getResource().adaptTo(type);
    }
}
