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
        <title>JSP Page</title>
        <link href="<c:url value="/resources/css/form-design.css" />" rel="stylesheet">
    </head>
    <body>
        <jsp:include page="header.jsp" /> 
        <div style="text-align: center">
            <h3>${message} ${userId}</h3>
        </div>
        <form action="add.htm" method="post" id="js-form">
            <h3>Create Employee Account</h3>
            <div class="name">
                <input type="text" name="firstName" placeholder="firstName" required="" />
                <input type="text" name="lastName" placeholder="LastName" required=""/>
                <!--<input type="text" name="userName" placeholder="Login Name" required=""/>-->
                <input type="mail" name="email" placeholder="email@dot.com" required=""/>
                <input type="password" name="password" placeholder="Password" required="" ma/>
            </div>
            <div class="dropdown">
                <label for="select-choice">Select Department:</label>
                <select name="deptId" id="deptId" required="">
                    <option value="none"/>Select</option>

                    <c:forEach items="${department}" var="dept">
                        <option value="${dept.departmentId}">${dept.name}</option>
                    </c:forEach>
                </select>
                <label for="select-choice">Select Role</label>
                <select name="roleId" id="roleId" required="">
                    <option value="none"/>Select</option>

                    <c:forEach items="${roles}" var="role">
                        <option value="${role.roleId}">${role.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="message">
                <label for="textarea">Address:</label>
                <textarea cols="40" rows="8" name="address" id="address"></textarea>
            </div>
            <div class="submit">
                <input type="submit" value="Submit"/>
            </div>
        </form>  
    </body>
</html>
