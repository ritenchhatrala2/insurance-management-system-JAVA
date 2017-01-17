<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.ilp.ims.Bean.*,com.ilp.ims.Service.*"%>
 
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
<% } %>
     <% if(request.getSession(false).getAttribute("type").toString().equalsIgnoreCase("FIELD OFFICER") || request.getSession(false).getAttribute("type").toString().equalsIgnoreCase("Insurance officer"))
        { %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cancle Claim</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="icon" href="images/favicon.ico" type="image/x-icon" />
<script type="text/javascript" src="js/validate.js"></script>
</head>
<body>

<jsp:directive.include file="header.jsp" />
<jsp:directive.include file="navigation.jsp" />

<div class="content">
<br>
<form action="ClaimController" method="post" name="claimform" class="basic-grey" onsubmit="return mandatoryClaimId()">
    <h1> 
        Cancel Claim
    </h1>
    <label>
        <span>Claim ID :<sup><font color="red">*</font></sup></span>
        <input type="text" name="claimid" onblur="claimId(this.value)"/><p id="claim_Msg" ><%if(request.getAttribute("Id")!=null && request.getAttribute("Id").toString().equals("Id")){out.print("Claim Id must be in NUMBER only and not empty."); } %> </p>
        <input type="hidden" name="action" value="cancleClaim" />
    </label>
    <br>
  <center>
        <span>&nbsp;</span> 
        <input type="submit"  value="Delete" /><input type="reset"  value="reset" />  
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
<noscript><h1>You are not Officer.</</h1></noscript>
<% }%>