<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 
 <!-- dichiarazione e instanziazione di un BeanAddReport !-->
<jsp:useBean id="addReportBean" scope="request" class="logic.bean.AddReportBean"/>

<jsp:setProperty name="addReportBean" property="*"/>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="ISO-8859-1">
	<title>Add Report</title>
	
	<link rel="stylesheet" href="css/mycss.css">

</head>
<body>
	
  	<img class="backImg" alt="" src="<%=request.getContextPath()%>/img/backgroundImage2.jpg" width="1366">
  	
  	<ul>
		<li class="left"><a class="left" href="#"><img src="<%=request.getContextPath()%>/img/undo2.png" alt="undo"></a></li>
  		<li class="left"><a class="left" href="#"><img src="<%=request.getContextPath()%>/img/redo2.png" alt="redo"></a></li>
  		<li class="center"><h4 class="font"><a class="active" href="#">Attractions</a></h4></li>
  		<li class="left"><h4 class="font"><a href="#">Your reports</a></h4></li>
  		<li class="left"><h4 class="font"><a href="#">Coupons</a></h4></li>
  		<li class="left"><h4 class="font"><a href="#">Favourites</a></h4></li>
  		<li class="right"><a class="right" href="#"><img src="<%=request.getContextPath()%>/img/login2.jpg" alt="undo"></a></li>
  	</ul>
  	
  	<div class="info">

  	</div>
 
  	<div class="infoside">

  	</div>

</body>
</html>