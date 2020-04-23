<%-- 
    Document   : adduser
    Created on : Apr 20, 2020, 2:27:06 PM
    Author     : My Computer
--%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page import="java.security.cert.PKIXRevocationChecker.Option"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="model.AttendenceSystemDb"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    <h1>user</h1>
</head>
<body>
    <form action="http://localhost:8084/BiomatricsAttendenceSystem/admin/add.htm" method="post">
        <%!
            ResultSet record;
        %>
        <table>
            <tr>
                <td>FirstName:</td>
                <td>
                    <input type="text" name="firstName" required=""/>
                </td>
            </tr>
            <tr>
                <td>LastName:</td>
                <td>
                    <input type="text" name="lastName" required=""/>
                </td>
            </tr>
            <tr>
                <td>DepartmentName:</td>
                <td>
                    <select id="deptId" name="deptId">
                        <option value="null">Select</option>
                        <%
                            String sql = "SELECT id,name from department";
                            this.record = AttendenceSystemDb.selectRecord(sql);
                            while (record.next()) {
                                int id = record.getInt("id");
                                System.out.println("id = " + id);
                                String deptName = record.getString("name");
                                out.print("<option value=" + id + ">" + deptName + "</option>");
                            }
                        %>
                    </select>
                </td>
            </tr>
            <tr>
                <td>RoleId:</td>
                <td>
                    <select id="roleId" name="roleId">
                        <option value="null">Select</option>
                        <%
                            String sqlRoleId = "SELECT id,role from roles";
                            record = AttendenceSystemDb.selectRecord(sqlRoleId);
                            while (record.next()) {
                                int id = record.getInt("id");
                                String role = record.getString("role");
                                out.print("<option value=" + id + ">" + role + "</option>");
                            }
                        %>
                </td>
            </tr>

            <tr>
                <td>Address:</td>
                <td>
                    <input type="text" name="address" required=""/>
                </td>
            </tr>
            <tr>
                <td>Email:</td>
                <td>
                    <input type="email" name="email" required=""/>
                </td>
            </tr>
            <tr>
                <td>IsActive:</td>
                <td>
                    <select id="isActive" name="isActive">
                        <option value="0">true</option>
                        <option value="1">false</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>CreatedDate:</td>
                <td>
                    <input type="date" name="createdDate" required=""/>
                </td>
            </tr>
            <tr>

                <td>UpdatedDate:</td>
                <td>
                    <input type="date" name="updatedDate" required=""/>
                </td>
            </tr>
            <tr>
                <td>UserName</td>
                <td>
                    <input type="text" name="userName" required=""/>
                </td>
            </tr>
            <tr>
                <td>Password</td>
                <td>
                    <input type="password" name="password" required=""/>
                </td>
            </tr>
            <tr>
                <td rowspan="2" align="center">
                    <input type="submit" value="add"/>
                    <input type="reset" value="clear"/>
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
