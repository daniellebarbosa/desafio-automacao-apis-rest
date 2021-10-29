package tests;

import io.restassured.http.Header;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.BasePage;
import utils.Constants;
import utils.RequestBase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;

public class AccountTests extends BasePage {
    RequestBase requestBase = new RequestBase();

    @BeforeTest
    public void Teste() {
        nameShell = "getAccountExists";
        quantifyParams = 4;
    }

    @Test(dataProvider = "dataProviderForIterations")
    public void getAccountExists(Hashtable<String , String> data) {
        List<String> codeStatus = Arrays.asList(data.get("statusCodeResult").split("\\."));
        int codeStatusExpected = Integer.parseInt(codeStatus.get(0));

        Header header = new Header("Authorization", Constants.CLIENTID_HEADER);
        List<Header> headerList = new ArrayList<>();
        headerList.add(header);

        String path = Constants.GETACCOUNT_ENDPOINT.replace("{username}", data.get("username"));

        Response response = requestBase.executeGet(path, headerList);

        writeRequestAndResponseInReport(writer.toString(), response.prettyPrint());

        switch (codeStatusExpected) {
            case 200:
                response.then()
                        .statusCode(codeStatusExpected)
                        .assertThat().body("data.id", equalTo(Integer.parseInt(data.get("id"))));
                break;
            case 400:
                response.then()
                        .statusCode(codeStatusExpected)
                        .assertThat().body("data.error", equalTo(data.get("message")));
                break;
            case 404:
                response.then()
                        .statusCode(codeStatusExpected);
                break;
        }
    }
    
    @Test
    public void accountImages(){
    	Header header = new Header("Authorization", "Bearer 89b1b15281dd9bb1dfe94b2eee7c94dfa9ad1410");
        List<Header> headerList = new ArrayList<>();
        headerList.add(header);
        
        String path = Constants.GETACCOUNTIMAGE_ENDPOINT;

        Response response = requestBase.executeGet(path, headerList);
        response.then()
                .statusCode(200)
                .assertThat().body("success", equalTo(true));
    }
    
    @Test
    public void accountAvatar(){
    	Header header = new Header("Authorization", "Bearer 89b1b15281dd9bb1dfe94b2eee7c94dfa9ad1410");
        List<Header> headerList = new ArrayList<>();
        headerList.add(header);
        
        String path = Constants.GETAVATARACCOUNT_ENDPOINT.replace("{username}", "danigb");

        Response response = requestBase.executeGet(path, headerList);
        response.then()
                .statusCode(200)
                .assertThat().body("success", equalTo(true));
    }
    
    @Test
    public void avatarAvailable(){
    	Header header = new Header("Authorization", "Bearer 89b1b15281dd9bb1dfe94b2eee7c94dfa9ad1410");
        List<Header> headerList = new ArrayList<>();
        headerList.add(header);
        
        String path = Constants.GETAVATARAVAILABLE_ENDPOINT.replace("{username}", "danigb");

        Response response = requestBase.executeGet(path, headerList);
        response.then()
                .statusCode(200)
                .assertThat().body("success", equalTo(true));
    }
    
    @Test
    public void getSettings(){
    	Header header = new Header("Authorization", "Bearer 89b1b15281dd9bb1dfe94b2eee7c94dfa9ad1410");
        List<Header> headerList = new ArrayList<>();
        headerList.add(header);
        
        String path = Constants.GETSETTINGS_ENDPOINT;

        Response response = requestBase.executeGet(path, headerList);
        response.then()
                .statusCode(200)
                .assertThat().body("data.email", equalTo("danielle.barbosa@base2.com.br"));
    }
    
    @Test
    public void getSettingsDenied(){
    	Header header = new Header("Authorization", "Bearer ");
        List<Header> headerList = new ArrayList<>();
        headerList.add(header);
        
        String path = Constants.GETSETTINGS_ENDPOINT;

        Response response = requestBase.executeGet(path, headerList);
        response.then()
                .statusCode(403)
                .assertThat().body("data.error", equalTo("Malformed auth header"));
    }   
    
