package utils;

import core.ExtentReport;
import core.LogStatus;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.commons.io.output.WriterOutputStream;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class BasePage {
    protected StringWriter writer;
    protected PrintStream captor;

    protected String token;

    @BeforeSuite
    public void setUpSuite() {
        ExtentReport.initialize();
    }

    @AfterSuite
    public void afterSuite() throws IOException {
        ExtentReport.report.flush();
        File htmlFile = new File(Constants.EXTENTREPORTPATH);
        Desktop.getDesktop().browse(htmlFile.toURI());
    }

    @BeforeMethod
    public void setUp() {
        writer = new StringWriter();
        captor = new PrintStream(new WriterOutputStream(writer), true);
    }

    protected void formatAPIAndLogInReport(String content) {
        String prettyPrint = content.replace("\n", "<br>");
        LogStatus.info("<pre>" + prettyPrint + "</pre>");
    }

    public void writeRequestAndResponseInReport(String request, String response) {
        LogStatus.info("---- Request ---");
        formatAPIAndLogInReport(request);
        LogStatus.info("---- Response ---");
        formatAPIAndLogInReport(response);
    }

    @BeforeClass
    public void setup() {
        generateToken();
        RestAssured.baseURI = Constants.BASE_URI;
    }

    public String generateToken() {
        RequestBase requestBase = new RequestBase();
        DataBase dataBase = new DataBase();

        Map<String, String> dataBaseTokenResult = dataBase.selectNewToken();

        Map<String, Object> body = new HashMap<>();
        body.put("grant_type", dataBaseTokenResult.get("grant_type"));
        body.put("refresh_token", dataBaseTokenResult.get("refresh_token"));
        body.put("client_id", dataBaseTokenResult.get("client_id"));
        body.put("client_secret", dataBaseTokenResult.get("client_secret"));

        Response response = requestBase.executePostWithBody(Constants.BASE_URI + Constants.GETTOKEN_ENDPOINT, requestBase.buildJson(body));

        String refreshToken = response.getBody().jsonPath().get("refresh_token").toString();

        dataBase.insertNewToken(refreshToken, Constants.GRANT_TYPE, Constants.CLIENTID, Constants.CLIENTSECRET);

        token = "Bearer "+response.getBody().jsonPath().get("access_token").toString();

        return token;
    }
}
