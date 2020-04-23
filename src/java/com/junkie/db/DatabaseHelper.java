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
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author ashish.yetre
 */
public class DatabaseHelper {

    private static final String INSERT_ATTENDANCE_SQL = "INSERT INTO userAttendance"
            + "(EMP_ID, in_time, out_time) VALUES (?,?,?,?)";
    private static final String SELECT_LOGIN_SQL = "select * from login where username = ? and password = ?";
    private static final String SELECT_USER_SQL = "select * from users where userId = ?";
    private static final String SELECT_ROLE_SQL = "SELECT * FROM roles WHERE id=?";
    private static final String SELECT_DEPARTMENT_SQL = "SELECT * FROM department WHERE id=?";

    public static void insertUserAttendance(List<AttendanceDTO> attendanceDTOList) throws SQLException, Exception {
        try (Connection connection = SQLConnectionHelper.getNewConnection();) {
            PreparedStatement prepareStatement = connection.prepareStatement(INSERT_ATTENDANCE_SQL);
            for (AttendanceDTO attendanceDTO : attendanceDTOList) {
                prepareStatement.setLong(0, new Integer(attendanceDTO.getEmpId()));
                prepareStatement.setDate(1, (Date) attendanceDTO.getAttendedDate());
                prepareStatement.setDate(2, (Date) attendanceDTO.getInTime());
                prepareStatement.setDate(3, (Date) attendanceDTO.getOutTime());
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
                user.setUpdatedDate(resultSet.getString("updateddate"));
            }
            return user;
        } catch (Exception ex) {
            throw ex;
        }
    }
}
