<%-- 
    Document   : viewlist
    Created on : Apr 20, 2020, 2:44:22 PM
    Author     : My Computer
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form>
            <table border>
                <tr>
                    <th>UserId</th>
                    <th>UserName</th>
                    <th>Department</th>
                    <th>Role</th>
                    <th>Address</th>
                    <th>Email</th>
                    <th>IsActive</th>
                    <th>CreatedDate</th>
                    <th>UpdatedDate</th>
                </tr>
                
                <c:forEach items="${list}" var="userList">
                    <tr>
                    <td>${userList.userId}</td>
                    <td>${userList.userName}</td>
                    <td>${userList.department}</td>
                    <td>${userList.role}</td>
                    <td>${userList.address}</td>
                    <td>${userList.email}</td>
                    <td>${userList.isActive}</td>
                    <td>${userList.createdDate}</td>
                    <td>${userList.updatedDate}</td>
                  </tr>
                </c:forEach>
                    
                
            </table>
        </form>
    </body>
</html>
