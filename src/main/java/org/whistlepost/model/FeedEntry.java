package org.whistlepost.model;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Required;

import javax.inject.Inject;

/**
 * Support for <a href="https://validator.w3.org/feed/docs/atom.html">Atom</a> feed generation.
 */
@Model(adaptables = {Resource.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public interface FeedEntry {

    @Inject @Required
    String getId();

    @Inject @Required
    String getTitle();

    @Inject @Required
    String getUpdated();

    @Inject
    String getLink();

    @Inject
    String getAuthor();

    @Inject
    String getSummary();
}
