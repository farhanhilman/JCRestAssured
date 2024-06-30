package com.juaracoding;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestUserProfile {

    String baseUrl = "http://localhost:8081/api";

    @Test
    public void testGetUserProfile() {

        String endpoint = baseUrl+"/users/me";
        JSONObject request = new JSONObject();

        String tokenProfile = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIyIiwiaWF0IjoxNzE5NzczNTYzLCJleHAiOjE3MTk3NzcxNjN9._d_GPCnTG5BJbbgM-xgrDUIlUXUWujmDTlYmsgdaw39LediQNg6YTv-7AzTMEKrC03y5DWBT2c_PbAIPIPJE6Q";

        RequestSpecification requestBody = RestAssured.given();
        requestBody.header("Authorization", "Bearer " + tokenProfile);
        requestBody.header("Content-Type", "application/json");

        System.out.println(request.toJSONString());

        Response response = requestBody.get(endpoint);
        Assert.assertEquals(response.getStatusCode(), 200);


        String id = response.getBody().jsonPath().getString("id");
        System.out.println(id);
        String firstName = response.getBody().jsonPath().getString("firstName");
        System.out.println(firstName);
        String lastName = response.getBody().jsonPath().getString("lastName");
        System.out.println(lastName);
        String username = response.getBody().jsonPath().getString("username");
        System.out.println(username);

        Assert.assertEquals(id, "2");
        Assert.assertEquals(firstName, "farhan");
        Assert.assertEquals(lastName, "hilman");
        Assert.assertEquals(username, "farhan");
    }
}
