<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="ISO-8859-1">
	<title>SpeedyFila</title>
	
	<link rel="stylesheet" href="css/mycss.css">

</head>
<body>
  	
  	<div class="box">
  			
  		<div class="header">	
  			<img class="backImg" alt="" src="<%=request.getContextPath()%>/img/backgroundImage2.jpg">
	
		  	<ul>
				<li class="left"><a class="left" href="#"><img src="<%=request.getContextPath()%>/img/undo2.png" alt="undo"></a></li>
		  		<li class="left"><a class="left" href="#"><img src="<%=request.getContextPath()%>/img/redo2.png" alt="redo"></a></li>
		  		<li class="center"><h4 class="font"><a class="active" href="#">Attractions</a></h4></li>
		  		<li class="left"><h4 class="font"><a href="#">Your reports</a></h4></li>
		  		<li class="left"><h4 class="font"><a href="#">Coupons</a></h4></li>
		  		<li class="left"><h4 class="font"><a href="#">Favourites</a></h4></li>
		  		<li class="right"><a class="right" href="#"><img src="<%=request.getContextPath()%>/img/login2.jpg" alt="undo"></a></li>
		  	</ul>
		</div>
  		
  		<div class="content">
  			<div class="box2">
			  	<div class="info">
					<a href="<%=request.getContextPath()%>/jspPages/addReport.jsp?attractionName=nome%20attrazione">Nome dell'attrazione</a>
			  	</div>
			 
			  	<div class="infoside">
	
			  	</div>
			</div>
		</div>
	</div>

</body>
</html>