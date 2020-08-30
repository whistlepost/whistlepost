package org.mnode.whistlepost.model.impl;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.mnode.whistlepost.model.util.Resources;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Arrays;
import java.util.stream.Collectors;

@Model(adaptables = {Resource.class, SlingHttpServletRequest.class})
public class Article extends AbstractModel {

    @Inject
    private String title;

    @Inject @Optional
    private String[] pars;

    @Inject @Optional
    private String media;

    @Inject @Named("date") @Optional
    private String publishedDate;

//    @Inject
//    private String permaLink;
//

    public String getTitle() {
        return title;
    }

    public String[] getPars() {
        return pars;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public String getMedia() {
        return media;
    }

    public String getSummary() {
        if (pars.length > 0) {
            // remove all markup from summary..
            return pars[0].replaceAll("<\\w.*>", "");
        } else {
            return "";
        }
    }
//
//    public String[] getPars() {
//        return resource.getValueMap().get("pars", new String[] {});
//    }
//
//    public String getPublishedDate() {
//        return resource.getValueMap().get("date", String.class);
//    }
//
//    public String getMedia() {
//        return resource.getValueMap().get("media", String.class);
//    }
//
//    public String getPermalink() {
//        return resource.getValueMap().get("permalink", String.class);
//    }

    public Article getSidebar() {
        Resource sidebarResource = null;

        String sidebar = resource.getValueMap().get("sidebar", String.class);
        if (sidebar != null) {
            sidebarResource = Resources.getResource(sidebar, this.resource);
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

    public Advertisement getAdvert() {
        Resource advertResource = null;

        String advert = resource.getValueMap().get("advert", String.class);
        if (advert != null) {
            advertResource = Resources.getResource(advert, this.resource);
        }

        if (advertResource != null) {
            return advertResource.adaptTo(Advertisement.class);
        } else {
            return null;
        }
    }
}
