/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.AddUser;
import model.AttendenceSystemDB;
import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author My Computer
 */
@Controller
public class AdminController
{
    private ResultSet resultSet;
   
    @RequestMapping("adduser.htm")
 public ModelAndView addUser()
 {
     ModelAndView mav=new ModelAndView("adduser");
     return mav;
 }
 @RequestMapping("viewlist")
 public ModelAndView viewList()
 {
      ArrayList<User> userList=new ArrayList<>();
     ModelAndView mav=new ModelAndView("viewlist");
     String sqlViewList="SELECT * from user";
     
        try {
           
            resultSet=AttendenceSystemDB.selectRecord(sqlViewList);
            while (resultSet.next()) 
            {  
             User user=new User();
            user.setUserId(resultSet.getInt("userid"));
            user.setAddress(resultSet.getString("address"));
            user.setEmail(resultSet.getString("email"));
            user.setIsActive(resultSet.getInt("isActive"));
            user.setCreatedDate(resultSet.getString("createddate"));
            user.setUpdatedDate(resultSet.getString("updateddate"));
            ResultSet userName=AttendenceSystemDB.selectUserName(resultSet.getInt("userid"));
                while (userName.next())
                {                    
                 user.setUserName(userName.getString("username"));
                }
                ResultSet role=AttendenceSystemDB.selectRole(resultSet.getInt("roleid"));
                while (role.next())
                {                    
                 user.setRole(role.getString("role"));
                }
                ResultSet deptName=AttendenceSystemDB.selectDepartment(resultSet.getInt("departmentid"));
                while (deptName.next())
                {                    
                 user.setDepartment(deptName.getString("name"));
                }
                userList.add(user);
            }
            mav.addObject("list", userList);
            
        } catch (SQLException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
     return mav;
 }
 @RequestMapping("delete")
 public ModelAndView delete()
 {
     ModelAndView mav=new ModelAndView("delete");
     return mav;
 }
 @RequestMapping("add.htm")
 public ModelAndView add(@ModelAttribute AddUser addUser)
 {
  ModelAndView mav=new ModelAndView("adduser");
  String firstName=addUser.getFirstName();
  String lastName=addUser.getLastName();
  int deptId=addUser.getDeptId();
  int roleId=addUser.getRoleId();
  String address=addUser.getAddress();
  String email=addUser.getEmail();
  int isActive=addUser.getIsActive();
  String createdDate=addUser.getCreatedDate();
  String updatedDate=addUser.getUpdatedDate();
  String userName=addUser.getUserName();
  String password=addUser.getPassword();
  boolean isPresent=AttendenceSystemDB.isPresent(firstName, lastName, email);
  if(!isPresent)
  {
  AttendenceSystemDB.insertUser(null,firstName, lastName, deptId, roleId, address, email, isActive, createdDate, updatedDate);
  ResultSet userId=AttendenceSystemDB.selectUserId(userName);
        try {
            while (userId.next())
            {
                int id=userId.getInt("userid");
                AttendenceSystemDB.insertLogIn(id, password, userName);
                
            }  } catch (SQLException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
  }
  return mav;
 }
 }
