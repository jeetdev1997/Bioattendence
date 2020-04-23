/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.junkie.dto;

import java.sql.Date;

/**
 *
 * @author ashish.yetre
 */
public class AttendanceDTO {

    private String empId;
    private Date attendedDate;
    private Date inTime;
    private Date outTime;
    
     public Date getAttendedDate() {
        return attendedDate;
    }

    public void setAttendedDate(Date attendedDate) {
        this.attendedDate = attendedDate;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public Date getInTime() {
        return inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    public Date getOutTime() {
        return outTime;
    }

    public void setOutTime(Date outTime) {
        this.outTime = outTime;
    }

}
