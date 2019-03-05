package org.whistlepost.resourceprovider.dynamodb;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.*;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.nosql.generic.adapter.AbstractNoSqlAdapter;
import org.apache.sling.nosql.generic.adapter.NoSqlData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class DynamoDBNoSqlAdapter extends AbstractNoSqlAdapter {

    private static final Logger LOG = LoggerFactory.getLogger(DynamoDBNoSqlAdapter.class);

    private final AmazonDynamoDB dynamoDB;

    private final String tableName;

    public DynamoDBNoSqlAdapter(AmazonDynamoDB dynamoDB, String tableName) {
        this.dynamoDB = dynamoDB;
        this.tableName = tableName;
    }

    @Override
    public NoSqlData get(String path) {
        List<Map<String, AttributeValue>> properties = getPathProperties(path, dynamoDB);

        return new NoSqlData(path, propertyMap(properties));
    }

    @Override
    public Iterator<NoSqlData> getChildren(String parentPath) {
        final List<Map<String, AttributeValue>> properties = getChildPathProperties(parentPath, dynamoDB);
        Set<String> paths = new HashSet<>();
        for (Map<String, AttributeValue> property : properties) {
            paths.add(property.get("PATH").getS());
        }

        final Iterator<String> pathIterator = paths.iterator();
        return new Iterator<NoSqlData>() {
            @Override
            public boolean hasNext() {
                return pathIterator.hasNext();
            }

            @Override
            public NoSqlData next() {
                String path = pathIterator.next();
                List<Map<String, AttributeValue>> pathProperties = new ArrayList<>();
                for (Map<String, AttributeValue> property : properties) {
                    if (path.equals(property.get("PATH").getS())) {
                        pathProperties.add(property);
                    }
                }
                return new NoSqlData(path, propertyMap(pathProperties));
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Iterator removal not supported");
            }
        };
    }

    private List<Map<String, AttributeValue>> getPathProperties(String path, AmazonDynamoDB client) {
        QueryRequest request = new QueryRequest().withTableName(tableName)
                .withKeyConditionExpression(String.format("partitionKeyName = :%s", path));
        return client.query(request).getItems();
    }

    private List<Map<String, AttributeValue>> getChildPathProperties(String parentPath, AmazonDynamoDB client) {
        ScanRequest request = new ScanRequest().withTableName(tableName)
                .withFilterExpression(String.format("begins_with(PATH, :%s)", parentPath));
        return client.scan(request).getItems();
    }

    private Map<String, Object> propertyMap(List<Map<String, AttributeValue>> properties) {
        Map<String, Object> retVal = new HashMap<>();

        for (Map<String, AttributeValue> property: properties){
            String propertyName = property.get("NAME").getS();
            String propertyType = property.get("TYPE").getS();
            Object propertyValue;
            if ("String".equals(propertyType)) {
                propertyValue = property.get("VALUE").getS();
            } else if ("Integer".equals(propertyType)) {
                propertyValue = Integer.parseInt(property.get("VALUE").getS());
            } else if ("Long".equals(propertyType)) {
                propertyValue = Long.parseLong(property.get("VALUE").getS());
            } else if ("Double".equals(propertyType)) {
                propertyValue = Double.parseDouble(property.get("VALUE").getS());
            } else if ("Boolean".equals(propertyType)) {
                propertyValue = Boolean.parseBoolean(property.get("VALUE").getS());
            } else {
                LOG.error("Unsupported property type: " + propertyType);
                continue;
            }

            retVal.put(propertyName, propertyValue);
        }
        return retVal;
    }

    @Override
    public boolean store(NoSqlData data) {
        for (String property : data.getProperties().keySet()) {
            Object propertyValue = data.getProperties().get(property);
            putProperty(data.getPath(), property, propertyValue, dynamoDB);
        }
        return true;
    }

    private void putProperty(String path, String propertyName, Object propertyValue, AmazonDynamoDB client) {
        Map<String, AttributeValue> item = new HashMap<>();
        item.put("PATH", new AttributeValue(path));
        item.put("NAME", new AttributeValue(propertyName));
        String propertyType;
        if (propertyValue instanceof String) {
            propertyType = "String";
        } else if (propertyValue instanceof Integer) {
            propertyType = "Integer";
        } else if (propertyValue instanceof Long) {
            propertyType = "Long";
        } else if (propertyValue instanceof Double) {
            propertyType = "Double";
        } else if (propertyValue instanceof Boolean) {
            propertyType = "Boolean";
        } else {
            throw new IllegalArgumentException("Unsupported property type: " + propertyValue.getClass().getName());
        }
        item.put("TYPE", new AttributeValue(propertyType));
        item.put("VALUE", new AttributeValue(String.valueOf(propertyValue)));

        PutItemRequest request = new PutItemRequest().withTableName(tableName).withItem(item);
        client.putItem(request);
    }

    @Override
    public boolean deleteRecursive(String path) {
        final List<Map<String, AttributeValue>> properties = getChildPathProperties(path, dynamoDB);
        for (Map<String, AttributeValue> property : properties) {
            removeProperty(property, dynamoDB);
        }
        return !properties.isEmpty();
    }

    private void removeProperty(Map<String, AttributeValue> key, AmazonDynamoDB client) {
        DeleteItemRequest request = new DeleteItemRequest().withTableName(tableName).withKey(key);
        client.deleteItem(request);
    }

    @Override
    public void checkConnection() throws LoginException {
        // the query is not relevant, just the successful round-trip
        try {
            dynamoDB.scan(tableName, Collections.singletonList("PATH")).getCount();
        } catch (InternalServerErrorException e) {
            throw new LoginException(e);
        }
    }

    @Override
    public void createIndexDefinitions() {

    }
}
