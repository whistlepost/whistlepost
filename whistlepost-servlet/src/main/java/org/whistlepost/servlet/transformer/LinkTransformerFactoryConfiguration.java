package org.whistlepost.servlet.transformer;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "Whistlepost Link Transformer Factory",
    description = "Rewrite links in response data")
@interface LinkTransformerFactoryConfiguration {

    @AttributeDefinition(name = "pipeline.type")
    String pipeline_type() default "wp-link-rewriter";

    @AttributeDefinition(name = "pattern.link", description = "Regular expression for matching links")
    String pattern_link() default "^/content(/wp)?(/\\w+)?(/.*)$";

    @AttributeDefinition(name = "replacement.string", description = "Replacement string for links")
    String replacement_string() default "$1$3";
}
