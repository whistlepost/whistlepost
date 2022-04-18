package org.whistlepost.model;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Required;

import javax.inject.Inject;

/**
 * Support for <a href="https://en.wikipedia.org/wiki/ICalendar">iCalendar</a> event publishing.
 */
@Model(adaptables = {Resource.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public interface CalendarEvent {

    @Inject
    @Required
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
