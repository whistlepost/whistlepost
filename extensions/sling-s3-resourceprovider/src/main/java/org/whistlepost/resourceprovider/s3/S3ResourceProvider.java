package org.whistlepost.resourceprovider.s3;

import com.amazonaws.services.s3.AmazonS3;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceProvider;
import org.apache.sling.api.resource.ResourceResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;

public class S3ResourceProvider implements ResourceProvider {

    private final AmazonS3 s3Client;

    public S3ResourceProvider(AmazonS3 s3Client) {
        this.s3Client = s3Client;
    }

    @Override
    public Resource getResource(ResourceResolver resourceResolver, HttpServletRequest request, String path) {
        return null; //s3Client.getObject("", "").g;
    }

    @Override
    public Resource getResource(ResourceResolver resourceResolver, String path) {
        return null;
    }

    @Override
    public Iterator<Resource> listChildren(Resource parent) {
        return null;
    }
}
