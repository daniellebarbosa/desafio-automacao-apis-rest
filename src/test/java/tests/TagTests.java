package tests;

import io.restassured.http.Header;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.BasePage;
import utils.Constants;
import utils.RequestBase;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;

public class TagTests extends BasePage {
    RequestBase requestBase = new RequestBase();

    @Test
    public void followAndUnfollowTag(){
    	Header header = new Header("Authorization", "Bearer 89b1b15281dd9bb1dfe94b2eee7c94dfa9ad1410");
        List<Header> headerList = new ArrayList<>();
        headerList.add(header);

        String path = Constants.FOLLOWTAG_ENDPOINT.replace("{{tagName}}", "food");

        Response response = requestBase.executePost(path, headerList);
        response.then()
                .statusCode(200);

        Response responseDelete = requestBase.executeDelete(path, headerList);
        responseDelete.then()
                .statusCode(200);
    }
    
    @Test
    public void followTagNotFound(){
    	Header header = new Header("Authorization", "Bearer 89b1b15281dd9bb1dfe94b2eee7c94dfa9ad1410");
        List<Header> headerList = new ArrayList<>();
        headerList.add(header);

        String path = Constants.FOLLOWTAG_ENDPOINT.replace("{{tagName}}", "tagnotexists");

        Response response = requestBase.executePost(path, headerList);
        response.then()
                .statusCode(404);
    }
    

    @Test
    public void unfollowTagNotFound(){
    	Header header = new Header("Authorization", "Bearer 89b1b15281dd9bb1dfe94b2eee7c94dfa9ad1410");
        List<Header> headerList = new ArrayList<>();
        headerList.add(header);

        String path = Constants.FOLLOWTAG_ENDPOINT.replace("{{tagName}}", "tagnotexists");

        Response responseDelete = requestBase.executeDelete(path, headerList);
        responseDelete.then()
                .statusCode(404);
    }

    @Test
    public void followTagAlreadyFollow(){
    	Header header = new Header("Authorization", "Bearer 89b1b15281dd9bb1dfe94b2eee7c94dfa9ad1410");
        List<Header> headerList = new ArrayList<>();
        headerList.add(header);

        String path = Constants.FOLLOWTAG_ENDPOINT.replace("{{tagName}}", "dog");

        Response response2 = requestBase.executePost(path, headerList);
        response2.then()
        		.statusCode(409)
        		.assertThat().body("data.error", equalTo("Already following tag"));
    }
    
    @Test
    public void unfollowTagNotFollow(){
    	Header header = new Header("Authorization", "Bearer 89b1b15281dd9bb1dfe94b2eee7c94dfa9ad1410");
        List<Header> headerList = new ArrayList<>();
        headerList.add(header);

        String path = Constants.FOLLOWTAG_ENDPOINT.replace("{{tagName}}", "xuxa");

        Response responseDelete2 = requestBase.executeDelete(path, headerList);
        responseDelete2.then()
                .statusCode(409)
                .assertThat().body("data.error", equalTo("Not following tag"));
    }

}
