<%-- 
    Document   : login
    Created on : Apr 20, 2020, 2:37:03 AM
    Author     : My Computer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div class="login">
            <h1>Login</h1>
            <form method="post" action="form.htm">
                <input type="text" name="userName" placeholder="Username" required="required" />
                <input type="password" name="password" placeholder="Password" required="required" />
                <button type="submit" class="btn btn-primary btn-block btn-large">LogIn</button>
            </form>
        </div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
