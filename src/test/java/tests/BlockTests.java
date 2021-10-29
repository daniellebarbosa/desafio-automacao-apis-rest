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

public class BlockTests extends BasePage {
    RequestBase requestBase = new RequestBase();

    @Test
    public void blockUser(){
    	Header header = new Header("Authorization", "Bearer 89b1b15281dd9bb1dfe94b2eee7c94dfa9ad1410");
        List<Header> headerList = new ArrayList<>();
        headerList.add(header);

        String pathPost = Constants.CREATEBLOCK_ENDPOINT.replace("{username}", "robertta");

        Response response = requestBase.executePost(pathPost, headerList);
        response.then()
                .statusCode(201);
        
        String pathGet = Constants.ACCOUNTBLOCK_ENDPOINT;

        Response responseGet = requestBase.executeGet(pathGet, headerList);
        responseGet.then()
                .statusCode(200);
        
        String pathDelete = Constants.DELTEBLOCK_ENDPOINT.replace("{username}", "robertta");

        Response responseDelete = requestBase.executeDelete(pathDelete, headerList);
        responseDelete.then()
                .statusCode(204);
    }
    
    @Test
    public void verifyUserNotBlock(){
    	Header header = new Header("Authorization", "Bearer 89b1b15281dd9bb1dfe94b2eee7c94dfa9ad1410");
        List<Header> headerList = new ArrayList<>();
        headerList.add(header);
        
        String path = Constants.VERIFYBLOCK_ENDPOINT.replace("{username}", "danigb");

        Response responseGet = requestBase.executeGet(path, headerList);
        responseGet.then()
                .statusCode(200)
                .assertThat().body("data.blocked", equalTo(false));
    }
    
    @Test
    public void deleteBlockUser(){
    	Header header = new Header("Authorization", "Bearer 89b1b15281dd9bb1dfe94b2eee7c94dfa9ad1410");
        List<Header> headerList = new ArrayList<>();
        headerList.add(header);

        String pathPost = Constants.CREATEBLOCK_ENDPOINT.replace("{username}", "joseph");

        Response response = requestBase.executePost(pathPost, headerList);
        response.then()
                .statusCode(201);       
        
        String pathDelete = Constants.DELTEBLOCK_ENDPOINT.replace("{username}", "joseph");

        Response responseDelete = requestBase.executeDelete(pathDelete, headerList);
        responseDelete.then()
                .statusCode(204);
        
        String pathGet = Constants.ACCOUNTBLOCK_ENDPOINT;

        Response responseGet = requestBase.executeGet(pathGet, headerList);
        responseGet.then()
                .statusCode(200);
    }

}
