<jsp:useBean id="userSession" scope="request" class="java.lang.String"/>
<%--
  Created by IntelliJ IDEA.
  User: Toto
  Date: 17/06/2014
  Time: 07:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/html">

<link rel="stylesheet" href="/Secure/calendar/components/bootstrap3/css/bootstrap.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/css/calendar.css">
<head>
    <title>Post Facebook</title>
    <style type="text/css">
        div {
            padding: 10px;
        }
    </style>
    <meta charset="UTF-8">
</head>
<body>
<div class="container">
<div class="jumbotron">
    <h1>Post To Facebook</h1>

    <p><%out.println("Logged as " + userSession + "\n");%></p>


    <a class="btn btn-default" onclick="window.history.back()">Back</a>

    <a class="btn btn-default" href="/Secure/calendar/calendar">Itinerary</a>
    <a class="btn btn-default" href="../welcome">Inbox</a>


</div>
<script type="text/javascript" src="face.js">
</script>

<!--
  Login Button

  https://developers.facebook.com/docs/reference/plugins/login

  This example needs the 'publish_actions' permission in
  order to publish an action.  The scope parameter below
  is what prompts the user for that permission.
-->

<div
        class="fb-login-button"
        data-show-faces="true"
        data-width="200"
        data-max-rows="1"
        data-scope="publish_actions">
</div>

<div>
    This creates a story on Facebook using the event.Should only be visible to you.
</div>

<div>
    <input
            type="button"
            value="Post"
            onclick="create();">
</div>

<div id="result"></div>
</div>
</body>
</html>
