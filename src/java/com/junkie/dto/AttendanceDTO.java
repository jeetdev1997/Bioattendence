/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.junkie.dto;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author ashish.yetre
 */
public class AttendanceDTO {

    private String empId;
    private Date attendedDate;
    private String day;
    private String inTimestamp;
    private String outTimestamp;
    private String formattedInTime;
    private String formattedOutTime;
    private Date inTime;
    private Date outTime;
    private String status;

    public AttendanceDTO(){
        
    }
    
    public AttendanceDTO(String empId, String status, String inTimestamp, String outTimestamp) {
        this.empId = empId;
        this.status = status;
        this.inTimestamp = inTimestamp;
        this.outTimestamp = outTimestamp;
        LocalDateTime localDate = LocalDateTime.ofEpochSecond(Long.valueOf(inTimestamp), 1, ZoneOffset.UTC);
        String format = localDate.toLocalDate().format(DateTimeFormatter.ISO_DATE);
        this.attendedDate = Date.valueOf(format);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFormattedInTime() {
        return formattedInTime;
    }

    public void setFormattedInTime(String formattedInTime) {
        this.formattedInTime = formattedInTime;
    }

    public String getFormattedOutTime() {
        return formattedOutTime;
    }

    public void setFormattedOutTime(String formattedOutTime) {
        this.formattedOutTime = formattedOutTime;
    }

    public String getDay() {
        return day;
    }

    public String getInTimestamp() {
        return inTimestamp;
    }

    public void setInTimestamp(String inTimestamp) {
        this.inTimestamp = inTimestamp;
    }

    public String getOutTimestamp() {
        return outTimestamp;
    }

    public void setOutTimestamp(String outTimestamp) {
        this.outTimestamp = outTimestamp;
    }

    public void setDay(String day) {
        this.day = day;
    }

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

    @Override
    public String toString() {
        return "{" + empId + " " + inTime + " " + outTime + "}"; //To change body of generated methods, choose Tools | Templates.
    }

}
