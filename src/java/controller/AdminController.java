/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.junkie.db.DatabaseHelper;
import com.junkie.dto.AttendanceDTO;
import com.junkie.dto.DepartmentDTO;
import com.junkie.dto.LoginDTO;
import com.junkie.dto.RolesDTO;
import com.junkie.dto.UserAttendanceDTO;
import com.junkie.dto.UserDTO;
import com.junkie.service.AttendanceService;
import com.junkie.service.IAttendanceService;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.AddUser;
import model.AttendenceSystemDb;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author My Computer
 */
@Controller
public class AdminController {

    private ResultSet resultSet;

    @RequestMapping("adduser.htm")
    public ModelAndView addUser() throws Exception {
        ModelAndView mav = new ModelAndView("adduser");
        ArrayList<DepartmentDTO> departmentDTOList=DatabaseHelper.getDepartment(); 
        ArrayList<RolesDTO> rolesDTOList=DatabaseHelper.getRoles();
        mav.addObject("department", departmentDTOList);
        mav.addObject("roles", rolesDTOList);
        for (RolesDTO rolesDTO : rolesDTOList) {
            System.out.println("rolesDTO = " + rolesDTO.getRoleId());
        }
        return mav;
    }

    @RequestMapping("viewlist")
    public ModelAndView viewList() throws SQLException, ClassNotFoundException {
        ArrayList<UserDTO> userList = new ArrayList<>();
        ModelAndView mav = new ModelAndView("viewlist");
        userList = DatabaseHelper.getUsers();
        mav.addObject("list", userList);
        return mav;
    }

    @RequestMapping("delete.htm")
    public ModelAndView delete(@RequestParam int userId, int isActive) throws SQLException, ClassNotFoundException {
        ModelAndView mav = new ModelAndView("delete");
        DatabaseHelper.updateUsers(userId);

        return mav;
    }

    @RequestMapping("add.htm")
    public ModelAndView add(@ModelAttribute AddUser addUser) throws Exception {
        ModelAndView mav = new ModelAndView();
        boolean isPresent = AttendenceSystemDb.isPresent(addUser);
        if (!isPresent) {
            DatabaseHelper.insertUsers(addUser);
            LoginDTO loginDTO = new LoginDTO();
            loginDTO.setPassword(addUser.getPassword());
            loginDTO.setUsername(addUser.getUserName());
            DatabaseHelper.insertlogIn(loginDTO, addUser.getEmail());
//            mav.addObject("employee", true);
            mav.setViewName("success");
        }
       else
        {
//            mav.addObject("employee",false);
            mav.setViewName("failed");
        }
        return mav;
    }
    @RequestMapping("viewAttendance")
    public ModelAndView attendanceView(@RequestParam("empId") String empId){
        ModelAndView modelAndView = new ModelAndView();
        if(!StringUtils.isEmpty(empId)){
            IAttendanceService attendanceService = new AttendanceService();
            try {
                UserAttendanceDTO userAttendanceDTO = attendanceService.getEmployeeAttendance(empId);
                modelAndView.addObject("userAttendance", userAttendanceDTO);
                modelAndView.setViewName("userAttendanceView");
            } catch (Exception ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            modelAndView.setViewName("");
        }
        return modelAndView;
    }
}
