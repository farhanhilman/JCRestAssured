package com.juaracoding;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCRUD {

    String baseUrl = "http://localhost:8081/api";
    String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIyIiwiaWF0IjoxNzE5NzczNTYzLCJleHAiOjE3MTk3NzcxNjN9._d_GPCnTG5BJbbgM-xgrDUIlUXUWujmDTlYmsgdaw39LediQNg6YTv-7AzTMEKrC03y5DWBT2c_PbAIPIPJE6Q";
    JSONObject requestBody;

    @Test(priority = 1)
    public void testCreateAlbum() {
        String endpoint = baseUrl+"/albums";
        requestBody = new JSONObject();
        requestBody.put("title", "Album Farhan");
        requestBody.put("description", "Ini adalah Album Farhan Hilman");

        RequestSpecification request = RestAssured.given();
        request.header("Authorization", "Bearer " + token);
        request.header("Content-Type", "application/json");
        request.body(requestBody.toJSONString());

        Response response = request.post(endpoint);
        Assert.assertEquals(response.getStatusCode(), 201);

        String albumId = response.getBody().jsonPath().getString("id");
        Assert.assertNotNull(albumId);
    }

    @Test(priority = 2)
    public void testReadAlbums() {
        String endpoint = baseUrl+"/albums/1";

        RequestSpecification request = RestAssured.given();
        request.header("Authorization", "Bearer " + token);
        request.header("Content-Type", "application/json");

        Response response = request.get(endpoint);
        Assert.assertEquals(response.getStatusCode(), 200);

        String title = response.getBody().jsonPath().getString("title");
        Assert.assertEquals(title, "New Album");
    }

    @Test(priority = 3)
    public void testUpdateAlbum() {
        String endpoint = baseUrl + "/albums/3";

        requestBody = new JSONObject();
        requestBody.put("title", "album farhan update");
        requestBody.put("description", "ini adalah album farhan yang telah di update");

        RequestSpecification request = RestAssured.given();
        request.header("Authorization", "Bearer " + token);
        request.header("Content-Type", "application/json");
        request.body(requestBody.toJSONString());

        Response response = request.get(endpoint);
        Assert.assertEquals(response.getStatusCode(), 200);

        String title = response.getBody().jsonPath().getString("title");
        Assert.assertEquals(title, "album farhan update");
    }

    @Test(priority = 4)
    public void testDeleteAlbum() {

        String endpoint = baseUrl+"/albums/23";

        RequestSpecification request = RestAssured.given();
        request.header("Authorization", "Bearer " + token);
        request.header("Content-Type", "application/json");

        Response response = request.delete(endpoint);
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody().asString());
        Assert.assertTrue(response.getStatusCode() == 200 || response.getStatusCode() == 204);
    }
}



