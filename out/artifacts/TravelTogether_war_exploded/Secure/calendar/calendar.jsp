<jsp:useBean id="userSession" scope="request" class="java.lang.String"/>
<jsp:useBean id="userCalendar" scope="request" class="java.lang.String"/>

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
    <link rel="stylesheet" type="text/css" href="/Secure/calendar/Jquery-DatePicker/jquery.datepick.css">
    <script type="text/javascript" src="/Secure/calendar/Jquery-DatePicker/jquery.plugin.js"></script>
    <script type="text/javascript" src="/Secure/calendar/Jquery-DatePicker/jquery.datepick.js"></script>
    <script type="text/javascript" src="http://code.jquery.com/ui/1.10.0/jquery-ui.min.js"></script>
    <script type="text/javascript" src="/Secure/calendar/js/Face.js"></script>
    <style>
        #map-canvas {
            height: 100%;
            margin: 0px;
            padding: 0px
        }
    </style>
    <title>Itinerary</title>

    <meta name="description"
          content="Full view calendar component for twitter bootstrap with year, month, week, day views.">
    <meta name="keywords" content="jQuery,Bootstrap,Calendar,HTML,CSS,JavaScript,responsive,month,week,year,day">
    <meta charset="UTF-8">

    <link rel="stylesheet" href="/Secure/calendar/components/bootstrap3/css/bootstrap.css">

    <link rel="stylesheet" href="/Secure/calendar/css/calendar.css">


</head>
<body>
<div class="container">
<div class="jumbotron">
    <h1>Itinerary</h1>

    <p><%out.println("Logged as " + userSession + "\n");%></p>

    <p><% out.println("You are seeing " + userCalendar + "'s calendar");%></p>

    <a class="btn btn-default" onclick="window.history.back()">Back</a>

    <a class="btn btn-default" href="../welcome">Inbox</a>

</div>

<div class="page-header">

    <div class="pull-right form-inline">
        <div class="btn-group">
            <button class="btn btn-primary" data-calendar-nav="prev"><< Prev</button>
            <button class="btn  btn-default" data-calendar-nav="today">Today</button>
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
    <div class="col-md-9">
        <div id="calendar"></div>

    </div>

    <div class="col-md-3">
        <div class="row">
            <select id="first_day" class="col-md-12">
                <option value="" selected="selected">First day of week language-dependant</option>
                <option value="2">First day of week is Sunday</option>
                <option value="1">First day of week is Monday</option>
            </select>
            <select id="language" class="col-md-12">
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
        <div class="table-wrapper">
            <div class="table-scroll">
            <ul id="eventlist" class="nav nav-list"></ul>
            </div>
        </div>


        <!-- Button to trigger modal -->
        <a href="#myModal" role="button" class="btn  btn-default" data-toggle="modal" id="addEventModal" >Add event</a>


        <!-- jsp to print matching users-->

        <h4>Matching users</h4>

        <div class="table-wrapper">
            <div class="table-scroll">
            <table>

            <%
                out.print(request.getAttribute("matchingUsers"));

            %>
        </table>
            </div>
        </div>


        <!-- Modal -->
        <div id="myModal" class="modal fade modal-lg " tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
             aria-hidden="true" > <div class="modal-dialog  "><div class="modal-content ">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h3 id="myModalLabel">Add event</h3>
            </div>
            <div class="modal-body">
                <form id="addEventForm" action="addEvent" method="POST" class="form-horizontal"
                      role="form">
                    <table>
                        <div class="form-group">

                            <tr>
                                <td>
                                    <label class="col-sm-2 control-label">Title</label>
                                </td>
                                <td>
                                    <div class="col-sm-10">
                                    <input class="form-control" id="Title" name="Title" type="text"
                                   value="Your title,hotel,etc">

                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label class="col-sm-2 control-label">URL</label>
                                </td>
                                <td>
                                    <div class="col-sm-10">
                                    <input class="form-control" id="URL" name="URL" type="text"
                                   value="http://www.example.com/">
                        </div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label class="col-sm-2 control-label">From:</label>
                                </td>
                                <td>
                                    <div class="col-sm-10">
                                    <input class="form-control" id="From" name="From" type="text"
                                   value="Where are you now">
                        </div>
                                </td>
                            </tr>

                        </div>
                        <div class="form-group">
                            <tr>
                                <td>
                                    <label class="col-sm-2 control-label">
                                    To:
                        </label>
                                </td>
                                <td>
                                    <div class="col-sm-10">
                                    <input class="form-control" id="To" name="To" type="text"
                                   placeholder="Where are you traveling" disabled>
                        </div>
                                </td>
                            </tr>
                        </div>
                    </table>
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

                    <div style="padding-bottom: 15px;">
                        <label class="radio-inline">
                            <input onclick="document.getElementById('To').disabled = true;" type="radio"
                                   name="optionsRadios" id="Staying"
                                   value="Staying" checked> Staying
                        </label>
                        <label class="radio-inline">
                            <input onclick="document.getElementById('To').disabled = false;" type="radio"
                                   name="optionsRadios" id="Traveling"
                                   value="Traveling">
                            Traveling
                        </label>
                    </div>
                    <div id="map-canvas-add" style="height: 40%;width: 100%;"></div>
                    <div class="modal-footer" style="padding-top: 15px;">

                        <button class="btn  btn-default" data-dismiss="modal" aria-hidden="true">Close</button>
                        <button type="button" id="submit" class="btn btn-primary myLink"
                                onclick=popitup('http://localhost:8081/Secure/calendar/face.jsp');>Save Changes
                        </button>
                    </div>

                </form>
            </div>


        </div>
        </div>
        </div>
        <%--Inbox Modal--%>

        <div id="myMessageModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
             aria-hidden="true" style="width: 50%;height: 50%;margin: auto;">  <div class="modal-dialog"> <div class="modal-content">
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
                        <div>
                            <input type="hidden" name="destinatary" id="destinatary" value=""/>
                        </div>
                    </div>


                </form>
            </div>
            <div class="modal-footer">

                <button class="btn  btn-default" data-dismiss="modal" aria-hidden="true">Close</button>
                <button type="submit" id="send" class="btn btn-primary">Send</button>
            </div>

        </div>

    </div>
        </div></div>

