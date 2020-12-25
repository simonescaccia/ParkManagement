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
  	
  	    <form action="index.jsp" name="myform" method="POST">
        <div class="row">
            <div class="col-lg-4 form-group">
                <label for="username">Username</label>
                <input type="text" id="username" name="username" autocomplete="off">
            </div>
        </div>
        <div class="row">
            <div class="col-lg-4 form-group">
                <label for="password">Password</label>
                <input type="password" id="password" name="password">
            </div>
        </div>
        <div class="row">
            <div class="col-lg-4 form-group">
                <input type="submit" name="login" value="login">
            </div>
        </div>
    </form>
  	 	
</body>
</html>