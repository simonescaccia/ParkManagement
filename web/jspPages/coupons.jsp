<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="logic.control.bean.MessageBean" %>

<%! String forward; %>
<% forward = (String)request.getParameter("forward"); %>  

<%
if(forward == null){%>
	<jsp:forward page="/showCoinsGuiControlServlet" />
<%
}
%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="ISO-8859-1">
	<title>Add Report</title>
	
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/attractionView.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/mycss.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://apis.google.com/js/platform.js" async defer></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/myjs.js"></script>
	<meta name="google-signin-client_id" content="80408227597-gnan03ov689qhrgt23eho38jmt0krs06.apps.googleusercontent.com">

</head>
<body>
	
	<div class="box">
  		<div class="header">
  			
		    <img class="backImg" alt="" src="<%=request.getContextPath()%>/img/backgroundImage2.jpg" width="1366">
		  	
		  	<ul>
				<li class="left"><a class="left" href="#"><img src="<%=request.getContextPath()%>/img/undo2.png" alt="undo"></a></li>
		  		<li class="left"><a class="left" href="#"><img src="<%=request.getContextPath()%>/img/redo2.png" alt="redo"></a></li>
		  		<li class="center"><h4 class="font"><a id="nounderline" href="<%=request.getContextPath()%>/index.jsp">Attractions</a></h4></li>
		  		<li class="left"><h4 class="font"><a id="nounderline" href="<%=request.getContextPath()%>/jspPages/yourReports.jsp">Your reports</a></h4></li>
		  		<li class="left"><h4 class="font"><a id="nounderline" class="active" href="<%=request.getContextPath()%>/jspPages/coupons.jsp">Coupons</a></h4></li>
		  		<li class="right">
					<div class="googleLogin" id="signin-container">
		  				<div class="g-signin2" data-onsuccess="onSignIn"></div>	
		  			</div>
					<div class="googleLogout" id="signout-container">
		  				<h4 class="font"><a id="nounderline" href="#" onclick="signOut();">Sign out</a></h4>
		  			</div>
				</li>
			</ul>
			
		</div>	

  		<div class="content">
  			<div class="box2">
  			
  				<!-- informazioni -->
			  	<div class="info">
			  		
			  	</div>
			 
			 	<!-- informazioni laterali -->
			  	<div class="infoside">
			  			
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
					<div>
						
					<%
					if(forward != null){
						if(forward.equals("true")) { %>
							
							<br>
							<h4 class="desc">Your coins: <%= request.getAttribute("coins") %></h4>
						
					<% 
						}
					}
					%>
					</div>
			  	</div>
			
			</div>
		</div>
	</div>

</body>
</html>