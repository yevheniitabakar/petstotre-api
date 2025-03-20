package com.store.base;

import com.store.constants.Constants;
import com.store.models.Order;
import com.store.utils.ApiUtils;
import io.restassured.RestAssured;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.BeforeSuite;

public abstract class BaseTest {
    protected static final Logger LOG = LoggerFactory.getLogger(BaseTest.class);
    protected static final String ORDER_ENDPOINT = Constants.STORE_ORDER_ENDPOINT;
    protected static final String INVENTORY_ENDPOINT = Constants.STORE_INVENTORY_ENDPOINT;
    public static final int DEFAULT_ORDER_ID = 8;
    protected ApiUtils apiUtils = new ApiUtils();

    @BeforeSuite
    public void setup(ITestContext context) {
        RestAssured.baseURI = Constants.BASE_URL;
        LOG.info("Base URL set to: {}", Constants.BASE_URL);
    }

    protected Order createDefaultOrder() {
        return new Order(
                DEFAULT_ORDER_ID,
                1,
                2,
                "2025-03-18T10:00:00.000Z",
                "placed",
                true
        );
    }
}
