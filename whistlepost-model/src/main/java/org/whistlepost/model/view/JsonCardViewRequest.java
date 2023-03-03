package org.whistlepost.model.view;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;

import javax.annotation.PostConstruct;
import java.util.Map;

@Model(adaptables = {SlingHttpServletRequest.class})
public class JsonCardViewRequest implements CardView {

//    private static final String SELECTOR_ALL = "$..*";
//
//    @Inject @Via("resource") @Default(values = SELECTOR_ALL)
//    private String selector;

    @Self
    private SlingHttpServletRequest request;

    private Map<String, String> cardData;

    @PostConstruct
    protected void init() {
        String json = request.getRequestParameter("json").getString();
        DocumentContext context = JsonPath.using(Configuration.builder().mappingProvider(new JacksonMappingProvider()).build())
                .parse(json);
//        cardData = context.read(selector, new TypeRef<Map<String, String>>() {});
        cardData = context.json();
    }

    @Override
    public Map<String, String> getCardData() {
        return cardData;
    }
}
