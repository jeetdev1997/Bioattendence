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
        <link href="<c:url value="/resources/css/upload-file.css" />" rel="stylesheet">
    </head>
    <body>
        <jsp:include page="header.jsp" />
        <div>
            <center>
                <div class="container">
                    <form action="uploadAttendance.htm" method="POST" enctype="multipart/form-data">
                        <div class="file-upload-wrapper" data-text="Select your file!">
                            <input name="file" type="file" class="file-upload-field" value="">
                        </div>
                        <input type="submit" value="Submit"/>
                    </form>
                    <div>
                        <span>
                            ${message}
                        </span>
                    </div>
                </div>
            </center>
        </div>
        <jsp:include page="footer.jsp" />
    </body>
</html>

</html>
