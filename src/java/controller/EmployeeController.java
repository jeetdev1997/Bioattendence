/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.junkie.db.DatabaseHelper;
import com.junkie.dto.UserAttendanceDTO;
import com.junkie.dto.UserDTO;
import com.junkie.service.AttendanceService;
import com.junkie.service.IAttendanceService;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.support.SecurityContextProvider;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author My Computer
 */
@Controller
public class EmployeeController
{
   @RequestMapping("profile.htm")
    public ModelAndView profile(@RequestParam int empId) throws SQLException, ClassNotFoundException
    {
        ModelAndView mav=new ModelAndView("profile");
        UserDTO userDTO=DatabaseHelper.getUserById(empId);
        System.out.println("role = " +userDTO.getRole());
        mav.addObject("user", userDTO);
        return mav;
    } 
    @RequestMapping("viewAttendance")
     public ModelAndView viewAttendance(@RequestParam ("empId")String empId)
    {
      ModelAndView modelAndView = new ModelAndView();
        if (!StringUtils.isEmpty(empId)) {
            IAttendanceService attendanceService = new AttendanceService();
            try {
                UserAttendanceDTO userAttendanceDTO = attendanceService.getEmployeeAttendance(empId);
                UserDTO dTO=userAttendanceDTO.getUser();
                System.out.println("dTO = " + dTO.getUserId());
                modelAndView.addObject("userAttendance", userAttendanceDTO);
                modelAndView.setViewName("userAttendanceView");
            } catch (Exception ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            modelAndView.setViewName("");
        }
        return modelAndView;  
    }
     
      @RequestMapping("logout.htm")
    public ModelAndView logout(HttpServletRequest httpServletRequest)
    {
        ModelAndView mav =new ModelAndView("login");
    httpServletRequest.getSession().invalidate();
        return mav;
    }
}
