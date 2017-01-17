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
<title>Remove Customer</title>
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
<form action="CustomerController" method="post" name="customerIdform" class="basic-grey" onsubmit="return idMandatory()">
    <h1> 
        Remove Customer
    </h1>
    <label>
        <span>Customer ID :<sup><font color="red">*</font></sup></span>
        <input type="text" name="custid" onblur="customerid(this.value)"/>
        <p id="cid_msg"><% if(request.getAttribute("custid")!=null && request.getAttribute("custid").toString().equals("custid")){out.print("Customer Id must be in number format and filled."); } %></p>
    </label>
    
     <center>
        <span>&nbsp;</span> 
        <input type="submit"  value="Remove" /><input type="reset"  value="Reset" />  
        <input type="hidden" name= "action" value="removeCustomer"/>
   </center>
    <br>
    
</form>
</div>
<br><br>

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