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

public class GalleryTests extends BasePage {
    RequestBase requestBase = new RequestBase();

    @Test
    public void getGalleryTags(){
    	Header header = new Header("Authorization", Constants.CLIENTID_HEADER);
        List<Header> headerList = new ArrayList<>();
        headerList.add(header);

        String path = Constants.GALLERYTAGS_ENDPOINT;

        Response response = requestBase.executeGet(path, headerList);
        response.then()
                .statusCode(200);
    }

    @Test
    public void getGalleryTagsInfoNotExists(){
    	Header header = new Header("Authorization", Constants.CLIENTID_HEADER);
        List<Header> headerList = new ArrayList<>();
        headerList.add(header);

        String path = Constants.GALLERYTAGINFO_ENDPOINT.replace("{{tagName}}", "tagnotexists");

        Response response = requestBase.executeGet(path, headerList);
        response.then()
                .statusCode(404);
    }

    @Test
    public void getTagsGallery(){
    	Header header = new Header("Authorization", Constants.CLIENTID_HEADER);
        List<Header> headerList = new ArrayList<>();
        headerList.add(header);

        String path = Constants.TAGSGALLERY_ENDPOINT.replace("{{galleryHash}}", "140ZdGx");

        Response response = requestBase.executeGet(path, headerList);
        response.then()
                .statusCode(200);
    }

    @Test
    public void getGalleryAlbum(){
    	Header header = new Header("Authorization", Constants.CLIENTID_HEADER);
        List<Header> headerList = new ArrayList<>();
        headerList.add(header);

        String path = Constants.GALLERYALBUM_ENDPOINT.replace("{{galleryHash}}", "140ZdGx");

        Response response = requestBase.executeGet(path, headerList);
        response.then()
                .statusCode(200);
    }

    @Test
    public void getGalleryAlbumNotExists(){
    	Header header = new Header("Authorization", Constants.CLIENTID_HEADER);
        List<Header> headerList = new ArrayList<>();
        headerList.add(header);

        String path = Constants.GALLERYALBUM_ENDPOINT.replace("{{galleryHash}}", "140ZdGxdejhfhew");

        Response response = requestBase.executeGet(path, headerList);
        response.then()
                .statusCode(404);
    }

    @Test
    public void imageReporting(){
    	Header header = new Header("Authorization", token);
        List<Header> headerList = new ArrayList<>();
        headerList.add(header);

        Map<String, Object> body = new HashMap<>();
        body.put("reason", "1");

        String path = Constants.IMAGEREPORTING_ENDPOINT.replace("{{galleryHash}}", "140ZdGx");

        Response response = requestBase.executePostWithBody(path, headerList, requestBase.buildJson(body));
        response.then()
                .statusCode(200)
                .assertThat().body("data", equalTo(true));
    }

    @Test
    public void getImageVotes(){
    	Header header = new Header("Authorization", Constants.CLIENTID_HEADER);
        List<Header> headerList = new ArrayList<>();
        headerList.add(header);

        String path = Constants.IMAGEVOTES_ENDPOINT.replace("{{galleryHash}}", "140ZdGx");

        Response response = requestBase.executeGet(path, headerList);
        response.then()
                .statusCode(200);
    }
    
    @Test
    public void getImageVotesNotExists(){
    	Header header = new Header("Authorization", Constants.CLIENTID_HEADER);
        List<Header> headerList = new ArrayList<>();
        headerList.add(header);

        String path = Constants.IMAGEVOTES_ENDPOINT.replace("{{galleryHash}}", "140ZdGxdejhfhew");

        Response response = requestBase.executeGet(path, headerList);
        response.then()
                .statusCode(400);
    }

    @Test
    public void createCommentImageBadRequest(){
    	Header header = new Header("Authorization", token);
        List<Header> headerList = new ArrayList<>();
        headerList.add(header);

        Map<String, Object> body = new HashMap<>();
        body.put("comment", "Good!");

        String path = Constants.CREATEIMAGECOMMENT_ENDPOINT.replace("{{galleryHash}}", "LAj3d44");

        Response response = requestBase.executePostWithBody(path, headerList, requestBase.buildJson(body));
        response.then()
                .statusCode(400)
                .assertThat().body("data.error", equalTo("The parameter image_id is required."));
    }

    @Test
    public void createCommentImageNotExists(){
    	Header header = new Header("Authorization", token);
        List<Header> headerList = new ArrayList<>();
        headerList.add(header);

        Map<String, Object> body = new HashMap<>();
        body.put("comment", "Good");

        String path = Constants.CREATEIMAGECOMMENT_ENDPOINT.replace("{{galleryHash}}", "notexists");

        Response response = requestBase.executePostWithBody(path, headerList, requestBase.buildJson(body));
        response.then()
                .statusCode(400);
    }
    
    @Test
    public void updateImageInfoDenied(){
    	Header header = new Header("Authorization", token);
        List<Header> headerList = new ArrayList<>();
        headerList.add(header);

        Map<String, Object> body = new HashMap<>();
        body.put("title", "Heart");
        body.put("description", "This is an image of a heart outline.");

        String path = Constants.UPDATEIMAGEINFO_ENDPOINT.replace("{{imageHash}}", "IkPqj");

        Response response = requestBase.executePostWithBody(path, headerList, requestBase.buildJson(body));
        response.then()
                .statusCode(403);
    }
    
    @Test
    public void unfavoritedImage(){
    	Header header = new Header("Authorization", token);
        List<Header> headerList = new ArrayList<>();
        headerList.add(header);

        String path = Constants.FAVORITEIMAGE_ENDPOINT.replace("{{imageHash}}", "IkPqj");

        Response response = requestBase.executePost(path, headerList);
        response.then()
                .statusCode(200);
        
        Response response2 = requestBase.executePost(path, headerList);
        response2.then()
                .statusCode(200);
    }
    
}
