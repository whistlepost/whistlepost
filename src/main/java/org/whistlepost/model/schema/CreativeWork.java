package org.whistlepost.model.schema;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

import static org.apache.sling.models.annotations.injectorspecific.InjectionStrategy.OPTIONAL;

/**
 * Sling models implementation of <a href="https://schema.org/CreativeWork">Schema.org CreativeWork</a>.
 */
@Model(adaptables = {Resource.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class CreativeWork extends Thing {

    @Inject @Named("abstract")
    private String abstract_;

    @Inject @Named("audio")
    private String audioRef;

    private AudioObject audio;

    @Inject @Named("author")
    private String authorRef;

    private Person author;

    @Inject
    private String datePublished;

    @Inject
    private String headline;

    @Inject
    private List<String> keywords;

    @Inject
    private String text;

    @Inject @Named("video")
    private String videoRef;

    private VideoObject video;

    @SlingObject
    protected ResourceResolver resolver;

    @SlingObject(injectionStrategy = OPTIONAL)
    protected SlingHttpServletRequest request;

    @PostConstruct
    protected void init() {
        if (request != null) {
            audio = resolveRef(request, audioRef, AudioObject.class);
            author = resolveRef(request, authorRef, Person.class);
            video = resolveRef(request, videoRef, VideoObject.class);
        } else {
            audio = resolveRef(audioRef, AudioObject.class);
            author = resolveRef(authorRef, Person.class);
            video = resolveRef(videoRef, VideoObject.class);
        }
    }

    private <T> T resolveRef(SlingHttpServletRequest request, String ref, Class<T> type) {
        if (ref != null) {
            return resolver.resolve(request, ref).adaptTo(type);
        }
        return null;
    }

    private <T> T resolveRef(String ref, Class<T> type) {
        if (ref != null) {
            return resolver.resolve(ref).adaptTo(type);
        }
        return null;
    }

    public String getAbstract() {
        return abstract_;
    }

    public AudioObject getAudio() {
        return audio;
    }

    public Person getAuthor() {
        return author;
    }

    public String getDatePublished() {
        return datePublished;
    }

    public String getHeadline() {
        return headline;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public String getText() {
        return text;
    }

    public VideoObject getVideo() {
        return video;
    }

    @Override
    public int compareTo(Thing o) {
        if (o instanceof CreativeWork && getDatePublished() != null) {
            CreativeWork other = (CreativeWork) o;
            if (other.getDatePublished() != null) {
                return getDatePublished().compareTo(other.getDatePublished());
            }
        }
        return super.compareTo(o);
    }
}
