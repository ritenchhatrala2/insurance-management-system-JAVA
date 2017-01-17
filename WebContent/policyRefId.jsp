<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Policy Reference Number</title>
</head>
<body>
<%if(Integer.parseInt(request.getAttribute("check").toString()) >0)
	{ %>
Your policy reference number is generated successfully.
<%if(request.getAttribute("type").equals("health")){	%>
And policy reference number is <%= request.getAttribute("check") %>
<% } 
else if(request.getAttribute("type").equals("vehicle"))
{
	%>
	policy reference number is <%= request.getAttribute("check") %>
	policy end date is <%=request.getAttribute("end_date") %>
<% } 
}
else
{
%> Error in page.
<%} %>
</body>
</html>