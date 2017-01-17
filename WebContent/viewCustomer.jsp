<%@page import="com.ilp.ims.Bean.*"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");//HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0);
%>
<%
if(request.getSession(false).getAttribute("type")==null)
{
%>
<jsp:forward page="Login.jsp" ></jsp:forward>
<%} %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Customers</title>
<script type="text/javascript" src="js/validateCustomer.js"></script>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="icon" href="images/favicon.ico" type="image/x-icon" />
</head>
<body>
<% if(request.getSession(false).getAttribute("type").toString().equalsIgnoreCase("FIELD OFFICER") || request.getSession(false).getAttribute("type").toString().equalsIgnoreCase("Insurance officer"))
 { %>
<jsp:directive.include file="header.jsp" />
<jsp:directive.include file="navigation.jsp" />

<div class="content">
<br>
<%
	ArrayList<Customer> custList = (ArrayList<Customer>) request.getAttribute("custList");	
	if(custList==null){
	%>
<form action="CustomerController" method="post" name="viewform" class="basic-grey" onsubmit="return dateMandatory()">
    <h1> 
        View Customers
    </h1>
    <label>
        <span>Start Date :<sup><font color="red">*</font></sup></span>
        <input type="date" name="sdate" onblur="startdate(this.value)"/>
        <p id="sdate_msg"><% if(request.getAttribute("sdate")!=null && request.getAttribute("sdate").toString().equals("sdate")){out.print("Start Date must not be empty."); } %></p>
    </label>
    <label>
        <span>End Date :<sup><font color="red">*</font></sup></span>
        <input type="date" name="edate" onblur="enddate(this.value)"/>
        <p id="edate_msg"><% if(request.getAttribute("edate")!=null && request.getAttribute("edate").toString().equals("edate")){out.print("End Date must not be empty."); } %></p>
    </label>
     
     <center>
        <span>&nbsp;</span> 
        <input type="submit"  value="View" /><input type="reset"  value="reset" />  
        </center>
     <br>
    <input type="hidden" name="action" value="viewCustomer">
    
</form>
<br>
<%} %>
</div>

   
	<% if(custList!=null){		
		%>
		
		<table border="1" align="center" >
			<tr>
				<th>Customer ID</th>
				<th>Customer Name</th>
				<th>DOB</th>
				<th>Email</th>
				<th>Address</th>
				<th>Contact No.</th>
				<th>Photo Id Proof</th>
				<th>Photo Id No.</th>
				<th>Address Proof</th>
				<th>Registration Date</th>
			</tr>
		<%
		int i=1;
		for( Customer customer : custList) {
		%>
		
			<tr>
				<td><%= customer.getCustomerId()%></td>
				<td><%= customer.getCustomerName() %></td>
				<td><%= customer.getDob() %></td>
				<td><%= customer.getEmail() %></td>
				<td><%= customer.getAddress() %></td>
				<td><%= customer.getContactNo() %></td>
				<td><%= customer.getPhotoIdProof() %></td>
				<td><%= customer.getPhotoIdNo() %></td>
				<td><%= customer.getAddressProof() %></td>
				<td><%= customer.getRegistrationDate() %></td>
			</tr>
		<%
			}
		%>
		
		</table>
		  	<!-- FOR OK AND BACK BUTTON  -->
		<table align="center">
		<tr>
		<td >
		<form action ="home.jsp" class="button_style">
		<input type="submit" value="Ok"/> 
		</form>
		</td>
		<td>
		<form action="viewCustomer.jsp" class="button_style">
		<input type="submit" value="Back">
		</form>
		</td>
		</tr>
		</table><br>
		
	<%
	}
	%>
	
<jsp:directive.include file="footer.jsp" />

</body>
</html>
<%}else{%>
<script>if(window.confirm('You are not officer')) 
					  		{window.location = 'home.jsp';}
					  		else{window.location = 'home.jsp';}
					  		</script>
<noscript>You are not officer</noscript>
<% }%>