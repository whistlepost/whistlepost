package org.mnode.whistlepost.model.api;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Page metadata includes a mandatory title and optional descrition and author.
 */
@Model(adaptables = {Resource.class})
public interface Metadata {

    /**
     * Accessor for mandatory page title.
     * @return the page title as a string
     */
    @Inject
    String getTitle();

    /**
     * Accessor for optional page description
     * @return the description as a string
     */
    @Inject @Optional
    String getDescription();

    /**
     * Accessor for optional author
     * @return the page author as a string
     */
    @Inject @Optional
    String getAuthor();

    /**
     * Accessor for optional published status
     * @return the page published status as a boolean value
     */
//    @Inject @Optional
    boolean isPublished();

    /**
     * Accessor for optional published date
     * @return the page published date as an instant
     */
    @Inject @Optional @Named("date")
    String getPublishedDate();

    /**
     * Accessor for optional image
     * @return the page image location
     */
    @Inject @Optional @Named("media")
    String getImage();
}
