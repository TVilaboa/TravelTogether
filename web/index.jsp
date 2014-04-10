<%@ page import="model.Constants" %>
<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
index
<form id="<%=Constants.HOME_FORM_ID%>"
      action="<%=response.encodeURL(request.getContextPath() + "/Secure/welcome.jsp")%>" method="POST">
    <input type="text" name="<%=Constants.HOME_POST_FIELD%>">
    <input type="submit">
</form>

</body>
</html>