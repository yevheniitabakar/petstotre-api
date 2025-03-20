package com.store.utils;

import io.restassured.response.Response;

@FunctionalInterface
public interface Loggable {
    void logResponse(String requestUrl, Response response);
}
