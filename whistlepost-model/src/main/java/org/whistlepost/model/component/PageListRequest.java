package org.whistlepost.model.component;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Model;
import org.whistlepost.model.Page;
import org.whistlepost.model.list.AbstractPageableItemListRequest;

@Model(adaptables = {SlingHttpServletRequest.class})
public class PageListRequest extends AbstractPageableItemListRequest<Page> {

    @Override
    public Class<Page> getMappedType() {
        return Page.class;
    }
}
