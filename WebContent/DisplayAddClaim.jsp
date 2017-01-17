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
<title>Display claim Id</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="icon" href="images/favicon.ico" type="image/x-icon" />
<script type="text/javascript" src="js/validate.js"></script>
</head>
<body>
<%String confirm="";
if(request.getAttribute("page")!=null)
confirm=request.getAttribute("page").toString(); %>
<jsp:directive.include file="header.jsp" />
<jsp:directive.include file="navigation.jsp" />
<div class="display">
<br>
<p>
<%if("healthpolicy".equalsIgnoreCase(confirm)) { %>
Policy registered successfully.<br>Policy reference number is <%=request.getAttribute("check") %>.<br>Policy end date is <%=request.getAttribute("end_date").toString() %>.
<%} else if("vehiclepolicy".equalsIgnoreCase(confirm)){ %>
Policy registered successfully.<br>Policy reference number is <%=request.getAttribute("check") %>.<br>Policy end date is <%=request.getAttribute("end_date").toString() %>.
<%} else if("regcust".equalsIgnoreCase(confirm)){ %>
Customer registered successfully.<br>Customer id is <%=request.getAttribute("check") %>.
<%} else if("applyclaim".equalsIgnoreCase(confirm)){ %>
Claim registered successfully.<br>Claim id is <%=request.getAttribute("check") %>.<%} %>
</p>
<center>
<a href="home.jsp"><button class="button_style_confirm">Ok</button></a>
</center>
</div>
<jsp:directive.include file="footer.jsp" />
</body>
</html>