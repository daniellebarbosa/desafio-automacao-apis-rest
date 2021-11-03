package tests;

import com.google.gson.Gson;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import org.testng.annotations.Test;
import utils.BasePage;
import utils.Constants;
import utils.DataBase;
import utils.RequestBase;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;

public class TokenTests extends BasePage {
    RequestBase requestBase = new RequestBase();

    @Test
    public void token(){
        DataBase dataBase = new DataBase();

        Map<String, String> dataBaseTokenResult = dataBase.selectNewToken();

        Map<String, Object> body = new HashMap<>();
        body.put("grant_type", dataBaseTokenResult.get("grant_type"));
        body.put("refresh_token", dataBaseTokenResult.get("refresh_token"));
        body.put("client_id", dataBaseTokenResult.get("client_id"));
        body.put("client_secret", dataBaseTokenResult.get("client_secret"));

        Response response = requestBase.executePostWithBody(Constants.GETTOKEN_ENDPOINT, requestBase.buildJson(body));
        response.then()
                .assertThat().body("account_username", equalTo("danigb"));

        String refreshToken = response.getBody().jsonPath().get("refresh_token").toString();

        dataBase.insertNewToken(refreshToken,Constants.GRANT_TYPE, Constants.CLIENTID, Constants.CLIENTSECRET);
    }
}
