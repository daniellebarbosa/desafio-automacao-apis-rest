package tests;

import io.restassured.http.Header;
import io.restassured.response.Response;
import org.junit.Test;
import utils.BasePage;
import utils.Constants;
import utils.RequestBase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;

public class CommentsTests extends BasePage {
    RequestBase requestBase = new RequestBase();

    @Test
    public void getAllCommentsByUser(){
        Header header = new Header("Authorization", "Bearer 89b1b15281dd9bb1dfe94b2eee7c94dfa9ad1410");
        List<Header> headerList = new ArrayList<>();
        headerList.add(header);

        String path = Constants.GETCOMMENTSBYUSER_ENDPOINT.replace("{username}", "danigb");

        Response response = requestBase.executeGet(path, headerList);
        response.then()
                .statusCode(200)
                .assertThat().body("success", equalTo(true));

        System.out.println(response.body().asString());
    }

    @Test
    public void createComment(){
        Header headerPost = new Header("Authorization", "Bearer 89b1b15281dd9bb1dfe94b2eee7c94dfa9ad1410");
        Header headerGet = new Header("Authorization", Constants.CLIENTID_HEADER);
        List<Header> headerList = new ArrayList<>();
        headerList.add(headerPost);

        Map<String, Object> body = new HashMap<>();
        body.put("image_id", "LAj3d44");
        body.put("comment", "I'm a pug!");

        String pathPost = Constants.POSTCREATECOMMENT_ENDPOINT;

        Response response = requestBase.executePostWithBody(pathPost, headerList, requestBase.buildJson(body));
        response.then()
                .statusCode(200);

        Long id = response.jsonPath().get("data.id");

        headerList.remove(headerPost);
        headerList.add(headerGet);

        String pathGet = Constants.GETCOMMENT_ENDPOINT.replace("{id}", id.toString());
        Response responseGet = requestBase.executeGet(pathGet,headerList);
        responseGet.then()
                .statusCode(200);
    }
}
