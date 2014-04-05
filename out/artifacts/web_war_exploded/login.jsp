<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 26/03/14
  Time: 11:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<form action="login" method="post">
    <table>
        <tr>
            <td>User</td>
            <td><input name="user"/></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input name="password"/></td>
        </tr>
    </table>
    <input type="submit"/>
</form>
You dont have an user? Register!!!
<form action="register" method="post">
    <table>
        <tr>
            <td>User</td>
            <td><input name="User"/></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input name="Password"/></td>
        </tr>
        <tr>
            <td>Email</td>
            <td><input name="Email"/></td>
        </tr>
    </table>
    <input type="submit"/>
</form>
</body>
</html>