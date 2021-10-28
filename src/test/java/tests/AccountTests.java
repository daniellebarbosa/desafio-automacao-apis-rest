package tests;

import io.restassured.http.Header;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.BasePage;
import utils.Constants;
import utils.RequestBase;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;

public class AccountTests extends BasePage {
    RequestBase requestBase = new RequestBase();

    @BeforeTest
    public void Teste(){
        nameShell = "getAccountExists";
        quantifyParams = 2;
    }

    @Test(dataProvider="ProvideData")
    public void getAccountExists(String username, String id){

        Header header = new Header("Authorization", Constants.CLIENTID_HEADER);
        List<Header> headerList = new ArrayList<>();
        headerList.add(header);

        String path = Constants.GETACCOUNT_ENDPOINT.replace("{username}", username);

        Response response = requestBase.executeGet(path, headerList);
        response.then()
                .statusCode(200)
                .assertThat().body("data.id", equalTo(Integer.parseInt(id)));

        System.out.println(response.body().asString());
    }

//    @Test
//    public void getAccountNotExists(){
//        Header header = new Header("Authorization", Constants.CLIENTID_HEADER);
//        List<Header> headerList = new ArrayList<Header>();
//        headerList.add(header);
//
//        String path = Constants.GETACCOUNT_ENDPOINT.replace("{username}", "joaopedefeijao12398");
//
//        Response response = requestBase.executeGet(path, headerList);
//        response.then()
//                .statusCode(404);
//    }
}
