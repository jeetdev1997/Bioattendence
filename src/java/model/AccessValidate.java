package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author My Computer
 */
public class AccessValidate
{
 private String userName;
 private String password;
 

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userId) {
        this.userName = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
//    public static void main(String[] args)
//    {
//     AttendenceSystemDB asdb=new AttendenceSystemDB();
//     String sql="SELECT userid, password,username FROM login";
//     try {
//             ResultSet resultRecord=asdb.selectRecord(sql);
//             while (resultRecord.next()) 
//             {             
//             int id=resultRecord.getInt("userId");
//                 System.out.println("id = " + id);
//                 String password=resultRecord.getString("password");
//                 System.out.println("password="+password);
//         }
//                 } catch (SQLException ex) {
//         Logger.getLogger(AccessValidate.class.getName()).log(Level.SEVERE, null, ex);
//     }
//     
//    }
}
