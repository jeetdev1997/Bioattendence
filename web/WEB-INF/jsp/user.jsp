<%-- 
    Document   : user
    Created on : Apr 20, 2020, 11:07:28 AM
    Author     : My Computer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
         <jsp:include page="header.jsp" /> 
        <jsp:include page="footer.jsp" />
        <jsp:include page="admincss.jsp" /> 
        <jsp:include page="dashboardscript.jsp" />
    </head>
    <body>
       <div class="fixed-header">
            <div class="split left" id="main"> <span style="font-size:20px;cursor:pointer" onclick="openNav()">&#9776; open</span></div>
            <div class="split right"><b> Biomatric Attendance System</b></div>
        </div>
        <div id="mySidenav" class="sidenav">
            <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
            <!--<a href="http://localhost:8084/BiomatricsAttendenceSystem/admin/adduser.htm">AddEmployee</a>-->
            <a href="http://localhost:8084/BiomatricsAttendenceSystem/admin/viewlist.htm">ViewAttendance</a>
        </div>  
        <div class=" division body">
            <h1>Welcome User</h1>
            <form method="post" action="logout.htm">
                <p></p>
                <center><label> User Name: ${user.userName}</label></center><br>
                <p></p>

                <center> <label>User Id: ${userid}</label></center><br>
                <p></p><br>
                <center><button type="submit" class="btn btn-primary btn-block btn-large">Logout</button></center>
            </form>
        </div>
        <div class="footer"> <center>@ Copyright...</center></div>
    </body>
    
</html>
