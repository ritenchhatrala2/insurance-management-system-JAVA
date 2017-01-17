<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/style.css" />
<title>Confirm Customer Registration</title>
</head>
<body>

<jsp:directive.include file="header.jsp" />
<jsp:directive.include file="navigation.jsp" />

<div class="content">
<br>
<%
String customerName = String.valueOf(request.getAttribute("customerName")); %>

<h4>Customer with the same Email Id already exist and Name of the customer is <h3><%= customerName %></h3></h4><br>
</div>

<jsp:directive.include file="footer.jsp" />

</body>
</html>