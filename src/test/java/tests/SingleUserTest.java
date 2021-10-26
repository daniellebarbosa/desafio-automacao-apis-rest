package tests;

import com.google.gson.Gson;
import io.restassured.http.ContentType;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class SingleUserTest {

    @Test
    public void getSingleUser() throws IOException {

        given()
                .contentType(ContentType.JSON)
        .when()
                .get("/users/2")
        .then()
                .statusCode(200)
                .assertThat()
                        .body("data.id", equalTo(2));
    }

    @Test
    public void login() throws IOException {

        ArrayList<String> dataDriven = getData("Login");

        Map<String, Object> bodyLogin = new HashMap<String, Object>();
        bodyLogin.put(dataDriven.get(1), dataDriven.get(2));
        bodyLogin.put(dataDriven.get(3), dataDriven.get(4));

        Gson gson = new Gson();
        String json = gson.toJson(bodyLogin);

        given()
            .body(json)
            .contentType(ContentType.JSON)
        .when()
            .post("/login")
        .then()
            .statusCode(201);

    }

    public ArrayList<String> getData(String testCaseName) throws IOException{
        ArrayList<String> dataList = new ArrayList<String>();

        FileInputStream stream = new FileInputStream("src//test//resources//testFile.xlsx");

        XSSFWorkbook workbook = new XSSFWorkbook(stream);

        int sheets = workbook.getNumberOfSheets();
        for (int i = 0; i < sheets; i++) {
            if (workbook.getSheetName(i).equalsIgnoreCase("ReqRes")) {
                XSSFSheet sheet = workbook.getSheetAt(i); //Identify Testcases column by scanning the entire 1st row


                Iterator<Row> rows = sheet.iterator(); // sheet is collection of rows
                Row firstrow = rows.next();
                Iterator<Cell> ce = firstrow.cellIterator(); //row is collection of cells
                int k = 0;
                int coloumn = 0;
                while (ce.hasNext()) {
                    Cell value = ce.next();

                    if (value.getStringCellValue().equalsIgnoreCase("TestCases")) {
                        coloumn = k;

                    }

                    k++;
                }


                ////once coloumn is identified then scan entire testcase column to identify purchase testcase row
                while (rows.hasNext()) {

                    Row r = rows.next();

                    if (r.getCell(coloumn).getStringCellValue().equalsIgnoreCase(testCaseName)) {

                        ////after you grab purchase testcase row = pull all the data of that row and feed into test

                        Iterator<Cell> cv = r.cellIterator();
                        while (cv.hasNext()) {
                            Cell cell = cv.next();
                            if (cell.getCellType() == CellType.STRING) {

                                dataList.add(cell.getStringCellValue());
                            } else {

                                dataList.add(NumberToTextConverter.toText(cell.getNumericCellValue()));

                            }
                        }
                    }


                }

            }
        }
        return dataList;
    }
}
