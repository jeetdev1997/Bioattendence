<%-- 
    Document   : uploadView
    Created on : 24 Apr, 2020, 8:11:52 PM
    Author     : ashish.yetre
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Upload View</title>
    </head>
    <body>
    <center>
        <form action="uploadAttendance.htm" method="POST" enctype="multipart/form-data">
            <input type="file" name="file" id="file"/>
            <input type="submit" value="uploadAttendance"/>
        </form> 
    </center>
</body>
</html>
