package org.mnode.whistlepost.model;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import java.net.URI;

@Model(adaptables = {Resource.class})
public class Banner {

    @ValueMapValue
    private URI bannerUrl;

    @ValueMapValue
    private URI bannerImageUrl;

    public URI getBannerUrl() {
        return bannerUrl;
    }

    public URI getBannerImageUrl() {
        return bannerImageUrl;
    }
}
