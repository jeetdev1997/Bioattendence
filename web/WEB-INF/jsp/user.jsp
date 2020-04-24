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
    </head>
    <body>
        <center><table border width="100px" cellspacing="0" cellpadding="0" class="Row">
            <tr>
                <td>
                    UserName ${user.userName}
                </td>
                <td>
                     UserID ${userid}
                </td>
                <td>
                    <form action="http://localhost:8084/BiomatricsAttendenceSystem/attendanceupload/uploadAttendance" method="post">
                        <input type="submit" value="ViewAttendance"/>
                    </form> 
                </td>
            </tr>
            <tr>

            </tr>
        </table>
                </center>
    </body>
</html>
