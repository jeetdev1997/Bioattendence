/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.junkie.db;

import com.junkie.dto.AttendanceDTO;
import com.junkie.dto.DepartmentDTO;
import com.junkie.dto.LoginDTO;
import com.junkie.dto.RolesDTO;
import com.junkie.dto.UserDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.AddUser;

/**
 *
 * @author ashish.yetre
 */
public class DatabaseHelper {

    private static final String INSERT_ATTENDANCE_SQL = "INSERT INTO userAttendance"
            + " (userid,attended_date,in_time, out_time) VALUES (?,?,?,?)";
    private static final String SELECT_LOGIN_SQL = "select * from login where username = ? and password = ?";
    private static final String SELECT_USER_SQL = "select * from users where userId = ?";
    private static final String SELECT_ROLE_SQL = "SELECT * FROM roles WHERE id=?";
    private static final String SELECT_DEPARTMENT_SQL = "SELECT * FROM department WHERE id=?";
    private static final String INSERT_USERS_SQL = "INSERT INTO users" + "(userid,firstname,lastname,departmentid,roleid,address,email,isActive,createddate,updateddate) VALUES(?,?,?,?,?,?,?,?,?,?)";
    private static final String INSERT_LOGIN_SQL = "INSERT INTO login" + "(userid,password,username) VALUES(?,?,?)";
    private static final String SELECT_USERId_SQL = "SELECT * FROM users WHERE email=?";
    private static final String SELECT_USERS_SQL = "SELECT * FROM users";
    private static final String SELECT_USERNAME_SQL = "SELECT username FROM login WHERE userid=?";
    private static final String UPDATE_USER_SQL = "UPDATE users set isActive=1 WHERE userid=?";

    public static void insertUserAttendance(List<AttendanceDTO> attendanceDTOList) throws SQLException, Exception {
        try (Connection connection = SQLConnectionHelper.getNewConnection();) {
            PreparedStatement prepareStatement = connection.prepareStatement(INSERT_ATTENDANCE_SQL);
            for (AttendanceDTO attendanceDTO : attendanceDTOList) {
                prepareStatement.setInt(1, new Integer(attendanceDTO.getEmpId()));
                prepareStatement.setDate(2, attendanceDTO.getAttendedDate());
                prepareStatement.setDate(3, attendanceDTO.getInTime());
                prepareStatement.setDate(4, attendanceDTO.getOutTime());
                prepareStatement.addBatch();
            }
            int[] executeBatch = prepareStatement.executeBatch();
            System.out.println("status : " + Arrays.toString(executeBatch));
        } catch (Exception e) {
            throw e;
        }

    }

    public static LoginDTO getLoginUser(String username, String password) throws SQLException, Exception {
        try (Connection connection = SQLConnectionHelper.getNewConnection();) {
            Statement statement = connection.createStatement();
            String sqlQuery = SELECT_LOGIN_SQL.replaceFirst("[?]", "'" + username + "'").replaceFirst("[?]", "'" + password + "'");
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            LoginDTO loginDTO = new LoginDTO();
            while (resultSet.next()) {
                loginDTO.setUserId(resultSet.getInt(1));
                loginDTO.setPassword(resultSet.getString(2));
                loginDTO.setUsername(resultSet.getString(3));
            }
            return loginDTO;
        } catch (Exception exception) {
            throw exception;
        }

    }

