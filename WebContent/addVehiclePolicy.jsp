<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page  import= "java.util.Date, java.text.SimpleDateFormat" %>
<%
response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");//HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0);
%>
<% if(request.getSession(false).getAttribute("type")==null) { %>
<jsp:forward page="Login.jsp" ></jsp:forward>
<% } %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Vehicle Policy</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="icon" href="images/favicon.ico" type="image/x-icon" />
<script type="text/javascript" src="js/Validate_policy.js"></script>
</head>
<body>
<% if(request.getSession(false).getAttribute("type").toString().equalsIgnoreCase("Field officer")) { %>
<jsp:directive.include file="header.jsp" />
<jsp:directive.include file="navigation.jsp" />

<% int check1=0;
if(request.getAttribute("check")!=null)
check1 = Integer.parseInt(request.getAttribute("check").toString());
%>
<div class="content">
<br>
<%if(check1==0){ %>
<form action="InsuranceController" method="post" name="vehiclepolicyform" class="basic-grey" onsubmit="return vehicle_register_mandatory()">
    <h1> 
        Add Vehicle Policy
    </h1>
    <label>
        <span>Customer ID :<sup><font color="red">*</font></sup></span>
        <input type="text" name="custid" maxlength="6" onblur="custId(this.value)"/>
        <p id="custid_msg"> <%if(request.getAttribute("custid")!=null && request.getAttribute("custid").toString().equals("custid")){out.print("Customer Id  must be in NUMBER only and not empty."); } %></p>
    </label>
     <label>
        <span>Policy Id :<sup><font color="red">*</font></sup></span>
        <input type="text" name="policyId" maxlength="4" onblur="pId(this.value)"/>
        <p id="policyId_msg"><%if(request.getAttribute("policyId")!=null && request.getAttribute("policyId").toString().equals("policyId")){out.print("Policy Id  must be in NUMBER only and not empty."); } %></p>
    </label>
    <label>
        <span>Vehicle No. :<sup><font color="red">*</font></sup></span>
        <input type="text" name="vehicleno" id="vehicleno" maxlength="10" onblur="vno(this.value)"/>
        <p id="vehicleno_msg"><% if(request.getAttribute("vehicleno")!=null && request.getAttribute("vehicleno").toString().equals("vehicleno")){out.print("Vehicle number  must not be empty."); } %></p>
    </label>
    <label>
        <span>License No. :<sup><font color="red">*</font></sup></span>
        <input type="text" name="licenseno" id="licenseno" maxlength="15" onblur="lno(this.value)"/>
        <p id="licenseno_msg"><% if(request.getAttribute("licenseno")!=null && request.getAttribute("licenseno").toString().equals("licenseno")){out.print("License number  must not be empty."); } %></p>
    </label>
    <label>
        <span>Vehicle Type :<sup><font color="red">*</font></sup></span>
        <select name="vehicletype" onBlur="vtype(this.value)">
        <option value="default" selected>--Select--</option>
        <option value="Car">Car</option>
        <option value="Bike">Bike</option>
        <option value="Cycle">Cycle</option>
        </select>
        <p id="vehicletype_msg"><% if(request.getAttribute("vehicletype")!=null && request.getAttribute("vehicletype").toString().equals("vehicletype")){out.print("Vehicle type must not be empty."); } %></p> 
    </label>
    <label>
        <span>Policy Start Date :<sup><font color="red">*</font></sup></span>
        <input type="date" name="psdate" id="psdate" onblur="psDate(this.value)"/>
        <p id="psdate_msg"><% if(request.getAttribute("psdate")!=null && request.getAttribute("psdate").toString().equals("psdate")){out.print("Start Date must not be empty."); } %></p>
    </label>
    
    <label>
        <span>Payment Frequency :<sup><font color="red">*</font></sup></span>
        <select name="paymentfrequency" onBlur="pfrncy(this.value)">
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
        <input type="text" name="pamount" id="pamount" maxlength="8" onblur="pAMOUNT(this.value)"/>
        <p id="pamount_msg"><% if(request.getAttribute("pamount")!=null && request.getAttribute("pamount").toString().equals("pamount")){out.print("Premium amount must not be empty."); } %></p>
    </label>
    
    <label>
        <span>Payment Mode :<sup><font color="red">*</font></sup></span>
        <input type="radio" name="pmode" value="Cash">Cash
        <input type="radio" name="pmode" value="Card/Debit">Card/Debit
        <input type="radio" name="pmode" value="Cheque">Cheque
        <input type="radio" name="pmode" value="Net Banking">Net Banking
    </label><br><p id="pmode_msg"></p> 
    <br>

    <center>
     <span>&nbsp;</span> 
     <input type="submit"  value="Add" /><input type="reset"  value="reset" /><input type="hidden" name="action" value="addVehiclePolicy" />  
      </center>

    
    

    <br>
</form>
<% } else if(check1==-2){ %>
<script>alert('Error in registration. Please try again.');
window.location = 'home.jsp';
</script>
<% } else if(check1==-3){ %>
<script>if(window.confirm('Invalid customer Id. Enter proper customer id.')){
		window.location = 'addVehiclePolicy.jsp?check=-1';}
	else{window.location = 'home.jsp';}
</script>
<%} %>
</div>
<jsp:directive.include file="footer.jsp" />
</body>
</html>
<%} else {%>
	<script>
	alert('You are not Field Officer.');
	window.location = 'home.jsp';
	</script>
	<noscript>You are not officer.</noscript>
<% } %>