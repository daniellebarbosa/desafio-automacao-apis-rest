package utils;

public class Constants {

    public static final String EXTENTREPORTPATH = System.getProperty("user.dir")+"/ExtentReports/index.html";
    public static final String EXTENTCONFIGFILEPATH = System.getProperty("user.dir") +"/src/test/resources/extentreport.xml";
    public static final String RUNMANAGERSHEET= "RUNMANAGER";
    public static final String TESTDATASHEETNAME = "getAccountExists";

    //Base Page
    public static final String BASE_URL = "https://api.imgur.com";
    public static final String PATH_SHEET = "src//test//resources//TestData.xls";
    public static final String PATH_SHEET_TESTE = "src//test//resources//dataDrivenFinal.xlsx";


    //Credentials
    public static final String CLIENTID_HEADER = "Client-ID 805ca6f3eca4ce9";
    public static final String CLIENTID = "805ca6f3eca4ce9";
    public static final String CLIENTSECRET = "0c98ddaa97e0ff17449bb9a79f0ee2adfcbd68f6";

    //Endpoints
    public static final String GETACCOUNT_ENDPOINT = "/3/account/{username}";
    public static final String GETCOMMENTSBYUSER_ENDPOINT = "/3/account/{username}/comments";
    public static final String POSTCREATECOMMENT_ENDPOINT = "/3/comment";
    public static final String GETCOMMENT_ENDPOINT = "/3/comment/{id}";
    public static final String GETTOKEN_ENDPOINT = "oauth2/token";
}
