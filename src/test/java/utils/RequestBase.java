package utils;

import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class RequestBase {

    public Response executePostWithBody(String path, List<Header> headers, String json){
        Headers header = new Headers(headers);

        return given()
                .contentType(ContentType.JSON)
                .headers(header)
                .body(json)
                .when()
                .post(path);
    }


    public Response executePostWithBody(String path, String json){
        return given()
                .contentType(ContentType.JSON)
                .body(json)
                .when()
                .post(path);
    }

    public Response executePost(String path, List<Header> headers){
        Headers header = new Headers(headers);

        return given()
                .contentType(ContentType.JSON)
                .headers(header)
                .when()
                .post(path);

    }

    public Response executeGet(String path, List<Header> headers){
        Headers header = new Headers(headers);

        return given()
                .contentType(ContentType.JSON)
                .headers(header)
                .when()
                .get(path);
    }
    
    public Response executeDelete(String path, List<Header> headers){
        Headers header = new Headers(headers);

        return given()
                .contentType(ContentType.JSON)
                .headers(header)
                .when()
                .delete(path);
    }

    public String buildJson(Map<String, Object> body){
        Gson gson = new Gson();
        return gson.toJson(body);
    }

}
