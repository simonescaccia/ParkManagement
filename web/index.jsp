<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="logic.control.bean.MessageBean" %>

    
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="ISO-8859-1">
	<title>SpeedyFila</title>
	
	<link rel="stylesheet" href="css/mycss.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://apis.google.com/js/platform.js" async defer></script>
	<script type="text/javascript" src="js/myjs.js"></script>
	<meta name="google-signin-client_id" content="80408227597-gnan03ov689qhrgt23eho38jmt0krs06.apps.googleusercontent.com">
	

</head>
<body>
  	
  	<div class="box">
  		
  		<!-- questo div contiene l'immagine e la lista dei bottoni -->
  		<div class="header">	
  			<img class="backImg" alt="" src="<%=request.getContextPath()%>/img/backgroundImage2.jpg">
	
		  	<ul>
				<li class="left"><a class="left" href="#"><img src="<%=request.getContextPath()%>/img/undo2.png" alt="undo"></a></li>
		  		<li class="left"><a class="left" href="#"><img src="<%=request.getContextPath()%>/img/redo2.png" alt="redo"></a></li>
		  		<li class="center"><h4 class="font"><a class="active" href="#">Attractions</a></h4></li>
		  		<li class="left"><h4 class="font"><a href="#">Your reports</a></h4></li>
		  		<li class="left"><h4 class="font"><a href="#">Coupons</a></h4></li>
		  		<li class="left"><h4 class="font"><a href="#">Favourites</a></h4></li>
		  		<li class="right">
		  			<div class="googleLogin" id="signin-container">
		  				<div class="g-signin2" data-onsuccess="onSignIn"></div>	
		  			</div>
					<div class="googleLogout" id="signout-container">
		  				<h4 class="font"><a href="#" onclick="signOut();">Sign out</a></h4>
		  			</div>
		  		</li>
		  	</ul>
		</div>
  		
  		<div class="content">
  			<div class="box2">
			  	<div class="info">
					<a href="<%=request.getContextPath()%>/jspPages/addReport.jsp?attractionName=nome%20attrazione">Nome dell'attrazione</a>
			  	</div>
			 
			  	<div class="infoside">
			  	
			  		<!-- il div message permette di visualizzare messagi di errore/successo -->
			  		<div class="message">
			  			<%if(request.getAttribute("mB") != null) {
							MessageBean mB = (MessageBean)request.getAttribute("mB");
							
							if(mB.getType()){	%>
								<div class="icon"><img alt="success" src="<%=request.getContextPath()%>/img/success-icon2.png"></div>
							<%
							} else { %>
								<div class="icon"><img alt="error" src="<%=request.getContextPath()%>/img/error-flat2.png"></div>
							<%
							}
							%>
							<div class="text"><h4 class="desc"><%= mB.getMessage() %></h4></div>
						<%}
						%>
				  	</div>
			  	</div>
			</div>
		</div>
	</div>

</body>
</html>