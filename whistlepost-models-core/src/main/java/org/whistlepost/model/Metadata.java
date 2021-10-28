package org.whistlepost.model;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
import java.util.List;

@Model(adaptables = {Resource.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public interface Metadata {

    @Inject
    List<String> getKeywords();

    @Inject
    String getDescription();

    @Inject
    String getSubject();

    @Inject
    String getLanguage();

    @Inject
    String getRobots();

    @Inject
    String getRevised();

    @Inject
    String getAbstract();

    @Inject
    String getTopic();

    @Inject
    String getSummary();

    @Inject
    String getAuthor();

    @Inject
    String getTarget();

    @Inject
    String getAudience();

    @Inject
    String getCoverage();

    @Inject
    String getDistribution();

    @Inject
    String getRating();

}
