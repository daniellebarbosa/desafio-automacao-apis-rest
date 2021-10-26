package utils;

import io.restassured.RestAssured;
import org.junit.BeforeClass;

public class BasePage {
    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "https://api.imgur.com";
    }
}
