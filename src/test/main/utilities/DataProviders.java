package utilities;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import java.io.IOException;

public class DataProviders {
	
	WebDriver driver;
    // Define the path to your test data file
    private static final String EXCEL_PATH = "./src/test/resources/TestData.xlsx"; 
    private static final String SHEET_NAME = "LoginDetails";
	
	@DataProvider(name = "loginData")
    public Object[][] provideData() throws IOException {
		ExcelUtility.openExcelFile(EXCEL_PATH, SHEET_NAME);
        int rowCount = ExcelUtility.getRowCount();
        // Create a 2D array: rows x 2 columns (username, password)
        Object[][] data = new Object[rowCount][2]; 

        for (int i = 0; i < rowCount; i++) {
            // Assuming Username is in Col 0, Password in Col 1
            data[i][0] = ExcelUtility.getCellData(i + 1, 0); // Start from row 1 (skipping header)
            data[i][1] = ExcelUtility.getCellData(i + 1, 1);
        }
        return data;
    }

}
