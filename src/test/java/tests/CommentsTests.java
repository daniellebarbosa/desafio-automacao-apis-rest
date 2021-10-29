package tests;

import io.restassured.http.Header;
import io.restassured.response.Response;
import org.testng.annotations.Test;
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

        String path = Constants.GETCOMMENTSBYUSER_ENDPOINT.replace("{{username}}", "danigb");

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

        String pathGet = Constants.GETCOMMENT_ENDPOINT.replace("{{id}}", id.toString());
        Response responseGet = requestBase.executeGet(pathGet,headerList);
        responseGet.then()
                .statusCode(200);
    }
    
    @Test
    public void deleteComment(){
        Header headerPostDelete = new Header("Authorization", "Bearer 89b1b15281dd9bb1dfe94b2eee7c94dfa9ad1410");
        Header headerGet = new Header("Authorization", Constants.CLIENTID_HEADER);
        List<Header> headerList = new ArrayList<>();
        headerList.add(headerPostDelete);

        Map<String, Object> body = new HashMap<>();
        body.put("image_id", "LAj3d44");
        body.put("comment", "I'm a pug!");

        String pathPost = Constants.POSTCREATECOMMENT_ENDPOINT;

        Response response = requestBase.executePostWithBody(pathPost, headerList, requestBase.buildJson(body));
        response.then()
                .statusCode(200);

        Long id = response.jsonPath().get("data.id");

        headerList.remove(headerPostDelete);
        headerList.add(headerGet);

        String pathGet = Constants.GETCOMMENT_ENDPOINT.replace("{{id}}", id.toString());
        Response responseGet = requestBase.executeGet(pathGet,headerList);
        responseGet.then()
                .statusCode(200);
        
        headerList.remove(headerGet);
        headerList.add(headerPostDelete);
        
        String pathDelete = Constants.DELETECOMMENT_ENDPOINT.replace("{{commentId}}", id.toString());
        Response responseDelete = requestBase.executeDelete(pathDelete,headerList);
        responseDelete.then()
                .statusCode(200);
    }
    
    @Test
    public void getReplies(){
    	Header header = new Header("Authorization", Constants.CLIENTID_HEADER);
        List<Header> headerList = new ArrayList<>();
        headerList.add(header);

        String path = Constants.GETREPLIES_ENDPOINT.replace("{{commentId}}", "15420");

        Response response = requestBase.executeGet(path, headerList);
        response.then()
                .statusCode(200)
                .assertThat().body("data.comment", equalTo("That's awesome!!"));
    }
    
    @Test
    public void getReplyNotExists(){
    	Header header = new Header("Authorization", Constants.CLIENTID_HEADER);
        List<Header> headerList = new ArrayList<>();
        headerList.add(header);

        String path = Constants.GETREPLIES_ENDPOINT.replace("{{commentId}}", "1542trs0");

        Response response = requestBase.executeGet(path, headerList);
        response.then()
                .statusCode(404);
    }
    
    @Test
    public void createReply(){
        Header header = new Header("Authorization", "Bearer 89b1b15281dd9bb1dfe94b2eee7c94dfa9ad1410");
        List<Header> headerList = new ArrayList<>();
        headerList.add(header);

        Map<String, Object> body = new HashMap<>();
        body.put("image_id", "IkPqj");
        body.put("comment", "I'm a pug!");

        String pathPost = Constants.POSTCREATEREPLY_ENDPOINT.replace("{{commentId}}", "15420");

        Response response = requestBase.executePostWithBody(pathPost, headerList, requestBase.buildJson(body));
        response.then()
                .statusCode(200);
    }
    
    @Test
    public void getComments(){
    	Header header = new Header("Authorization", Constants.CLIENTID_HEADER);
        List<Header> headerList = new ArrayList<>();
        headerList.add(header);

        String path = Constants.GETCOMMENTS_ENDPOINT.replace("{{username}}", "danigb").replace("{{commentId}}", "15420");

        Response response = requestBase.executeGet(path, headerList);
        response.then()
                .statusCode(200)
                .assertThat().body("data.image_id", equalTo("IkPqj"));
    }
      
    @Test
    public void invalidComment(){
    	Header header = new Header("Authorization", Constants.CLIENTID_HEADER);
        List<Header> headerList = new ArrayList<>();
        headerList.add(header);

        String path = Constants.GETCOMMENTS_ENDPOINT.replace("{{username}}", "danigb").replace("{{commentId}}", "15654523213");

        Response response = requestBase.executeGet(path, headerList);
        response.then()
                .statusCode(200)
                .assertThat().body("data.error", equalTo("Invalid Comment, 15654523213"));
    }    
    
    @Test
    public void commentCount(){
    	Header header = new Header("Authorization", Constants.CLIENTID_HEADER);
        List<Header> headerList = new ArrayList<>();
        headerList.add(header);

        String path = Constants.GETCOMMENTSCOUNT_ENDPOINT.replace("{{username}}", "danigb");

        Response response = requestBase.executeGet(path, headerList);
        response.then()
                .statusCode(200);
    }
}
