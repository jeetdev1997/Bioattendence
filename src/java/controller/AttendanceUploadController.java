/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.junkie.parser.AttendanceParser;
import com.junkie.service.AttendanceService;
import com.junkie.service.IAttendanceService;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ashish.yetre
 */
@Controller
public class AttendanceUploadController {

    @Autowired
    private IAttendanceService attendanceService;
    
    @RequestMapping(value = "uploadAttendance",method = RequestMethod.POST)
    public void uploadAttendance(@RequestParam("file") MultipartFile file) {
       
        if(file!=null){
            String filename = file.getName();
            System.out.println("fileName : " + filename);     
            try {
                attendanceService.parseAndSaveAttendance(file);
            } catch (Exception ex) {
                Logger.getLogger(AttendanceUploadController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
