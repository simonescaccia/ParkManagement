<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="logic.control.bean.MessageBean" %>
<%@ page import="logic.control.bean.ParkAttractionBean" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
    
    
<%! String forward; %>
<% forward = (String)request.getParameter("forward"); %>    

<%
//call the servlet
if(forward == null){

	request.setAttribute("filter", null);
	request.setAttribute("order", null);
	%>
	<jsp:forward page="/viewAttractionsGuiControlServlet" />
<%
}
%>    
    
    
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="ISO-8859-1">
	<title>SpeedyFila</title>
	
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
		  		<li class="center"><h4 class="font"><a id="nounderline" class="active" href="<%=request.getContextPath()%>/index.jsp">Attractions</a></h4></li>
		  		<li class="left"><h4 class="font"><a id="nounderline" href="<%=request.getContextPath()%>/jspPages/yourReports.jsp">Your reports</a></h4></li>
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
					
					<%
					if(forward != null){
						if(forward.equals("true")) { %>
						
							<%! List<ParkAttractionBean> listOfParkAttraction; %>
							<%!	Iterator<ParkAttractionBean> i; %>
							<%  listOfParkAttraction = (List<ParkAttractionBean>)request.getAttribute("listOfParkAttraction");		
								i = listOfParkAttraction.iterator();
							
							while(i.hasNext()) { %>
								<%!ParkAttractionBean pABean; %>
								<%pABean = i.next();%>
								<%! String wt; %>
								<%! String distance; %>
								<%
								if(pABean.getWaitingTime() == -1){
									wt = "no recent info";
								} else {
									wt = String.valueOf(pABean.getWaitingTime());
								}
								if(pABean.getDistanceFromUser() == 0){
									distance = "no info";
								} else {
									distance = String.valueOf(pABean.getDistanceFromUser());
								}
								%>
								<div class="container" id="attractionView">
									<div class="row">
										<div class="col-xs-6">
											<img class="attrImg "src="data:image/png;base64,<%= pABean.getImgAttractionString() %>" alt="imgA" />
										</div>
										<div class="col-xs-6">
											<a class="attrName" href="<%=request.getContextPath()%>/jspPages/addReport.jsp?attractionName=<%=pABean.getName()%>"><%=pABean.getName()%></a>
											<br>
											<div class="container">
												<div class="row">
													<div class= "col-xs-6">
														<img class="icon" src="<%=request.getContextPath()%>/img/clessidra2.jpg" alt="imgCl"/>
													</div>
													<div id="param" class= "col-xs-6">
														<h4 class="desc"><%= wt %> min</h4> 
													</div>
													<div class= "col-xs-6">
														<img class="icon"  src="data:image/png;base64,<%= pABean.getImgCategoryString() %>" alt="imgC"/>
													</div>
													<div id="param" class= "col-xs-6">
														<h4 class="desc"><%= pABean.getCategoryName() %></h4>
													</div>
													<div class= "col-xs-6">
														<img class="icon"  src="<%=request.getContextPath()%>/img/maps2.png" alt="imgM"/>
													</div>
													<div id="param" class= "col-xs-6">
														<h4 class="desc"><%= distance %> m</h4>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<% 
							}
						}
					}
					%>
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
				  	
				    <div class="container">
						<div class="row">
							<div class= "col-xs-6">
								<h4 class="desc">Sort:</h4>
								<br>
								<h4 class="desc">Filter:</h4>						
							</div>
							<div class="col-xs-6">
						  		<form action="<%=request.getContextPath()%>/viewAttractionsGuiControlServlet" name="myform" method="GET" >
									<!-- order -->
									<div class="form-check">
									    <input name="order" type="radio" value="waitingtime">
									    <label class="desc" for="radio1">Waititng Time</label>
									</div>
									<div class="form-check">
									    <input name="order" type="radio" value="distance">
									    <label class="desc" for="radio2">Distance</label>
									</div>
									<!-- filter -->
									<div class="form-check">
									    <input name="filter" type="radio" value="Fun">
									    <label class="desc" for="radio1">Fun</label>
									</div>
									<div class="form-check">
									    <input name="filter" type="radio" value="Restaurant">
									    <label class="desc" for="radio2">Restaurant</label>
									</div>
									<div class="form-check">
									    <input name="filter" type="radio" value="Toilet">
									    <label class="desc" for="radio1">Toilet</label>
									</div>
									<div class="form-check">
									    <input name="filter" type="radio" value="Shop">
									    <label class="desc" for="radio2">Shop</label>
									</div>
									<div class="form-check">
									    <input name="filter" type="radio" value="Photo Store">
									    <label class="desc" for="radio2">Photo Store</label>
									</div>
									<input class="addreport" type="submit" name="search" value="Search">
								</form>
							</div>
						</div>
					</div>	
			  	</div>
			</div>
		</div>
	</div>

</body>
</html>