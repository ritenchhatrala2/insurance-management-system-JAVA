<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.ilp.ims.Bean.*,java.util.ArrayList"%>
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
<title>About Us</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="icon" href="images/favicon.ico" type="image/x-icon" />
<script type="text/javascript" src="js/validate.js"></script>

</head>
<body>
<jsp:directive.include file="header.jsp" />
<jsp:directive.include file="navigation.jsp" />
<div class="content_about">

<h1>About Us</h1>
INDIA ASSURANCE CO.LTD  helps you compare Financial Products like Life Insurance, General Insurance, Loans and Credit Cards. To enable easy and sensible comparison, we have tied up with most of the Financial Institutions disbursing Loans and selling Insurance in India. This helps you get free insurance quotes and you can compare plans based on multiple features; we help you select the most suitable plan for your needs. This is your one stop platform for comparing Loans and Insurance in India.

Insurance can be divided into two categories of Life and General Insurance. In Life Insurance you can compare Term Insurance, Pension Plans or Retirement Plans, Child Plans and Investment Plans; both Endowment and Unit Linked Plans. In General Insurance you can compare Health Insurance or Mediclaim Plans, Car Insurance, 2 Wheeler Insurance, Travel Insurance as well as Home Insurance.

Compare Financial Services in India

Along with Insurance Products, we also lets you compare Personal Loans and Home Loans since we have tie-ups with most of the Financial Institution in India. We offer end-to-end services right from Free Loans & Insurance Quotes, to Application to Disbursal of Loans.

We strongly suggest that you compare insurance plans and loan products before choosing any particular plan. Every time you see our analysis and compare insurance plans on the basis of price, services, duration, etc you get a better understanding of what is being offered and which of the offers are most suitable for you.

This brings to its customers unbiased comparison of financial services from all major insurance companies and banks. Our focus on providing online systems and integrations help you directly link to insurance companies & banks; which in turn results in large amounts of saving while taking an insurance plan or applying for a loan. We are the one of the largest insurance and loans destination in the country and our proficiency in financial services helps our customers make balanced and beneficial financial decisions.

<br><br>

Move Ahead.... Your Financial Security is just a few clicks away!!
</div>

<br>

<jsp:directive.include file="footer.jsp" />
</body>
</html>