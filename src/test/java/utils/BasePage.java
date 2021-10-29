package utils;

import core.ExtentReport;
import core.LogStatus;
import io.restassured.RestAssured;
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

public class BasePage {

    protected StringWriter writer;
    protected PrintStream captor;

    protected String nameShell;
    protected int quantifyParams;

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

    public void writeRequestAndResponseInReport(String request,String response) {
        LogStatus.info("---- Request ---");
        formatAPIAndLogInReport(request);
        LogStatus.info("---- Response ---");
        formatAPIAndLogInReport(response);
    }

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = Constants.BASE_URL;
    }
}
