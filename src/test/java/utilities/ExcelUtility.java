package utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtility {
    private static Workbook workbook;
    private static Sheet sheet;
    private static String filePath;

    /**
     * Set the file path and open the Excel file.
     * @param path The absolute path to the Excel file.
     * @param sheetName The name of the sheet to use.
     * @throws IOException
     */
    public static void openExcelFile(String path, String sheetName) throws IOException {
        filePath = path;
        FileInputStream inputStream = new FileInputStream(filePath);
        // Use XSSFWorkbook for .xlsx files
        workbook = new XSSFWorkbook(inputStream);
        sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            throw new IllegalArgumentException("Sheet name \"" + sheetName + "\" not found.");
        }
    }

    /**
     * Get the data from a specific cell (row and column index based).
     * @param rowNum The row number (0-indexed).
     * @param colNum The column number (0-indexed).
     * @return The cell value as a String.
     */
    public static String getCellData(int rowNum, int colNum) {
        Row row = sheet.getRow(rowNum);
        if (row == null) {
            return "";
        }
        Cell cell = row.getCell(colNum);
        if (cell == null) {
            return "";
        }

        // Handle different cell types
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    // Convert numeric to string to return a consistent type
                    cell.setCellType(CellType.STRING);
                    return cell.getStringCellValue();
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                // Note: might require an Evaluator for complex formulas
                return cell.getCellFormula();
            default:
                return "";
        }
    }

    /**
     * Get the total number of rows in the current sheet with data.
     * @return The row count.
     */
    public static int getRowCount() {
        return sheet.getLastRowNum(); // Returns the index of the last row (0-indexed)
    }

    /**
     * Close the workbook to prevent memory leaks.
     * @throws IOException
     */
    public static void closeWorkbook() throws IOException {
        if (workbook != null) {
            workbook.close();
        }
    }
}
