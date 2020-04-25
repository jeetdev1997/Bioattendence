/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.junkie.service;

import com.junkie.db.DatabaseHelper;
import com.junkie.dto.AttendanceDTO;
import com.junkie.parser.AttendanceParser;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ashish.yetre
 */
@Service
public class AttendanceService implements IAttendanceService {

    @Override
    public void parseAndSaveAttendance(MultipartFile file) throws SQLException, IOException {
        AttendanceParser attendanceParser = new AttendanceParser(file);
        attendanceParser.parse();
        List<AttendanceDTO> attendanceDTOList = attendanceParser.getAttendanceDTOList();
        System.out.println(attendanceDTOList);
        System.out.println("Number Of Attedee Records : " + attendanceDTOList.size());
        saveAttedanceToDB(attendanceDTOList);
    }

    private void saveAttedanceToDB(List<AttendanceDTO> attendanceDTOList) throws SQLException {
        try {
            DatabaseHelper.insertUserAttendance(attendanceDTOList);
        } catch (Exception ex) {
            Logger.getLogger(AttendanceService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
