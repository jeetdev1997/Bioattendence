/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.junkie.service.AttendanceService;
import com.junkie.service.IAttendanceService;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author ashish.yetre
 */
@Controller
public class AttendanceController {

    private final IAttendanceService attendanceService = new AttendanceService();
    
    @RequestMapping("uploadView")
    public ModelAndView redirectToUploadAttendanceView(){
        System.out.println("request recieved");
        return new ModelAndView("uploadView");
    }
    
    @RequestMapping(value = "uploadAttendance",method = RequestMethod.POST)
    public ModelAndView uploadAttendance(@RequestParam("file") CommonsMultipartFile file) {
        System.out.println("file :"+file);
        ModelAndView modelAndView=new ModelAndView();
        if(file!=null){
            String filename = file.getOriginalFilename();
            System.out.println("fileName : " + filename);     
            try {
                attendanceService.parseAndSaveAttendance(file);
                modelAndView.addObject("message",file.getOriginalFilename() + " uploaded successfully.");
            } catch (Exception ex) {
                Logger.getLogger(AttendanceController.class.getName()).log(Level.SEVERE, null, ex);
                modelAndView.addObject("message",file.getOriginalFilename() + " failed to upload.");
            }
        }
        modelAndView.setViewName("uploadView");
         return modelAndView;
    }
}
