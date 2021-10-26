package tests;

import com.google.gson.Gson;
import io.restassured.response.Response;
import org.junit.Test;
import utils.BasePage;
import utils.RequestBase;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;

public class TokenTests extends BasePage {
    RequestBase requestBase = new RequestBase();

    @Test
    public void generateToken() throws IOException{
        Map<String, Object> body = new HashMap<String, Object>();
        body.put("grant_type", "refresh_token");
        body.put("refresh_token", "efbc337e998bb77dd8f03cdd3e0c54f3f402ac82");
        body.put("client_id", "805ca6f3eca4ce9");
        body.put("client_secret", "0c98ddaa97e0ff17449bb9a79f0ee2adfcbd68f6");


        Response response = requestBase.executePostWithBody("/oauth2/token", buildJson(body));
        response.then().assertThat().body("account_username", equalTo("danigb"));

        System.out.println(response.body().asString());
    }

    public String buildJson(Map<String, Object> body){
        Gson gson = new Gson();
        return gson.toJson(body);
    }
}
