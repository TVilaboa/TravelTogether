/**
 * Created by Toto on 17/06/2014.
 */

// You probably don't want to use globals, but this is just example code
var fbAppId = '1418734481740966';
var objectToLike = 'http://techcrunch.com/2013/02/06/facebook-launches-developers-live-video-channel-to-keep-its-developer-ecosystem-up-to-date/';


/*
 * This is boilerplate code that is used to initialize
 * the Facebook JS SDK.  You would normally set your
 * App ID in this code.
 */

// Additional JS functions here
window.fbAsyncInit = function () {
    FB.init({
        appId: fbAppId, // App ID
        status: true,    // check login status
        cookie: true,    // enable cookies to allow the
        // server to access the session
        xfbml: true,     // parse page for xfbml or html5
        // social plugins like login button below
        version: 'v2.0'  // Specify an API version
    });

    // Put additional init code here
};

// Load the SDK Asynchronously
(function (d, s, id) {
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) {
        return;
    }
    js = d.createElement(s);
    js.id = id;
    js.src = "//connect.facebook.net/en_US/sdk.js";
    fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));

/*
 * This function makes a call to the og.likes API.  The
 * object argument is the object you like.  Other types
 * of APIs may take other arguments. (i.e. the book.reads
 * API takes a book= argument.)
 *
 * Because it's a sample, it also sets the privacy
 * parameter so that it will create a story that only you
 * can see.  Remove the privacy parameter and the story
 * will be visible to whatever the default privacy was when
 * you added the app.
 *
 * Also note that you can view any story with the id, as
 * demonstrated with the code below.
 *
 * APIs used in postLike():
 * Call the Graph API from JS:
 *   https://developers.facebook.com/docs/reference/javascript/FB.api
 * The Open Graph og.likes API:
 *   https://developers.facebook.com/docs/reference/opengraph/action-type/og.likes
 * Privacy argument:
 *   https://developers.facebook.com/docs/reference/api/privacy-parameter
 */

function getTitle() {
    var title = 'From ' + window.opener.document.getElementById('From').value;
    if (window.opener.document.getElementsByName('optionsRadios').value = 'Traveling') {
        title += ' to ' + window.opener.document.getElementById('To').value;
    }
    return  title;
}
//function close(){
//    alert("holis");
//    window.close();
//
//}

function create() {
    FB.api(
        'me/traveltogetheronline:create',
        'post',
        {
            event: {
                app_id: 1418734481740966,
                type: "traveltogetheronline:event",
                url: "http://localhost:8081/Secure/calendar/calendar?user=toto",
                title: getTitle(),
                image: "http://erasmussinmaletas.com/wp-content/uploads/2013/03/destinos-viajes.jpg",
                description: window.opener.document.getElementById('Title').value
            },
            privacy: {'value': 'SELF'}
        }

        ,
        function (response) {
            if (!response) {
                alert('Error occurred.');
            } else if (response.error) {
                document.getElementById('result').innerHTML =
                    'Error: ' + response.error.message;
            } else {
                document.getElementById('result').innerHTML =
//                    '<a href=\"https://www.facebook.com/me/activity/' +
//                    response.id + '\"  target="_blank" onclick="alert("holis");">' +
//                    'Story created.  ID is ' +
//                    response.id + '</a>';
                    document.getElementById('result').innerHTML =
                        '<a href=\"https://www.facebook.com/me/activity/' +
                        response.id + '\">' + 'Click' +  '</a>';


            }

        }
    );

}

function postLike() {
    FB.api(
        'https://graph.facebook.com/me/og.likes',
        'post',
        { object: objectToLike,
            privacy: {'value': 'SELF'} },
        function (response) {
            if (!response) {
                alert('Error occurred.');
            } else if (response.error) {
                document.getElementById('result').innerHTML =
                    'Error: ' + response.error.message;
            } else {
                document.getElementById('result').innerHTML =
                    '<a href=\"https://www.facebook.com/me/activity/' +
                    response.id + '\">' +
                   'Click' + '</a>';
            }
        }
    );
}