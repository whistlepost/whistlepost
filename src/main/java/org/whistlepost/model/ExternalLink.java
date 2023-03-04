package org.whistlepost.model;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

/**
 * Model an external hyperlink.
 */
@Model(adaptables = {Resource.class})
public interface ExternalLink {

    String getText();

    String getUrl();
}
