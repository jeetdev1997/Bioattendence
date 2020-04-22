/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author My Computer
 */
public class AttendenceSystemDB 
{
 private static final String JDBC_DRIVER="com.mysql.jdbc.Driver";
 private static final String JDBC_URL="jdbc:mysql://localhost:3306/attendence_system_db";
 private static final String USER="root";
 private static final String PASSWORD="root";
 private static Connection connection=null;
 private static Statement statement=null;
 

    static
    {
     try {
         Class.forName(JDBC_DRIVER);
         connection=DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
         System.out.println("connected");
         
     } catch (Exception ex) {
         Logger.getLogger(AttendenceSystemDB.class.getName()).log(Level.SEVERE, null, ex);
     }
    }
    
    public static void insertRoles(int id,String role,String createdDate,String UpdatedDate) {
        try {
            String sql="INSERT into roles(id,role,createdate,updatedate) VALUES ("+id+","+"'"+role+"'"+","+"'"+createdDate+"'"+","+"'"+UpdatedDate+"'"+")";
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(AttendenceSystemDB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public static void insertDept(int id,String name,boolean isActive,String createdDate,String UpdatedDate) {
        try {
            String sql="INSERT into department(id,name,isActive,createdate,updatedate) VALUES ("+id+","+"'"+name+"'"+","+isActive+","+"'"+createdDate+"'"+","+"'"+UpdatedDate+"'"+")";
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(AttendenceSystemDB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
     public static void insertUser(String userid,String firstName,String lastName,int deptId,int roleId,String address,String email,int isActive,String createdDate,String UpdatedDate) {
        try {
            String sql="INSERT IGNORE into user(userid,firstname,lastname,departmentid,roleid,address,email,isActive,createddate,updateddate) VALUES ("+userid+","+"'"+firstName+"'"+","+"'"+lastName+"'"+","+deptId+","+roleId+","+"'"+address+"'"+","+"'"+email+"'"+","+isActive+","+"'"+createdDate+"'"+","+"'"+UpdatedDate+"'"+")";
            System.out.println("sql = " + sql);
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(AttendenceSystemDB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
      public static void insertLogIn(int userId,String password,String userName) {
        try {
            String sql="INSERT IGNORE into login(userid,password,username) VALUES ("+userId+","+"'"+password+"'"+","+"'"+userName+"'"+")";
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(AttendenceSystemDB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public static void deleteRecord(String string)
    {
     try {
         statement=connection.createStatement();
         statement.executeUpdate(string);
     } catch (SQLException ex) {
         Logger.getLogger(AttendenceSystemDB.class.getName()).log(Level.SEVERE, null, ex);
     }
     
    }
    public static void updateRecord(String string) {
        try {
            statement = connection.createStatement();
            statement.executeUpdate(string);
        } catch (SQLException ex) {
            Logger.getLogger(AttendenceSystemDB.class.getName()).log(Level.SEVERE, null, ex);
        }

}
    public static ResultSet selectRecord(String string) throws SQLException {
        ResultSet resultRecord = null;
        try {
            statement = connection.createStatement();
            resultRecord = statement.executeQuery(string);
        } catch (SQLException ex) {
            Logger.getLogger(AttendenceSystemDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultRecord;
    }
    public static ResultSet selectUserName(int userId) throws SQLException {
        ResultSet resultRecord = null;
        try {
            String string="SELECT username FROM login WHERE userid="+userId;
            statement = connection.createStatement();
            resultRecord = statement.executeQuery(string);
        } catch (SQLException ex) {
            Logger.getLogger(AttendenceSystemDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultRecord;
    }
     public static ResultSet selectRole(int roleId) throws SQLException {
        ResultSet resultRecord = null;
        try {
            String string="SELECT role FROM roles WHERE id="+roleId;
            statement = connection.createStatement();
            resultRecord = statement.executeQuery(string);
        } catch (SQLException ex) {
            Logger.getLogger(AttendenceSystemDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultRecord;
     }
     
     public static ResultSet selectDepartment(int deptId) throws SQLException {
        ResultSet resultRecord = null;
        try {
            String string="SELECT name FROM department WHERE id="+deptId;
            statement = connection.createStatement();
            resultRecord = statement.executeQuery(string);
        } catch (SQLException ex) {
            Logger.getLogger(AttendenceSystemDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultRecord;
    }
     public static ResultSet selectUserId(String userName)
     {
          ResultSet resultRecord = null;
        try {
            String string="SELECT userid from user";
            statement = connection.createStatement();
            resultRecord = statement.executeQuery(string);
        } catch (SQLException ex) {
            Logger.getLogger(AttendenceSystemDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultRecord;
     }
    
        
    public static void connectionClose()
    {
     try {
         connection.close();
     } catch (SQLException ex) {
         Logger.getLogger(AttendenceSystemDB.class.getName()).log(Level.SEVERE, null, ex);
     }
    }
    public static boolean isPresent(String firstName,String lastName,String email)
    {
          ResultSet resultRecord = null;
        try {
            String string="SELECT firstname,lastname,email from user";
            statement = connection.createStatement();
            resultRecord = statement.executeQuery(string);
            while (resultRecord.next())
            {                
             String fName=resultRecord.getString("firstname");
             String lName=resultRecord.getString("lastname");
             String emailId=resultRecord.getString("email");
             if(firstName.equals(fName) && lastName.equals(lName) && email.equals(emailId))
             {
                 return true;
             }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AttendenceSystemDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
        
    }
            
}
