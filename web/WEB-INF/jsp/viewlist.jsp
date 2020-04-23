<%-- 
    Document   : viewlist
    Created on : Apr 20, 2020, 2:44:22 PM
    Author     : My Computer
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
            .hide{visibility: hidden}
            .selected
            {
                background-color: #cccccc;
            }
            tr{cursor: pointer; transition: all .25s ease-in-out}
        </style>
    </head>
    <body>
    <center>
        <table id="table" border cellpadding="0" cellspacing="0">
            <tr>
                <th>UserId</th>
                <th>UserName</th>
                <th>Department</th>
                <th>Role</th>
                <th>Address</th>
                <th>Email</th>
                <th>IsActive</th>
                <th>CreatedDate</th>
                <th>UpdatedDate</th>
            </tr>

            <c:forEach items="${list}" var="userList">
                <tr onclick="selcetRow()">
                    <td align="center">${userList.userId}</td>
                    <td align="center">${userList.userName}</td>
                    <td align="center">${userList.department}</td>
                    <td align="center">${userList.role}</td>
                    <td align="center">${userList.address}</td>
                    <td align="center">${userList.email}</td>
                    <td align="center">${userList.isActive}</td>
                    <td align="center">${userList.createdDate}</td>
                    <td align="center">${userList.updatedDate}</td>
                </tr>
            </c:forEach>
        </table>
    </center>
    <p></p>
    <form action="http://localhost:8084/BiomatricsAttendenceSystem/admin/delete.htm" method="post">
        <div>
            <center>
                <input type="text" id="userid" name="userId" class="hide" />
                <input type="text" id="isactive" name="isActive" class="hide"/>
                <input type="submit" value="DeleteEmployee" align="left"/> &nbsp;
                <input type="button" value="ViewAttendence" align="left"/>
            </center>
        </div>
    </form>

    <script>
        function selectedRow()
        {
            var index, table = document.getElementById("table");
            for (var i = 1; i < table.rows.length; i++) {

                table.rows[i].onclick = function ()
                {
                    if (typeof index !== "undefined") {
                        table.rows[index].classList.toggle("selected");
                        document.getElementById("userid").value = this.cells[0].innerHTML;
                        document.getElementById("isactive").value = this.cells[6].innerHTML;

                    }
                    index = this.rowIndex;
                    this.classList.toggle("selected");
                    console.log(index);
                };
            }
        }
        selectedRow();

    </script>
</body>
</html>
