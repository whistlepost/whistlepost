package org.whistlepost.model.schema;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = {Resource.class})
public class Organization extends Thing {
}
