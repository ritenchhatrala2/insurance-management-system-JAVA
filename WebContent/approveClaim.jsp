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
<%
Claim c=(Claim)request.getAttribute("claimObj"); 


if(c==null)
{
%>
<form action="ClaimController" method="post" name="healthpolicyform" class="basic-grey" onsubmit="return mandatoryClaimId()">
    <h1> 
        Approve/Reject Claim
    </h1>
    <label>
        <span>Claim ID :<sup><font color="red">*</font></sup></span>
        <input type="text" name="claimid"/><p id="claim_Msg" onblur="claimId(this.value)" ><%if(request.getAttribute("Id")!=null && request.getAttribute("Id").toString().equals("Id")){out.print("Claim Id must be in NUMBER only and not empty."); } %> </p>
        <input type="hidden" name="action" value="ArClaimDetails" />
    </label>
    <br>
     <center>
        <span>&nbsp;</span> 
        <input type="submit" value="Approve/Reject" /><input type="reset"  value="reset" />  
    </center>
    <br>
    
</form>
<br><br>
<%} %>
<%
if(c!=null)
{
	String claimStatus=request.getAttribute("claimStatus").toString();	
%>

<br>

<table border="1">
<tr><th>Claim Id</th>
    <th>Policy Reference number</th>
    <th>Claim Amount</th>
    <th>Claim submission Date</th>
    <th>Beneficiary Name</th>
    <th>Approve</th>
    <th>Reject</th>
</tr>
<tr><td><%=c.getClaimId() %></td>
   <td><%=c.getPolicyReferenceId() %></td>
   <td><%=c.getClaimAmount() %></td>
   <td><%=c.getClaimSubmissionDate() %></td>
   <td><%=c.getBeneficiaryName()%></td>
   <form action="ClaimController" method="post" onsubmit="" >
   <input type="hidden" name="claimid" value="<%=c.getClaimId() %>" />
  
   <td><input type="submit" value="Approve" <%if(claimStatus.equalsIgnoreCase("approved")){%>style="background-color: green;color:white;"<% }%>/></td>
   	<input type="hidden" name="action" value="approveClaim" />
	</form>
    <td><button onclick="display()" <%if(claimStatus.equalsIgnoreCase("rejected")){%>style="background-color: green;color:white;"<% }%>/>Reject</button></td>
   
</tr>
</table>
<br>


<input type="hidden" name="action" value="approveClaim" />
</form>

<div id="disp">


<form action="ClaimController"   class="basic-grey" method="post" onsubmit="return mandatory()">
<h1> 
        Reason For Rejection  <button onClick="return yourFunction()" class="closebutton">Close</button>
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
<%} %>
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