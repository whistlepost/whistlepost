package org.whistlepost.model.page;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;

@Model(adaptables = {Resource.class})
public class DefaultBlock implements BlockResource {

    @Self
    private BlockEditor editor;

    @Override
    public BlockEditor getEditor() {
        return editor;
    }

    @Override
    public String getRendered() {
        if (editor.getPars() != null) {
            return "<p>" + String.join("</p><p>", editor.getPars()) + "</p>";
        } else {
            return editor.getContent();
        }
    }
}
