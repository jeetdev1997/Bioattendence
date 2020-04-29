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
            <div style="text-align: center; padding-top: 15px">
                <c:if test="${not empty userAttendance.attendanceList}">
                    <form method="post" action="updateAttendance.htm">
                        <input type="hidden" name="userId" value="${userAttendance.user.userId}"/>
                        <span>Select Month</span>
                        <select name="month" id="month" required="">
                            <option value="none"/>Select</option>
                            <c:forEach items="${userAttendance.monthList}" var="month">
                                <c:choose>
                                    <c:when test="${userAttendance.currentMonth eq month}">
                                        <option value="${month}" selected="selected">${month}</option>      
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${month}">${month}</option>         
                                    </c:otherwise>
                                </c:choose>

                            </c:forEach>
                        </select>    
                        <input type="submit" value="UpdateMonth"/>
                    </form>
                </c:if>
            </div>
            <div>
                <table>
                    <thead>
                        <tr>
                            <th scope="col">Employee Name</th>
                            <th scope="col">Month</th>
                            <th scope="col">Present Days</th>
                            <th scope="col">Leave Days</th>
                            <th scope="col">Email Notice</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td scope="row">${userAttendance.user.name}</td>
                            <td align="center">${userAttendance.currentMonth}</td>
                            <td align="center">${userAttendance.presentDays}</td>
                            <td align="center">${userAttendance.absentDays}</td>
                            <td align="center"><a href="email.htm?userId=${userAttendance.user.userId}"> Send Email </a></td>
                        </tr>
                    </tbody>
                </table>
                <div style="text-align: center">
                    <span>${emailMessage}</span>
                </div> 
            </div>
            <table>
                <thead>
                <caption>Attendance Details</caption>
                <tr>
                    <th scope="col">Date</th>
                    <th scope="col">Day</th>
                    <th scope="col">Status</th>
                    <th scope="col">In Time</th>
                    <th scope="col">Out Time</th>
                </tr>
                </thead>
                <tbody>
                    <c:forEach items="${userAttendance.attendanceList}" var="attendance">
                        <tr>
                            <td scope="row">${attendance.attendedDate}</td>
                            <td align="center">${attendance.day}</td>
                            <td align="center">${attendance.status}</td>
                            <c:choose>
                                <c:when test="${attendance.day ne 'SUNDAY'}">
                                    <td align="center">${attendance.formattedInTime}</td>
                                    <td align="center">${attendance.formattedOutTime}</td>        
                                </c:when>
                                <c:otherwise>
                                    <td align="center">HOLIDAY</td>
                                    <td align="center">HOLIDAY</td>  
                                </c:otherwise>
                            </c:choose>

                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <%--<jsp:include page="footer.jsp" />--%> 
    </body>
</html>
