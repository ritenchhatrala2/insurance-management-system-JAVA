<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.ilp.ims.Bean.*" %>
    <%
response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");//HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0);
if(request.getSession(false).getAttribute("type")==null){ %>
<jsp:forward page="Login.jsp" ></jsp:forward>
<% } %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Policy</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="icon" href="images/favicon.ico" type="image/x-icon" />
<script type="text/javascript"  src="js/Validate_policy.js"></script>
</head>
<body>
<% int check1=0;
if(request.getAttribute("check")!=null)
check1=Integer.parseInt(request.getAttribute("check").toString());
String type="";
if(!(request.getAttribute("type")==null))
type=request.getAttribute("type").toString();
VehicleInsurance vinsurance = new VehicleInsurance();
if(request.getAttribute("Insurance")!=null)
vinsurance=(VehicleInsurance)request.getAttribute("Insurance");%>
<jsp:directive.include file="header.jsp" />
<jsp:directive.include file="navigation.jsp" />

<div class="content">
<% if(check1==0){ %>
<br>
<form action="InsuranceController" method="post" name="viewpolicyform" class="basic-grey"  onsubmit="return update_policy_mandatory()">
    <h1> 
        Update Policy
    </h1>
    <label>
        <span>Policy Reference No. :<sup><font color="red">*</font></sup></span>
        <input type="text" name="policyReferenceNo" maxlength="4" onblur="PolicyRef(this.value)"/>
        <p id="policyReferenceNo_msg"><%if(request.getAttribute("policyReferenceNo")!=null && request.getAttribute("policyReferenceNo").toString().equals("policyReferenceNo")){out.print("Policy Reference No  must be in NUMBER only and not empty."); } %></p>
    </label>
     
     <center>
        <span>&nbsp;</span> 
        <input type="submit"  value="Update" /><input type="reset"  value="reset" />
        <input type="hidden" name="action" value="viewPolicy"/>
        <input type="hidden" name="check" value="x1"/>  
    </center>
    <br>
</form>
<br><br>
<% } else if(check1==-2){ %>
<script>if(window.confirm('Policy registration number invalid. Try again.')){
		window.location = 'updatePolicy.jsp?check=-1';}
	else{window.location = 'home.jsp';}
</script>
<% } else if(check1==-3){ %>
<script>alert('Error occurred while updating. Try again.');
	window.location = 'home.jsp';
</script>
<%} else if(check1==-1 && vinsurance!=null){ %>
<br>
<% if((request.getSession(false).getAttribute("type").toString().equalsIgnoreCase("Insurance officer")&&type.equalsIgnoreCase("health"))||(request.getSession(false).getAttribute("type").toString().equalsIgnoreCase("Field officer")&&type.equalsIgnoreCase("vehicle")))
{ %>

<form action="InsuranceController" method="post" name="updatepolicyform" class="basic-grey">
    <h1> 
        Update Policy
    </h1>
    <label>
        <span>Customer ID :<sup><font color="red">*</font></sup></span>
        <input type="text" name="custid"  value=  <%=vinsurance.getCustomerId() %> disabled/>
    </label>
     <label>
        <span>Policy Id :<sup><font color="red">*</font></sup></span>
        <input type="text" name="policyId" value= <%=vinsurance.getPolicyId()%> disabled/>
        
    </label>
    <label>
        <span>Policy Reference Number :<sup><font color="red">*</font></sup></span>
        <input type="text" name="policyreferencenumber" value= <%=vinsurance.getPolicyReferenceNumber()%> disabled/>
        
    </label>
    <label>
        <span>Vehicle No. :<sup><font color="red">*</font></sup></span>
        <input type="text" name="vehicleno" id="vehicleno" value=<%=vinsurance.getVehicleNo()%>  disabled/>
    </label>
    <label>
        <span>License No. :<sup><font color="red">*</font></sup></span>
        <input type="text" name="licenseno" id="licenseno"  value=  <%=vinsurance.getLicenseNo()%> disabled/>
        
    </label>
    <label>
        <span>Vehicle Type :<sup><font color="red">*</font></sup></span>
        <input type="text" name="vehicletype" id="vehicletype"  value= <%=vinsurance.getVehicleType() %> disabled/>
        
    </label>
    <label>
        <span>Policy Start Date :<sup><font color="red">*</font></sup></span>
        <input type="date" name="psdate" id="psdate" value= <%=vinsurance.getPolicyCommencementDate()%> disabled/>
          
    </label>
    <label>
         <span>Policy End Date :<sup><font color="red">*</font></sup></span>
	<input type="date" name="pedate" id="pedate" value=    <%=vinsurance.getPolicyEndDate()%> disabled/>
	</label>
	<label>
        <span>Payment Frequency :<sup><font color="red">*</font></sup></span>
        <select name="paymentfrequency">
        <option value="default" <%if(vinsurance.getPaymentFrequency().equalsIgnoreCase("default")) {%> 
        selected<%}%>>--Select--</option>
        <option value="monthly"  <%if(vinsurance.getPaymentFrequency().equalsIgnoreCase("monthly")) {%> 
        selected<%}%>>Monthly</option>
        <option value="quaterly" <%if(vinsurance.getPaymentFrequency().equalsIgnoreCase("quaterly")) {%> 
        selected<%}%>>Quaterly</option>
        <option value="halfyearly" <%if(vinsurance.getPaymentFrequency().equalsIgnoreCase("halfyearly")) {%> 
        selected<%}%>>Halfyearly</option>
        <option value="yearly" <%if(vinsurance.getPaymentFrequency().equalsIgnoreCase("yearly")) {%> 
        selected<%}%>>Yearly</option>
        </select>
    </label>
    <label>
        <span>Premium Amount :<sup><font color="red">*</font></sup></span>
        <input type="text" name="pamount" id="pamount"  value=<%=vinsurance.getPremiumAmount()%> disabled/>
    </label>
    <label>
        <span>Payment Mode :<sup><font color="red">*</font></sup></span>
        <input type="radio" name="pmode" value="Cash"  <%if(vinsurance.getPaymentMode().equalsIgnoreCase("Cash")) {%> 
        checked<%}%>>Cash
        <input type="radio" name="pmode" value="Card/Debit" <%if(vinsurance.getPaymentMode().equalsIgnoreCase("Card/Debit")) {%> 
        checked<%}%>>Card/Debit
        <input type="radio" name="pmode" value="Cheque"  <%if(vinsurance.getPaymentMode().equalsIgnoreCase("Cheque")) {%> 
        checked<%}%>>Cheque
        <input type="radio" name="pmode" value="Net Banking"  <%if(vinsurance.getPaymentMode().equalsIgnoreCase("Net Banking")) {%> 
        checked<%}%>>Net Banking
    </label><br> 
    <br>
    <center>
        <span>&nbsp;</span> 
        <input type="submit"  value="Update" /><input type="reset"  value="Back" onClick="window.history.back()"  />
        <input type="hidden" name="action" value="updatePolicy" /><input type="hidden" name="policyreferencenumber" value="<%=vinsurance.getPolicyReferenceNumber()%>"/>  
    </center>
    <br>
   </form>
   <% } else{ %>
	<script>
	alert('You are not officer in charge');
	window.location = 'home.jsp';
	</script>
	<noscript>You are not officer.</noscript>
   <% } %>
   <% } %>
</div>
<jsp:directive.include file="footer.jsp" />
</body>
</html>