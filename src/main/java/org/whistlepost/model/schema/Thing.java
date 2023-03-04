package org.whistlepost.model.schema;

import org.apache.sling.api.adapter.Adaptable;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Required;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.inject.Inject;

/**
 * Sling models implementation of <a href="https://schema.org/Thing">Schema.org Thing</a>.
 */
@Model(adaptables = {Resource.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Thing implements Comparable<Thing>, Adaptable {

    @Inject
    private String name;

    @Inject
    private String description;

    @Inject
    private String image;

    @Inject
    private String url;

    @Inject @Required
    private String title;

    @SlingObject
    private Resource resource;

    /**
     * @return The name of the item.
     */
    public String getName() {
        return name;
    }

    /**
     * @return A description of the item. 
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns a URL to an image representation of this thing.
     * @return a URL string
     */
    public String getImage() {
        return image;
    }

    /**
     * Returns a permalink URL for this thing.
     * @return a URL string
     */
    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public Resource getResource() {
        return resource;
    }

    @Override
    public int compareTo(Thing o) {
        return getTitle().compareTo(o.getTitle());
    }

    @Override
    public <AdapterType> @Nullable AdapterType adaptTo(@NotNull Class<AdapterType> type) {
        return resource.adaptTo(type);
    }
}
