package org.whistlepost.model;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Model(adaptables = {Resource.class})
public interface Robots {

    @Inject @Named("user-agent")
    String getUserAgent();

    @Inject @Default(values = {})
    List<String> getAllow();

    @Inject
    String getSitemap();
}
