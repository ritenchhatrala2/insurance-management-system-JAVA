<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");//HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0);
%>
<%
if(request.getSession(false).getAttribute("type")!=null)
{
%>
<jsp:forward page="home.jsp"></jsp:forward>
<%} %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>home</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="icon" href="images/favicon.ico" type="image/x-icon" />
<script type="text/javascript" src="js/validate.js"></script>
<link href="css/js-image-slider.css" rel="stylesheet" type="text/css" />
<script src="js/js-image-slider.js" type="text/javascript"></script>
<link href="css/generic.css" rel="stylesheet" type="text/css" />
<%int check1=0;
if(request.getAttribute("check")!=null)
check1=Integer.parseInt(request.getAttribute("check").toString());

%>
</head>
<body>

	<jsp:directive.include file="header.jsp" />
	<div class="content_home">
					<% if(check1==0){%>
		<br>
		<div class="row">
			<div class="home_row_1" id="sliderFrame">
				
					<div id="slider">
						<a href="Login.jsp" target="_blank"> <img src="images/image-slider-1.jpg" /></a> 
						<img src="images/banner5.jpg" />
						<img src="images/insurance-comparison.jpg"  />
						<img src="images/1.jpg" />
						
						<img src="images/4.jpg" />
						 	
							 
							
					</div>
				
			</div>
			<div class="home_row_2">
				<h2>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Login&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</h2>
				<form method="post" name="login" action="LogInController" class="home-loginForm" onsubmit="return mandatoryLogin()">
					<input name="action" type="hidden" value="login" />
					<p>
						<label>UserId<sup>&nbsp;*</sup></label> 
						<input type="text" name="userid" onblur="loginUserId(this.value)" /> <span id="username"><% if(request.getAttribute("userId")!=null && request.getAttribute("userId").toString().equals("userId")){out.print("User Id must not be empty."); } %></span>
					</p>
					<p>
						<label for="password">Password<sup>&nbsp;*</sup></label> <input
							type="password" name="password" onblur="loginPassword(this.value)"> <span id="password"><% if(request.getAttribute("pass")!=null && request.getAttribute("pass").toString().equals("pass")){out.print("Password must not be empty."); } %></span>
					</p>
					<br>
					<p>
						<input type="submit" value="Login"><!-- <a href="#" style="text-decoration:none; font-size:12px;">Forgot Your Password?</a> -->
					</p>
					
				</form>
			</div>
		</div>
<%} else if(check1==1){%>
	<script>
	alert("Invalid User");
	window.location = "Login.jsp";
	</script>
<%} %>
	</div>

	<br>

	<jsp:directive.include file="footer.jsp" />
</body>
</html>