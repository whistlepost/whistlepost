package org.whistlepost.model.component;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.caconfig.annotations.ContextAwareConfiguration;
import org.whistlepost.config.component.BreadcrumbConfig;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * A model used to render a breadcrumb for any resource.
 */
@Model(adaptables = {Resource.class})
public class Breadcrumb {

    @ContextAwareConfiguration(name = "breadcrumb")
    private BreadcrumbConfig config;

    private List<Resource> pathSegments;

    @Self
    private Resource leaf;

    @PostConstruct
    private void init() {
        List<String> ignoredResources = Arrays.asList(config.ignoredResourceNames());
        pathSegments = new ArrayList<>();
        Resource segment = leaf.getParent();
        while (segment.getParent() != null) {
            if (!ignoredResources.contains(segment.getName())) {
                pathSegments.add(segment);
            }
            segment = segment.getParent();
        }
        Collections.reverse(pathSegments);
    }

    public List<Resource> getPathSegments() {
        return pathSegments;
    }
}
