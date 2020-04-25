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
        <jsp:include page="addusercss.jsp" /> 
        <jsp:include page="header.jsp" /> 
        <jsp:include page="footer.jsp" /> 
    </head>
    <body>
        <div class="fixed-header" id="myHeader"> <center><b>Biomatric Attendance System</b></center></div>
                <%!
                    ResultSet record;
                %>
        <div class="div">
            <form action="http://localhost:8084/BiomatricsAttendenceSystem/admin/add.htm" method="post">
                <table class="table">
                    <tr>
                        <td><label for="fname">First Name</label></td>
                        <td><input type="text"  name="firstName" placeholder="Your name.." required="" autocomplete="off"/></td></tr>

                    <tr> <td><label for="lname">Last Name</label></td>
                        <td><input type="text"  name="lastName" placeholder="Your last name.." required="" autocomplete="off"></td></tr>
                    <tr>
                        <td><label for="deaprtment">Department</label></td>
                        <td><select  name="deptId">
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
                            </select></td></tr>
                    <tr><td><label for="country">Role</label></td>
                        <td><select id="roleId" name="roleId">
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
                            </select></td></tr>
                    <tr><td><label for="Address">Address</label></td>
                        <td><input type="text" id="address" name="address" placeholder="Address.." required="" autocomplete="off"></td></tr>
                    <tr> <td><label for="Email">Email</label></td>
                        <td><input type="email" class="email" id="email" name="email" placeholder="email.." required="" autocomplete="off"></td></tr>
                    <tr><td><label for="is">isActive</label></td>
                        <td> <select id="isActive" name="isActive">
                                <option value="0">true</option>
                            </select></td></tr>
                    <tr><td><label for="createddate">CreatedDate</label></td>
                        <td><input type="Date" id="createdate" name="createdDate" required="" autocomplete="off"/></td><tr>
                    <tr><td><label for="updateddate">UpdatedDate</label></td>
                        <td><input type="Date" id="updatedate" name="updatedDate" required="" autocomplete="off"/></td></tr>
                    <tr> <td><label for="username">UserName</label></td>
                        <td><input type="text" id="username" name="userName" required="" autocomplete="off"/><td><tr>
                    <tr><td><label for="password">Password</label></td>
                        <td><input type="password" id="password" name="password" required="" autocomplete="off"/></td></tr>
                    <tr>
                        <td colspan="2" align="center"/>
                    <input type="submit" value="AddEmployee"/></td>
                    </tr>
                </table>
            </form>
        </div>
        <div class="footer"> <center>@ Copyright...</center></div> 
    </body>
</html>
