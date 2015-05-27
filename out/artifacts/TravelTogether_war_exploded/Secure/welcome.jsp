
<jsp:useBean id="userSession" scope="request" class="java.lang.String"/>
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



<link rel="stylesheet" href="/Secure/calendar/components/bootstrap3/css/bootstrap.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/css/calendar.css">


<script type="text/javascript" src="${pageContext.request.contextPath}/styles/js/jquery-ui.js"></script>

<head>
    <title></title>
</head>
<body>
<div class="container">
    <div class="jumbotron">
        <h1>Inbox</h1>

        <p><%out.println("Logged as " + userSession + "\n");%></p>


        <a class="btn btn-default" onclick="window.history.back()">Back</a>

        <a class="btn btn-default" href="/Secure/calendar/calendar">Itinerary</a>

    </div>
    <form id="Calendar"
          action="${pageContext.request.contextPath}/Secure/calendar/calendar" method="POST">
        <input type="text" id="userCalendar" name="userCalendar"> Blank if you want to access own calendar
        <input type="submit" value="See Calendar">


    </form>

        <%
            out.print(request.getAttribute("userMessages"));

        %>

</div>


</body>
<script type="text/javascript">


    jQuery("#userCalendar").autocomplete({
        source: function (request, response) {
            jQuery.get("users", {
                query: request.term
            }, function (data) {
                var parsed = JSON.parse(data);
                var newArray = new Array(parsed.length);
                var i = 0;

                parsed.forEach(function (entry) {
                    var newObject = {
                        label: entry
                    };
                    newArray[i] = newObject;
                    i++;
                });

                response(newArray);

            });
        }
    });
</script>
</html>