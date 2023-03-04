package org.whistlepost.model.view;

import com.opencsv.CSVReader;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.Self;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Model(adaptables = {SlingHttpServletRequest.class})
public class CsvListViewRequest implements ListView<String> {

    /**
     * Specify the columns included in list items. e.g. [0,1]
     */
    @Inject @Via("resource") @Optional
    private int[] selector;

    /**
     * A joining string used to combine multiple columns.
     */
    @Inject @Via("resource") @Default(values = " ")
    private String joiner;

    @Self
    private SlingHttpServletRequest request;

    private List<String> listItems;

    @PostConstruct
    protected void init() {
        CSVReader csvReader = new CSVReader(new StringReader(request.getRequestParameter("csv").getString()));
        listItems = new ArrayList<>();
        csvReader.iterator().forEachRemaining(s -> {
            if (selector != null) {
                String[] cols = Arrays.stream(selector).mapToObj(i -> s[i]).toArray(String[]::new);
                listItems.add(String.join(joiner, cols));
            } else {
                listItems.add(String.join(joiner, s));
            }
        });
    }

    @Override
    public List<String> getListItems() {
        return listItems;
    }
}
