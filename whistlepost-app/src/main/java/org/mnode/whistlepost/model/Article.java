package org.mnode.whistlepost.model;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = {Resource.class, SlingHttpServletRequest.class})
public class Article extends Page {

    public String getSummary() {
        if (getPars().length > 0) {
            // remove all markup from summary..
            return getPars()[0].replaceAll("<\\w.*>", "");
        } else {
            return "";
        }
    }

    public String[] getPars() {
        return resource.getValueMap().get("pars", new String[] {});
    }

    public String getPublishedDate() {
        return resource.getValueMap().get("date", String.class);
    }

    public String getMedia() {
        return resource.getValueMap().get("media", String.class);
    }
}
