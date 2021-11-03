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

public class AlbumTests extends BasePage {
    RequestBase requestBase = new RequestBase();

    @Test
    public void albumNotExists(){
    	Header header = new Header("Authorization", Constants.CLIENTID_HEADER);
        List<Header> headerList = new ArrayList<>();
        headerList.add(header);

        String path = Constants.GETALBUM_ENDPOINT.replace("{{albumHash}}", "gdewnf");

        Response response = requestBase.executeGet(path, headerList);
        response.then()
                .statusCode(404);
    }
    
    @Test
    public void createAlbumDenied(){
        Header header = new Header("Authorization", token);
        List<Header> headerList = new ArrayList<>();
        headerList.add(header);

        Map<String, Object> body = new HashMap<>();
        body.put("ids", "140ZdGx");
        body.put("title", "Dani");
        body.put("description", "Album da Dani");

        String path = Constants.CREATEALBUM_ENDPOINT;

        Response response = requestBase.executePostWithBody(path, headerList, requestBase.buildJson(body));
        if(response.getStatusCode() == 403){
            response.then()
                    .statusCode(403);
        }
        if(response.getStatusCode() == 429){
            response.then()
                    .statusCode(429)
                    .assertThat().body("data.error", equalTo("Album creation limit exceeded for 1 hour period"));
        }

    }    
    
    @Test
    public void deleteAlbumNotExists(){
        Header header = new Header("Authorization", token);
        List<Header> headerList = new ArrayList<>();
        headerList.add(header);

        String pathPost = Constants.DELTEALBUM_ENDPOINT.replace("{{albumHash}}", "gdewnf");

        Response response = requestBase.executeDelete(pathPost, headerList);
        response.then()
                .statusCode(404);
    }
    
    @Test
    public void favoriteAlbumNotExists(){
        Header header = new Header("Authorization", token);
        List<Header> headerList = new ArrayList<>();
        headerList.add(header);

        String pathPost = Constants.FAVORITEALBUM_ENDPOIT.replace("{{albumHash}}", "gdewnf");

        Response response = requestBase.executePost(pathPost, headerList);
        response.then()
                .statusCode(404);
    }  
    
    @Test
    public void albumCount(){
    	Header header = new Header("Authorization", Constants.CLIENTID_HEADER);
        List<Header> headerList = new ArrayList<>();
        headerList.add(header);

        String path = Constants.GETALBUMCOUNT_ENDPOINT.replace("{{username}}", "danigb");

        Response response = requestBase.executeGet(path, headerList);
        response.then()
                .statusCode(200);
    }
     
    @Test
    public void albumCountNotExists(){
    	Header header = new Header("Authorization", Constants.CLIENTID_HEADER);
        List<Header> headerList = new ArrayList<>();
        headerList.add(header);

        String path = Constants.GETALBUMCOUNT_ENDPOINT.replace("{{username}}", "albumnotexists");

        Response response = requestBase.executeGet(path, headerList);
        response.then()
                .statusCode(404);
    }

}
