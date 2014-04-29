<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <title>SecurityFilter Example Application: servlets.Login Error Page</title>
</head>
<body>
<h1>SecurityFilter Example Application: servlets.Login Error Page</h1>
<%@include file="/menu.jsp" %>
Bad username/password combination, please <a href="<%=response.encodeURL("login.jsp")%>">try again</a>.
</body>
</html>