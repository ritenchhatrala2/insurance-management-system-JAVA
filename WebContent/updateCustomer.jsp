<%@page import="com.ilp.ims.Bean.*"%>
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
<title>Update Customer</title>
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
<br><%
Customer customer=(Customer)request.getAttribute("customer");
//int checking = (Integer) request.getAttribute("check");
if(customer==null)
{
%>
<form action="CustomerController" method="post" name="customerIdform" class="basic-grey" onsubmit="return idMandatory()">
    <h1> 
        Update Customer
    </h1>
    <label>
        <span>Customer ID :<sup><font color="red">*</font></sup></span>
        <input type="text" name="custid" onblur="customerid(this.value)" maxlength="6"/>
        <p id="cid_msg"><% if(request.getAttribute("custid")!=null && request.getAttribute("custid").toString().equals("custid")){out.print("Customer Id must be valid and filled."); } %></p>
    </label>
     
    <center>
        <span>&nbsp;</span> 
        <input type="submit" value="Submit" />&nbsp;&nbsp;<input type="reset" value="Reset" />  
   </center>
    <br>
    <input type="hidden" name="action" value="get"/>
</form>
<br><br><br>
<%} %>
<% 
if(customer!=null)
{
%>
<br>
<form action="CustomerController" method="post" name="customerform" class="basic-grey" onsubmit="return registrationMandatory()">
    <h1> 
        Update Customer
    </h1>
    <input type="hidden" name="custid" value="<%=customer.getCustomerId() %>">
    <label>
        <span>Name :<sup><font color="red">*</font></sup></span>
        <input type="text" name="cname" value="<%=customer.getCustomerName() %>" onblur="customername(this.value)"/>
        <p id="cname_msg"><%if(request.getAttribute("customerName")!=null && request.getAttribute("customerName").toString().equals("customerName")){out.print("Customer name must be valid and filled."); } %></p>
    </label>
    <label>
        <span>Date of Birth :<sup><font color="red">*</font></sup></span>
        <input type="date" name="cdob" value="<%=customer.getDob() %>" onblur="customerdob(this.value)"/>
        <p id="cdob_msg"><%if(request.getAttribute("DOB")!=null && request.getAttribute("DOB").toString().equals("DOB")){out.print("Date Of Birth must be filled."); } %></p>
    </label>
    <label>
        <span>Email-ID :<sup><font color="red">*</font></sup></span>
        <input type="text" name="cemail" id="cemail" value="<%=customer.getEmail() %>" onblur="customeremail(this.value)"/>
        <p id="cemail_msg"><%if(request.getAttribute("customerEmail")!=null && request.getAttribute("customerEmail").toString().equals("customerEmail")){out.print("Email must be filled."); } %></p>
    </label>
    <label>
        <span>Address :<sup><font color="red">*</font></sup></span>
        <textarea name="caddress" onblur="customeraddress(this.value)" maxlength="150"> <%=customer.getAddress()%></textarea>
        <p id="caddress_msg"><%if(request.getAttribute("address")!=null && request.getAttribute("address").toString().equals("address")){out.print("Address must be filled."); } %></p>
    </label>
    <label>
        <span>Contact No. :<sup><font color="red">*</font></sup></span>
        <input type="text" name="ccontact" id="ccontact" value="<%=customer.getContactNo()%>" onblur="customercontact(this.value)"/>
        <p id="ccontact_msg"><%if(request.getAttribute("cNo")!=null && request.getAttribute("cNo").toString().equals("cNo")){out.print("Contact numeber must be filled."); } %></p>
    </label>
    <label>
        <span>Photo ID Proof :<sup><font color="red">*</font></sup></span>
        <select name="cphotoproof" onblur="customerphotoproof(this.value)">
        	<option value="pancard" <%if(customer.getPhotoIdProof().equalsIgnoreCase("pancard")) {%>selected<%} %>>PAN Card</option>
        	<option value="passport" <%if(customer.getPhotoIdProof().equalsIgnoreCase("passport")) {%>selected<%} %>>Passport</option>
        	<option value="voterid" <%if(customer.getPhotoIdProof().equalsIgnoreCase("voterid")) {%>selected<%} %>>Voter ID Card</option>
        	<option value="drivinglicence" <%if(customer.getPhotoIdProof().equalsIgnoreCase("drivinglicence")) {%>selected<%} %>>Driving License</option>
        	<option value="employeeid" <%if(customer.getPhotoIdProof().equalsIgnoreCase("employeeid")) {%>selected<%} %>>Employee ID Card</option>
        	<option value="bankpassbook" <%if(customer.getPhotoIdProof().equalsIgnoreCase("bankpassbook")) {%>selected<%} %>>Bank Passbook</option>
        </select><p id="cphotoproof_msg"><%if(request.getAttribute("photoIdProof")!=null  && request.getAttribute("photoIdProof").toString().equals("photoIdProof")){out.print("Photo Id proof must be selected."); } %></p>
    </label>
    <label>
        <span>Photo ID Proof No. :<sup><font color="red">*</font></sup></span>
        <input type="text" name="cphotoproofno" id="cphotoproofno" value="<%=customer.getPhotoIdNo()%>" onblur="customerphotoproofno(this.value)"/>
        <p id="cphotoproofno_msg"><%if(request.getAttribute("photoIdNo")!=null && request.getAttribute("photoIdNo").toString().equals("photoIdNo")){out.print("Photo Id number must be filled."); } %></p>
    </label>
    <label>
        <span>Address Proof :<sup><font color="red">*</font></sup></span>
        <select name="caddressproof" onblur="customeraddressproof(this.value)">
        <option value="passport" <%if(customer.getAddressProof().equalsIgnoreCase("passport")) {%>selected<%} %>>Passport</option>
        <option value="voterid" <%if(customer.getAddressProof().equalsIgnoreCase("voterid")) {%>selected<%} %>>Voter ID Card</option>
        <option value="drivinglicence" <%if(customer.getAddressProof().equalsIgnoreCase("drivinglicence")) {%>selected<%} %>>Driving License</option>
        <option value="electricitybill" <%if(customer.getAddressProof().equalsIgnoreCase("electricitybill")) {%>selected<%} %>>Electricity Bill</option>
        <option value="bankpassbook" <%if(customer.getAddressProof().equalsIgnoreCase("bankpassbook")) {%>selected<%} %>>Bank Passbook</option>
        </select><p id="caddressproof_msg"><%if(request.getAttribute("addressProof")!=null  && request.getAttribute("addressProof").toString().equals("addressProof")){out.print("Address Proof must be selected."); } %></p>
    </label>
    <br>   
   <center>
        <span>&nbsp;</span> 
        <input type="submit"  value="Update" /><input type="reset"  value="Reset" />  
   </center>
    <input type="hidden" name="action" value="updateCustomer"/>
    </center>
    <br>
</form>



<%} %>
</div>

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