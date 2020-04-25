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
                    <caption>${userAttendance.user.name} Attendance of Current Month</caption>
                    <thead>
                        <tr>
                            <th scope="col">Date</th>
                            <th scope="col">Day</th>
                            <th scope="col">In Time</th>
                            <th scope="col">Out Time</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${userAttendance.attendanceList}" var="attendance">
                            <tr>
                                <td scope="row">${attendance.attendedDate}</td>
                                <td align="center">${attendance.day}</td>
                                <td align="center">${attendance.formattedInTime}</td>
                                <td align="center">${attendance.formattedOutTime}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <jsp:include page="footer.jsp" /> 
    </body>
</html>
