package com.store.utils;

import static io.restassured.RestAssured.given;

import io.restassured.specification.RequestSpecification;
import lombok.Builder;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Builder
@Getter
public class RequestBuilder {
    @Builder.Default
    private final Map<String, String> headers = new HashMap<>();
    private String pathParam;
    @Builder.Default
    private String contentType = "application/json";
    private Object body;

    public RequestSpecification build() {
        RequestSpecification request = given()
                .contentType(contentType);

        if (!headers.isEmpty()) {
            request.headers(headers);
        }

        if (body != null) {
            request.body(body);
        }

        return request;
    }
}
