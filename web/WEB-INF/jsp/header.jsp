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
                <ul class="main-nav">
                    <li><a href="/BiomatricsAttendenceSystem/attendance/uploadView.htm">Upload Attendance</a></li>
                    <li><a href="/BiomatricsAttendenceSystem/admin/viewlist.htm">Employees</a></li>
                    <li><a href="/BiomatricsAttendenceSystem/admin/adduser.htm">Add Employee</a></li>
                    <li><a href="#">Contact</a></li>
                </ul>
            </header> 
        </div>
    </body>
</html>
