package helpers;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class HandlerDataDriven {
    public ArrayList<String> getDataDriven(String testCaseName) throws IOException {
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
