package org.whistlepost.scripting.groovy;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "Whistlepost Groovy Markup Script Engine Factory",
description = "Script engine factory for Groovy Markup Template Engine")
@interface GroovyMarkupScriptEngineFactoryConfiguration {

    @AttributeDefinition(name = "service ranking", description = "Specifies the ranking of the OSGi service")
    int service_ranking() default 0;

    @AttributeDefinition(name = "extensions", description = "File extensions associated with this script engine")
    String[] extensions() default {"gtpl"};

    @AttributeDefinition(name = "mime types", description = "The content types associated with this script engine")
    String[] mimeTypes() default {"text/plain"};

    @AttributeDefinition(name = "names", description = "The names registered to this script engine")
    String[] names() default {"gtpl"};
}
