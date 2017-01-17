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
<% } %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Health Policy</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="icon" href="images/favicon.ico" type="image/x-icon" />
<script type="text/javascript" src="js/Validate_policy.js" ></script>
</head>
<body>


<% if(request.getSession(false).getAttribute("type").toString().equalsIgnoreCase("Insurance officer"))
 { %>
<jsp:directive.include file="header.jsp" />
<jsp:directive.include file="navigation.jsp" />

<%int check1=0;
if(request.getAttribute("check")!=null)
check1=Integer.parseInt(request.getAttribute("check").toString());
%>
<div class="content">
<br>
<%if(check1==0){ %>
<form action="InsuranceController" method="post" name="healthpolicyform" class="basic-grey" onsubmit="return health_register_mandatory()">
    <h1> 
        Add Health Policy
    </h1>
    <label>
        <span>Customer ID :<sup><font color="red">*</font></sup></span>
        <input type="text" name="custid" maxlength="6" onblur="custId(this.value)"/><p id="custid_msg">
        <%if(request.getAttribute("custid")!=null && request.getAttribute("custid").toString().equals("custid")){out.print("Customer Id  must be in NUMBER only and not empty."); } %></p>
        
    </label>
     <label>
        <span>Policy Id :<sup><font color="red">*</font></sup></span>
        <input type="text" name="policyId" maxlength="4" onblur="PolicyId(this.value)"/><p id="policyId_msg">
        <%if(request.getAttribute("policyId")!=null && request.getAttribute("policyId").toString().equals("policyId")){out.print("Policy Id  must be in NUMBER only and not empty."); } %></p>
         
    </label>
    <label>
        <span>Policy Tenure :<sup><font color="red">*</font></sup></span>
        <input type="text" name="tenure" id="tenure" maxlength="2" onblur="Tenure(this.value)" placeholder="In years only"/>
            <p id="tenure_msg"><% if(request.getAttribute("tenure")!=null && request.getAttribute("tenure").toString().equals("tenure")){out.print("tenure must not be empty."); } %></p>
    </label>
    
    <label>
        <span>Policy Start Date :<sup><font color="red">*</font></sup></span>
        <input type="date" name="psdate" id="psdate"  onblur="psDate(this.value)"/>
         <p id="psdate_msg"><% if(request.getAttribute("psdate")!=null && request.getAttribute("psdate").toString().equals("psdate")){out.print("Start Date must be valid."); } %></p>
           
    </label>
    <label>
        <span>Payment Frequency :<sup><font color="red">*</font></sup></span>
        <select name="paymentfrequency" onblur="PaymentFrequency(this.value)">
        <option value="default" selected>--Select--</option>
        <option value="monthly">Monthly</option>
        <option value="quaterly">Quaterly</option>
        <option value="halfyearly">Halfyearly</option>
        <option value="yearly">Yearly</option>
        </select>
   		<p id="paymentfrequency_msg"><% if(request.getAttribute("paymentfrequency")!=null && request.getAttribute("paymentfrequency").toString().equals("paymentfrequency")){out.print("Payment Frequency must not be empty."); } %></p>
    </label>
    
    <label>
        <span>Premium Amount :<sup><font color="red">*</font></sup></span>
        <input type="text" name="pamount" id="pamount" maxlength="8" onblur="Premium(this.value)" />
        <p id="pamount_msg"><% if(request.getAttribute("pamount")!=null && request.getAttribute("pamount").toString().equals("pamount")){out.print("Premium amount must not be empty."); } %></p>
    </label>
    <label>
        <span>Payment Mode :<sup><font color="red">*</font></sup></span>
        <input type="radio" name="pmode" value="Cash" >Cash
        <input type="radio" name="pmode" value="Card/Debit">Card/Debit
        <input type="radio" name="pmode" value="Cheque">Cheque
        <input type="radio" name="pmode" value="Net Banking">Net Banking
   	</label><br><p id="pmode_msg"></p>
    <br>
     <center>
        <span>&nbsp;</span> 
        <input type="submit"  value="Add" /><input type="reset"  value="reset" /><input type="hidden" name="action" value="addHealthPolicy" />  
    </center>
    <br>
</form>
<% } else if(check1==-2){ %>
<script>alert('Error in registration. Please try again.');
window.location = 'home.jsp';
</script>
<% } else if(check1==-5){ %>
<script>alert('Customer has Active policy with this policy id. Please try again.');
window.location = 'home.jsp';
</script>
<% } else if(check1==-3){ %>
<script>if(window.confirm('Invalid customer Id. Enter proper customer id.')){
		window.location = 'addHealthPolicy.jsp?check=-1';}
	else{window.location = 'home.jsp';}
</script>
<%} %>
</div>
<jsp:directive.include file="footer.jsp" />
</body>
</html>
<%} else {%>
<script>alert('You are not Insurance Officer');
	window.location = 'home.jsp';
</script>
<noscript>You are not officer.</noscript>
<% } %>