<%@ page import="model.Constants" %>
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
<form id="<%=Constants.LOGIN_FORM_ID%>" action="<%=response.encodeURL(Constants.LOGIN_FORM_ACTION)%>" method="POST">
    <table>
        <tr>
            <td>User</td>
            <td><input type="text"
                       name="<%=Constants.LOGIN_USERNAME_FIELD%>"
                       value="<%=Constants.VALID_USERNAME%>"
                    ></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password"
                       name="<%=Constants.LOGIN_PASSWORD_FIELD%>"
                       value="<%=Constants.VALID_PASSWORD%>"
                    ></td>
        </tr>
    </table>
    <input type="Submit" value="<%=Constants.LOG_IN%>"/>
</form>
You dont have an user? Register!!!
<form id="<%=Constants.REGISTER_FORM_ID%>" action="register" method="POST">
    <table>
        <tr>
            <td>User</td>
            <td><input type="text"
                       name="<%=Constants.LOGIN_USERNAME_FIELD%>"
                       value="<%=Constants.VALID_USERNAME%>"
                    ></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password"
                       name="<%=Constants.LOGIN_PASSWORD_FIELD%>"
                       value="<%=Constants.VALID_PASSWORD%>"
                    ></td>
        </tr>
        <tr>
            <td>Email</td>
            <td><input name="Email"/></td>
        </tr>
    </table>
    <input type="submit" value="<%=Constants.REGISTER%>"/>
</form>
</body>
</html>