    @Test
    public void changeSettings(){
    	Header header = new Header("Authorization", "Bearer 89b1b15281dd9bb1dfe94b2eee7c94dfa9ad1410");
        List<Header> headerList = new ArrayList<>();
        headerList.add(header);
        
        Map<String, Object> body = new HashMap<>();
        body.put("bio", "Long time lurker...");

        String path = Constants.CHANGESETTINGS_ENDPOINT.replace("{username}", "danigb");

        Response response = requestBase.executePostWithBody(path, headerList, requestBase.buildJson(body));
        response.then()
                .statusCode(200);
    }
    
    @Test
    public void accountImageProfile(){
    	Header header = new Header("Authorization", "Bearer 89b1b15281dd9bb1dfe94b2eee7c94dfa9ad1410");
        List<Header> headerList = new ArrayList<>();
        headerList.add(header);

        String path = Constants.GETACCOUNTIMAGEPROFILE_ENDPOINT.replace("{username}", "danigb");

        Response response = requestBase.executeGet(path, headerList);
        response.then()
                .statusCode(200)
                .assertThat().body("data.account_url", equalTo("danigb"));
    }
    
    @Test
    public void accountImageProfileNotExists(){
    	Header header = new Header("Authorization", "Bearer 89b1b15281dd9bb1dfe94b2eee7c94dfa9ad1410");
        List<Header> headerList = new ArrayList<>();
        headerList.add(header);

        String path = Constants.GETACCOUNTIMAGEPROFILE_ENDPOINT.replace("{username}", "usernotexists");

        Response response = requestBase.executeGet(path, headerList);
        response.then()
                .statusCode(404);
    }
    
    @Test
    public void accountImageProfileDenied(){
    	Header header = new Header("Authorization", "Bearer 89b1b15281dd9bb1dfe94b2eee7c94dfa9ad1410");
        List<Header> headerList = new ArrayList<>();
        headerList.add(header);

        String path = Constants.GETACCOUNTIMAGEPROFILE_ENDPOINT.replace("{username}", "joseph");

        Response response = requestBase.executeGet(path, headerList);
        response.then()
                .statusCode(403)
                .assertThat().body("data.error", equalTo("Unauthorized"));
    }
    
    @Test
    public void sendVerificationEmail(){
    	Header header = new Header("Authorization", "Bearer 89b1b15281dd9bb1dfe94b2eee7c94dfa9ad1410");
        List<Header> headerList = new ArrayList<>();
        headerList.add(header);

        String path = Constants.POSTVERIFICATIONEMAIL_ENDPOINT.replace("{username}", "danigb");

        Response response = requestBase.executePost(path, headerList);
        response.then()
                .statusCode(200);
    }
    
    @Test
    public void verifyEmail(){
    	Header header = new Header("Authorization", "Bearer 89b1b15281dd9bb1dfe94b2eee7c94dfa9ad1410");
        List<Header> headerList = new ArrayList<>();
        headerList.add(header);

        String path = Constants.POSTVERIFICATIONEMAIL_ENDPOINT.replace("{username}", "danigb");

        Response response = requestBase.executePost(path, headerList);
        response.then()
                .statusCode(200)
                .assertThat().body("data", equalTo(false));
    }
    
    @Test
    public void accountImage(){
    	Header header = new Header("Authorization", "Bearer 89b1b15281dd9bb1dfe94b2eee7c94dfa9ad1410");
        List<Header> headerList = new ArrayList<>();
        headerList.add(header);

        String path = Constants.ACCOUNTIMAGE_ENDPOINT.replace("{username}", "danigb").replace("{imageId}", "IkPqj");

        Response response = requestBase.executeGet(path, headerList);
        response.then()
                .statusCode(200);
    }
    
    @Test
    public void accountImageNotExists(){
    	Header header = new Header("Authorization", "Bearer 89b1b15281dd9bb1dfe94b2eee7c94dfa9ad1410");
        List<Header> headerList = new ArrayList<>();
        headerList.add(header);

        String path = Constants.ACCOUNTIMAGE_ENDPOINT.replace("{username}", "danigb").replace("{imageId}", "NoTeXists");

        Response response = requestBase.executeGet(path, headerList);
        response.then()
                .statusCode(200)
                .assertThat().body("data.error", equalTo("Unable to find an image with the id, NoTeXists"));
    }
         
}
