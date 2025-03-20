package com.store.tests;

import com.store.base.BaseTest;
import com.store.utils.RequestBuilder;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class StoreInventoryTests extends BaseTest {

    @Test(description = "Test getting inventory", groups = {"smoke", "regression"})
    public void testGetInventory() {
        RequestBuilder request = RequestBuilder.builder()
                .build();
        Response response = apiUtils.sendGetRequest(INVENTORY_ENDPOINT, request);
        String contentType = response.getContentType();

        Assert.assertEquals(response.getStatusCode(), 200,
                "Inventory should be retrieved successfully");
        Assert.assertTrue(contentType.contains("application/json"),
                "Response should be in JSON format, but found: " + contentType);
    }

    @Test(description = "Test wrong method inventory endpoint", groups = {"regression", "negative"})
    public void testInventoryWrongMethod() {
        RequestBuilder request =RequestBuilder.builder()
                .build();
        Response response = apiUtils.sendDeleteRequest(INVENTORY_ENDPOINT, request);

        Assert.assertEquals(response.getStatusCode(), 405, "Method should return 405");
    }
}
