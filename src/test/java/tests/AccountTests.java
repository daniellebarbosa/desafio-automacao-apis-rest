package tests;

import io.restassured.http.Header;
import io.restassured.response.Response;
import org.junit.Test;
import utils.BasePage;
import utils.RequestBase;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;

public class AccountTests extends BasePage {
    RequestBase requestBase = new RequestBase();

    @Test
    public void getAccountExists(){
        Header header = new Header("Authorization", "Client-ID 805ca6f3eca4ce9");
        List<Header> headerList = new ArrayList<Header>();
        headerList.add(header);

        String path = "/3/account/danigb";

        Response response = requestBase.executeGet(path, headerList);
        response.then()
                .statusCode(200)
                .assertThat().body("data.url", equalTo("danigb"));

        System.out.println(response.body().asString());
    }

    @Test
    public void getAccountNotExists(){
        Header header = new Header("Authorization", "Client-ID 805ca6f3eca4ce9");
        List<Header> headerList = new ArrayList<Header>();
        headerList.add(header);

        String path = "/3/account/joaopedefeijao12398";

        Response response = requestBase.executeGet(path, headerList);
        response.then()
                .statusCode(404);

        System.out.println(response.body().asString());
    }
}
