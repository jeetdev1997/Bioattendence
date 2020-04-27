<%-- 
    Document   : user
    Created on : Apr 20, 2020, 11:07:28 AM
    Author     : My Computer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dashboard</title>
        <link href="<c:url value="/resources/css/anchor-button.css" />" rel="stylesheet">
    </head>
    <body>
        <jsp:include page="header.jsp" />

        <div class="buttons">
            <button class="raise">Raise</button>
        </div>
        
        <%--<jsp:include page="footer.jsp" />--%>

    </body>

</html>
