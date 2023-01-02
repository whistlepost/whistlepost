package org.whistlepost.config.site;

import org.apache.sling.caconfig.annotation.Configuration;

/**
 * Media assets such as images and video may be potentially hosted externally
 * to a Whistlepost site. This configuration supports alternative locations for media
 * content.
 */
@Configuration(name = "site.media")
public @interface MediaConfig {

    String name();

    String description();

    String basepath();

    String uploadpath();
}
