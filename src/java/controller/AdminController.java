/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.junkie.db.DatabaseHelper;
import com.junkie.dto.LoginDTO;
import com.junkie.dto.UserDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.AddUser;
import model.AttendenceSystemDb;
import model.User;
import org.springframework.stereotype.Controller;
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
    public ModelAndView addUser() {
        ModelAndView mav = new ModelAndView("adduser");
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
        ModelAndView mav = new ModelAndView("adduser");
        boolean isPresent = AttendenceSystemDb.isPresent(addUser);
        if (!isPresent) {
            DatabaseHelper.insertUsers(addUser);
            LoginDTO loginDTO = new LoginDTO();
            loginDTO.setPassword(addUser.getPassword());
            loginDTO.setUsername(addUser.getUserName());
            DatabaseHelper.insertlogIn(loginDTO, addUser.getEmail());
        }
        return mav;
    }
}
