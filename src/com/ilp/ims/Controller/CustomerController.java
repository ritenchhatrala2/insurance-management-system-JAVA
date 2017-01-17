package com.ilp.ims.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import java.util.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ilp.ims.Bean.Customer;
import com.ilp.ims.Service.CustomerService;


public class CustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CustomerController() {
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Customer customer = new Customer();
		CustomerService customerService = new CustomerService();
		String action =request.getParameter("action");
		SimpleDateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd");
		PrintWriter out = response.getWriter();
//************************************ REGISTER CUSTOMER ******************************************
		if(action.equals("addCustomer"))
		{
			String customerName = request.getParameter("cname");
			String DOB=request.getParameter("cdob"); 
			String customerEmail = request.getParameter("cemail");
			String address= request.getParameter("caddress");
			String contactNo =request.getParameter("ccontact");		
			String photoIdProof = (request.getParameter("cphotoproof"));
			String photoIdNo =request.getParameter("cphotoproofno");
			String addressProof = request.getParameter("caddressproof");
			String alphaRegex = "[a-zA-Z]+";
			String numRegex = "[0-9]+";
			
			boolean flagValidate=false;
			
			if (customerName == "" ||  (!customerName.matches(alphaRegex)))   // server side validation for name
			{
				request.setAttribute("customerName", "customerName");
				flagValidate=true;
			}
  
			if(DOB == null || DOB =="")                                        // server side validation for DOB
			{	
				request.setAttribute("DOB", "DOB");
				flagValidate=true;
					
			}
			if(customerEmail == "")
			{
				request.setAttribute("customerEmail" , "customerEmail");
				flagValidate=true;
			}
			if(address== "" )
			{
				request.setAttribute("address", "address");
				flagValidate=true;
			}
			if(contactNo.length()!=10 || contactNo=="")
			{
				request.setAttribute("cNo", "cNo");
				flagValidate=true;
			}
			if(photoIdProof.equals("default"))
			{
				request.setAttribute("photoIdProof", "photoIdProof");
				flagValidate=true;
			}
			if(photoIdNo=="")
			{
				request.setAttribute("photoIdNo", "photoIdNo");
				flagValidate=true;
			}
			if(addressProof.equals("default"))
			{
				request.setAttribute("addressProof", "addressProof");
				flagValidate=true;
			}
			
			if(flagValidate)
			{
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/addCustomer.jsp");
				rd.forward(request, response);
			}
			
			customer.setCustomerName(customerName);
			try {
				customer.setDob(dateFormat.parse(DOB));
			} catch (ParseException e) {
				e.printStackTrace();
			}
				
			customer.setEmail(customerEmail);
			customer.setAddress(address);
			try
			{
			customer.setContactNo(Long.parseLong(request.getParameter("ccontact")));
			}
			catch(NumberFormatException e)
			{
				e.printStackTrace();
			}
			customer.setPhotoIdProof(photoIdProof);
			customer.setPhotoIdNo(photoIdNo);
			customer.setAddressProof(addressProof);
					
			int ID = 0;
			try {
				if(!customerService.checkEmail(customerEmail))
					ID = customerService.registerCustomer(customer);
			
			if(ID>0)
			{
				request.setAttribute("page", "regcust");
				request.setAttribute("check", ID);
				RequestDispatcher rd = request.getRequestDispatcher("DisplayAddClaim.jsp");
				customer.setCustomerId(ID);
				rd.include(request, response);
			}
			else{
				out.print("<script>if(window.confirm('Email already exist.'))" +
				  		"{window.location = 'addCustomer.jsp';}" +
				  		"else{window.location = 'home.jsp';}" +
				  		"</script>");
			}
			} catch (SQLException e) {
			e.printStackTrace();
			}
		}

//*************************************** UPDATE CUSTOMER **************************************************
		//-------------------------getting all data to show from textboxes ------------------------------
		
