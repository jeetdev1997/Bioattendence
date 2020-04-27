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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.AddUser;
import org.springframework.util.StringUtils;

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
    private static final String INSERT_USERS_SQL = "INSERT INTO users" + "(firstname,lastname,departmentid,roleid,address,email,isActive,createddate,updateddate) VALUES(?,?,?,?,?,?,?,?,?)";
    private static final String INSERT_LOGIN_SQL = "INSERT INTO login" + "(userid,username,password) VALUES(?,?,?)";
    private static final String SELECT_USERID_SQL = "SELECT * FROM login WHERE username=?";
    private static final String SELECT_USERS_SQL = "SELECT * FROM users";
    private static final String SELECT_USERNAME_SQL = "SELECT username FROM login WHERE userid=?";
    private static final String SELECT_ATTENDANCE_CURRENT_DATE_SQL = "select * from userAttendance where userid = ? and attended_date between ? and CURDATE();";
    private static final String UPDATE_USER_SQL = "UPDATE users set isActive=1 WHERE userid=?";
    private static final String FIND_LOGIN_USER_NAME_SQL = "select * from login";
//    private static final String FIND_USER_SQL = "select * from users";
    private static final String UPDATE_USERS_SQL = "UPDATE users SET firstname=?" + "," + "lastname=?" + "," + "departmentid=?" + "," + "roleid=?" + "," + "address=?" + " " + " WHERE userid=?;";
    private static final String INSERT_DEPARTMENT_SQL = "INSERT INTO department" + "(name,isActive,createdate,updatedate) VALUES(?,?,?,?)";
    private static final String FIND_DEPARTMENT_SQL = "select * from department";
    private static final String INSERT_ROLES_SQL = "INSERT INTO roles" + "(role,createdate,updatedate) VALUES(?,?,?)";
    private static final String FIND_ROLES_SQL = "select * from roles";

    public static void insertUserAttendance(List<AttendanceDTO> attendanceDTOList) throws SQLException, Exception {
        try (Connection connection = SQLConnectionHelper.getNewConnection();) {
            PreparedStatement prepareStatement = connection.prepareStatement(INSERT_ATTENDANCE_SQL);
            for (AttendanceDTO attendanceDTO : attendanceDTOList) {
                prepareStatement.setInt(1, new Integer(attendanceDTO.getEmpId()));
                prepareStatement.setString(2, attendanceDTO.getStatus());
                prepareStatement.setDate(3, attendanceDTO.getAttendedDate());
                prepareStatement.setString(4, attendanceDTO.getInTimestamp());
                prepareStatement.setString(5, attendanceDTO.getOutTimestamp());
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
                loginDTO.setPassword(resultSet.getString("password"));
                loginDTO.setUsername(resultSet.getString("username"));
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
                user.setFirstName(resultSet.getString("firstname"));
                user.setLastName(resultSet.getString("lastname"));
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


    public static int insertUsers(AddUser addUser) throws Exception {
        try (Connection connection = SQLConnectionHelper.getNewConnection();) {
            PreparedStatement prepareStatement = connection.prepareStatement(INSERT_USERS_SQL, Statement.RETURN_GENERATED_KEYS);
            prepareStatement.setString(1, addUser.getFirstName());
            prepareStatement.setString(2, addUser.getLastName());
            System.out.println("Department Id : " + addUser.getDeptId());
            prepareStatement.setInt(3, addUser.getDeptId());
            prepareStatement.setInt(4, addUser.getRoleId());
            prepareStatement.setString(5, addUser.getAddress());
            prepareStatement.setString(6, addUser.getEmail());
            prepareStatement.setInt(7, 0);
            prepareStatement.setDate(8, new Date(System.currentTimeMillis()));
            prepareStatement.setDate(9, new Date(System.currentTimeMillis()));
            prepareStatement.executeUpdate();
            ResultSet rs = prepareStatement.getGeneratedKeys();
            if (rs != null && rs.next()) {
                int userId = rs.getInt(1);
                System.out.println("Generated Emp Id: " + userId);
                rs.close();
                return userId;
            }
            return 0;
        } catch (Exception e) {
            throw e;
        }
    }

    public static void insertlogIn(LoginDTO loginDTO) throws Exception {
        try (Connection connection = SQLConnectionHelper.getNewConnection();) {
            PreparedStatement prepareStatement = connection.prepareStatement(INSERT_LOGIN_SQL);
            prepareStatement.setInt(1, loginDTO.getUserId());
            prepareStatement.setString(2, loginDTO.getUsername());
            prepareStatement.setString(3, loginDTO.getPassword());
            prepareStatement.addBatch();
            int[] executeBatch = prepareStatement.executeBatch();
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
                user.setFirstName(resultSet.getString("firstname"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAddress(resultSet.getString("address"));
                user.setEmail(resultSet.getString("email"));
                user.setRoles(getRoleById(resultSet.getInt("roleId")));
                user.setDepartment(getDepartmentById(resultSet.getInt("departmentid")));
                user.setUpdatedDate(resultSet.getString("updateddate"));
                user.setIsActive(resultSet.getInt("isActive"));
                user.setCreatedDate(resultSet.getString("createddate"));
                if (user.getIsActive() != 1) {
                    userDtoList.add(user);
                }
            }
            return userDtoList;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static String getUserName(Integer userId) throws SQLException, ClassNotFoundException {
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

    public static void updateUsers(Integer userId) throws SQLException, ClassNotFoundException {
        try (Connection connection = SQLConnectionHelper.getNewConnection();) {
            Statement statement = connection.createStatement();
            String sqlQuery = UPDATE_USER_SQL.replaceFirst("[?]", "" + userId);
            int resultSet = statement.executeUpdate(sqlQuery);

        } catch (Exception e) {
            throw e;
        }
    }

     public static List<AttendanceDTO> getEmployeeAttendanceByEmpId(Integer userId, String month) throws Exception {
        try (Connection connection = SQLConnectionHelper.getNewConnection();) {
            PreparedStatement statement = connection.prepareStatement(SELECT_ATTENDANCE_CURRENT_DATE_SQL);
            statement.setInt(1, userId);
            LocalDate withDayOfMonth = LocalDate.now().withDayOfMonth(1);
            String endDate = "CURDATE()";
            if (!StringUtils.isEmpty(month)) {
                System.out.println("Month:" + month);
                withDayOfMonth = LocalDate.of(2020, Month.valueOf(month), 1);
                endDate = LocalDate.of(2020, Month.valueOf(month), withDayOfMonth.lengthOfMonth()).toString();
            }
            statement.setString(2, withDayOfMonth.toString());
            statement.setString(3, endDate);
            System.out.println("SQL :" + statement.toString());
            ResultSet resultSet = statement.executeQuery();
            List<AttendanceDTO> attendanceDTOList = new ArrayList();
            while (resultSet.next()) {
                AttendanceDTO attendanceDTO = new AttendanceDTO();
                Date attended_date = resultSet.getDate("attended_date");
                attendanceDTO.setDay(attended_date.toLocalDate().getDayOfWeek().name());
                attendanceDTO.setAttendedDate(attended_date);
                attendanceDTO.setStatus(resultSet.getString("status"));
                String inTimestamp = resultSet.getString("in_time");
                LocalDateTime ofEpochSecond = LocalDateTime.ofEpochSecond(Long.valueOf(inTimestamp), 1, ZoneOffset.UTC);
                String formatInTime = ofEpochSecond.plusHours(5).plusMinutes(30).format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT));
                attendanceDTO.setFormattedInTime(formatInTime);
                String outTimestamp = resultSet.getString("out_time");
                ofEpochSecond = LocalDateTime.ofEpochSecond(Long.valueOf(outTimestamp), 1, ZoneOffset.UTC);
                String formatOutTime = ofEpochSecond.plusHours(5).plusMinutes(30).format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT));
                attendanceDTO.setFormattedOutTime(formatOutTime);
                attendanceDTOList.add(attendanceDTO);
            }
            return attendanceDTOList;
        } catch (Exception e) {
            throw e;
        }
    }

    public static ArrayList<RolesDTO> getRoles() throws Exception {
        ArrayList<RolesDTO> list = new ArrayList<>();
        try (Connection connection = SQLConnectionHelper.getNewConnection();) {
            Statement statement = connection.createStatement();
            String sqlQuery = SELECT_ROLE_SQL.replace(SELECT_ROLE_SQL, "SELECT * FROM roles");
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                RolesDTO rolesDTO = new RolesDTO();
                rolesDTO.setRoleId(resultSet.getInt("id"));
                rolesDTO.setName(resultSet.getString("role"));
//                rolesDTO.setName(resultSet.getString("role"));
                list.add(rolesDTO);
            }
        } catch (Exception e) {
            throw e;
        }
        return list;

    }

    public static ArrayList<DepartmentDTO> getDepartment() throws Exception {
        ArrayList<DepartmentDTO> list = new ArrayList<>();
        try (Connection connection = SQLConnectionHelper.getNewConnection();) {
            Statement statement = connection.createStatement();
            String sqlQuery = SELECT_DEPARTMENT_SQL.replace(SELECT_DEPARTMENT_SQL, "SELECT * FROM department");
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                DepartmentDTO departmentDTO = new DepartmentDTO();
                departmentDTO.setDepartmentId(resultSet.getInt("id"));
                departmentDTO.setName(resultSet.getString("name"));
                departmentDTO.setIsActive(resultSet.getBoolean("isActive"));
                list.add(departmentDTO);
            }
        } catch (Exception e) {
            throw e;
        }
        return list;
    }

    public static boolean isLoginUserNamePresent(String email) throws Exception {
        try (Connection connection = SQLConnectionHelper.getNewConnection();) {
            Statement statement = connection.createStatement();
            String sqlQuery = FIND_LOGIN_USER_NAME_SQL;
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                if (email.equals(resultSet.getString("username"))) {
                    return true;
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return false;
    }

    public static void updateUsers(AddUser addUser, int userId) throws SQLException, ClassNotFoundException {
        try (Connection connection = SQLConnectionHelper.getNewConnection();) {
            Statement statement = connection.createStatement();
            String sqlQuery = UPDATE_USERS_SQL.replaceFirst("[?]", "'" + addUser.getFirstName() + "'").replaceFirst("[?]", "'" + addUser.getLastName() + "'").replaceFirst("[?]", "" + addUser.getDeptId()+"").replaceFirst("[?]", "" + addUser.getRoleId()+"").replaceFirst("[?]", "'" + addUser.getAddress() + "'").replaceFirst("[?]", "" + userId);
//            String sqlQuery1 = sqlQuery.replaceFirst("[?]", "'" + addUser.getLastName() + "'");
//            String sqlQuery2 = sqlQuery1.replaceFirst("[?]", "" + addUser.getDeptId()+"");
//            String sqlQuery3 = sqlQuery2.replaceFirst("[?]", "" + addUser.getRoleId()+"");
//            String sqlQuery4 = sqlQuery3.replaceFirst("[?]", "'" + addUser.getAddress() + "'");
//            String sqlQuery5 = sqlQuery4.replaceFirst("[?]", "'" + addUser.getEmail() + "'");
//            String sqlQuery6 = sqlQuery5.replaceFirst("[?]", "" + userId);
            System.out.println("sqlQuery6 = " + sqlQuery);
            int resultSet = statement.executeUpdate(sqlQuery);

        } catch (Exception e) {
            throw e;
        }
    }

    public static int getUserId(String userName) throws Exception {
        int userId = 0;
        try (Connection connection = SQLConnectionHelper.getNewConnection();) {
            Statement statement = connection.createStatement();
            String sqlQuery = SELECT_USERID_SQL.replaceFirst("[?]", "'" + userName + "'");
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                userId = resultSet.getInt("userid");
            }
        } catch (Exception e) {
            throw e;
        }
        return userId;
    }

//    public static boolean isLoginUserNamePresent(String userName) throws Exception {
//        try (Connection connection = SQLConnectionHelper.getNewConnection();) {
//            Statement statement = connection.createStatement();
//            String sqlQuery = FIND_LOGIN_USER_NAME_SQL;
//            ResultSet resultSet = statement.executeQuery(sqlQuery);
//            while (resultSet.next()) {
//                if (userName.equals(resultSet.getString("username"))) {
//                    return true;
//                }
//            }
//        } catch (Exception e) {
//            throw e;
//        }
//        return false;
//    }

    public static int insertDepartment(DepartmentDTO dTO) throws Exception {
        try (Connection connection = SQLConnectionHelper.getNewConnection();) {
            PreparedStatement prepareStatement = connection.prepareStatement(INSERT_DEPARTMENT_SQL, Statement.RETURN_GENERATED_KEYS);
            prepareStatement.setString(1, dTO.getName());
            prepareStatement.setInt(2, 0);
            prepareStatement.setDate(3, new Date(System.currentTimeMillis()));
            prepareStatement.setDate(4, new Date(System.currentTimeMillis()));
            prepareStatement.executeUpdate();
            ResultSet rs = prepareStatement.getGeneratedKeys();
            if (rs != null && rs.next()) {
                int deptId = rs.getInt(1);
                System.out.println("Generated Emp Id: " + deptId);
                rs.close();
                return deptId;
            }
            return 0;
        } catch (Exception e) {
            throw e;
        }
    }
    
     public static boolean isDepartmentPresent(String deptName) throws Exception {
        try (Connection connection = SQLConnectionHelper.getNewConnection();) {
            Statement statement = connection.createStatement();
            String sqlQuery = FIND_DEPARTMENT_SQL;
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                if (deptName.equals(resultSet.getString("name"))) {
                    return true;
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return false;
    }
     
      public static int insertRoles(RolesDTO rolesDTO) throws Exception {
        try (Connection connection = SQLConnectionHelper.getNewConnection();) {
            PreparedStatement prepareStatement = connection.prepareStatement(INSERT_ROLES_SQL, Statement.RETURN_GENERATED_KEYS);
            prepareStatement.setString(1, rolesDTO.getName());
            prepareStatement.setDate(2, new Date(System.currentTimeMillis()));
            prepareStatement.setDate(3, new Date(System.currentTimeMillis()));
            prepareStatement.executeUpdate();
            ResultSet rs = prepareStatement.getGeneratedKeys();
            if (rs != null && rs.next()) {
                int roleId = rs.getInt(1);
                System.out.println("Generated Emp Id: " + roleId);
                rs.close();
                return roleId;
            }
            return 0;
        } catch (Exception e) {
            throw e;
        }
      }
      
        public static boolean isRolePresent(String role) throws Exception {
        try (Connection connection = SQLConnectionHelper.getNewConnection();) {
            Statement statement = connection.createStatement();
            String sqlQuery = FIND_ROLES_SQL;
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                if (role.equals(resultSet.getString("role"))) {
                    return true;
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return false;
    }

}
