package org.whistlepost.model.view;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.TypeRef;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.Self;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;

@Model(adaptables = {SlingHttpServletRequest.class})
public class JsonListViewRequest implements ListView<String> {

    private static final String SELECTOR_ALL = "$..*";

    @Inject @Via("resource") @Default(values = SELECTOR_ALL)
    private String selector;

    @Self
    private SlingHttpServletRequest request;

    private List<String> listItems;

    @PostConstruct
    protected void init() {
        String json = request.getRequestParameter("json").getString();
        DocumentContext context = JsonPath.using(Configuration.builder().mappingProvider(new JacksonMappingProvider()).build())
                .parse(json);
        listItems = context.read(selector, new TypeRef<List<String>>() {});
    }

    @Override
    public List<String> getListItems() {
        return listItems;
    }
}
