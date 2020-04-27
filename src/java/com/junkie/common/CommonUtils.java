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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ashish.yetre
 */
public class CommonUtils {

    private static final File outputFile = new File("C:\\Users\\ashish.yetre\\Documents\\NetBeansProjects\\Bioattendence\\files\\");

    public static void sortListByDate(List<AttendanceDTO> list) {
        //Collections.sort(list, (AttendanceDTO o1, AttendanceDTO o2) -> o1.getInTime().compareTo(o2.getInTime()));
    }

    public static void writeDataIntoFile(List<String> list) throws IOException {

        outputFile.createNewFile();
        Files.write(outputFile.toPath(), (Iterable<String>) list::iterator);
    }

    public static void generateRandomTimeStamp(String empId) throws IOException {
        LocalDate firstDayOfMonth = LocalDate.of(2020, Month.APRIL, 1);
        LocalDateTime atTime = firstDayOfMonth.atTime(11, 01);
        LocalDateTime outTime = firstDayOfMonth.atTime(18, 01);
        List<String> list = new ArrayList();
        for (int i = 1; i < 31; i++) {
            long in = atTime.plusDays(i).atZone(ZoneId.of("Asia/Kolkata")).toEpochSecond();
            long out = outTime.plusDays(i).atZone(ZoneId.of("Asia/Kolkata")).toEpochSecond();
            list.add(empId + "," + in + "," + out);
        }
        writeDataIntoFile(list);
    }

    private static int getRandomInteger(int maximum, int minimum) {
        return ((int) (Math.random() * (maximum - minimum))) + minimum;
    }

    public static List<String> getTillDateMonthList() {
        int dayOfMonth = LocalDate.now().getMonthValue();
        List<String> monthList = new ArrayList();
        for (int i = 1; i <= dayOfMonth; i++) {
            monthList.add(Month.of(i).toString());
        }
        return monthList;
    }

    private static String getRandomStatus() {
        if (getRandomInteger(10, 1) == 8) {
            return "ABSENT";
        } else {
            return "PRESENT";
        }
    }

    private static List<Book> createAttendanceListByEmpId(String empId) {
        List<Book> list = new ArrayList();
        int noOfMonths = 5;
        for (int i = 1; i <= noOfMonths; i++) {
            YearMonth yearMonthObject = YearMonth.of(2020, i);
            int daysInMonth = yearMonthObject.lengthOfMonth();
            for (int index = 1; index <= daysInMonth; index++) {
                LocalDate firstDayOfMonth = LocalDate.of(2020, Month.of(i), index);
                LocalDateTime atTime = firstDayOfMonth.atTime(getRandomInteger(12, 10), 01);
                LocalDateTime outTime = firstDayOfMonth.atTime(getRandomInteger(18, 21), 01);
                long in = atTime.plusDays(i).atZone(ZoneId.of("Asia/Kolkata")).toEpochSecond();
                long out = outTime.plusDays(i).atZone(ZoneId.of("Asia/Kolkata")).toEpochSecond();
                String randomStatus = getRandomStatus();
                Book book = new Book(empId, randomStatus, in + "", "" + out);
                list.add(book);
            }
        }
        return list;
    }

    private static void generateAttendanceXLSSheet() throws IOException {
        int numberOfEmployees = 10;
        for (int i = 1; i < numberOfEmployees; i++) {
            List<Book> employeeAttendanceBook = createAttendanceListByEmpId(i + "");
            String filePath = outputFile.getPath().concat(i + ".xls");
            XLSFileCreator.writeExcel(employeeAttendanceBook, filePath);
        }
    }

    public static void main(String[] args) throws IOException {
        generateAttendanceXLSSheet();
//        createAttendanceListByEmpId("7");
    }
}
