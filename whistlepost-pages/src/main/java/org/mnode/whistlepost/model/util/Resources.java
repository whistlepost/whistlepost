package org.mnode.whistlepost.model.util;

import org.apache.sling.api.resource.Resource;

public class Resources {


    /**
     * Get a resource based on a relative or absolution path reference.
     * @param resourceRef a relative or absolute path
     * @return the resource at the specified path or null if no resource exists
     */
    public static Resource getResource(String resourceRef, Resource base) {
        Resource r = null;
        if (resourceRef != null) {
            if (resourceRef.startsWith("/")) {
                r = base.getResourceResolver().resolve(resourceRef);
            } else {
                r = base.getResourceResolver().getResource(base.getParent(), resourceRef);
            }
        }
        return r;
    }

}
