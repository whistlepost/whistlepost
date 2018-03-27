package org.whistlepost.resourceprovider.dynamodb;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.amazonaws.services.dynamodbv2.model.InternalServerErrorException;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.amazonaws.services.dynamodbv2.model.TableDescription;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.nosql.generic.adapter.AbstractNoSqlAdapter;
import org.apache.sling.nosql.generic.adapter.NoSqlData;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DynamoDBNoSqlAdapter extends AbstractNoSqlAdapter {

    private final AmazonDynamoDB dynamoDB;

    private final String tableName;

    public DynamoDBNoSqlAdapter(AmazonDynamoDB dynamoDB, String tableName) {
        this.dynamoDB = dynamoDB;
        this.tableName = tableName;
        TableDescription tableDescription = dynamoDB.describeTable(tableName).getTable();
    }

    @Override
    public NoSqlData get(String path) {
        Map<String, Condition> scanFilter = new HashMap<>();
//        scanFilter.put("parent", new Condition().withComparisonOperator("EQ").withAttributeValueList(new AttributeValue(parent)));
//        scanFilter.put("child", new Condition().withComparisonOperator("EQ").withAttributeValueList(new AttributeValue(child)));
//        ScanFilter parentFilter = new ScanFilter("parent").eq(parent);
//        ScanFilter childFilter = new ScanFilter("child_id").eq(child);
        ScanResult scanResult = dynamoDB.scan(tableName, scanFilter);
        if (scanResult == null) {
            return null;
        }
        else {
//            return new NoSqlData(path, scanResult.getItems().get(0), MultiValueMode.LISTS);
            return null;
        }
    }

    @Override
    public Iterator<NoSqlData> getChildren(String parentPath) {
        return null;
    }

    @Override
    public boolean store(NoSqlData data) {
        return false;
    }

    @Override
    public boolean deleteRecursive(String path) {
        return false;
    }

    @Override
    public void checkConnection() throws LoginException {
        // the query is not relevant, just the successful round-trip
        try {
            dynamoDB.scan(tableName, Arrays.asList("id")).getCount();
        } catch (InternalServerErrorException e) {
            throw new LoginException(e);
        }
    }

    @Override
    public void createIndexDefinitions() {

    }
}
