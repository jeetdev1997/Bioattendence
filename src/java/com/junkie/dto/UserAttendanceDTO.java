/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.junkie.dto;

import java.util.List;

/**
 *
 * @author ashish.yetre
 */
public class UserAttendanceDTO {

    private UserDTO user;
    private List<AttendanceDTO> attendanceList;

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
