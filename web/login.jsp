<%@ page import="security.Constants" %>
<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 26/03/14
  Time: 11:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/html">
<link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
<script src="http://code.jquery.com/jquery-1.9.0.js"></script>

<script type="text/javascript" src="styles/components/underscore/underscore-min.js"></script>
<script type="text/javascript" src="styles/components/bootstrap2/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="styles/components/bootstrap2/css/bootstrap.css">
<link rel="stylesheet" href="styles/components/bootstrap2/css/bootstrap-responsive.css">
<link rel="stylesheet" href="styles/css/calendar.css">


<script type="text/javascript" src="http://code.jquery.com/ui/1.10.0/jquery-ui.min.js"></script>


<head>
    <title></title>
    <%--<style>--%>

    <%--*{--%>
    <%--margin:0;--%>
    <%--padding:0;--%>
    <%--}--%>

    <%--html{--%>
    <%--/* This image will be displayed fullscreen */--%>
    <%--background:url('img/VIAJAR.jpg') no-repeat center center;--%>

    <%--/* Ensure the html element always takes up the full height of the browser window */--%>
    <%--min-height:100%;--%>

    <%--/* The Magic */--%>
    <%--background-size:cover;--%>
    <%--}--%>

    <%--body{--%>
    <%--/* Workaround for some mobile browsers */--%>
    <%--min-height:100%;--%>
    <%--}--%>
    <%--</style>--%>

</head>
<body>
<div class="span3">
    <a href="#myAccessModal" role="button" class="btn" data-toggle="modal" id="accessModal">Access</a>

    <div id="myAccessModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            <h3 id="myModalLabel">Login / Register</h3>
        </div>
        <div class="modal-body">
            <form id="<%=Constants.LOGIN_FORM_ID%>" action="<%=response.encodeURL(Constants.LOGIN_FORM_ACTION)%>"
                  method="POST">
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
                                   name="<%=Constants.REGISTER_USERNAME_FIELD%>"
                                   value="<%=Constants.VALID_USERNAME%>"
                                ></td>
                    </tr>
                    <tr>
                        <td>Password</td>
                        <td><input type="password"
                                   name="<%=Constants.REGISTER_PASSWORD_FIELD%>"
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
        </div>


    </div>
</div>
</body>


</html>
