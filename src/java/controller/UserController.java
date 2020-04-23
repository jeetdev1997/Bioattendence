/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.junkie.common.ICommonConstant;
import com.junkie.db.DatabaseHelper;
import com.junkie.dto.LoginDTO;
import com.junkie.dto.UserDTO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.AccessValidate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author My Computer
 */
@Controller
public class UserController {

    private final String ADMIN = "ADMIN";
    private final String USER = "EMPLOYEE";

    @RequestMapping("login.htm")
    public ModelAndView getlogin(@ModelAttribute AccessValidate accessValidate) {
        ModelAndView mav = new ModelAndView("login");
        return mav;
    }

    @RequestMapping("form.htm")
    public ModelAndView login(@ModelAttribute AccessValidate accessValidate) {
        ModelAndView mav = new ModelAndView();
        String userName = accessValidate.getUserName();
        System.out.println("userName = " + userName);
        String password = accessValidate.getPassword();
        System.out.println("password = " + password);
        try {
            LoginDTO loginDTO = DatabaseHelper.getLoginUser(userName, password);
            if (loginDTO != null & loginDTO.getUserId() != 0) {
                UserDTO userDto = DatabaseHelper.getUserById(loginDTO.getUserId());
                if (userDto.getRoles().getName().equalsIgnoreCase(ICommonConstant.ADMIN)) {
                    mav.setViewName("admin");
                    mav.addObject("user", accessValidate);
                    mav.addObject("userid", loginDTO.getUserId());
                    return mav;
                } else {
                    mav.setViewName("user");
                    mav.addObject("user", accessValidate);
                    mav.addObject("userid", loginDTO.getUserId());
                    return mav;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        mav.setViewName("login");
        return mav;
    }
}
