/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.junkie.parser;

import com.junkie.common.ReadXLSFile;
import com.junkie.dto.AttendanceDTO;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ashish.yetre
 */
public class AttendanceParser {

    private final MultipartFile file;
    final String COMMO_SEPARATOR = ",";

    private List<AttendanceDTO> attendanceDTOList;

    public AttendanceParser(MultipartFile file) {
        this.file = file;
    }

    public void parse() throws IOException {
        if (file != null & file.getSize() > 0) {
            this.attendanceDTOList = ReadXLSFile.parseXLS(file);
        }
    }

    private List<String> getAllContentFromFile() throws IOException {
        InputStream inputStream = file.getInputStream();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));) {
            Stream<String> lines = bufferedReader.lines();
            return lines.collect(Collectors.toList());
        }
    }

    private void readAttendance(List<String> allContentFromFile) {
        attendanceDTOList = new ArrayList();
        for (int i = 1; i < allContentFromFile.size(); i++) {
            String[] values = allContentFromFile.get(i).split(COMMO_SEPARATOR);
            if (values.length == 3) {
                AttendanceDTO attendanceDTO = new AttendanceDTO();
                System.out.println("values: " + Arrays.toString(values));
                attendanceDTO.setEmpId(values[0].trim());
                attendanceDTO.setInTimestamp(values[1]);
                attendanceDTO.setOutTimestamp(values[2]);

                LocalDateTime localDate = LocalDateTime.ofEpochSecond(Long.valueOf(values[1]), 1, ZoneOffset.UTC);
                String format = localDate.toLocalDate().format(DateTimeFormatter.ISO_DATE);
                Date date = Date.valueOf(format);
                System.out.println("date : " + date);
                attendanceDTO.setAttendedDate(date);
                attendanceDTOList.add(attendanceDTO);
            }
        }
    }

    public List<AttendanceDTO> getAttendanceDTOList() {
        return attendanceDTOList;
    }
}
