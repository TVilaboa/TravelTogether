<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 26/03/14
  Time: 09:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
Welcome
<form id="Calendar"
      action="${pageContext.request.contextPath}/Secure/calendar/calendar" method="POST">

    <input type="submit">

    //TODO add input to send user via get and see others itineraries
</form>
</body>
</html>