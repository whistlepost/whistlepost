package org.whistlepost.resourceprovider.dynamodb;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import org.apache.felix.scr.annotations.*;
import org.apache.sling.api.resource.ResourceProvider;
import org.apache.sling.api.resource.ResourceProviderFactory;
import org.apache.sling.commons.osgi.PropertiesUtil;
import org.apache.sling.nosql.generic.adapter.MetricsNoSqlAdapterWrapper;
import org.apache.sling.nosql.generic.adapter.NoSqlAdapter;
import org.apache.sling.nosql.generic.resource.AbstractNoSqlResourceProviderFactory;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.event.EventAdmin;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * {@link ResourceProviderFactory} implementation that uses DynamoDB as persistence.
 */
@Component(immediate = true, metatype = true,
        name="org.whistlepost.resourceprovider.dynamodb.DynamoDBNoSqlResourceProviderFactory.factory.config",
        label = "Apache Sling NoSQL DynamoDB Resource Provider Factory",
        description = "Defines a resource provider factory with DynamoDB persistence.",
        configurationFactory = true, policy = ConfigurationPolicy.REQUIRE)
@Service(value = ResourceProviderFactory.class)
@Property(name = "webconsole.configurationFactory.nameHint",
        value = "Root paths: {" + DynamoDBNoSqlResourceProviderFactory.PROVIDER_ROOTS_PROPERTY + "}")
public class DynamoDBNoSqlResourceProviderFactory extends AbstractNoSqlResourceProviderFactory {

    @Property(label = "Root paths", description = "Root paths for resource provider.", cardinality = Integer.MAX_VALUE)
    static final String PROVIDER_ROOTS_PROPERTY = ResourceProvider.ROOTS;

    @Property(label = "AWS region",
            description = "DynamoDB region. Example: 'ap-southeast-1'",
            value = DynamoDBNoSqlResourceProviderFactory.AWS_REGION_DEFAULT)
    static final String AWS_REGION_PROPERTY = "awsRegion";
    private static final String AWS_REGION_DEFAULT = "us-west-2"; //Regions.DEFAULT_REGION.getName();

    @Property(label = "Endpoint",
            description = "DynamoDB endpoint to store resource data in.",
            value = DynamoDBNoSqlResourceProviderFactory.ENDPOINT_DEFAULT)
    static final String ENDPOINT_PROPERTY = "endpoint";
    private static final String ENDPOINT_DEFAULT = "sling";

    @Property(label = "Table",
            description = "DynamoDB table to store resource data in.",
            value = DynamoDBNoSqlResourceProviderFactory.TABLE_DEFAULT)
    static final String TABLE_PROPERTY = "table";
    private static final String TABLE_DEFAULT = "sling-resources";

    @Reference
    private EventAdmin eventAdmin;

    private AmazonDynamoDB dynamoDB;
    private NoSqlAdapter noSqlAdapter;

    @Activate
    private void activate(ComponentContext componentContext, Map<String, Object> config) {
        String awsRegion = PropertiesUtil.toString(config.get(AWS_REGION_PROPERTY), AWS_REGION_DEFAULT);
        String endpoint = PropertiesUtil.toString(config.get(ENDPOINT_PROPERTY), ENDPOINT_DEFAULT);
        String table = PropertiesUtil.toString(config.get(TABLE_PROPERTY), TABLE_DEFAULT);

        AmazonDynamoDBClientBuilder clientBuilder = AmazonDynamoDBClientBuilder.standard();
        dynamoDB = clientBuilder.withRegion(awsRegion)
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endpoint, awsRegion))
                .build();

        NoSqlAdapter dynamodbAdapter = new DynamoDBNoSqlAdapter(dynamoDB, table);

        // enable call logging and metrics for {@link MongoDBNoSqlAdapter}
        noSqlAdapter = new MetricsNoSqlAdapterWrapper(dynamodbAdapter, LoggerFactory.getLogger(DynamoDBNoSqlAdapter.class));
    }

    @Deactivate
    private void deactivate() {
        if (dynamoDB != null) {
            dynamoDB.shutdown();
        }
    }

    @Override
    protected NoSqlAdapter getNoSqlAdapter() {
        return noSqlAdapter;
    }

    @Override
    protected EventAdmin getEventAdmin() {
        return eventAdmin;
    }
}
