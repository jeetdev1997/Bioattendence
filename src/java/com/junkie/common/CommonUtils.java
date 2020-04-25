/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.junkie.common;

import com.junkie.dto.AttendanceDTO;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author ashish.yetre
 */
public class CommonUtils {

    public static void sortListByDate(List<AttendanceDTO> list) {
        //Collections.sort(list, (AttendanceDTO o1, AttendanceDTO o2) -> o1.getInTime().compareTo(o2.getInTime()));
    }

    public static void writeDataIntoFile(List<String> list) throws IOException {
        File outputFile = new File("C:\\Users\\ashish.yetre\\Documents\\NetBeansProjects\\Bioattendence\\files\\generatedFile.txt");
        if (outputFile.createNewFile()) {
            Files.write(outputFile.toPath(), (Iterable<String>) list::iterator);
        }

    }

    public static void generateRandomTimeStamp(String empId) throws IOException {
        LocalDate firstDayOfMonth = LocalDate.of(2020, Month.MARCH, 1);
        LocalDateTime atTime = firstDayOfMonth.atTime(11, 01);
        LocalDateTime outTime = firstDayOfMonth.atTime(18, 01);
        List<String> list = new ArrayList();
        for (int i = 1; i < 31; i++) {
            long in = atTime.plusDays(i).atZone(ZoneId.of("Asia/Kolkata")).toEpochSecond();
            long out= outTime.plusDays(i).atZone(ZoneId.of("Asia/Kolkata")).toEpochSecond();
            list.add(empId+","+in +","+out);
        }
        writeDataIntoFile(list);
    }

    public static void main(String[] args) throws IOException {
        generateRandomTimeStamp("2");
    }
}
