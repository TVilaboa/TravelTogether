<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 26/03/14
  Time: 09:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<html xmlns="http://www.w3.org/1999/html">
<link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
<script src="http://code.jquery.com/jquery-1.9.0.js"></script>

<script type="text/javascript"
        src="${pageContext.request.contextPath}/styles/components/underscore/underscore-min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/styles/components/bootstrap2/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/components/bootstrap2/css/bootstrap.css">
<link rel="stylesheet"
      href="${pageContext.request.contextPath}/styles/components/bootstrap2/css/bootstrap-responsive.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/css/calendar.css">


<script type="text/javascript" src="http://code.jquery.com/ui/1.10.0/jquery-ui.min.js"></script>

<head>
    <title></title>
</head>
<body>
Welcome
<form id="Calendar"
      action="${pageContext.request.contextPath}/Secure/calendar/calendar" method="POST">
    <input type="text" id="userCalendar" name="userCalendar"> blank if you want to access own calendar
    <input type="submit">


</form>
<table>
    <%
        out.print(request.getAttribute("userMessages"));

    %>
</table>
</body>
</html>