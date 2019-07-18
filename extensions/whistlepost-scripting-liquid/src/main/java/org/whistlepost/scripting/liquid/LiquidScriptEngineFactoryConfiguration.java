package org.whistlepost.scripting.liquid;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "Whistlepost Liquid Script Engine Factory",
description = "Script engine factory for Liquid Template Engine")
@interface LiquidScriptEngineFactoryConfiguration {

    @AttributeDefinition(name = "service ranking", description = "Specifies the ranking of the OSGi service")
    int service_ranking() default 0;

    @AttributeDefinition(name = "extensions", description = "File extensions associated with this script engine")
    String[] extensions() default {"html"};

    @AttributeDefinition(name = "mime types", description = "The content types associated with this script engine")
    String[] mimeTypes() default {"text/plain"};

    @AttributeDefinition(name = "names", description = "The names registered to this script engine")
    String[] names() default {"liquid"};
}
