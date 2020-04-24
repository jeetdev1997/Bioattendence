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
        <style>
     .Row
    {
        display: table-row;
        width: 100p;
    }
    .Cell
    {
        display: table-cell;
        border: solid;
        border-width: thin;
        padding-left: 5px;
        padding-right: 5px;
    }
    .fixed-header, .fixed-footer{
        width: 100%;
        position: fixed;        
        background: #333;
        padding: 15px 0;
        color: #fff;
    }
    .fixed-header{
        top: 0;
    }
    .fixed-footer{
        bottom: 0;
    }  
        </style>
    </head>
    <body>
    <center><table border width="100px" cellspacing="0" cellpadding="0" class="Row">
            <tr>
                <td>
                    UserName ${user.userName}
                </td>
                <td>
                     UserName ${user.userName}
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
        </table>
                </center>
    </body>
</html>