		else if (action.equals("get")) {
			
			try {
				String custId = request.getParameter("custid");
				String numRegex = "[0-9]+";
				boolean flagValidate=false;	
				System.out.println(flagValidate);
				if(custId == "" || (!custId.matches(numRegex)))
				{
					request.setAttribute("custid", "custid");
					flagValidate=true;
				}
				
				if(flagValidate)
				{

					RequestDispatcher rd = getServletContext().getRequestDispatcher("/updateCustomer.jsp");
					rd.forward(request, response);
				}
				if(customerService.checkCustomerId(Integer.parseInt(custId)))
				{
					System.out.println("inside check customerId");
					customer = customerService.getCustomerDetails(Integer.parseInt(custId));
					request.setAttribute("customer", customer);
					request.getRequestDispatcher("updateCustomer.jsp").include(request, response);

				}
				else
				{
					out.print("<script>if(window.confirm('Customer with ID: "+ request.getParameter("custid") +" does not exist.'))" +
							"{window.location = 'updateCustomer.jsp';}" +
							"else{window.location = 'home.jsp';}" +
							"</script>");
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		
		//******************************* UPDATE CUSTOMER DETAILS *************************
		
		else if(action.equals("updateCustomer"))
		{
			
			try{
			System.out.println("UPDATION");
			String customerName = request.getParameter("cname");
			String DOB=request.getParameter("cdob"); 
			String customerEmail = request.getParameter("cemail");
			String address= request.getParameter("caddress");
			String contactNo =request.getParameter("ccontact");		
			String photoIdProof = (request.getParameter("cphotoproof"));
			String photoIdNo =request.getParameter("cphotoproofno");
			String addressProof = request.getParameter("caddressproof");
			String alphaRegex = "[a-zA-Z]+";
			String numRegex = "[0-9]+";
			
			boolean flagValidate=false;
			
			if (customerName == "" ||  (!customerName.matches(alphaRegex)))   // server side validation for name
			{
				request.setAttribute("customerName", "customerName");
				flagValidate=true;
			}
  
			if(DOB == null || DOB =="")                                        // server side validation for DOB
			{	
				request.setAttribute("DOB", "DOB");
				flagValidate=true;
					
			}
			if(customerEmail == "")
			{
				request.setAttribute("customerEmail" , "customerEmail");
				flagValidate=true;
			}
			if(address== "")
			{
				request.setAttribute("address", "address");
				flagValidate=true;
			}
			if(contactNo.length()!=10 || contactNo=="" ||  (!contactNo.matches(numRegex)))
			{
				request.setAttribute("cNo", "cNo");
				flagValidate=true;
			}
			if(photoIdNo=="")
			{
				request.setAttribute("photoIdNo", "photoIdNo");
				flagValidate=true;
			}
			if(flagValidate)
			{
				System.out.println("inside server validation");
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/updateCustomer.jsp");
				rd.forward(request, response);
			}
			
			customer.setCustomerId(Integer.parseInt(request.getParameter("custid")));
			customer.setCustomerName(customerName);
			try {
				customer.setDob(dateFormat.parse(DOB));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			customer.setEmail(customerEmail);
			customer.setAddress(address);
			try
			{
			customer.setContactNo(Long.parseLong(request.getParameter("ccontact")));
			}
			catch(NumberFormatException e)
			{
				e.printStackTrace();
			}
			customer.setPhotoIdProof(photoIdProof);
			customer.setPhotoIdNo(photoIdNo);
			customer.setAddressProof(addressProof);
			
			if(customerService.updateCustomerDetails(customer))
			  {
				System.out.println("Updation Successful");
				out.print("<script>if(window.confirm('Details of "+ customerName +" updated successful.'))" +
				  		"{window.location = 'home.jsp';}" +
				  		"else{window.location = 'home.jsp';}" +
				  		"</script>");
				
			  }
			else{
				out.print("<script>if(window.confirm('Error in updation.'))" +
				  		"{window.location = 'home.jsp';}" +
				  		"else{window.location = 'home.jsp';}" +
				  		"</script>");
			   }
			}
			catch(Exception ex)
			  {
				System.out.println(ex.getMessage());
			  }
	}
		
		
//***************************** VIEW CUSTOMER DETAILS BY DATE *****************************
		
		else if (action.equals("viewCustomer")) 
		{
			ArrayList<Customer> custList = new ArrayList<Customer>();
			String sDate = request.getParameter("sdate");
			String eDate = request.getParameter("edate");
			boolean flagValidate=false;	

			if(sDate == "" || sDate == null)
			{
				request.setAttribute("sdate", "sdate");
				flagValidate=true;
			}

			if(eDate == "" || eDate == null)
			{
				request.setAttribute("edate", "edate");
				flagValidate=true;
			}

			if(flagValidate)
			{

				RequestDispatcher rd = getServletContext().getRequestDispatcher("/viewCustomer.jsp");
				rd.forward(request, response);
			}
			try {
				Date startDate = dateFormat.parse(sDate);
				Date endDate = dateFormat.parse(eDate);

				custList = customerService.viewCustomerDetails(startDate, endDate);
				if(custList.size()>0)
				{
					request.setAttribute("custList", custList);
					RequestDispatcher rd = request.getRequestDispatcher("viewCustomer.jsp");
					rd.include(request, response);
				}
				else
				{
					out.print("<script>if(window.confirm('No customers between given registration dates!!!'))" +
							"{window.location = 'viewCustomer.jsp';}" +
							"else{window.location = 'home.jsp';}" +
							"</script>");
				}
			}
			catch(Exception ex)
			{
				System.out.println(ex.getMessage());
			} 
		}



		//***************************** REMOVE CUTOMER DETAILS BY ID ******************************



		else if(action.equals("removeCustomer"))
		{

			String custId = request.getParameter("custid");
			String numRegex = "[0-9]+";
			boolean flagValidate=false;	
			System.out.println(flagValidate);
			if(custId == "" || (!custId.matches(numRegex)))
			{
				request.setAttribute("custid", "custid");
				flagValidate=true;
			}

			if(flagValidate)
			{

				RequestDispatcher rd = getServletContext().getRequestDispatcher("/removeCustomer.jsp");
				rd.forward(request, response);
			}
			try {
				if(customerService.checkCustomerId(Integer.parseInt(custId)))
				{
					if(customerService.checkPolicyStatus(Integer.parseInt(custId)))
					{
						if(customerService.removeCustomer(Integer.parseInt(custId)))
						{							
							out.print("<script>if(window.confirm('Customer with ID: "+ request.getParameter("custid") +" deleted.'))" +
									"{window.location = 'home.jsp';}" +
									"else{window.location = 'home.jsp';}" +
									"</script>");
						}
					}
					else
					{
						out.print("<script>if(window.confirm('Customer with ID: "+ request.getParameter("custid") +" may have an ACTIVE POLICY.'))" +
								"{window.location = 'removeCustomer.jsp';}" +
								"else{window.location = 'home.jsp';}" +
								"</script>");
					}
				}
				else
				{
					out.print("<script>if(window.confirm('Customer with ID: "+ request.getParameter("custid") +" does not exist.'))" +
							"{window.location = 'removeCustomer.jsp';}" +
							"else{window.location = 'home.jsp';}" +
							"</script>");
				}
			} catch (SQLException e) {			
				e.printStackTrace();
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}		

		}
	}
}