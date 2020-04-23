/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.junkie.parser;

import com.junkie.dto.AttendanceDTO;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
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
            List<String> allContentFromFile = getAllContentFromFile();
            if (allContentFromFile.size() > 0) {
                readAttendance(allContentFromFile);
            }
        }
    }

    private List<String> getAllContentFromFile() throws IOException {
        InputStream inputStream = file.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        List<String> readAllLines = new ArrayList();
        while (bufferedReader.read() != -1) {
            readAllLines.add(bufferedReader.readLine());
        }

        return readAllLines;
    }

    private void readAttendance(List<String> allContentFromFile) {
        String headers = allContentFromFile.get(0);
        String[] values = headers.split(COMMO_SEPARATOR);
        AttendanceDTO attendanceDTO = new AttendanceDTO();
        attendanceDTOList = new ArrayList();
        for (int i = 1; i < allContentFromFile.size(); i++) {
            attendanceDTO.setEmpId(values[0]);
            attendanceDTO.setAttendedDate(new Date(new Long(values[1])));
            attendanceDTO.setInTime(new Date(new Long(values[1])));
            attendanceDTO.setOutTime(new Date(new Long(values[2])));
            attendanceDTOList.add(attendanceDTO);
        }
    }

    public List<AttendanceDTO> getAttendanceDTOList() {
        return attendanceDTOList;
    }
}
