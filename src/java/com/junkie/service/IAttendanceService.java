/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.junkie.service;

import com.junkie.dto.UserAttendanceDTO;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ashish.yetre
 */
public interface IAttendanceService {

    public void parseAndSaveAttendance(MultipartFile file) throws Exception;

    public UserAttendanceDTO getEmployeeAttendance(String userId) throws Exception;

    public UserAttendanceDTO getEmployeeAttendanceByMonth(int userId, String month) throws Exception;
}
