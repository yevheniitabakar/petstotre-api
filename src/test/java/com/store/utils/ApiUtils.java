package com.store.utils;

import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApiUtils implements Loggable {
    private final Logger LOG = LoggerFactory.getLogger(ApiUtils.class);

    @Override
    public void logResponse(String requestUrl, Response response) {
        LOG.info("Request URL: {} | Status Code: {} | Response: {}",
                requestUrl, response.getStatusCode(), response.asString());
    }

    public Response sendGetRequest(String endpoint, RequestBuilder builder) {
        String finalEndpoint = endpoint;
        if(builder.getPathParam() != null) {
            finalEndpoint = finalEndpoint + "/" + builder.getPathParam();
        }

        Response response = builder.build()
                .when()
                .get(finalEndpoint)
                .then()
                .extract()
                .response();
        logResponse(finalEndpoint, response);

        return response;
    }

    public Response sendPostRequest(String endpoint, RequestBuilder builder) {
        Response response = builder.build()
                .when()
                .post(endpoint)
                .then()
                .extract()
                .response();
        logResponse(endpoint, response);

        return response;
    }

    public Response sendDeleteRequest(String endpoint, RequestBuilder builder) {
        String finalEndpoint = endpoint;
        if (builder.getPathParam() != null) {
            finalEndpoint = finalEndpoint + "/" + builder.getPathParam();
        }

        Response response = builder.build()
                .when()
                .delete(finalEndpoint)
                .then()
                .extract()
                .response();
        logResponse(finalEndpoint, response);

        return response;
    }
}
