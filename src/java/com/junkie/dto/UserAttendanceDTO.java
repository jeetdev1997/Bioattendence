/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.junkie.dto;

import com.junkie.common.CommonUtils;
import java.time.Month;
import java.util.List;

/**
 *
 * @author ashish.yetre
 */
public class UserAttendanceDTO {

    private List<String> monthList;
    private UserDTO user;
    private List<AttendanceDTO> attendanceList;
    private String currentMonth;
    private int presentDays;
    private int absentDays;

    public List<String> getMonthList() {
        this.monthList = CommonUtils.getTillDateMonthList();
        return monthList;
    }

    public int getPresentDays() {
        if (attendanceList != null & attendanceList.size() > 0) {
            for (AttendanceDTO attendanceDTO : attendanceList) {
                if (attendanceDTO.getStatus().equals("PRESENT") && !attendanceDTO.getDay().equals("SUNDAY")) {
                    presentDays++;
                }
            }
        }
        return presentDays;
    }

    public int getAbsentDays() {
        if (attendanceList != null & attendanceList.size() > 0) {
            for (AttendanceDTO attendanceDTO : attendanceList) {
                if (attendanceDTO.getStatus().equals("ABSENT")) {
                    absentDays++;
                }
            }
        }
        return absentDays;
    }

    public String getCurrentMonth() {
        if (attendanceList != null & attendanceList.size() > 0) {
            currentMonth = Month.of(attendanceList.get(0).getAttendedDate().getMonth()+1).toString();
        }
        return currentMonth;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public List<AttendanceDTO> getAttendanceList() {
        return attendanceList;
    }

    public void setAttendanceList(List<AttendanceDTO> attendanceList) {
        this.attendanceList = attendanceList;
    }

}
