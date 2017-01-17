<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.ilp.ims.Bean.*,java.util.ArrayList"%>
<%
response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");//HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0);
if(request.getSession(false).getAttribute("type")==null){ %>
<jsp:forward page="Login.jsp" ></jsp:forward>
<% } %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Policies</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="icon" href="images/favicon.ico" type="image/x-icon" />
<script type="text/javascript"  src="js/Validate_policy.js"></script>
</head>
<body>

	<jsp:directive.include file="header.jsp" />
	<jsp:directive.include file="navigation.jsp" />
	<%
	ArrayList<Policy> policylist = null;
		try {
			if(request.getAttribute("policylist")!=null)
			policylist = (ArrayList<Policy>) request.getAttribute("policylist");
		} catch (Exception e) {
			e.printStackTrace();
		}%>
	<div class="content">
<% if(policylist==null){ %>
<script>alert('No policy available. Try again.');
window.location = 'home.jsp';
</script>
<% } else if(policylist!=null) {
	
	%>
		<p align="left">
		<table align="center" border="1" cellspacing="5" cellpadding="5">
			<tr>
				<th>Policy Id</th>
				<th>Policy Name</th>
				<th>Policy Description</th>
				<th>Policy Type</th>
			</tr>
			<%
				for (Policy policy : policylist) {
			%>
			<tr>
				<td><%=policy.getPolicyId()%></td>
				<td><%=policy.getPolicyName()%></td>
				<td><%=policy.getPolicyDescription()%></td>
				<td><%=policy.getPolicyType()%></td>
			</tr>
			<%
				}
					}
			%>
		</table>
	</p>
</div>
<jsp:directive.include file="footer.jsp" />
</body>
</html>
