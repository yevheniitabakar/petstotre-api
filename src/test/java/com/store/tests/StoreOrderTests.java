package com.store.tests;

import com.store.base.BaseTest;
import com.store.models.Order;
import com.store.utils.RequestBuilder;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class StoreOrderTests extends BaseTest {

    @Test(description = "Test placing a valid order", groups = {"smoke", "regression"})
    public void testPlaceOrder() {
        Order order = createDefaultOrder();
        RequestBuilder requestBuilder = RequestBuilder.builder()
                .body(order)
                .build();
        Response response = apiUtils.sendPostRequest(ORDER_ENDPOINT, requestBuilder);

        Assert.assertEquals(response.getStatusCode(), 200, "Order should be placed successfully");
        Assert.assertEquals(response.jsonPath().getLong("id"), DEFAULT_ORDER_ID);
    }

    @Test(description = "Test getting order by ID", groups = {"smoke", "regression"})
    public void testGetOrderById() {
        Order order = createDefaultOrder();
        RequestBuilder createOrderBuilder = RequestBuilder.builder()
                .body(order)
                .build();
        apiUtils.sendPostRequest(ORDER_ENDPOINT, createOrderBuilder);

        RequestBuilder requestBuilder = RequestBuilder.builder()
                .pathParam("74")
                .build();
        Response response = apiUtils.sendGetRequest(ORDER_ENDPOINT, requestBuilder);

        Assert.assertEquals(response.getStatusCode(), 200, "Order should be retrieved successfully");
        Assert.assertEquals(response.jsonPath().getInt("id"), DEFAULT_ORDER_ID);
    }

    @Test(description = "Test deleting order", groups = {"smoke", "regression"})
    public void testDeleteOrder() {
        Order order = createDefaultOrder();
        RequestBuilder createOrderBuilder = RequestBuilder.builder()
                .body(order)
                .build();
        apiUtils.sendPostRequest(ORDER_ENDPOINT, createOrderBuilder);

        RequestBuilder requestBuilder = RequestBuilder.builder()
                .pathParam("74")
                .build();
        Response response = apiUtils.sendDeleteRequest(ORDER_ENDPOINT, requestBuilder);

        Assert.assertEquals(response.getStatusCode(), 200, "Order should be deleted successfully");
    }

    @Test(description = "Test getting non-existent order", groups = {"regression", "negative"})
    public void testGetNonExistingOrder() {
        RequestBuilder requestBuilder = RequestBuilder.builder()
                .pathParam("999999")
                .build();
        Response response = apiUtils.sendGetRequest(ORDER_ENDPOINT, requestBuilder);

        Assert.assertEquals(response.getStatusCode(), 404, "Should return not found for non-existent order");
    }

    @Test(description = "Test placing order with invalid data", groups = {"regression", "negative"})
    public void testPlaceWrongOrder() {
        Order invalidOrder = new Order(-1, -1, 0, "invalid-date", "invalid", false);
        RequestBuilder requestBuilder = RequestBuilder.builder()
                .body(invalidOrder)
                .build();
        Response response = apiUtils.sendPostRequest(ORDER_ENDPOINT, requestBuilder);

        Assert.assertEquals(response.getStatusCode(), 400, "Should return bad request for invalid order");
    }

    @Test(description = "Test deleting order with not valid Id", groups = {"regression", "negative"})
    public void testDeleteNonExistingOrder() {
        RequestBuilder requestBuilder = RequestBuilder.builder()
                .pathParam("-1")
                .build();
        Response response = apiUtils.sendDeleteRequest(ORDER_ENDPOINT, requestBuilder);

        Assert.assertEquals(response.getStatusCode(), 404, "Should return not found for non-existent order");
    }

    @Test(description = "Test deleting order without Id", groups = {"regression", "negative"})
    public void testDeleteOrderMissingId() {
        RequestBuilder requestBuilder = RequestBuilder.builder()
                .build();
        Response response = apiUtils.sendDeleteRequest(ORDER_ENDPOINT, requestBuilder);

        Assert.assertEquals(response.getStatusCode(), 400, "Should return bad request for absent order Id");
    }
}
