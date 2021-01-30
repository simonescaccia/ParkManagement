<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="logic.control.bean.MessageBean" %>
<%@ page import="logic.control.bean.ReportBean" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
    
    
<%! String forward; %>
<% forward = (String)request.getParameter("forward"); %>    

<%
//call the servlet
if(forward == null){
	%>
	<jsp:forward page="/showYourReportsGuiControlServlet" />
<%
}
%>    
    
    
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="ISO-8859-1">
	<title>Your Reports</title>
	
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/mycss.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/attractionView.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://apis.google.com/js/platform.js" async defer></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/myjs.js"></script>
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
		  		<li class="center"><h4 class="font"><a id="nounderline" href="<%=request.getContextPath()%>/index.jsp">Attractions</a></h4></li>
		  		<li class="left"><h4 class="font"><a id="nounderline" class="active" href="<%=request.getContextPath()%>/jspPages/yourReports.jsp">Your reports</a></h4></li>
		  		<li class="left"><h4 class="font"><a id="nounderline" href="<%=request.getContextPath()%>/jspPages/coupons.jsp">Coupons</a></h4></li>
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
  			
  				<!-- contenitore delle informazioni -->
			  	<div class="info">
			  		<div id="attrInfo">
					
					<%
					if(forward != null){
						if(forward.equals("true")) { %>
						
							<%! List<ReportBean> listOfReports; %>
							<%!	Iterator<ReportBean> i; %>
							<%  listOfReports = (List<ReportBean>)request.getAttribute("listOfReports");		
								i = listOfReports.iterator();
							
							while(i.hasNext()) { %>
								<%!ReportBean reportBean; %>
								<%reportBean = i.next();%>
								<form action="<%=request.getContextPath()%>/insertFeedbackGuiControlServlet" method="post">
									<input type="hidden" name="attractionName" value="<%= reportBean.getParkAttraction().getName() %>">
									<div class="container" id="attractionView">
										<div class="row">
											<div class="col-xs-6">
												<img class="attrImg "src="data:image/png;base64,<%= reportBean.getParkAttraction().getImgAttractionString() %>" alt="imgA" />
											</div>
											<div class="col-xs-6">
												<a class="attrName" href="<%=request.getContextPath()%>/jspPages/addReport.jsp?attractionName=<%= reportBean.getParkAttraction().getName()%>"><%=reportBean.getParkAttraction().getName() %></a>
												<br>
												<div class="container">
													<div class="row">
														<div class= "col-xs-6" id="width2">
														</div>
														<div class= "col-xs-6">
															<input class="insertFeedback" type="text" name="feedback" autocomplete="off"  placeholder="insert the time in minutes">
														</div>
														<div id="param" class= "col-xs-6">
															 <input class="insertFeedback" type="submit" name="insertFeedback" value="Insert feedback">
														</div>
														<div class= "col-xs-6">
															 <h4 class="desc">    Date: <%= reportBean.getDate() %></h4>
															 <input type="hidden" name="date" value="<%= reportBean.getDate() %>">
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</form>
								<% 
							}
						}
					}
					%>
					</div>
					<div id="videoAds">
						<img src="<%=request.getContextPath()%>/img/videoAds.png" width="400" alt="videoAds"><br>
						<input class="addreport" type="submit" name="Close" value="Close" onclick="closeAds();">
					</div>
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
					<br>
						<%if((request.getAttribute("mB") != null)) {
							MessageBean mB = (MessageBean)request.getAttribute("mB");
							
							if(mB.getType()){	%>
								<div id="hideMe">
									<input id="gain1coin" class="addreport" type="submit" name="Gain1coin" value="Gain1coin" onclick="showAds();">
								</div>
							<%
							}
						}
						%>
			  	</div>
			</div>
		</div>
	</div>

</body>
</html>