package com.ephemeralin.dao;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;

public class DynamoDBAdapter {

    private static DynamoDBAdapter db_adapter;
    private final AmazonDynamoDB client;
    private DynamoDBMapper mapper;

    private DynamoDBAdapter() {
        this.client = AmazonDynamoDBClientBuilder.standard()
                .withRegion(Regions.EU_CENTRAL_1)
                .build();
    }

    public static DynamoDBAdapter getInstance() {
        if (db_adapter == null)
            db_adapter = new DynamoDBAdapter();

        return db_adapter;
    }

    public DynamoDBMapper createDbMapper(DynamoDBMapperConfig mapperConfig) {
        if (this.client != null)
            mapper = new DynamoDBMapper(this.client, mapperConfig);

        return this.mapper;
    }

    public AmazonDynamoDB getDbClient() {
        return client;
    }
}
