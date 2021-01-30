<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
You can close this page<br>
response = 
<% if(request.getParameter("code")!=null) %> accepted <% else %> Rejected  
</body>
</html>