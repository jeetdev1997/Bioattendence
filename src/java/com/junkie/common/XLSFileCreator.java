/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.junkie.common;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author ashish.yetre
 */
public class XLSFileCreator {

    public static void writeExcel(List<Book> listBook, String excelFilePath) throws IOException {
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet();

        int rowCount = 0;

        for (Book aBook : listBook) {
            Row row = sheet.createRow(++rowCount);
            writeBook(aBook, row);
        }

        try (FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
            workbook.write(outputStream);
        }
    }

    private static void writeBook(Book aBook, Row row) {
        Cell cell = row.createCell(0);
        cell.setCellValue(aBook.getEmpId());

        cell = row.createCell(1);
        cell.setCellValue(aBook.getStatus());

        cell = row.createCell(2);
        cell.setCellValue(aBook.getInTime());
        
        cell = row.createCell(3);
        cell.setCellValue(aBook.getOutTime());
    }
}
