package org.whistlepost.httpclient;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "Whistlepost Caching HTTP Client Service", description = "HTTP client with cache")
@interface CachingHttpClientServiceConfiguration {

    @AttributeDefinition(name = "cache.dir", description = "Cache directory")
    String cache_dir();
}
