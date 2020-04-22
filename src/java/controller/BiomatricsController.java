/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.AccessValidate;
import model.AttendenceSystemDB;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author My Computer
 */
@Controller
public class BiomatricsController
{
    private final String ADMIN="admin";
    private final String USER="user";
    private AttendenceSystemDB attendenceSystemDB;
    @RequestMapping("login.htm")
    public ModelAndView getlogin(@ModelAttribute AccessValidate accessValidate) {
        ModelAndView mav=new ModelAndView("login");
        return mav;
    }
    @RequestMapping("form.htm")
    public ModelAndView login(@ModelAttribute AccessValidate accessValidate)
    {
        ModelAndView mav = null;
        String userName = accessValidate.getUserName();
        String password = accessValidate.getPassword();
        String sql = "SELECT userid, password,username FROM login";
        try {
            attendenceSystemDB=new AttendenceSystemDB();
            ResultSet record = attendenceSystemDB.selectRecord(sql);
            while(record.next())
            {
            int id = record.getInt("userid");
                System.out.println("id = " + id);
            String recordPassword = record.getString("password");
            String recordUserName = record.getString("username");
            if (userName.equals(recordUserName) && password.equals(recordPassword))
            {
                String getRoleId="SELECT roleid FROM user INNER JOIN login ON user.userid=login.userid";
                ResultSet resultRoleId=attendenceSystemDB.selectRecord(getRoleId);
                 int roleId=0;
                while(resultRoleId.next())
                {
                roleId=resultRoleId.getInt("roleid");
                    System.out.println("roleId = " + roleId);
                }
                String getRoleQuery="SELECT role FROM roles INNER JOIN user ON roles.id=user.roleid WHERE user.userid="+id+"";
                ResultSet resultRole=attendenceSystemDB.selectRecord(getRoleQuery);
                String role="";
                while(resultRole.next())
                {
                role=resultRole.getString("role");
                    System.out.println("role = " + role);
                }
                if(role.equals(ADMIN))
                {
                    mav=new ModelAndView("admin");
                    mav.addObject("user", accessValidate);
                   mav.addObject("userid", id);
                    return mav;
                }
                else
                {
                   mav=new ModelAndView("user");
                   mav.addObject("user", accessValidate);
                   mav.addObject("userid", id);
                   return mav;
                }
            } 
            else {
                mav=new ModelAndView("index");
                return mav;
            }
            }

        } catch (SQLException ex) {
            Logger.getLogger(BiomatricsController.class.getName()).log(Level.SEVERE, null, ex);
        }
//        finally
//        {
//            attendenceSystemDB.connectionClose();
//        }

        return mav;
    }
    }
