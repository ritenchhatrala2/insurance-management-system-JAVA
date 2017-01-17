<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Navigation</title>
</head>
<body>
<nav id="nav">
  <ul class="clearfix">
		<li><a href="home.jsp">Home</a></li>
		<li><a href="about.jsp">About Us</a></li>
		<li><a href="InsuranceController?test=-1">Policy Details</a></li>
		
		<li><a href="" aria-haspopup="true"><span>Customer</span></a>
		<% if(request.getSession(false).getAttribute("type")!=null)
		if(request.getSession(false).getAttribute("type").toString().equalsIgnoreCase("FIELD OFFICER") || request.getSession(false).getAttribute("type").toString().equalsIgnoreCase("Insurance officer"))
 { %>
			<ul>
				<li><a href="addCustomer.jsp">Register Customer</a></li>
				<li><a href="updateCustomer.jsp">Update Customer</a></li>
				<li><a href="viewCustomer.jsp">View Customer</a></li>
				<li><a href="removeCustomer.jsp">Remove Customer</a></li>
			</ul><%} %>
		</li>
		<li>
			<a href="" aria-haspopup="true"><span>Policy</span></a>
			<% if(request.getSession(false).getAttribute("type").toString().equalsIgnoreCase("FIELD OFFICER") || request.getSession(false).getAttribute("type").toString().equalsIgnoreCase("Insurance officer"))
 { %>
			<ul>
			<%if(request.getSession(false).getAttribute("type").toString().equalsIgnoreCase("Insurance officer")){ %>
				<li><a href="addHealthPolicy.jsp" >Add health Policy</a></li><%} %>
				<% if(request.getSession(false).getAttribute("type").toString().equalsIgnoreCase("FIELD OFFICER")) {%>
				<li><a href="addVehiclePolicy.jsp" >Add vehicle Policy</a></li><%} %>
				<li><a href="updatePolicy.jsp">Update Policy</a></li>
				<li><a href="viewPolicy.jsp">View Policy</a></li>
				<li><a href="terminatePolicy.jsp">Terminate Policy</a></li>
			</ul><% } %>
		</li>
		<li>
			<a href="" aria-haspopup="true"><span>Claim</span></a>
			<ul>
			<% if(request.getSession(false).getAttribute("type").toString().equalsIgnoreCase("FIELD OFFICER") || request.getSession(false).getAttribute("type").toString().equalsIgnoreCase("Insurance officer"))
 			{ %>
				<li><a href="applyClaim.jsp">Apply Claim</a></li>
				<li><a href="updateClaim.jsp">Update Claim</a></li>
				<%} %>
				<% if(request.getSession(false).getAttribute("type").toString().equalsIgnoreCase("ADMIN")){%>
				<li><a href="approveClaim.jsp">Approve/Reject Claim</a></li>
				<li><a href="ViewClaim.jsp">View Claim</a></li><%}%>
				<% if(request.getSession(false).getAttribute("type").toString().equalsIgnoreCase("FIELD OFFICER") || request.getSession(false).getAttribute("type").toString().equalsIgnoreCase("Insurance officer"))
 				{ %>
				<li><a href="cancleClaim.jsp">Remove Claim</a></li>
				<%} %>
			</ul>
		</li>
	
  </ul>
</nav>
</body>
</html>