package org.mnode.whistlepost.model;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import java.util.Arrays;
import java.util.stream.Collectors;

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

    public String getPermalink() {
        return resource.getValueMap().get("permalink", String.class);
    }

    public Article getSidebar() {
        Resource sidebarResource = null;

        String sidebar = resource.getValueMap().get("sidebar", String.class);
        if (sidebar != null) {
            sidebarResource = getResource(sidebar);
        }

        if (sidebarResource != null) {
            return sidebarResource.adaptTo(Article.class);
        } else {
            return null;
        }
    }

    public Iterable<Article> getRelated() {
        String[] related = resource.getValueMap().get("related", new String[] {});

        return Arrays.stream(related).map(p -> resourceResolver.getResource(resource.getParent(), p).adaptTo(Article.class))
                .collect(Collectors.toList());
    }
}
