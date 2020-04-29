<%-- 
    Document   : adduser
    Created on : Apr 20, 2020, 2:27:06 PM
    Author     : My Computer
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Department</title>
        <link href="<c:url value="/resources/css/form-design.css" />" rel="stylesheet">
    </head>
    <body>
        <jsp:include page="header.jsp" /> 
        <div style="text-align: center">
            <h3>${message}</h3>
        </div>
        <form action="adddepartment.htm" method="post" id="js-form">
            <h3>Create Department</h3>
            <div class="name">
                <!--<input type="text" name="firstName" placeholder="firstName" />-->
                <!--<input type="text" name="lastName" placeholder="LastName" />-->
                <!--<input type="text" name="userName" placeholder="Login Name" />-->
                <input type="text" class="deptInput" name="name" placeholder="department" required="" />
                <!--<input type="mail" name="email" placeholder="email@dot.com"/>-->
            </div>
            <div class="submit">
                <input type="submit" value="Submit"/>
            </div>
        </form>  
    </body>
</html>
