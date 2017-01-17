<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Header</title>
<link rel="icon" href="images/favicon.ico" type="image/x-icon" />
</head>
<body>
<div class="site-header">
	
			<div class="imgdiv">
			<img alt="Logo" src="images/InLogo.png">
			</div>
		 
			<div class="logout">
			<%if(request.getSession(false).getAttribute("type")!=null){ %>
			<form method="post" action="LogInController">
			<button type="submit" class="exit-btn exit-btn-1">LogOut</button>
			<input name="action" type="hidden" value="logout"/>
			</form>
			<%} %>
			</div>
	
	
</div>
</body>
</html>