package com.jumper.zip;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class Demo2 {

    /**
     * 解析快钱对账文件excel
     */
    private static void parseKqExcel(String path) throws IOException {
        ZipFile zipFile = new ZipFile(path, Charset.forName("GBK"));
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        if(entries.hasMoreElements()) {
            ZipEntry zipEntry = entries.nextElement();
            System.out.println("parseKqExcel - fileName:" + zipEntry.getName());
            try(InputStream ftpInputStream = zipFile.getInputStream(zipEntry)) {
                Workbook workbook = new HSSFWorkbook(ftpInputStream);
                Sheet sheet = workbook.getSheetAt(0);
                int rowNum = sheet.getLastRowNum();
                Row row;
                int gatherCount = 0; //收款笔数
                int refundCount = 0; //退款笔数
                BigDecimal gatherMoney = BigDecimal.ZERO; //收款金额
                BigDecimal gatherMoneyFee = BigDecimal.ZERO; //收款金额手续费
                BigDecimal refundMoney = BigDecimal.ZERO; //退款金额
                BigDecimal refundMoneyFee = BigDecimal.ZERO; //退款金额手续费
                for(int i = 6; i < rowNum - 1; i++) {
                    row = sheet.getRow(i);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        parseKqExcel("C:\\Users\\jumpe\\Desktop\\kq.zip");
    }

}
