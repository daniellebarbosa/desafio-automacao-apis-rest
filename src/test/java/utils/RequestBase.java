package utils;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import java.util.List;

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

}
