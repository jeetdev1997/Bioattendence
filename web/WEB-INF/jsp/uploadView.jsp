<%-- 
    Document   : uploadView
    Created on : 24 Apr, 2020, 8:11:52 PM
    Author     : ashish.yetre
--%>
<%@taglib uri="http://www.springframework.org/tags" prefix="html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Upload view</title>
    </head>
    <body>
        <jsp:include page="header.jsp" />
        <div>
            <center>
                <form action="uploadAttendance.htm" method="POST" enctype="multipart/form-data">
                    <input type="file" name="file" id="file"/>
                    <input type="submit" value="uploadAttendance"/>
                </form> 
            </center>
        </div>
        <jsp:include page="footer.jsp" />
    </body>
</html>

</html>
