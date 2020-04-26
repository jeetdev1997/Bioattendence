<%-- 
    Document   : adduser
    Created on : Apr 20, 2020, 2:27:06 PM
    Author     : My Computer
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page import="java.security.cert.PKIXRevocationChecker.Option"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="model.AttendenceSystemDb"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
       <link href="<c:url value="/resources/css/adduser.css" />" rel="stylesheet">
        <jsp:include page="header.jsp" /> 
        <%--<jsp:include page="footer.jsp" />--%> 
         
        <js>
    </head>
    <body>
       <center> <div class="div" >
            <form action="http://localhost:8084/BiomatricsAttendenceSystem/admin/add.htm" method="post">
                <table class="table"  cellspacing="0" cellpadding="0">
                    <tr>
                        <td ><label for="fname">First Name</label></td>
                        <td><input type="text"  name="firstName" placeholder="Your name.." required="" autocomplete="off"/></td></tr>

                    <tr> <td><label for="lname">Last Name</label></td>
                        <td><input type="text"  name="lastName" placeholder="Your last name.." required="" autocomplete="off"></td></tr>
                    <tr>
                        <td><label for="deaprtment">Department</label></td>
                        <td><select  name="deptId">
                                <option value="null">Select</option>
                                <c:forEach items="${department}" var="dept">
                                    <option value="${dept.departmentId}">${dept.name}</option>
                                </c:forEach>
                            </select></td></tr>
                    <tr><td><label for="country">Role</label></td>
                        <td><select id="roleId" name="roleId">
                                <option value="null">Select</option>
                                <c:forEach items="${roles}" var="role">
                                    <option value="${role.roleId}">${role.name}</option>
                                </c:forEach>
                            </select></td></tr>
                    <tr><td><label for="Address">Address</label></td>
                        <td><input type="text" id="address" name="address" placeholder="Address.." required="" autocomplete="off"></td></tr>
                    <tr> <td><label for="Email">Email</label></td>
                        <td><input type="email" class="email" id="email" name="email" placeholder="email.." required="" autocomplete="off"></td></tr>
                    <tr><td><label for="is">isActive</label></td>
                    <td><input type="text" name="isActive" value="0" readonly=""/></td>
                    </tr>
                    <tr><td><label for="createddate">CreatedDate</label></td>
                        <td><input type="Date" id="createdate" name="createdDate" required="" autocomplete="off"/></td><tr>
                    <tr><td><label for="updateddate">UpdatedDate</label></td>
                        <td><input type="Date" id="updatedate" name="updatedDate" required="" autocomplete="off"/></td></tr>
                    <tr> <td><label for="username">UserName</label></td>
                        <td><input type="text" id="username" name="userName" required="" autocomplete="off"/><td><tr>
                    <tr><td><label for="password">Password</label></td>
                        <td><input type="password" id="password" name="password" required="" autocomplete="off"/></td></tr>
                    <tr>
                        <td colspan="2" align="center"/>
                    <input type="submit" value="AddEmployee"/></td>
                    </tr>
                </table>
            </form>
        </div>
           </center>
    <p><center><jsp:include page="massage.jsp" /></center></p>
    </body>
</html>
