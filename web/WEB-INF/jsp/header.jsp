<%-- 
    Document   : header
    Created on : Apr 23, 2020, 8:35:19 PM
    Author     : My Computer
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<c:url value="/resources/css/dashboard-buttons-css.css" />" rel="stylesheet">
    </head>
    <body>
        <div class="wrapper">
            <header class="header">
                <h1 class="logo"><a href="#">Biometrics Attendance System</a></h1>
              <!--${sessionScope.adminUser}-->
              <c:choose>
              <c:when test="${sessionScope.adminUser}">
                <ul class="main-nav">
                    <li><a href="/BiomatricsAttendenceSystem/attendance/uploadView.htm">Upload Attendance</a></li>
                    <li><a href="/BiomatricsAttendenceSystem/admin/viewlist.htm">Employees</a></li>
                    <li><a href="/BiomatricsAttendenceSystem/admin/adduser.htm">Add Employee</a></li>
                    <li><a href="/BiomatricsAttendenceSystem/admin/department.htm">Add Department</a></li>
                    <li><a href="/BiomatricsAttendenceSystem/admin/role.htm">Add Role</a></li>
                     <li><a href="/BiomatricsAttendenceSystem/user/logout.htm">Logout</a></li>
                </ul>
              </c:when>
                  <c:when test="${sessionScope.EmployeeUser}">
                      <ul class="main-nav">
                          <li><a href="/BiomatricsAttendenceSystem/employee/profile.htm?empId=${sessionScope.userid}">Profile</a></li>
                      <li><a href="/BiomatricsAttendenceSystem/employee/viewAttendance.htm?empId=${sessionScope.userid}">View Attendance</a></li>
                      <li><a href="/BiomatricsAttendenceSystem/user/logout.htm">Logout</a></li>
                    </ul>
                  </c:when>
                </c:choose>
            </header> 
        </div>
    </body>
</html>
