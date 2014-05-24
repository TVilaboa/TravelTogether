<%--
  Created by IntelliJ IDEA.
  User: Toto
  Date: 13/05/2014
  Time: 04:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<html xmlns="http://www.w3.org/1999/html">
<head>


    <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
    <script src="http://code.jquery.com/jquery-1.9.0.js"></script>
    <script src="http://code.jquery.com/jquery-migrate-1.2.1.js"></script>
    <link rel="stylesheet" type="text/css" href="Jquery-DatePicker/jquery.datepick.css">
    <script type="text/javascript" src="Jquery-DatePicker/jquery.plugin.js"></script>
    <script type="text/javascript" src="Jquery-DatePicker/jquery.datepick.js"></script>
    <script type="text/javascript" src="http://code.jquery.com/ui/1.10.0/jquery-ui.min.js"></script>


    <title>Itinerary</title>

    <meta name="description"
          content="Full view calendar component for twitter bootstrap with year, month, week, day views.">
    <meta name="keywords" content="jQuery,Bootstrap,Calendar,HTML,CSS,JavaScript,responsive,month,week,year,day">
    <meta name="author" content="Serhioromano">
    <meta charset="UTF-8">

    <link rel="stylesheet" href="components/bootstrap2/css/bootstrap.css">
    <link rel="stylesheet" href="components/bootstrap2/css/bootstrap-responsive.css">
    <link rel="stylesheet" href="css/calendar.css">

    <style type="text/css">
        .btn-twitter {
            padding-left: 30px;
            background: rgba(0, 0, 0, 0) url(https://platform.twitter.com/widgets/images/btn.27237bab4db188ca749164efd38861b0.png) -20px 6px no-repeat;
            background-position: -20px 11px !important;
        }

        .btn-twitter:hover {
            background-position: -20px -18px !important;
        }
    </style>
</head>
<body>
<div class="container">
<div class="hero-unit">
    <h1>Itinerary</h1>

    <p>Bootstrap based itinerary.</p>

    <a class="btn btn-inverse" href="/Secure/welcome.jsp">Back</a>
    <a class="btn" href="Profile">User Profile</a>

    <script>!function (d, s, id) {
        var js, fjs = d.getElementsByTagName(s)[0], p = /^http:/.test(d.location) ? 'http' : 'https';
        if (!d.getElementById(id)) {
            js = d.createElement(s);
            js.id = id;
            js.src = p + '://platform.twitter.com/widgets.js';
            fjs.parentNode.insertBefore(js, fjs);
        }
    }(document, 'script', 'twitter-wjs');
    </script>
</div>

<div class="page-header">

    <div class="pull-right form-inline">
        <div class="btn-group">
            <button class="btn btn-primary" data-calendar-nav="prev"><< Prev</button>
            <button class="btn" data-calendar-nav="today">Today</button>
            <button class="btn btn-primary" data-calendar-nav="next">Next >></button>
        </div>
        <div class="btn-group">
            <button class="btn btn-warning" data-calendar-view="year">Year</button>
            <button class="btn btn-warning active" data-calendar-view="month">Month</button>
            <button class="btn btn-warning" data-calendar-view="week">Week</button>
            <button class="btn btn-warning" data-calendar-view="day">Day</button>
        </div>
    </div>

    <h3></h3>

</div>

<div class="row">
    <div class="span9">
        <div id="calendar"></div>

    </div>

    <div class="span3">
        <div class="row-fluid">
            <select id="first_day" class="span12">
                <option value="" selected="selected">First day of week language-dependant</option>
                <option value="2">First day of week is Sunday</option>
                <option value="1">First day of week is Monday</option>
            </select>
            <select id="language" class="span12">
                <option value="">Select Language (default: en-US)</option>
                <option value="nl-NL">Dutch</option>
                <option value="fr-FR">French</option>
                <option value="de-DE">German</option>
                <option value="el-GR">Greek</option>
                <option value="it-IT">Italian</option>
                <option value="pl-PL">Polish</option>
                <option value="pt-BR">Portuguese (Brazil)</option>
                <option value="es-MX">Spanish (Mexico)</option>
                <option value="es-ES">Spanish (Spain)</option>
                <option value="ru-RU">Russian</option>
                <option value="sv-SE">Swedish</option>
                <option value="zh-CN">简体中文</option>
            </select>
            <label class="checkbox">
                <input type="checkbox" value="#events-modal" id="events-in-modal"> Open events in modal window
            </label>
        </div>

        <h4>Events</h4>
        <small>Events</small>
        <ul id="eventlist" class="nav nav-list"></ul>

        <!-- Button to trigger modal -->
        <a href="#myModal" role="button" class="btn" data-toggle="modal" id="addEventModal">Add event</a>


        <!-- jsp to print matching users-->

        <p>Matching users</p>
        <table>

            <%
                out.print(request.getAttribute("matchingUsers"));
            %>
        </table>


        <!-- Modal -->
        <div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
             aria-hidden="true">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h3 id="myModalLabel">Add event</h3>
            </div>
            <div class="modal-body">
                <form id="addEventForm" action="addEvent" method="POST" class="form-horizontal"
                      role="form">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Title</label>

                        <div class="col-sm-10">
                            <input class="form-control" id="Title" name="Title" type="text"
                                   value="Your title,hotel,etc">
                        </div>
                        <label class="col-sm-2 control-label">URL</label>

                        <div class="col-sm-10">
                            <input class="form-control" id="URL" name="URL" type="text"
                                   value="http://www.example.com/">
                        </div>
                        <label class="col-sm-2 control-label">From:</label>

                        <div class="col-sm-10">
                            <input class="form-control" id="From" name="From" type="text"
                                   value="Where are you now">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">
                            To:
                        </label>

                        <div class="col-sm-10">
                            <input class="form-control" id="To" name="To" type="text"
                                   placeholder="Where are you traveling" disabled>
                        </div>
                    </div>
                    <div class="clearfix">
                        <label>Date range</label>


                        <div class="input" id="dates">
                            <div class="inline-inputs">
                                <input class="small" type="text" value="05/21/2014" id="StartDay" name="StartDay"/>
                                <input class="mini" type="text" value="08:00" id="StartHour" name="StartHour"/>
                                to
                                <input class="small" type="text" value="05/21/2014" id="EndDay" name="EndDay"/>
                                <input class="mini" type="text" value="23:52" id="EndHour" name="EndHour"/>
                                <span class="help-block">UTC</span>
                            </div>

                        </div>
                    </div>

                    <div class="radio">
                        <label class="checkbox-inLine">
                            <input onclick="document.getElementById('To').disabled = true;" type="radio"
                                   name="optionsRadios" id="Staying"
                                   value="Staying" checked> Staying
                        </label>
                        <label class="checkbox-inLine">
                            <input onclick="document.getElementById('To').disabled = false;" type="radio"
                                   name="optionsRadios" id="Traveling"
                                   value="Traveling">
                            Traveling
                        </label>
                    </div>
                    <div class="modal-footer">

                        <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
                        <button type="submit" id="submit" class="btn btn-primary">Save Changes</button>
                    </div>
                </form>
            </div>


        </div>



        <%--Inbox Modal--%>

        <div id="myMessageModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
             aria-hidden="true">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h3 id="myMesageModalLabel">Send Message</h3>
            </div>
            <div class="modal-body">
                <form id="sendMessageForm" action="sendMessage" method="POST" class="form-horizontal"
                      role="form">
                    <div class="form-group">


                          <div>

                              <textarea class="form-control" id="Message" name="Message"
                                      >Hi, we share some events!!....</textarea>
                        </div>
                        </div>

                    <div class="modal-footer">

                        <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
                        <button type="submit" id="send" class="btn btn-primary">Send</button>
                    </div>
                </form>
            </div>


        </div>

    </div>
</div>

<!--<div class="clearfix"></div>-->
<!--<br><br>-->
<!--<a href="https://github.com/Serhioromano/bootstrap-calendar/issues" class="btn btn-block btn-info">-->
<!--<center>-->
<!--<span class="lead">-->
<!--Submit an issue, ask questions or give your ideas here!<br>-->
<!--</span>-->
<!--<small>Please do not post your "How to ..." questions in comments. use GitHub issue tracker.</small>-->
<!--</center>-->
<!--</a>-->
<!--<br><br>-->

<!--<div id="disqus_thread"></div>-->
<!--<noscript>Please enable JavaScript to view the <a href="http://disqus.com/?ref_noscript">comments powered by Disqus.</a></noscript>-->

<div class="modal hide fade" id="events-modal">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h3>Event</h3>
    </div>
    <div class="modal-body" style="height: 400px">
    </div>
    <div class="modal-footer">
        <a href="#" data-dismiss="modal" class="btn">Close</a>
    </div>
</div>


<script type="text/javascript" src="components/underscore/underscore-min.js"></script>
<script type="text/javascript" src="components/bootstrap2/js/bootstrap.min.js"></script>
<script type="text/javascript" src="components/jstimezonedetect/jstz.min.js"></script>
<script type="text/javascript" src="js/language/nl-NL.js"></script>
<script type="text/javascript" src="js/language/fr-FR.js"></script>
<script type="text/javascript" src="js/language/de-DE.js"></script>
<script type="text/javascript" src="js/language/el-GR.js"></script>
<script type="text/javascript" src="js/language/it-IT.js"></script>
<script type="text/javascript" src="js/language/pl-PL.js"></script>
<script type="text/javascript" src="js/language/pt-BR.js"></script>
<script type="text/javascript" src="js/language/es-MX.js"></script>
<script type="text/javascript" src="js/language/es-ES.js"></script>
<script type="text/javascript" src="js/language/ru-RU.js"></script>
<script type="text/javascript" src="js/language/sv-SE.js"></script>
<script type="text/javascript" src="js/language/zh-CN.js"></script>
<script type="text/javascript" src="js/language/cs-CZ.js"></script>
<script type="text/javascript" src="js/calendar.js"></script>
<script type="text/javascript" src="js/app.js"></script>
<script type="text/javascript">
    $(function () {
        $("#StartDay").datepicker();
        $("#EndDay").datepicker();

    });

</script>


<!--<script type="text/javascript">-->
<!--var disqus_shortname = 'bootstrapcalendar'; // required: replace example with your forum shortname-->
<!--(function() {-->
<!--var dsq = document.createElement('script'); dsq.type = 'text/javascript'; dsq.async = true;-->
<!--dsq.src = '//' + disqus_shortname + '.disqus.com/embed.js';-->
<!--(document.getElementsByTagName('head')[0] || document.getElementsByTagName('body')[0]).appendChild(dsq);-->
<!--})();-->
<!--</script>-->
<script type="text/javascript">
    $(document).ready(function () {
        $("input#submit").click(function () {
            //Serialize the form and post it to the server
            $.post("/Secure/calendar/addEvent", $('#sendMessageForm').serialize(), function () {

                // When this executes, we know the form was submitted

                // To give some time for the animation,
                // let's add a delay of 200 ms before the redirect
                var delay = 200;
                setTimeout(function () {
                    window.location.href = '/Secure/calendar/calendar.html';
                }, delay);

                // Hide the modal
                $("#my-modal").modal('hide');

            });

            // Stop the normal form submission
            return false;
        });

    });
</script>

<script type="text/javascript">
    $(document).ready(function () {
        $("input#send").click(function () {
            //Serialize the form and post it to the server
            $.post("/Secure/calendar/SendMessage", $('#addEventForm').serialize(), function () {

                // When this executes, we know the form was submitted

                // To give some time for the animation,
                // let's add a delay of 200 ms before the redirect
                var delay = 200;
                setTimeout(function () {
                    window.location.href = '/Secure/calendar/calendar.html';
                }, delay);

                // Hide the modal
                $("#my-message-modal").modal('hide');

            });

            // Stop the normal form submission
            return false;
        });

    });
</script>


<script>
    function getParameterByName(name) {
        name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
        var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
                results = regex.exec(location.search);
        return results == null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
    }
    $(document).ready(function () {
        var userCalendar = getParameterByName("userCalendar");
        var userSession = getParameterByName("userSession");
        if (userCalendar.localeCompare(userSession) == 0) {

        } else {
            document.getElementById("addEventModal").disabled = true;
        }

    })
</script>

</div>
</body>
</html>
