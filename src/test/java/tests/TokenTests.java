package tests;

import io.restassured.response.Response;
import org.junit.Test;
import utils.BasePage;
import utils.Constants;
import utils.RequestBase;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;

public class TokenTests extends BasePage {
    RequestBase requestBase = new RequestBase();

    @Test
    public void generateToken(){
        Map<String, Object> body = new HashMap<>();
        body.put("grant_type", "refresh_token");
        body.put("refresh_token", "efbc337e998bb77dd8f03cdd3e0c54f3f402ac82");
        body.put("client_id", Constants.CLIENTID);
        body.put("client_secret", Constants.CLIENTSECRET);


        Response response = requestBase.executePostWithBody(Constants.GETTOKEN_ENDPOINT, requestBase.buildJson(body));
        response.then()
                .assertThat().body("account_username", equalTo("danigb"));

        System.out.println(response.body().asString());
    }
}
