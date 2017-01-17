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
<title>Register Customer</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="icon" href="images/favicon.ico" type="image/x-icon" />
<script type="text/javascript" src="js/validateCustomer.js"></script>
</head>
<body>
<% if(request.getSession(false).getAttribute("type").toString().equalsIgnoreCase("FIELD OFFICER") || request.getSession(false).getAttribute("type").toString().equalsIgnoreCase("Insurance officer"))
 { %>
<jsp:directive.include file="header.jsp" />
<jsp:directive.include file="navigation.jsp" />

<div class="content">
<br>
<form action="CustomerController" method="post" name="customerform" class="basic-grey" onsubmit="return registrationMandatory()">
    <h1> 
        Register Customer
    </h1>
    <label>
        <span>Name :<sup><font color="red">*</font></sup></span>
        <input type="text" name="cname" onblur="customername(this.value)" placeholder="First name only" maxlength="15"/>
        <p id="cname_msg" ><%if(request.getAttribute("customerName")!=null && request.getAttribute("customerName").toString().equals("customerName")){out.print("Customer name must be valid or filled."); } %></p>
    </label>
     <label>
        <span>Date of Birth :<sup><font color="red">*</font></sup></span>
        <input type="date" name="cdob" id="cdate" onblur="customerdob(this.value)"/>
        <p id="cdob_msg"><%if(request.getAttribute("DOB")!=null && request.getAttribute("DOB").toString().equals("DOB")){out.print("Date Of Birth must be filled."); } %></p>
    </label>
    <label>
        <span>Email-ID :<sup><font color="red">*</font></sup></span>
        <input type="text" name="cemail" id="cemail" onblur="customeremail(this.value)"/>
        <p id="cemail_msg"><%if(request.getAttribute("customerEmail")!=null && request.getAttribute("customerEmail").toString().equals("customerEmail")){out.print("Email must be filled."); } %></p>
    </label>
    <label>
        <span>Address :<sup><font color="red">*</font></sup></span>
        <textarea name="caddress" onblur="customeraddress(this.value)" maxlength="150"></textarea>
        <p id="caddress_msg"><%if(request.getAttribute("address")!=null && request.getAttribute("address").toString().equals("address")){out.print("Address must be filled."); } %></p>
    </label>
     <label>
        <span>Contact No. :<sup><font color="red">*</font></sup></span>
        <input type="text" name="ccontact" id="ccontact" onblur="customercontact(this.value)" maxlength="10"/>
        <p id="ccontact_msg"><%if(request.getAttribute("cNo")!=null && request.getAttribute("cNo").toString().equals("cNo")){out.print("Contact number must be filled."); } %></p>
    </label>
    <label>
        <span>Photo ID Proof :<sup><font color="red">*</font></sup></span>
        <select name="cphotoproof" onblur="customerphotoproof(this.value)">
        <option value="default" selected>--Select--</option>
        <option value="pancard">PAN Card</option>
        <option value="passport">Passport</option>
        <option value="voterid">Voter ID Card</option>
        <option value="drivinglicence">Driving License</option>
        <option value="employeeid">Employee ID Card</option>
        <option value="bankpassbook">Bank Passbook</option>
        </select><p id="cphotoproof_msg"><%if(request.getAttribute("photoIdProof")!=null  && request.getAttribute("photoIdProof").toString().equals("photoIdProof")){out.print("Photo Id proof must be selected."); } %></p>
    </label>
    <label>
        <span>Photo ID Proof No. :<sup><font color="red">*</font></sup></span>
        <input type="text" name="cphotoproofno" id="cphotoproofno" onblur="customerphotoproofno(this.value)"/>
        <p id="cphotoproofno_msg"><%if(request.getAttribute("photoIdNo")!=null && request.getAttribute("photoIdNo").toString().equals("photoIdNo")){out.print("Photo Id number must be filled."); } %></p>
    </label>
    <label>
        <span>Address Proof :<sup><font color="red">*</font></sup></span>
        <select name="caddressproof" onblur="customeraddressproof(this.value)">
        <option value="default" selected>--Select--</option>
        <option value="passport">Passport</option>
        <option value="voterid">Voter ID Card</option>
        <option value="drivinglicence">Driving License</option>
        <option value="electricitybill">Electricity Bill</option>
        <option value="bankpassbook">Bank Passbook</option>
        </select><p id="caddressproof_msg"><%if(request.getAttribute("addressProof")!=null  && request.getAttribute("addressProof").toString().equals("addressProof")){out.print("Address Proof must be selected."); } %></p>
    </label>
    
    
    <br>
    
   <center>
        <span>&nbsp;</span> 
       <input type="submit"  value="Add" /><input type="reset"  value="Reset" />  
   </center>
    <br>
    <input type="hidden" name="action" value="addCustomer"/>
    <i><sup><font color="red">*</font></sup> fields are mandatory.</i>
</form>
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