package Exel_DataProvider;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class ExcelDataProviders {

    @DataProvider
    public Object[][] usersFromSheet1() throws Exception {
        String path = "src/test/resources/TestData.xlsx";
        ExcelReader excelReader = new ExcelReader(path);
        return excelReader.getSheetDataForTDD();

    }

    @DataProvider
    public Object[][] usersFromSheet2() throws Exception {
        String path = "src/test/resources/TestData.xlsx";
        ExcelReader excelReader = new ExcelReader(path, "Sheet2");
        return excelReader.getCustomSheetForTDD();

    }

    @DataProvider
    public Object[][] usersForApi() throws Exception {
        String path = "src/test/resources/TestData.xlsx";
        ExcelReader excelReader = new ExcelReader(path, "Sheet3");
        return excelReader.getCustomSheetForTDD();
    }
}
