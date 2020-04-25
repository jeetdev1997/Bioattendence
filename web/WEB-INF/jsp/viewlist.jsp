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
        <link rel="stylesheet" href="css/normalize.css">
        <link href='https://fonts.googleapis.com/css?family=Nunito:400,300' rel='stylesheet' type='text/css'>
        <link href="<c:url value="/resources/css/table.css" />" rel="stylesheet">
        <link href="<c:url value="/resources/css/anchor-button.css" />" rel="stylesheet">
        <style>

        </style>
    </head>
    <body>
        <jsp:include page="header.jsp" /> 
        <div>

            <div>
                <table>
                    <caption>Employee Information</caption>
                    <thead>
                        <tr>
                            <th scope="col">Name</th>
                            <th scope="col">Department</th>
                            <th scope="col">Role</th>
                            <th scope="col">Address</th>
                            <th scope="col">Email</th>
                            <th scope="col">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${list}" var="userList">
                            <tr>
                                <td scope="row">${userList.name}</td>
                                <td align="center">${userList.department}</td>
                                <td align="center">${userList.role}</td>
                                <td align="center">${userList.address}</td>
                                <td align="center">${userList.email}</td>
                                <td align="center">

                                    <span class="menu effect-02">
                                        <ul>
                                            <li><a href="delete.htm?empId=${userList.userId}">Delete</a></li>
                                            <li><a href="viewAttendance.htm?empId=${userList.userId}">Show Attendance</a></li>
                                        </ul>
                                    </span>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <jsp:include page="footer.jsp" /> 
    </body>
</html>
