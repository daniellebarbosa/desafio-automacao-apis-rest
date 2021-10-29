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
    public static final String GETACCOUNT_ENDPOINT = "/3/account/{{username}}";
    public static final String GETCOMMENTSBYUSER_ENDPOINT = "/3/account/{{username}}/comments";
    public static final String POSTCREATECOMMENT_ENDPOINT = "/3/comment";
    public static final String DELETECOMMENT_ENDPOINT = "/3/comment/{{commentId}}";
    public static final String GETREPLIES_ENDPOINT = "/3/comment/{{commentId}}/replies";
    public static final String POSTCREATEREPLY_ENDPOINT = "/3/comment/{{commentId}}";
    public static final String GETCOMMENT_ENDPOINT = "/3/comment/{{id}}";
    public static final String GETTOKEN_ENDPOINT = "oauth2/token";
    public static final String GETALBUM_ENDPOINT = "/3/album/{{albumHash}}";
    public static final String CREATEALBUM_ENDPOINT = "/3/album";
    public static final String DELTEALBUM_ENDPOINT = "/3/album/{{albumHash}}";
    public static final String FAVORITEALBUM_ENDPOIT = "/3/album/{{albumHash}}/favorite";
    public static final String CREATEBLOCK_ENDPOINT = "/account/v1/{{username}}/block";
    public static final String ACCOUNTBLOCK_ENDPOINT = "/3/account/me/block";
    public static final String DELTEBLOCK_ENDPOINT = "/account/v1/{{username}}/block";
    public static final String VERIFYBLOCK_ENDPOINT = "/account/v1/{{username}}/block";
    public static final String GETACCOUNTIMAGE_ENDPOINT = "/3/account/me/images";
    public static final String GETAVATARACCOUNT_ENDPOINT = "/3/account/{{username}}/avatar";
    public static final String GETAVATARAVAILABLE_ENDPOINT = "/3/account/{{username}}/available_avatars";
    public static final String GETSETTINGS_ENDPOINT = "/3/account/me/settings";
    public static final String CHANGESETTINGS_ENDPOINT = "/3/account/{{username}}/settings";
    public static final String GETACCOUNTIMAGEPROFILE_ENDPOINT = "/3/account/{{username}}/settings";
    public static final String POSTVERIFICATIONEMAIL_ENDPOINT = "/3/account/{{username}}/verifyemail";
    public static final String GETALBUMCOUNT_ENDPOINT = "/3/account/{{username}}/albums/count";
    public static final String GETCOMMENTS_ENDPOINT = "/3/account/{{username}}/comment/{{commentId}}";
    public static final String GETCOMMENTSCOUNT_ENDPOINT = "/3/account/{{username}}/comments/count";
    public static final String ACCOUNTIMAGE_ENDPOINT = "/3/account/{{username}}/image/{{imageId}}";
    public static final String FOLLOWTAG_ENDPOINT = "/3/account/me/follow/tag/{{tagName}}";
    public static final String GALLERYTAGS_ENDPOINT = "/3/tags";
    public static final String GALLERYTAGINFO_ENDPOINT = "/3/gallery/tag_info/{{tagName}}";
    public static final String TAGSGALLERY_ENDPOINT = "/3/gallery/{{galleryHash}}/tags";
    public static final String GALLERYALBUM_ENDPOINT = "/3/gallery/album/{{galleryHash}}";
    public static final String IMAGEREPORTING_ENDPOINT = "/3/gallery/image/{{galleryHash}}/report";
    public static final String IMAGEVOTES_ENDPOINT = "/3/gallery/{{galleryHash}}/votes";
    public static final String CREATEIMAGECOMMENT_ENDPOINT = "/3/gallery/{{galleryHash}}/comment";
    public static final String UPDATEIMAGEINFO_ENDPOINT = "/3/image/{{imageHash}}";
    public static final String FAVORITEIMAGE_ENDPOINT = "/3/image/{{imageHash}}/favorite";
    
}
