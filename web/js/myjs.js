$(document).ready(
	function(){
			$("#signout-container").hide();
});

function onSignIn(googleUser) {
	var profile = googleUser.getBasicProfile();
	$("#signout-container").show();
	$("#signin-container").hide();
	console.log('Email: ' + profile.getEmail()); // This is null if the 'email' scope is not present.
	  
	var url_string = window.location.href
	var url = new URL(url_string);
	var attractionName = url.searchParams.get("attractionName");

	var id_token = googleUser.getAuthResponse().id_token;
	var xhr = new XMLHttpRequest();
	xhr.open('POST', '/SpeedyFila/loginGuiControlServlet');
	xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	xhr.send('idtoken=' + id_token + '&page=' + window.location.pathname + '&param=' + attractionName);
	
}

function signOut() {
	var xhr = new XMLHttpRequest();
    xhr.open('POST', '/SpeedyFila/logoutGuiControlServlet');
    xhr.send();
	var auth2 = gapi.auth2.getAuthInstance();
	auth2.signOut().then(function () {
	  console.log('User signed out.');
	  $("#signout-container").hide();
	  $("#signin-container").show();
	});
}