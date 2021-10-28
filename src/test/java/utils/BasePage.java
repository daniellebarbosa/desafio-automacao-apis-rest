package utils;

import io.restassured.RestAssured;
import jxl.Sheet;
import jxl.Workbook;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;

public class BasePage {

    protected String nameShell;
    protected int quantifyParams;

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "https://api.imgur.com";
    }

    @DataProvider
    public Object[][] ProvideData() throws Exception{
        //open excel file
        FileInputStream stream = new FileInputStream("src//test//resources//TestData.xls");
        Workbook workbook = Workbook.getWorkbook(stream);
        //the required sheet
        Sheet sheet = workbook.getSheet(nameShell);
        //return number of rows(records)
        int records = sheet.getRows()-1;
        int currentPosition = 1;
        Object[][] values = new Object[records][quantifyParams];
        for(int i = 0 ; i < records ; i++, currentPosition++){
            //loop over the rows
            for(int j = 0 ; j < quantifyParams ; j++) values[i][j] = sheet.getCell(j, currentPosition).getContents();
        }
        workbook.close();
        return values;
    }
}
