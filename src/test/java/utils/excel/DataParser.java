package utils.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class DataParser {
    private static XSSFWorkbook workbook = null;
    private static XSSFSheet sheet = null;
    private static XSSFRow row = null;
    private static XSSFCell cell = null;
    public static String path;
    public static InputStream fis = null;
    public static FileOutputStream fileOut = null;
    public static String TDPath = "src/test/resource/testdata.xlsx";
    public static String TabName = null;
    public static ArrayList<String> columndata = null;
    public static final Logger logger = LoggerFactory.getLogger(DataParser.class);

    public DataParser() {
        try {
            fis = new FileInputStream(TDPath);
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheetAt(0);
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isSheetExist(String sheetName) {
        int index = workbook.getSheetIndex(sheetName);
        if (index == -1) {
            index = workbook.getSheetIndex(sheetName.toUpperCase());
            if (index == -1)
                return false;
            else
                return true;
        } else
            return true;
    }

    public static String getSheetName(String tdPath) throws IOException {
        fis = new FileInputStream(tdPath);
        workbook = new XSSFWorkbook(fis);
        for (int i = 0; i < workbook.getNumberOfSheets(); i++)
            logger.info("Tab names::-->" + workbook.getSheetName(i));
        int ExcelTab = workbook.getFirstVisibleTab();
        sheet = workbook.getSheetAt(ExcelTab);
        TabName = sheet.getSheetName();
        System.out.println(TabName);
        return TabName;
    }

    public static int getRowCount(String sheetName) {
        int index = workbook.getSheetIndex(sheetName);
        if (index == -1)
            return 0;
        else {
            sheet = workbook.getSheetAt(index);
            int number = sheet.getLastRowNum() + 1;
            return number;
        }
    }


    public static ArrayList<String> extractExcelContentByColumnIndex(String tdPath, int columnIndex, int excelTab) {
        try {
            FileInputStream ios = new FileInputStream(tdPath);
            XSSFWorkbook workbook = new XSSFWorkbook(ios);
            XSSFSheet sheet = workbook.getSheetAt(excelTab);
            Iterator<Row> rowIterator = sheet.iterator();
            columndata = new ArrayList<>();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    if (row.getRowNum() > 0) {
                        if (cell.getColumnIndex() == columnIndex) {
                            switch (cell.getCellType()) {
                                case Cell.CELL_TYPE_NUMERIC:
                                    columndata.add(cell.getNumericCellValue() + "");
                                    break;
                                case Cell.CELL_TYPE_STRING:
                                    columndata.add(cell.getStringCellValue());
                                    break;
                            }
                        }
                    }
                }
            }
            ios.close();
            //logger.info(columndata);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return columndata;
    }
}

