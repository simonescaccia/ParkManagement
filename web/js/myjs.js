$(document).ready(
	function(){
			$("#signout-container").hide();
			$("#videoAds").hide();
});

function onSignIn(googleUser) {
	var profile = googleUser.getBasicProfile();
	$("#signout-container").show();
	$("#signin-container").hide();

	var id_token = googleUser.getAuthResponse().id_token;
	var xhr = new XMLHttpRequest();
	xhr.open('POST', '/SpeedyFila/loginGuiControlServlet');
	xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	xhr.send('idtoken=' + id_token );
	
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

function showAds(){
	$("#attrInfo").hide();
	$("#videoAds").show();
	$("#gain1coin").hide();
	var xhr = new XMLHttpRequest();
	xhr.open('POST', '/SpeedyFila/showVideoAdsGuiControlServlet');
	xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	xhr.send();
}

function closeAds(){
	$("#attrInfo").show();
	$("#videoAds").hide();
}