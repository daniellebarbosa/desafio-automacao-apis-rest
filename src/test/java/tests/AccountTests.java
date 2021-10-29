package tests;

import io.restassured.http.Header;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.BasePage;
import utils.Constants;
import utils.RequestBase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;

public class AccountTests extends BasePage {
    RequestBase requestBase = new RequestBase();

    @Test(dataProvider = "dataProviderForIterations")
    public void getAccountExists(Hashtable<String , String> data) {
        List<String> codeStatus = Arrays.asList(data.get("statusCodeResult").split("\\."));
        int codeStatusExpected = Integer.parseInt(codeStatus.get(0));

        Header header = new Header("Authorization", Constants.CLIENTID_HEADER);
        List<Header> headerList = new ArrayList<>();
        headerList.add(header);

        String path = Constants.GETACCOUNT_ENDPOINT.replace("{{username}}", data.get("username"));

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
         
}
