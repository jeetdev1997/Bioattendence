/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.junkie.db.DatabaseHelper;
import com.junkie.dto.AttendanceRequestDTO;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.AddUser;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
        ArrayList<DepartmentDTO> departmentDTOList = DatabaseHelper.getDepartment();
        ArrayList<RolesDTO> rolesDTOList = DatabaseHelper.getRoles();
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
    public ModelAndView delete(@RequestParam int empId) throws SQLException, ClassNotFoundException {
        ModelAndView mav = new ModelAndView("delete");
        DatabaseHelper.updateUsers(empId);
        return mav;
    }

    @RequestMapping("add.htm")
    public ModelAndView add(@ModelAttribute AddUser addUser) throws Exception {
        ModelAndView mav = new ModelAndView("adduser");
        if (!DatabaseHelper.isLoginUserNamePresent(addUser.getEmail())) {
            int userId = DatabaseHelper.insertUsers(addUser);
            LoginDTO loginDTO = new LoginDTO();
            loginDTO.setUserId(userId);
            loginDTO.setUsername(addUser.getEmail());
            loginDTO.setPassword(addUser.getPassword());
            DatabaseHelper.insertlogIn(loginDTO);
            setDepartmentAndRoles(mav);
            mav.addObject("message", "Registration Successfull.");
        } else {
            setDepartmentAndRoles(mav);
            mav.addObject("message", "username already present");
        }
        return mav;
    }

    private void setDepartmentAndRoles(ModelAndView mav) throws Exception {
        ArrayList<DepartmentDTO> departmentDTOList = DatabaseHelper.getDepartment();
        ArrayList<RolesDTO> rolesDTOList = DatabaseHelper.getRoles();
        mav.addObject("department", departmentDTOList);
        mav.addObject("roles", rolesDTOList);
    }

    @RequestMapping("viewAttendance")
    public ModelAndView attendanceView(@RequestParam("empId") String empId) {
        ModelAndView modelAndView = new ModelAndView();
        if (!StringUtils.isEmpty(empId)) {
            IAttendanceService attendanceService = new AttendanceService();
            try {
                UserAttendanceDTO userAttendanceDTO = attendanceService.getEmployeeAttendance(empId);
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

    @RequestMapping(method = RequestMethod.POST, value = "updateAttendance")
    public ModelAndView getAttendanceByMonth(@ModelAttribute AttendanceRequestDTO requestDTO) {
        ModelAndView modelAndView = new ModelAndView();
        if (requestDTO.getUserId() > 0) {
            IAttendanceService attendanceService = new AttendanceService();
            try {
                UserAttendanceDTO userAttendanceDTO = attendanceService.getEmployeeAttendanceByMonth(requestDTO.getUserId(), requestDTO.getMonth());
                modelAndView.addObject("userAttendance", userAttendanceDTO);
                modelAndView.setViewName("userAttendanceView");
            } catch (Exception ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            //redirect to failure state
            modelAndView.setViewName("fail");
        }
        return modelAndView;
    }

    @RequestMapping("edit.htm")
    public ModelAndView editEmployee(@RequestParam int empId) throws Exception {
        ModelAndView mav = new ModelAndView("edit");
        ArrayList<DepartmentDTO> departmentDTOList = DatabaseHelper.getDepartment();
        ArrayList<RolesDTO> rolesDTOList = DatabaseHelper.getRoles();
        UserDTO userDTO = DatabaseHelper.getUserById(empId);
        mav.addObject("email", userDTO.getEmail());
        mav.addObject("department", departmentDTOList);
        mav.addObject("roles", rolesDTOList);
        return mav;
    }

    @RequestMapping("updateemployee.htm")
    public ModelAndView updateEmployee(@ModelAttribute AddUser addUser) throws Exception {
        ModelAndView mav = new ModelAndView("edit");
        int userId = DatabaseHelper.getUserId(addUser.getEmail());
        System.out.println("ControlleruserId = " + userId);
        DatabaseHelper.updateUsers(addUser, userId);
        setDepartmentAndRoles(mav);
        mav.addObject("message", "Update Successfull.");
        return mav;
    }

    @RequestMapping("department.htm")
    public ModelAndView viewDepartment() {
        ModelAndView mav = new ModelAndView("adddepartment");
        return mav;
    }

    @RequestMapping("adddepartment.htm")
    public ModelAndView addDepartment(@ModelAttribute DepartmentDTO departmentDTO) throws Exception {
        ModelAndView mav = new ModelAndView("adddepartment");
        if (!DatabaseHelper.isDepartmentPresent(departmentDTO.getName())) {
            int deptId = DatabaseHelper.insertDepartment(departmentDTO);
            mav.addObject("message", "Department Added Successfully");
        } else {
            mav.addObject("message", "Department Already Exist");
        }
        return mav;
    }

    @RequestMapping("role.htm")
    public ModelAndView viewRole() {
        ModelAndView mav = new ModelAndView("addrole");
        return mav;
    }

    @RequestMapping("addroles.htm")
    public ModelAndView addRole(@ModelAttribute RolesDTO rolesDTO) throws Exception {
        ModelAndView mav = new ModelAndView("addrole");
        if (!DatabaseHelper.isRolePresent(rolesDTO.getName())) {
            int roleId = DatabaseHelper.insertRoles(rolesDTO);
            mav.addObject("message", "Role Added Successfully");
        } else {
            mav.addObject("message", "Role Already Exist");
        }
        return mav;
    }

}
