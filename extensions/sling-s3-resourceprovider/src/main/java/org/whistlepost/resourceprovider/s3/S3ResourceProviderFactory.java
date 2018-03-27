package org.whistlepost.resourceprovider.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Deactivate;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceProvider;
import org.apache.sling.api.resource.ResourceProviderFactory;
import org.osgi.framework.BundleContext;

import java.util.Map;

public class S3ResourceProviderFactory implements ResourceProviderFactory {

    private AmazonS3 s3Client;

    @Override
    public ResourceProvider getResourceProvider(Map<String, Object> authenticationInfo) throws LoginException {
        return new S3ResourceProvider(s3Client);
    }

    @Override
    public ResourceProvider getAdministrativeResourceProvider(Map<String, Object> authenticationInfo) throws LoginException {
        return new S3ResourceProvider(s3Client);
    }

    @Activate
    protected void activate(BundleContext context, Map<String, String> config) {
        s3Client = AmazonS3ClientBuilder.defaultClient();
    }

    @Deactivate
    protected void deactivate(BundleContext context, Map<String, String> config) {
        if (s3Client != null) {
            s3Client.shutdown();
            s3Client = null;
        }
    }
}
