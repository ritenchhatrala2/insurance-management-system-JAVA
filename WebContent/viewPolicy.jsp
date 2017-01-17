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
	int check1=0;
	if(request.getAttribute("check")!=null)
	check1=Integer.parseInt(request.getAttribute("check").toString());
	ArrayList<VehicleInsurance> insurancelist = null;
		try {
			insurancelist = (ArrayList<VehicleInsurance>) request.getAttribute("insuranceList");
		} catch (Exception e) {
			e.printStackTrace();
		}%>
	<div class="content">
	<%	if (check1==0) { %>
		<br>
		<form action="InsuranceController" method="post" name="viewpolicyform1"
			class="basic-grey" onSubmit="return View_policy_mandatory()">
			<h1>View Policies</h1>
			<label> <span>Customer ID :<sup><font color="red">*</font></sup></span> 
			<input type="text" name="custid" id="custid" maxlength="6" onblur="CustId(this.value)"/>
			<p id="custid_msg"><%if(request.getAttribute("custid")!=null && request.getAttribute("custid").toString().equals("custid")){out.print("Customer id  must be in NUMBER only and not empty."); } %></p>
			</label> 
			
			<center><span>&nbsp;</span> <input type="submit"
				value="View" /><input type="reset" value="reset" />
				<input type="hidden" name="action" value="viewPolicies" />
				
				<input type="hidden" name="check" value="view" />
				</center>
			 <br>
	</form>
	<br><br><br>
<% } else if(check1==-2){ %>
<script>alert('No policy available for provided customer Id. Try again.');
window.location = 'home.jsp';
</script>
<% } else if(check1==-3){ %>
<script>alert('Invalid customer id. Try again.');
window.location = 'home.jsp';
</script>
<% } else if(check1==-1) {
	if((request.getSession(false).getAttribute("type").toString().equalsIgnoreCase("Insurance officer"))||(request.getSession(false).getAttribute("type").toString().equalsIgnoreCase("Field officer")))
	{
	%>
		<p align="left">
		<form action="home.jsp">
		<table align="center" border="1" cellspacing="5" cellpadding="5">
			<tr>
				<th>Policy Reference Number</th>
				<th>Customer Id</th>
				<th>Policy Id</th>
				<th>Policy Commencement Date</th>
				<th>Policy End Date</th>
				<th>Payment Mode</th>
				<th>Payment Frequency</th>
				<th>Policy tenure</th>
				<th>Premium Amount</th>
				<th>Policy Status</th>
				<th>Policy type</th>
				<th>Vehicle No</th>
				<th>License No</th>
				<th>Vehicle Type</th>
			</tr>
			<%
				for (VehicleInsurance insurance : insurancelist) {
			%>
			<tr>
				<td><%=insurance.getPolicyReferenceNumber()%></td>
				<td><%=insurance.getCustomerId()%></td>
				<td><%=insurance.getPolicyId() %></td>
				<td><%=insurance.getPolicyCommencementDate()%></td>
				<td><%=insurance.getPolicyEndDate()%></td>
				<td><%=insurance.getPaymentMode()%></td>
				<td><%=insurance.getPaymentFrequency()%></td>
				<td><%=insurance.getPolicyTenure()%></td>
				<td><%=insurance.getPremiumAmount()%></td>
				<td><%=insurance.getPolicyStatus()%></td>
				<td><%=insurance.getPolicyType()%></td>
				<%
					if (insurance.getVehicleNo() == null
									|| insurance.getVehicleNo() == "") {
				%>
				<td>NA</td>
				<%
					} else {
				%>
				<td><%=insurance.getVehicleNo()%></td>
				<%
					}
							if (insurance.getLicenseNo() == null
									|| insurance.getLicenseNo() == "") {
				%>
				<td>NA</td>
				<%
					} else {
				%>
				<td><%=insurance.getLicenseNo()%></td>
				<%
					}
							if (insurance.getVehicleType() == null
									|| insurance.getVehicleType() == "") {
				%>
				<td>NA</td>
				<%
					} else {
				%>
				<td><%=insurance.getVehicleType()%></td>
			</tr>
			<%
				}
					}
			%>
		</table>
		<input type="submit" name="submit" value="Ok"/>
		</form>
		</p>
		<% } else {%>
<script>alert('you are not officer');
window.location = 'home.jsp';
</script>
<noscript>You are not officer.</noscript>
		 <% }} %>
</div>
<jsp:directive.include file="footer.jsp" />
</body>
</html>
