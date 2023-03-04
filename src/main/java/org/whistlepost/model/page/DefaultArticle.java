package org.whistlepost.model.page;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.injectorspecific.ResourcePath;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Model(adaptables = {Resource.class})
public class DefaultArticle extends DefaultPage implements Article {

    @Inject @Named("abstract")
    private String articleAbstract;

    @Inject
    private DefaultBlock body;

    @ResourcePath(name = "media") @Optional
    private Iterable<String> media;

    @ResourcePath(name = "sidebar") @Optional
    private Article sidebar;

    @Inject @Optional
    private List<Reference> related;

    @Override
    public String getAbstract() {
        return articleAbstract;
    }

    @Override
    public DefaultBlock getBody() {
        return body;
    }

    @Override
    public Iterable<String> getMedia() {
        return media;
    }

    @Override
    public Article getSidebar() {
        return sidebar;
    }

    @Override
    public List<Reference> getRelated() {
        return related;
    }
}
