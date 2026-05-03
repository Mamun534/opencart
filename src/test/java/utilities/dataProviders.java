package utilities;

import org.testng.annotations.DataProvider;

public class dataProviders {

    @DataProvider(name = "LoginData")
    public Object[][] getLoginData() throws Exception {

    	String path = System.getProperty("user.dir") + "\\testData\\opencart_LoginData.xlsx";
        ExcelUtility xl = new ExcelUtility(path);

        String sheetName = "Sheet1";

        int rowCount = xl.getRowCount(sheetName);
        int colCount = 3;

        Object[][] data = new Object[rowCount][colCount];

        for (int i = 1; i <= rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                data[i - 1][j] = xl.getCellData(sheetName, i, j);
            }
        }
        		return data;
    }
}









