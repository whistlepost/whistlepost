package org.mnode.whistlepost.model.api;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;

/**
 * A page represents any content that can exist as a discrete, independent resource.
 */
@Model(adaptables = {Resource.class})
public interface Page {

    @Self
    Metadata getMetadata();

    @Self
    Resource getResource();

    @Self
    TableOfContents getToc();

    /**
     * Returns a list of all parent pages in the content hierarchy.
     * @return an iterable list of pages
     */
    Iterable<Page> getParents();

    /**
     * Returns a list of all sibling pages in the content hierarchy.
     * @return an iterable list of pages
     */
    Iterable<Page> getSiblings();

    /**
     * Returns a list of all child pages in the content hierarchy.
     * @return an iterable list of pages
     */
    Iterable<Page> getChildren();
}
