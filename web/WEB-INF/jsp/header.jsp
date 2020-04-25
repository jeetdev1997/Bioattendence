<%-- 
    Document   : header
    Created on : Apr 23, 2020, 8:35:19 PM
    Author     : My Computer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
            *, *:before, *:after {
                box-sizing: border-box;
            }

            body {
                margin: 10px;
                font-family: 'Open Sans', 'sans-serif';
                background-color: #fff;
                color: #444;
            }

            .wrapper > * {
                background-color: #444;
                color: #fff;
                border-radius: 5px;
                padding: 15px;
                font-size: 150%;
                /* needed for the floated layout*/
                margin-bottom: 20px;
            }
        </style>
    </head>
    <body>
        <div class="wrapper">
            <center> 
                <header class="header">Biomatric Attendance System</header>
            </center>
        </div>
    </body>
</html>
