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
String customerName = String.valueOf(request.getAttribute("customerName"));
int customerID = Integer.parseInt(request.getAttribute("customerID").toString());
if(customerID>0)
{
	%> <h4>Registration of <i><%=customerName %></i> is successful.</h4><br>
	<b>Please note the customer ID: <%=customerID %>.</b><%
}
else
{
	%> <h6>Sorry, registration is not successful.</h6><br>
	Please try again!!!<%
}%>
</div><p></p>

<jsp:directive.include file="footer.jsp" />

</body>
</html>