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
<%
ArrayList<Claim> List=(ArrayList<Claim>)request.getAttribute("arraylist"); 


if(List==null)
{
%>
<form action="ClaimController" method="post" name="healthpolicyform" class="basic-grey" onsubmit="return mandatoryClaimId()">
 
  <input type="hidden" name="action" value="Open">
 <center> <input type="submit"  value="Open Claims" /></center>
  </form>
  
  <form action="ClaimController" method="post" name="healthpolicyform" class="basic-grey" onsubmit="return mandatoryClaimId()">
  <input type="hidden" name="action" value="Approved">
  <center><input type="submit"  value="Approve Claims" /></center>
  </form>
  
  <form action="ClaimController" method="post" name="healthpolicyform" class="basic-grey" onsubmit="return mandatoryClaimId()">
  <input type="hidden" name="action" value="Rejected">
  <center><input type="submit"  value="Reject Claims" /></center>
  </form>
<br><br>
<%} %>
<%
if(List!=null)
{
	String claimStatus=request.getAttribute("claimStatus").toString();	
%>

<br>
<h1 align="center"><%=(request.getAttribute("claimStatus").toString() ) %>&nbsp;Claims</h1>


<table border="1">
<tr><th>Claim Id</th>
    <th>Policy Reference number</th>
    <th>Claim Amount</th>
    <th>Claim submission Date</th>
    <th>Beneficiary Name</th>
    <th>Approve</th>
    <th>Reject</th>
</tr>
<%for(Claim c:List){
	if(c.getClaimStatus().equalsIgnoreCase(request.getAttribute("claimStatus").toString())) {
	%>

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
	
	<form action="rejectView.jsp" method="post">
   <input type="hidden" name="claimid" value="<%=c.getClaimId() %>" />
   
    <td><button onclick="display()" <%if(claimStatus.equalsIgnoreCase("rejected")){%>style="background-color: red;color:white;"<% }%>/>Reject</button></td>
   </form>
</tr>

<%} }%>
</table>
<br>




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