 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.ilp.ims.Bean.*,com.ilp.ims.Service.*,java.util.*"%>
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
  <% if(request.getSession(false).getAttribute("type").toString().equalsIgnoreCase("ADMIN"))
   { %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Approve/Reject Claim</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="icon" href="images/favicon.ico" type="image/x-icon" />
<script type="text/javascript" src="js/validate.js"></script>
<script>
function display()
{
	document.getElementById("disp").style.display = "block";
}

function yourFunction()
{
	document.getElementById("disp").style.display = "none";
	return false;
}

</script>
</head>
<body>

<jsp:directive.include file="header.jsp" />
<jsp:directive.include file="navigation.jsp" />

<div class="content">
<br>

<form action="ClaimController"   class="basic-grey" method="post" onsubmit="return mandatory()">
<h1> 
        Reason For Rejection  
    </h1>
    
        <input type="hidden" name="claimid" value="<%= request.getParameter("claimid")%>"/>
    
    <label>
        <span>Reason :<sup><font color="red">*</font></sup></span>
        <textarea name="reason"></textarea>
    </label>
    <br>
    <center>
        <span>&nbsp;</span> <input type="hidden" name="action" value="rejectClaim" />
        <input type="submit"  value="Reject" /><input type="reset"  value="reset" />  
  	</center>
    <br>
</form>

</div>
<jsp:directive.include file="footer.jsp" />
</body>
</html>
<%}else{%>
<script>if(window.confirm('You are not Admin')) 
					  		{window.location = 'home.jsp';}
					  		else{window.location = 'home.jsp';}
					  		</script>
	<noscript><h1>You are not admin.</h1></noscript>
	
<% }%>