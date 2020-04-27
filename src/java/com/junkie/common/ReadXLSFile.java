/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.junkie.common;

import com.junkie.dto.AttendanceDTO;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ashish.yetre
 */
public class ReadXLSFile {

    public static List<AttendanceDTO> parseXLS(MultipartFile file) throws IOException {
        List<AttendanceDTO> attendanceList = new ArrayList<>();
//creating workbook instance that refers to .xls file  
        HSSFWorkbook wb = new HSSFWorkbook(file.getInputStream());
//creating a Sheet object to retrieve the object  
        HSSFSheet sheet = wb.getSheetAt(0);
//evaluating cell type   
        FormulaEvaluator formulaEvaluator = wb.getCreationHelper().createFormulaEvaluator();
        for (Row row : sheet) //iteration over row using for each loop  
        {
            String empId = row.getCell(0).getStringCellValue();
            String status = row.getCell(1).getStringCellValue();
            String inTime = row.getCell(2).getStringCellValue();
            String outTime = row.getCell(3).getStringCellValue();
            System.out.println(empId + " " + status + " " + inTime + " " + outTime);
            AttendanceDTO attendanceDTO = new AttendanceDTO(empId, status, inTime, outTime);
            attendanceList.add(attendanceDTO);
        }
        return attendanceList;
    }
}
