package utilities;

import java.io.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

public class ExcelUtility 
{
    public FileInputStream fi;
    public FileOutputStream fo;
    public XSSFWorkbook workbook;
    public XSSFSheet sheet;
    public XSSFRow row;
    public XSSFCell cell;
    public CellStyle style;
    String path;

    public ExcelUtility(String path) 
    {
        this.path = path;
    }

    public int getRowCount(String sheetName) throws IOException 
    {
        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);

        sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            workbook.close();
            fi.close();
            return 0;
        }

        int rowcount = sheet.getLastRowNum();

        workbook.close();
        fi.close();
        return rowcount;
    }

    public String getCellData(String sheetName, int rownum, int colnum) throws IOException 
    {
        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);

        sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            workbook.close();
            fi.close();
            return "";
        }

        row = sheet.getRow(rownum);
        if (row == null) {
            workbook.close();
            fi.close();
            return "";
        }

        cell = row.getCell(colnum);

        DataFormatter formatter = new DataFormatter();
        String data;

        try {
            data = formatter.formatCellValue(cell);
        } catch (Exception e) {
            data = "";
        }

        workbook.close();
        fi.close();
        return data;
    }

    public void setCellData(String sheetName, int rownum, int colnum, String data) throws IOException 
    {
        File xlfile = new File(path);

        // Create file if not exists
        if (!xlfile.exists()) {
            workbook = new XSSFWorkbook();
            fo = new FileOutputStream(path);
            workbook.write(fo);
            workbook.close();
            fo.close();
        }

        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);

        // Create sheet if not exists
        if (workbook.getSheetIndex(sheetName) == -1) {
            workbook.createSheet(sheetName);
        }

        sheet = workbook.getSheet(sheetName);

        // Create row if not exists..
        
        if (sheet.getRow(rownum) == null)
            sheet.createRow(rownum);

        row = sheet.getRow(rownum);

        // Create cell if not exists
        cell = row.getCell(colnum);
        if (cell == null)
            cell = row.createCell(colnum);

        cell.setCellValue(data);

        fo = new FileOutputStream(path);
        workbook.write(fo);

        workbook.close();
        fi.close();
        fo.close();
    }
}