</div>
<div class="clearfix">
    <div id="result"></div>
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

<div class="modal fade modal-lg" id="events-modal"  >
    <div class="modal-dialog" >
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h3>Event</h3>
            </div>
            <div class="modal-body" style="width: 100%;height: 45%;" >

            </div>
            <div id="map-canvas-show" style="height: 35%;width: 100%"></div>
            <div class="modal-footer">
                <a href="#" data-dismiss="modal" class="btn  btn-default">Close</a>
            </div>
        </div>
    </div>

</div>





<script type="text/javascript" src="/Secure/calendar/components/underscore/underscore-min.js"></script>
<script type="text/javascript" src="/Secure/calendar/components/bootstrap2/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/Secure/calendar/components/jstimezonedetect/jstz.min.js"></script>
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
<script src="https://maps.googleapis.com/maps/api/js?v=3.exp&signed_in=true"></script>
<script type="text/javascript">
    $(document).on("click", ".open-sendMessageModal", function () {
        var destinatary = $(this).data('id');
        document.getElementById("destinatary").value = destinatary;

    });
</script>
<script>
      var showmap,addmap;
    function initialize() {
        var mapOptions = {
            zoom: 8,
            center: new google.maps.LatLng(-34.397, 150.644)
        };
        showmap = new google.maps.Map(document.getElementById('map-canvas-show'),
                mapOptions);
        addmap = new google.maps.Map(document.getElementById('map-canvas-add'),
                mapOptions);

    }
    $('#myModal').on('shown', function () {
        google.maps.event.trigger(addmap, 'resize');
        addmap.setCenter(new google.maps.LatLng(42.3605336, -72.6362989));
    });
    $('#events-modal').on('shown', function (e) {
        google.maps.event.trigger(showmap, 'resize');

    });
    google.maps.event.addDomListener(window, 'load', initialize);



      $('.myLink').click(function(){ //listen for submit event
          var params = [
              {
                  name: "X",
                  value: addmap.center.A
              },
              {
                  name: "Y",
                  value: addmap.center.F
              }   ];
          $.each(params, function(i,param){
              $('<input />').attr('type', 'hidden')
                      .attr('name', param.name)
                      .attr('value', param.value)
                      .appendTo('#addEventForm');
          });

          return true;
      });


      $('a.event').click(function() {
          var id=$(this).data('event-id') ;
          $.getJSON( "events?id=" + id, function(json ) {
              var x=json.x;
              var y=json.y;


              showmap.setCenter(new google.maps.LatLng(x,y));


          });
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

    function postEventForm() {

        //Serialize the form and post it to the server
        $.post("/Secure/calendar/addEvent", $('#addEventForm').serialize(), function () {

            // When this executes, we know the form was submitted

            // To give some time for the animation,
            // let's add a delay of 200 ms before the redirect
            var delay = 200;
            setTimeout(function () {
                window.location.href = '/Secure/calendar/calendar?user=' + "${userSession}";
            }, delay);
            // Hide the modal
            $("#myModal").modal('hide');

        });

        // Stop the normal form submission
        return false;
    }

    $(document).ready(function () {
        $("input#submit").click();

    });
</script>

<script type="text/javascript">
    $(document).ready(function () {
        $('#send').click(function () {
            //Serialize the form and post it to the server
            $.post("/Secure/calendar/sendMessage", $('#sendMessageForm').serialize(), function () {

                // When this executes, we know the form was submitted

                // To give some time for the animation,
                // let's add a delay of 200 ms before the redirect
                var delay = 200;
                setTimeout(function () {
                    window.location.href = '/Secure/calendar/calendar';
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

    function popitup(url) {
        newwindow = window.open(url, 'TravelTogether Post');
        newwindow.onbeforeunload = function () {

            postEventForm();
        };
        if (window.focus) {
            newwindow.focus()
        }
        return false;
    }

    $(document).ready(function () {
        var userCalendar = "${userCalendar}";
        var userSession = "${userSession}";



        if (userCalendar.localeCompare(userSession) == 0) {

        } else {
            document.getElementById("addEventModal").disabled = true;
            document.getElementById("send").disabled = true;


        }

    });

</script>

</div>
</body>
</html>
