<%-- 
    Document   : login
    Created on : Apr 20, 2020, 2:37:03 AM
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
        <form action="form.htm" method="post">
            <table width="100">
                <tr>
                    <td width="100px" align="center">
                        Biomatrics Attendence System
                    </td>
                </tr>
                <tr>
                    <td>
                        <table>
                            <tr>
                                <td>UserName :</td>
                                <td>
                                    <input type="text" name="userName" required/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                  Password : 
                                </td>
                                <td>
                                    <input type="password" name="password" required/>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <input type="submit" value="LogIn"/>
                                    <input type="reset" value="Clear"/>
                                </td>
                                
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