    public static DepartmentDTO getDepartmentById(Integer departmentId) throws SQLException, ClassNotFoundException {
        DepartmentDTO departmentDTO = new DepartmentDTO();
        try (Connection connection = SQLConnectionHelper.getNewConnection();) {
            Statement statement = connection.createStatement();
            String sqlQuery = SELECT_DEPARTMENT_SQL.replaceFirst("[?]", "" + departmentId);
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                departmentDTO.setDepartmentId(resultSet.getInt("id"));
                departmentDTO.setName(resultSet.getString("name"));
                departmentDTO.setIsActive(resultSet.getBoolean("isActive"));
            }
        } catch (Exception e) {
            throw e;
        }
        return departmentDTO;
    }

    public static RolesDTO getRoleById(Integer roleId) throws SQLException, ClassNotFoundException {
        RolesDTO rolesDTO = new RolesDTO();
        try (Connection connection = SQLConnectionHelper.getNewConnection();) {
            Statement statement = connection.createStatement();
            String sqlQuery = SELECT_ROLE_SQL.replaceFirst("[?]", "" + roleId);
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            rolesDTO.setRoleId(roleId);
            while (resultSet.next()) {
                rolesDTO.setName(resultSet.getString("role"));
            }
        } catch (Exception e) {
            throw e;
        }
        return rolesDTO;
    }

    public static UserDTO getUserById(Integer userId) throws SQLException, ClassNotFoundException {
        try (Connection connection = SQLConnectionHelper.getNewConnection();) {
            Statement createStatement = connection.createStatement();
            String sqlQuery = SELECT_USER_SQL.replaceFirst("[?]", "" + userId);
            ResultSet resultSet = createStatement.executeQuery(sqlQuery);
            UserDTO user = new UserDTO();
            while (resultSet.next()) {
                user.setUserId(resultSet.getInt("userid"));
                user.setAddress(resultSet.getString("address"));
                user.setEmail(resultSet.getString("email"));
                user.setRoles(getRoleById(resultSet.getInt("roleId")));
                user.setDepartment(getDepartmentById(resultSet.getInt("departmentid")));
                user.setUpdatedDate(resultSet.getString("updateddate"));
                user.setIsActive(resultSet.getInt("isActive"));
                user.setCreatedDate(resultSet.getString("createddate"));
//                user.setUpdatedDate(resultSet.getString("updateddate"));
            }
            return user;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static void insertUsers(AddUser addUser) throws Exception {
        try (Connection connection = SQLConnectionHelper.getNewConnection();) {
            PreparedStatement prepareStatement = connection.prepareStatement(INSERT_USERS_SQL);
            prepareStatement.setString(1, null);
            prepareStatement.setString(2, addUser.getFirstName());
            prepareStatement.setString(3, addUser.getLastName());
            prepareStatement.setInt(4, addUser.getDeptId());
            prepareStatement.setInt(5, addUser.getRoleId());
            prepareStatement.setString(6, addUser.getAddress());
            prepareStatement.setString(7, addUser.getEmail());
            prepareStatement.setInt(8, addUser.getIsActive());
            prepareStatement.setString(9, addUser.getCreatedDate());
            prepareStatement.setString(10, addUser.getUpdatedDate());
            prepareStatement.addBatch();

            int[] executeBatch = prepareStatement.executeBatch();
            System.out.println("status : " + Arrays.toString(executeBatch));
        } catch (Exception e) {
            throw e;
        }
    }

    public static void insertlogIn(LoginDTO loginDTO, String email) throws SQLException, Exception {
        try (Connection connection = SQLConnectionHelper.getNewConnection();) {
            Statement statement = connection.createStatement();
            String sqlQuery = SELECT_USERId_SQL.replaceFirst("[?]", "'" + email + "'");
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                int userId = resultSet.getInt("userid");
                PreparedStatement prepareStatement = connection.prepareStatement(INSERT_LOGIN_SQL);
                prepareStatement.setInt(1, userId);
                prepareStatement.setString(2, loginDTO.getPassword());
                prepareStatement.setString(3, loginDTO.getUsername());
                prepareStatement.addBatch();
                int[] executeBatch = prepareStatement.executeBatch();
            }
        } catch (Exception e) {
            throw e;
        }

    }

    public static ArrayList<UserDTO> getUsers() throws SQLException, ClassNotFoundException {
        try (Connection connection = SQLConnectionHelper.getNewConnection();) {
            ArrayList<UserDTO> userDtoList = new ArrayList<>();
            Statement createStatement = connection.createStatement();
            String sqlQuery = SELECT_USERS_SQL;
            ResultSet resultSet = createStatement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                UserDTO user = new UserDTO();
                user.setUserId(resultSet.getInt("userid"));
                user.setName((resultSet.getString("firstname")+" "+resultSet.getString("lastname")));
                user.setAddress(resultSet.getString("address"));
                user.setEmail(resultSet.getString("email"));
                user.setRoles(getRoleById(resultSet.getInt("roleId")));
                user.setDepartment(getDepartmentById(resultSet.getInt("departmentid")));
                user.setUpdatedDate(resultSet.getString("updateddate"));
                user.setIsActive(resultSet.getInt("isActive"));
                user.setCreatedDate(resultSet.getString("createddate"));
                if (user.getIsActive() == 1) {
                    userDtoList.add(user);
                }
            }
            return userDtoList;
        } catch (Exception ex) {
            throw ex;
        }
    }

    private static String getUserName(int userId) throws SQLException, ClassNotFoundException {
        String userName = "";
        try (Connection connection = SQLConnectionHelper.getNewConnection();) {
            Statement statement = connection.createStatement();
            String sqlQuery = SELECT_USERNAME_SQL.replaceFirst("[?]", "" + userId);
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                userName = resultSet.getString("username");
            }
        } catch (Exception e) {
            throw e;
        }
        return userName;
    }

    public static void updateUsers(int userId) throws SQLException, ClassNotFoundException {
        try (Connection connection = SQLConnectionHelper.getNewConnection();) {
            Statement statement = connection.createStatement();
            String sqlQuery = UPDATE_USER_SQL.replaceFirst("[?]", "" + userId);
            int resultSet = statement.executeUpdate(sqlQuery);

        } catch (Exception e) {
            throw e;
        }
    }

}
