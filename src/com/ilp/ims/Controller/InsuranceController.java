package com.ilp.ims.Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.ilp.ims.Bean.HealthInsurance;
import com.ilp.ims.Bean.Policy;
import com.ilp.ims.Bean.VehicleInsurance;
import com.ilp.ims.DAO.InsuranceDAO;
import com.ilp.ims.Service.ClaimService;
import com.ilp.ims.Service.CustomerService;
import com.ilp.ims.Service.InsuranceService;

public class InsuranceController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@SuppressWarnings("deprecation")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		InsuranceService insuranceService = new InsuranceService();
		String action = "";
		String check = "";
		if (request.getParameter("action") != null)
			action = request.getParameter("action");
		if (request.getParameter("check") != null)
			check = request.getParameter("check");
		int test = 0;
		if (request.getParameter("test") != null)
			test = Integer.parseInt(request.getParameter("test"));

		
//------------------------- add health policy------------------------------//
		
		if (action.equalsIgnoreCase("addHealthPolicy")) 
		{
			
			
			
			//--------------------------------------SERVER SIDE VALIDATION--------------------------------------//
			String policyId = request.getParameter("policyId");
			String custid = request.getParameter("custid");
			String tenure = request.getParameter("tenure");
			String psdate = request.getParameter("psdate");
			String paymentfrequency = request.getParameter("paymentfrequency");
			String pamount = request.getParameter("pamount");
			String pmode = request.getParameter("pmode");
			String numRegex = "[0-9]+";
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date startDate=null;
			Date d = null;
			boolean flagValidate = false;
			try 
			{
				startDate = format.parse(request.getParameter("psdate"));
				d=format.parse(format.format(new Date()));
			} 
			catch (ParseException e1) {
				e1.printStackTrace();
			}
			
			if (policyId == "" || policyId == null || (!policyId.matches(numRegex))) {
				request.setAttribute("policyId", "policyId");
				flagValidate = true;
			}
			if (custid == "" || custid == null || (!custid.matches(numRegex))) {
				request.setAttribute("custid", "custid");
				flagValidate = true;
			}
			if (tenure == "" || tenure == null || (!tenure.matches(numRegex))) {
				request.setAttribute("tenure", "tenure");
				flagValidate = true;
			}

			if (pmode == "" || pmode == null) {
				request.setAttribute("pmode", "pmode");
				flagValidate = true;
			}

			if (psdate == "" || psdate == null || (startDate.getTime()<d.getTime())) {
				request.setAttribute("psdate", "psdate");
				flagValidate = true;
			}
			
			if (paymentfrequency == "default") {
				request.setAttribute("paymentfrequency", "paymentfrequency");
				flagValidate = true;
			}

			if (pamount == "" || tenure == null || (!pamount.matches(numRegex))) {
				request.setAttribute("pamount", "pamount");
				flagValidate = true;
			}

			if (flagValidate) {
				RequestDispatcher rd = request
						.getRequestDispatcher("addHealthPolicy.jsp");
				rd.forward(request, response);
				return;
			}

			
			//----------------------------------------------------------ADD HEALTH POLICY METHOD-------------------------------------//
			HealthInsurance healthInsurance = new HealthInsurance();
			int i = 0;
			String type = "";
			String xa = "";
			Date endDate = null;
			boolean checkCustId = false, exists = false;
			
			try 
			{
				InsuranceDAO idao = new InsuranceDAO();
				exists = idao.checkPolicyId(Integer.parseInt(request.getParameter("custid")), Integer.parseInt(request.getParameter("policyId")));
				type = insuranceService.viewPolicyType(Integer.parseInt(request.getParameter("policyId")));
				
				if (exists==false && type != null && type.equalsIgnoreCase("health"))
				{
					CustomerService custService = new CustomerService();
					checkCustId = custService.checkCustomerId(Integer.parseInt(request.getParameter("custid")));
						
					if (checkCustId == true) 		// customer already exists
					{
						healthInsurance.setCustomerId(Integer.parseInt(request.getParameter("custid")));
						healthInsurance.setPolicyId(Integer.parseInt(request.getParameter("policyId")));
						healthInsurance.setPolicyCommencementDate(format.parse(request.getParameter("psdate")));
						startDate = format.parse(request.getParameter("psdate"));
						endDate = new Date((startDate.getYear() + Integer.parseInt(request.getParameter("tenure"))), startDate.getMonth(), startDate.getDate());
						xa = endDate.getDate() + "-" + (endDate.getMonth() + 1)	+ "-" + (endDate.getYear() + 1900);
						healthInsurance.setPolicyEndDate(endDate);
						healthInsurance.setPolicyTenure(Integer.parseInt(request.getParameter("tenure")));
						healthInsurance.setPaymentMode(request.getParameter("pmode"));
						healthInsurance.setPaymentFrequency(request.getParameter("paymentfrequency"));
						healthInsurance.setPremiumAmount(Double.parseDouble(request.getParameter("pamount")));
						i = insuranceService.registerHealthInsurancePolicy(healthInsurance);
						request.setAttribute("check", i);// FOR ERROR PAGE || FOR ADD HEALTHPOLICY PAGE || FOR CONFIRMATION PAGE
					} 
					else 
						request.setAttribute("check", -3); // INVALID CUSTOMER
				} 
				else
					request.setAttribute("check", -5); // same customer already has same policy active
				
				RequestDispatcher rd = null;
				
				if (i > 0) 
				{
					request.setAttribute("end_date", xa); // SHOWING END DATE
					request.setAttribute("page", "healthpolicy"); // COMING FROM THIS(healthpolicy) PAGE IN CONFIRMTION PAGE
					rd = request.getRequestDispatcher("DisplayAddClaim.jsp"); //CONFIRMATION PAGE
				} 
				else
					rd = request.getRequestDispatcher("addHealthPolicy.jsp");
					rd.forward(request, response);
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
		
		

//----------------------add vehicle policy------------------------------------------------------------------------------------------//
		
		
		else if (action.equalsIgnoreCase("addVehiclePolicy")) 
		{
			
			//------------------------------------------SERVER SIDE VALIDATION---------------------------------------------//
			String policyId = request.getParameter("policyId");
			String custid = request.getParameter("custid");
			String psdate = request.getParameter("psdate");
			String vehicleno = request.getParameter("vehicleno");
			String licenseno = request.getParameter("licenseno");
			String vehicletype = request.getParameter("vehicletype");
			String paymentfrequency = request.getParameter("paymentfrequency");
			String pamount = request.getParameter("pamount");
			String pmode = request.getParameter("pmode");
			String numRegex = "[0-9]+";
			String x3="/^[a-zA-Z]+[\\s-]*[a-zA-Z0-9]+$/";
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date startDate=null;
			Date d = null;
			boolean flagValidate = false;
			try 
			{
				startDate = format.parse(request.getParameter("psdate"));
				d=format.parse(format.format(new Date()));
			} 
			catch (ParseException e1) {
				e1.printStackTrace();
			}

			if (policyId == "" || policyId == null || (!policyId.matches(numRegex))) {
				request.setAttribute("policyId", "policyId");
				flagValidate = true;
			}
			if (custid == "" || custid == null || (!custid.matches(numRegex))) {
				request.setAttribute("custid", "custid");
				flagValidate = true;
			}

			if (pmode == "" || pmode == null) {
				request.setAttribute("pmode", "pmode");
				flagValidate = true;
			}
			if (vehicleno == "" || vehicleno == null || (!vehicleno.matches(x3))) {
				request.setAttribute("vehicleno", "vehicleno");
				flagValidate = true;
			}

			if (licenseno == "" || licenseno == null || (!licenseno.matches(x3))) {
				request.setAttribute("licenseno", "licenseno");
				flagValidate = true;
			}

			if (vehicletype == "default") {
				request.setAttribute("vehicletype", "vehicletype");
				flagValidate = true;
			}

			if (psdate == "" || psdate == null || (startDate.getTime()<d.getTime())) {
				request.setAttribute("psdate", "psdate");
				flagValidate = true;
			}

			if (paymentfrequency == "default") {
				request.setAttribute("paymentfrequency", "paymentfrequency");
				flagValidate = true;
			}
			if (pamount == "" || pamount == null || (!pamount.matches(numRegex))) {
				request.setAttribute("pamount", "pamount");
				flagValidate = true;
			}

			if (flagValidate) {
				RequestDispatcher rd = request.getRequestDispatcher("addVehiclePolicy.jsp");
				rd.forward(request, response);
				return;
			}

			
			
			//------------------------------------------------add vehicle Policy-----------------------------------------//
			VehicleInsurance vehicleInsurance = new VehicleInsurance();
			int i = 0;
			Date endDate = null;
			String type = "";
			String xa = "";
			boolean checkCustId = false, exists = false;
			try 
			{
				InsuranceDAO idao = new InsuranceDAO();
				exists = idao.checkPolicyId(Integer.parseInt(request.getParameter("custid")), Integer.parseInt(request.getParameter("policyId")));
				type = insuranceService.viewPolicyType(Integer.parseInt(request.getParameter("policyId")));
								
				if (exists == false && type != null && type.equalsIgnoreCase("vehicle")) 
				{
					CustomerService custService = new CustomerService();
					checkCustId = custService.checkCustomerId(Integer.parseInt(request.getParameter("custid")));
						
					if (checkCustId == true) 
					{
						vehicleInsurance.setCustomerId(Integer.parseInt(request.getParameter("custid")));
						vehicleInsurance.setPolicyId(Integer.parseInt(request.getParameter("policyId")));
						vehicleInsurance.setVehicleNo(request.getParameter("vehicleno"));
						vehicleInsurance.setLicenseNo(request.getParameter("licenseno"));
						vehicleInsurance.setVehicleType(request.getParameter("vehicletype"));
						vehicleInsurance.setPolicyCommencementDate(format.parse(request.getParameter("psdate")));
						startDate = format.parse(request.getParameter("psdate"));
						endDate = new Date(startDate.getYear() + 1,startDate.getMonth(), startDate.getDate());
						xa = endDate.getDate() + "-" + (endDate.getMonth() + 1)	+ "-" + (endDate.getYear() + 1900);
						vehicleInsurance.setPolicyEndDate(endDate);
						vehicleInsurance.setPaymentMode(request.getParameter("pmode"));
						vehicleInsurance.setPaymentFrequency(request.getParameter("paymentfrequency"));
						vehicleInsurance.setPremiumAmount(Double.parseDouble(request.getParameter("pamount")));
						i = insuranceService.registerVehicleInsurancePolicy(vehicleInsurance);
						request.setAttribute("check", i);
					} 
					else
						request.setAttribute("check", -3);
				} 
				else
					request.setAttribute("check", -2);
				
				RequestDispatcher rd = null;
				if (i > 0) 
				{
					request.setAttribute("end_date", xa);
					request.setAttribute("page", "vehiclepolicy");
					rd = request.getRequestDispatcher("DisplayAddClaim.jsp");
				}
				else
					rd = request.getRequestDispatcher("addVehiclePolicy.jsp");
					rd.forward(request, response);
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}

//------------------view policies for a customer-------------------//
		
		else if (action.equalsIgnoreCase("viewPolicies")) 
		{
			String custid = request.getParameter("custid").trim();
			String numRegex = "[0-9]+";
			boolean flagValidate = false;

			if (custid == "" || custid == null || (!custid.matches(numRegex))) {
				request.setAttribute("custid", "custid");
				flagValidate = true;
			}
			if (flagValidate) {
				RequestDispatcher rd = request
						.getRequestDispatcher("addVehiclePolicy.jsp");
				rd.forward(request, response);
				return;
			}
					
			ArrayList<VehicleInsurance> insurancelist = new ArrayList<VehicleInsurance>();
			int custId = Integer.parseInt(request.getParameter("custid").trim());
			boolean checkCustId = false;
			CustomerService custService = new CustomerService();
			
			try 
			{
				checkCustId = custService.checkCustomerId(Integer.parseInt(request.getParameter("custid").trim()));
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			if (checkCustId == true) 
			{
				try 
				{
					insurancelist = insuranceService.viewPoliciesToDelete(custId,request.getSession(false).getAttribute("type").toString());
				} catch (SQLException e) {
					e.printStackTrace();
				}

				if (insurancelist == null)
					request.setAttribute("check", -2);
				else
					request.setAttribute("check", -1);
			} 
			else
				request.setAttribute("check", -3);			//invalid customer id

			request.setAttribute("insuranceList", insurancelist);
			RequestDispatcher rs = null;
			if (check.equalsIgnoreCase("view")) 
			{
				rs = request.getRequestDispatcher("viewPolicy.jsp");
			} 
			else if (check.equalsIgnoreCase("remove")) 
			{
				rs = request.getRequestDispatcher("terminatePolicy.jsp");
			}
			
			rs.include(request, response);
		}

//------------------------- view policy for updation -------------------------//
		
		else if (action.equalsIgnoreCase("viewPolicy")) 
		{
			String policyReferenceNumber = request.getParameter("policyReferenceNo");
			String numRegex = "[0-9]+";
			boolean flagValidate = false;
			
			if(policyReferenceNumber == ""  || (!policyReferenceNumber.matches(numRegex))) 
			{
				request.setAttribute("policyReferenceNumber", policyReferenceNumber);
				flagValidate = true;
			}
			if(flagValidate) 
			{
				RequestDispatcher rd = request.getRequestDispatcher("updatePolicy.jsp");
				rd.forward(request, response);
				return;
			}
			
			VehicleInsurance vinsurance = new VehicleInsurance();
			int policyReferenceNumber1 = Integer.parseInt(request.getParameter("policyReferenceNo"));
			String type = "";
			ClaimService cs = new ClaimService();
			try 
			{
				vinsurance = insuranceService.viewPolicy(policyReferenceNumber1);
				if (vinsurance != null)
					type = cs.getPolicyType(policyReferenceNumber1);
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
			if (vinsurance == null) 
			{
				request.setAttribute("check", -2);
			} 
			else 
			{
				request.setAttribute("type", type);
				request.setAttribute("check", -1);
				if (vinsurance.getVehicleNo() == null)
					vinsurance.setVehicleNo("NA");
				if (vinsurance.getVehicleType() == null)
					vinsurance.setVehicleType("NA");
				if (vinsurance.getLicenseNo() == null)
					vinsurance.setLicenseNo("NA");
			}
			request.setAttribute("Insurance", vinsurance);
			RequestDispatcher rd = request.getRequestDispatcher("updatePolicy.jsp");
			rd.forward(request, response);
		}

//----------------------------- update policy ---------------------------//

		else if (action.equalsIgnoreCase("updatePolicy")) 
		{			
			VehicleInsurance vinsurance = new VehicleInsurance();
			int policyReferenceNumber1 = Integer.parseInt(request.getParameter("policyreferencenumber"));
			String paymentMode = request.getParameter("pmode");
			String paymentFrequency = request.getParameter("paymentfrequency");
			try 
			{
				vinsurance = insuranceService.updatePolicyDetails(policyReferenceNumber1, paymentMode, paymentFrequency);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if (vinsurance != null) 
			{
				ArrayList<VehicleInsurance> insuranceList = new ArrayList<VehicleInsurance>();
				insuranceList.add(vinsurance);
				request.setAttribute("check", -1);
				request.setAttribute("insuranceList", insuranceList);
				RequestDispatcher rd = request.getRequestDispatcher("viewPolicy.jsp");
				rd.forward(request, response);
			} 
			else 
			{
				request.setAttribute("check", -3);
				request.setAttribute("Insurance", vinsurance);
				RequestDispatcher rd = request.getRequestDispatcher("updatePolicy.jsp");
				rd.include(request, response);
			}
		}

//------------------------------ terminate policy ------------------------------//
		
		else if (action.equalsIgnoreCase("terminate")) {
			
			String[] policyIdList = request.getParameterValues("select");
			boolean[] policyTermination = new boolean[policyIdList.length];
			try {
				policyTermination = insuranceService
						.terminatePolicy(policyIdList);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			request.setAttribute("checks", policyTermination);
			request.setAttribute("check", -4);
			request.setAttribute("policyIdList", policyIdList);
			RequestDispatcher rd = request.getRequestDispatcher("terminatePolicy.jsp");
			rd.forward(request, response);
		}

//------------------------ view all policies in about us-------------------------//
		
		if (test == -1) 
		{
			ArrayList<Policy> policylist = new ArrayList<Policy>();
			try 
			{
				policylist = insuranceService.allPolicies();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			request.setAttribute("policylist", policylist);
			RequestDispatcher rs = request.getRequestDispatcher("AllPolicies.jsp");
			rs.include(request, response);
		}
	}
}