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
<title>Apply Claim</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="icon" href="images/favicon.ico" type="image/x-icon" />
<script type="text/javascript" src="js/validate.js"></script>
</head>

<body>
<% if(request.getSession(false).getAttribute("type").toString().equalsIgnoreCase("FIELD OFFICER") || request.getSession(false).getAttribute("type").toString().equalsIgnoreCase("Insurance officer"))
 { %>
<jsp:directive.include file="header.jsp" />
<jsp:directive.include file="navigation.jsp" />
<div class="content">
<br>
<form action="ClaimController" method="post" name="claimform" class="basic-grey" onsubmit="return mandatory()">
    <h1> 
        Apply Claim
    </h1> <input type="hidden" name="action" value="addClaim" />
     <label>
        <span>Policy Reference No :<sup><font color="red">*</font></sup></span>
        <input type="text" name="prefno"  onblur="policyN(this.value)"/><p id="policy_msg"><%if(request.getAttribute("pref")!=null && request.getAttribute("pref").toString().equals("pref")){out.print("Policy reference number must be in NUMBER only and not empty."); } %></p>
    </label>
    <label>
        <span>Claim Amount :<sup><font color="red">*</font></sup></span>
        <input type="text" name="camount" onblur="claimA(this.value)"/><p id="claimAmu_msg"><%if(request.getAttribute("camount")!=null && request.getAttribute("camount").toString().equals("camount")){out.print("Amount must be in NUMBER onlyand not empty."); } %></p>
    </label>
    <label>
        <span>Beneficiary Name :<sup><font color="red">*</font></sup></span>
        <input type="text" name="bname" onblur="bName(this.value)"/><p id="bname_msg"><%if(request.getAttribute("bname")!=null && request.getAttribute("bname").toString().equals("bname")){out.print("Benificiery name is not empty and Character only."); } %></p>
    </label>
     <label>
        <span>Beneficiary Address :<sup><font color="red">*</font></sup></span>
        <textarea name="baddress" onblur="bAddress(this.value)"></textarea><p id="baddress_msg"><%if(request.getAttribute("baddress")!=null && request.getAttribute("baddress").toString().equals("baddress")){out.print("Benificiery Address must not empty."); } %></p>
    </label>
    <label>
        <span>Beneficiary Email Id :<sup><font color="red">*</font></sup></span>
        <input type="text" name="bemail" onblur="bEmail(this.value)"/><p id="bemail_msg"></p>
    </label>
    <label>
        <span>Beneficiary Contact No. :<sup><font color="red">*</font></sup></span>
        <input type="text" name="bcontact" onblur="bCon(this.value)"/><p id="bcon_msg"><%if(request.getAttribute("bcontact")!=null && request.getAttribute("bcontact").toString().equals("bcontact")){out.print("Contact must be only 10 digit and not empty."); } %></p>
    </label> 
   
    <br>
    
     <center>
        <span>&nbsp;</span> 
       <input type="submit"  value="Submit" /> &nbsp; <input type="reset" value="reset"/><br>  
    </center>

    
</form>

</div>

<jsp:directive.include file="footer.jsp" />
</body>
</html>
<%}else{%>
<script>if(window.confirm('You are not Officer')) 
					  		{window.location = 'home.jsp';}
					  		else{window.location = 'home.jsp';}
					  		</script>
<noscript>You are not officer.</noscript>
<% }%>