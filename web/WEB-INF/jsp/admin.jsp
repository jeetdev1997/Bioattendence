<%-- 
    Document   : admin
    Created on : Apr 20, 2020, 11:06:55 AM
    Author     : My Computer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table border width="100px" widht="100px">
            <tr>
                <td>
                   UserName ${user.userName}
                </td>
                <td>
                   UserId ${userid}
                </td>
                <td>
                    <form action="http://localhost:8084/BiomatricsAttendenceSystem/admin/adduser.htm" method="post">
                        <input type="submit" value="AddUser"/>
                    </form>
                </td>
                <td>
                     <form action="http://localhost:8084/BiomatricsAttendenceSystem/admin/viewlist.htm" method="post">
                        <input type="submit" value="ViewList"/>
                    </form> 
                </td>
            </tr>
            <tr>
                
            </tr>
        </table>
    </body>
</html>
