<%-- 
    Document   : login
    Created on : Apr 20, 2020, 2:37:03 AM
    Author     : My Computer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <link href="<c:url value="/resources/css/login.css" />" rel="stylesheet">
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div class="login">
            <form method="post" action="form.htm">
                <div class="login">
                    <h1>Login</h1>
                    <!--<form method="post" action="form.htm" >-->
                    <input type="text" name="userName" placeholder="UserName" required="required" />
                    <input type="password" name="password" placeholder="Password" required="required" />
                    <button type="submit" class="btn btn-primary btn-block btn-large">Let me in.</button>
                    <!--</form>-->
                </div>
            </form>
            <<br/><br/><br/><br/><br/><br/><br/><br/><br/>
             <h5 style="color: whitesmoke"> ${message}</h5>
        </div>
    </body>
</html>
