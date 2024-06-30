package com.juaracoding;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import netscape.javascript.JSObject;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCRUD {

    String baseUrl = "http://localhost:8081/api";
    String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI1IiwiaWF0IjoxNzE5NzYxNzM4LCJleHAiOjE3MTk3NjUzMzh9.3_lgoq0AxQ-MQrLCCy_Jpze1J7SRfeQlyR6hE2J6u9k2BajFFH-mcirOhG92Sjqxodp-AR_qxX7WbzWu88vwFA";
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
    public void testGetAlbums() {
        String endpoint = baseUrl+"/albums/3";

        RequestSpecification request = RestAssured.given();
        request.header("Authorization", "Bearer " + token);
        request.header("Content-Type", "application/json");

        Response response = request.get(endpoint);
        Assert.assertEquals(response.getStatusCode(), 200);

        String title = response.getBody().jsonPath().getString("title");
        Assert.assertEquals(title, "Album Satu");
    }

    @Test(priority = 3)
    public void testUpdateAlbum() {
        String endpoint = baseUrl + "/albums/1";
        requestBody = new JSONObject();
        requestBody.put("title", "album farhan update");
        requestBody.put("description", "ini adalah album farhan yang telah di update");

        RequestSpecification request = RestAssured.given();
        request.header("Authorization", "Bearer " + token);
        request.header("Content-Type", "application/json");
        request.body(requestBody.toJSONString());

        Response response = request.put(endpoint);
        Assert.assertEquals(response.getStatusCode(), 500);

        String title = response.getBody().jsonPath().getString("title");
        Assert.assertEquals(title, "Updated Album");
    }

}
