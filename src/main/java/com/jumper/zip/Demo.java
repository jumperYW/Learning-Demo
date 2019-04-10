package com.jumper.zip;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

import java.io.InputStream;
import java.util.Enumeration;

public class Demo {

    public static void main(String[] args) throws Exception {

        String filePath = "C:\\Users\\jumpe\\Desktop\\支付\\快钱网银接入\\对账\\网关交易明细2019-03-02.zip";
        ZipFile zf = new ZipFile(filePath, "UTF-8");
        Enumeration<ZipEntry> entries = zf.getEntries();
        if (entries.hasMoreElements()) {
            ZipEntry zipEntry = entries.nextElement();
            try (InputStream inputStream = zf.getInputStream(zipEntry);
                 Workbook workbook = new HSSFWorkbook(inputStream)) {
                Sheet sheet = workbook.getSheetAt(0);
                Row row;
                for(int i = sheet.getFirstRowNum(); i < sheet.getLastRowNum(); i++) {
                    row = sheet.getRow(i);
                    for(int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {
                        Cell cell = row.getCell(j);
                        switch (cell.getCellType()) {
                            case 0://数值型
                                System.out.print(cell.getNumericCellValue());
                                break;
                            case 1://字符串型
                                System.out.print(cell.getStringCellValue());
                                break;
                            case 2://公式型
                                System.out.print(cell.getStringCellValue());
                                break;
                            case 3://空值
                                break;
                            case 4://布尔值
                                System.out.print(cell.getBooleanCellValue());
                                break;
                            case 5://错误
                                System.out.print("error");
                                break;
                            default:
                                System.out.print("wrong Type");
                                break;
                        }
                        System.out.print(" | ");
                    }
                    System.out.println();
                }
            }
        }

    }




}
