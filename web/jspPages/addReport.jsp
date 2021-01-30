<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="logic.control.bean.MessageBean" %>
<%@ page import="logic.control.bean.ParkAttractionBean" %>
<%@ page import="logic.control.bean.ReportBean" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>

<%! String forward; %>
<%! String forwardAddR; %>
<%! String attractionName; %>
<% forward = (String)request.getParameter("forward"); %>  
<% attractionName = request.getParameter("attractionName");
   forwardAddR = (String)request.getAttribute("forwardAddR");

if(forward == null){%>
	<jsp:forward page="/showAttractionInfoGuiControlServlet" />
<%
}
%>

<%if(!forward.equals("true") || attractionName == null){
	%>
	<jsp:forward page="/index.jsp" />
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
  			
  				<!-- informazioni -->
			  	<div class="info">
			  		<div id="attrInfo">
			  			<br>
			  			<div class="container">
							<div class="row">
								<div class="col-xs-6" id="width2">
								</div>
								<div class="col-xs-6" id="width">
									<h4 class = "desc">Attractions ><%= attractionName %></h4>
								</div>
						
						<%
						if(forward != null){
							if(forward.equals("true")) { %>
							
							 <%! ParkAttractionBean parkAttractionInfo; %>
							 <%  parkAttractionInfo = (ParkAttractionBean)request.getAttribute("parkAttractionInfo");%>
								 <div class="col-xs-6">
								 	<img class="icon" src="data:image/png;base64,<%= parkAttractionInfo.getImgCategoryString() %>" alt="imgC"/>
								 </div>
							</div>
						</div>
						<br>
						<div class="container">
							<div class="row">
								<div class="col-xs-6" id="width2">
								</div>
								<div class="col-xs-6">
							 		<img src="data:image/png;base64,<%= parkAttractionInfo.getImgAttractionString() %>" alt="imgA" width="700"/>
							    </div>
							</div>
						</div>
							 <%}
							}
						%>
					</div>
					<div id="videoAds">
						<img src="<%=request.getContextPath()%>/img/videoAds.png" width="400" alt="videoAds"><br>
						<input class="addreport" type="submit" name="Close" value="Close" onclick="closeAds();">
					</div>
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
					<div class="addreport">
						        
						<form action="<%=request.getContextPath()%>/addReportGuiControlServlet?attractionName=<%=attractionName %>" name="myform" method="POST">
							<h4 class="desc">How many people are there in the queue?</h4>
							<div>	
								<input class="addreport" type="checkbox" id="isLast" name="isLast">
								<label class="desc">I'm the last of the queue</label>
							</div>
							<div class="container">
								<div class="row">
									<div class="col-xs-6">
										<input class="addreport" type="text" name="queueLen" id="queueLen" autocomplete="off">
									</div>
									<div class="col-xs-6">
										<input class="addreport" type="submit" name="addReport" value="Add Report">
									</div>
								</div>
							</div>
						</form>
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
						<br>
						<br>
						<br>
						<br>
						<br>
						<h4 class="desc">Last reports:</h4>
						<div class="container">
							<div class="row">
								<div class="col-xs-6">
								<%	
								if(forward != null){
									if(forward.equals("true")) { %>
									
										<%! List<ReportBean> listOfReport; %>
										<%!	Iterator<ReportBean> i; %>
										<%  listOfReport = parkAttractionInfo.getListOfReports();		
											i = listOfReport.iterator();
										
										while(i.hasNext()) { %>
											<%!ReportBean reportBean; %>
											<%reportBean = i.next();%>
											<div>
												<h4 class="desc2"><%=reportBean.getDate()%>, len: <%=reportBean.getLenQueue() %></h4>
											</div>
											<% 
										}
									}
								}
								%>
								</div>
							</div>
						</div>
					</div>
			  	</div>
			
			</div>
		</div>
	</div>

</body>
</html>