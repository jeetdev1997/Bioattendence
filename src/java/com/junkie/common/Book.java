/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.junkie.common;

/**
 *
 * @author ashish.yetre
 */
public class Book {

    private String EmpId;
    private String status;
    private String inTime;
    private String outTime;

    public Book() {

    }

    public Book(String EmpId, String status, String inTime, String outTime) {
        this.EmpId = EmpId;
        this.status = status;
        this.inTime = inTime;
        this.outTime = outTime;
    }

    public String getEmpId() {
        return EmpId;
    }

    public void setEmpId(String EmpId) {
        this.EmpId = EmpId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInTime() {
        return inTime;
    }

    public void setInTime(String inTime) {
        this.inTime = inTime;
    }

    public String getOutTime() {
        return outTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }

}
