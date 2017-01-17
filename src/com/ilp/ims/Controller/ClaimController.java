package com.ilp.ims.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.ilp.ims.Bean.Claim;
import com.ilp.ims.Service.ClaimService;


public class ClaimController extends HttpServlet {

   
    private static final long serialVersionUID = 1L;


	public ClaimController() {
       
    }

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		ClaimService cs=new ClaimService();
		PrintWriter out=response.getWriter();
		String action=request.getParameter("action");
		
		
	/*=========================================================================
	 * 			add claim to database
	 * ========================================================================*/
		if(action.equals("addClaim"))
		{
			
			String policyRef=request.getParameter("prefno");
			String camount=request.getParameter("camount");
			String bname=request.getParameter("bname");
			String baddress=request.getParameter("baddress");
			String bemail=request.getParameter("bemail");
			String bcontact=request.getParameter("bcontact");
			String type="";
			String numRegex = "[0-9]+";
			
			boolean flagValidate=false;
			
			if(policyRef=="" || (!policyRef.matches(numRegex)))
			{
				request.setAttribute("pref", "pref");
				flagValidate=true;
			}
			if(camount=="")
			{
				request.setAttribute("camount", "camount");
				flagValidate=true;
			}
			if(bname=="" || (!bname.matches("[a-zA-Z]+\\s*[a-zA-Z]*")))
			{
				request.setAttribute("bname", "bname");
				flagValidate=true;
			}
			if(baddress=="")
			{
				request.setAttribute("baddress", "baddress");
				flagValidate=true;
			}
			if(bemail=="")
			{
				request.setAttribute("bemail", "bemail");
				flagValidate=true;
			}
			if(bcontact=="" || (bcontact.length()!=10))
			{
				request.setAttribute("bcontact", "bcontact");
				flagValidate=true;
			}
			
			if(flagValidate)
			{
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/applyClaim.jsp");
				rd.forward(request, response);
				return;
			}
			
			try {
				type=cs.getPolicyType(Integer.parseInt(request.getParameter("prefno")));
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			String utype=request.getSession(false).getAttribute("type").toString();
			
			
			
			try {
				if(cs.checkExistclaimService(Integer.parseInt(request.getParameter("prefno"))))
				{
				if(cs.checkPolicyRefNoService(Integer.parseInt(request.getParameter("prefno"))))        //---->check policy reference number in database
				{
					//check user type(health,field,admin)---
				if((type.equalsIgnoreCase("health") && utype.equalsIgnoreCase("INSURANCE OFFICER")) || (type.equalsIgnoreCase("VEHICLE") && utype.equalsIgnoreCase("FIELD OFFICER")) )
				{
				Claim claim=new Claim();
				claim.setPolicyReferenceId(Integer.parseInt(request.getParameter("prefno")));
				claim.setClaimAmount(Double.parseDouble(request.getParameter("camount")));
				claim.setBeneficiaryName(request.getParameter("bname"));
				claim.setBeneficiaryAddress(request.getParameter("baddress"));
				claim.setBeneficiaryEmail(request.getParameter("bemail"));
				claim.setBeneficiaryContactNo(Long.parseLong(request.getParameter("bcontact")));
				
				int claimId=cs.submissionOfInsuranceClaimService(claim);  //add claim to database method  
				request.setAttribute("check", claimId);
				request.setAttribute("page", "applyclaim");
				RequestDispatcher rd = request.getRequestDispatcher("DisplayAddClaim.jsp");
				rd.forward(request, response);
				return;
				}
				else
				{
					out.print("<script>if(window.confirm('"+utype+" can not add " +type+" claim'))" +
					  		"{window.location = 'applyClaim.jsp';" +
					  		"}" +
					  		"else{window.location = 'home.jsp';" +
					  		"}" +
					  		"</script>");
				}
			}
				else
				{
					//out.print("Policy reference number does not exist");
					out.print("<script>if(window.confirm('Policy reference number does not exist or Policy is not active.'))" +
					  		"{window.location = 'applyClaim.jsp';" +
					  		"}" +
					  		"else{window.location = 'home.jsp';" +
					  		"}" +
					  		"</script>"); 
				}
			}
				else
				{
					//out.print("Policy reference number does not exist");
					out.print("<script>if(window.confirm('Claim for Policy reference Number :"+request.getParameter("prefno")+"exist and is Open. '))" +
					  		"{window.location = 'applyClaim.jsp';" +
					  		"}" +
					  		"else{window.location = 'home.jsp';" +
					  		"}" +
					  		"</script>"); 
				}
			}
			catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		
		/*=========================================================================
		 * 			update claim to database
		 * ========================================================================*/
		
		//------->first screen for update page, it will "check claim id exist?","check officer type","check policy status"
		//------->fetch data from database and display on updateClaim.jsp page
		if(action.equals("updateDetail"))  
		{
			String type="";
			boolean claimFlag=false;
			String Id=request.getParameter("claimid");
			String numRegex = "[0-9]+";
            boolean flagValidate=false;
			
			if(Id=="" || (!Id.matches(numRegex))) //server side validation
			{
				request.setAttribute("Id", "Id");
				flagValidate=true;
			}
			
			if(flagValidate)
			{
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/updateClaim.jsp");
				rd.forward(request, response); 
				return;
			}
			try {
				claimFlag=cs.checkClaimIdService(Integer.parseInt(request.getParameter("claimid"))); //check claim id exist or not
				} catch (NumberFormatException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (ClassNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			if(claimFlag)  //check claim id exist or not
			{
			try {
				type=cs.getPolicyStatusClaimId(Integer.parseInt(request.getParameter("claimid"))); //get policy status by claim id
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			String utype=request.getSession(false).getAttribute("type").toString();
			
			//check user type(health,field,admin)---with policy type
			if((type.equalsIgnoreCase("health") && utype.equalsIgnoreCase("INSURANCE OFFICER")) || (type.equalsIgnoreCase("VEHICLE") && utype.equalsIgnoreCase("FIELD OFFICER")) )
			{
				Claim c=null;
				int claimId=Integer.parseInt(request.getParameter("claimid"));
				try {
					
					c=cs.getClimDetailsService(claimId);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				request.getSession(false).setAttribute("claim", c);
				request.setAttribute("claimObj", c);
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/updateClaim.jsp");
				rd.forward(request, response);
			}
			else
			{
				out.print("<script>if(window.confirm('"+utype+" can not update " +type+" claim'))" +
				  		"{window.location = 'updateClaim.jsp';" +
				  		"}" +
				  		"else{window.location = 'home.jsp';" +
				  		"}" +
				  		"</script>");
			}
			}
			else
			{
				out.print("<script>if(window.confirm('Claim Id does not exist.'))" +
				  		"{window.location = 'updateClaim.jsp';" +
				  		"}" +
				  		"else{window.location = 'home.jsp';" +
				  		"}" +
				  		"</script>");
			}
		}
	
		
		//-------->it will update claim data in database------------
		
	  if(action.equals("updateClaim"))
	  {    
		  	String camount=request.getParameter("camount");
			String bname=request.getParameter("bname");
			String baddress=request.getParameter("baddress");
			String bemail=request.getParameter("bemail");
			String bcontact=request.getParameter("bcontact");
			
			String forfloat = "[0-9]*\\.[0-9]*";
			String forint = "[0-9]+";
			
			boolean flagValidate=false;
		
			if(camount=="" || !camount.matches(forfloat) && (camount=="" || !camount.matches(forint)))
			{
				request.setAttribute("camount", "camount");
				flagValidate=true;
			}
			if(bname=="" || (!bname.matches("[a-zA-Z]+\\s*[a-zA-Z]+")))
			{
				request.setAttribute("bname", "bname");
				flagValidate=true;
			}
			if(baddress=="")
			{
				request.setAttribute("baddress", "baddress");
				flagValidate=true;
			}
			if(bemail=="")
			{
				request.setAttribute("bemail", "bemail");
				flagValidate=true;
			}
			if(bcontact=="" || (bcontact.length()!=10))
			{
				request.setAttribute("bcontact", "bcontact");
				flagValidate=true;
			}
			
			if(flagValidate)
			{
				//System.out.println("update"+c1.getPolicyReferenceId());
				request.setAttribute("claimObj", request.getSession(false).getAttribute("claim"));
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/updateClaim.jsp");
				rd.forward(request, response);
				return;
			}
				
		  try {
			if(cs.checkClaimStatusService(Integer.parseInt(request.getParameter("claimid"))))
				{
				Claim claim=new Claim();
				claim.setClaimId(Integer.parseInt(request.getParameter("claimid")));
				claim.setClaimAmount(Double.parseDouble(request.getParameter("camount")));
				claim.setBeneficiaryName(request.getParameter("bname"));
				claim.setBeneficiaryAddress(request.getParameter("baddress"));
				claim.setBeneficiaryEmail(request.getParameter("bemail"));
				claim.setBeneficiaryContactNo(Long.parseLong(request.getParameter("bcontact")));
				
			
				boolean s=cs.updateClaimDetailsService(claim);
				if(!s)
				out.print("<script>if(window.confirm(' "+request.getParameter("claimid")+"  Updated'))" +
				  		"{window.location = 'updateClaim.jsp';" +
				  		"}" +
				  		"else{window.location = 'home.jsp';" +
				  		"}" +
				  		"</script>");
				else
				out.print("<script>if(window.confirm(' "+request.getParameter("claimid")+"  not Updated'))" +
				  		"{window.location = 'updateClaim.jsp';" +
				  		"}" +
				  		"else{window.location = 'home.jsp';" +
				  		"}" +
				  		"</script>");
				}
				else
				{
					out.print("<script>if(window.confirm('Claim status is not open'))" +
					  		"{window.location = 'updateClaim.jsp';" +
					  		"}" +
					  		"else{window.location = 'home.jsp';" +
					  		"}" +
					  		"</script>");
				}
			} 
			  catch (NumberFormatException e) {
				
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
	  }
	  
	  
	  
	  if(action.equals("cancleClaim"))
	  {
		  String type="";
		  boolean claimFlag=false;
		  String Id=request.getParameter("claimid");
			String numRegex = "[0-9]+";
          boolean flagValidate=false;
			
			if(Id=="" || (!Id.matches(numRegex)))
			{
				request.setAttribute("Id", "Id");
				flagValidate=true;
			}
			
			if(flagValidate)
			{
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/cancleClaim.jsp");
				rd.forward(request, response);
				return;
			}
			
			try {
				claimFlag=cs.checkClaimIdService(Integer.parseInt(request.getParameter("claimid")));
				} catch (NumberFormatException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (ClassNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			if(claimFlag)
			{
			try {
				type=cs.getPolicyStatusClaimId(Integer.parseInt(request.getParameter("claimid")));
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			String utype=request.getSession(false).getAttribute("type").toString();
			
			if((type.equalsIgnoreCase("health") && utype.equalsIgnoreCase("INSURANCE OFFICER")) || (type.equalsIgnoreCase("VEHICLE") && utype.equalsIgnoreCase("FIELD OFFICER")) )
			{
		  
		  int claimId=Integer.parseInt(request.getParameter("claimid"));
			try {
				if(cs.checkClaimStatusService(claimId))
				{
					boolean s=cs.cancellationOfClaimService(claimId);
					if(s)
					out.print("<script>if(window.confirm(' "+request.getParameter("claimid")+" Deleted'))" +
					  		"{window.location = 'cancleClaim.jsp';" +
					  		"}" +
					  		"else{window.location = 'home.jsp';" +
					  		"}" +
					  		"</script>");
					else
					out.print("<script>if(window.confirm(' "+request.getParameter("claimid")+" not Deleted'))" +
					  		"{window.location = 'cancleClaim.jsp';" +
					  		"}" +
					  		"else{window.location = 'home.jsp';" +
					  		"}" +
					  		"</script>");
				}
				else
				{
					out.print("<script>if(window.confirm('Claim status is not open'))" +
					  		"{window.location = 'cancleClaim.jsp';" +
					  		"}" +
					  		"else{window.location = 'home.jsp';" +
					  		"}" +
					  		"</script>");
				}
			}
				
			 catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			}else
			{
				out.print("<script>if(window.confirm('"+utype+" can not cancel " +type+" claim'))" +
				  		"{window.location = 'cancleClaim.jsp';" +
				  		"}" +
				  		"else{window.location = 'home.jsp';" +
				  		"}" +
				  		"</script>");
			}
			}
			else
			{
				out.print("<script>if(window.confirm('Claim Id does not exist.'))" +
				  		"{window.location = 'cancleClaim.jsp';" +
				  		"}" +
				  		"else{window.location = 'home.jsp';" +
				  		"}" +
				  		"</script>");
			}
	  }
	  
	  
	  
	  if(action.equals("ArClaimDetails"))
	  {
		  String Id=request.getParameter("claimid");
		  String numRegex = "[0-9]+";
		  boolean flagValidate=false;
		  boolean claimFlag=false;

		  if(Id=="" || (!Id.matches(numRegex)))
		  {
			  request.setAttribute("Id", "Id");
			  flagValidate=true;
		  }

		  if(flagValidate)
		  {
			  RequestDispatcher rd = getServletContext().getRequestDispatcher("/approveClaim.jsp");
			  rd.forward(request, response);
			  return;
		  }
		  
		  try {
				claimFlag=cs.checkClaimIdService(Integer.parseInt(request.getParameter("claimid")));
				} catch (NumberFormatException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (ClassNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			if(claimFlag)
			{
		  
		  
		  String utype=request.getSession(false).getAttribute("type").toString();
		  if(utype.equalsIgnoreCase("ADMIN"))
		  {
			  Claim c=null;
			
				int claimId=0;
				String claimStatus="null";
				try {
					claimId=Integer.parseInt(request.getParameter("claimid"));
					c=cs.getClimDetailsService(claimId);
					claimStatus=cs.getClaimStatusService(claimId);
					request.setAttribute("claimStatus", claimStatus);
					request.setAttribute("claimObj", c);
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/approveClaim.jsp");
					rd.forward(request, response);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		  else
		  {
			  out.print("<script>if(window.confirm('Only admin can approve/reject claim'))" +
				  		"{window.location = 'approveClaim.jsp';" +
				  		"}" +
				  		"else{window.location = 'home.jsp';" +
				  		"}" +
				  		"</script>");
		  }
			}
			else
			{
				out.print("<script>if(window.confirm('Claim Id does not exist.'))" +
				  		"{window.location = 'approveClaim.jsp';" +
				  		"}" +
				  		"else{window.location = 'home.jsp';" +
				  		"}" +
				  		"</script>");
			}
	  }
	  
	  if(action.equals("approveClaim"))
	  {
		  boolean flag=false;
		  int claimId=Integer.parseInt(request.getParameter("claimid"));
			try {
				
				flag=cs.approvalClaimService(claimId);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			if(flag)
			{
				out.print("<script>if(window.confirm(' "+request.getParameter("claimid")+"is approved'))" +
				  		"{window.location = 'approveClaim.jsp';" +
				  		"}" +
				  		"else{window.location = 'home.jsp';" +
				  		"}" +
				  		"</script>");
			}
			else
			{
				out.print("<script>if(window.confirm(' "+request.getParameter("claimid")+"is not approved'))" +
				  		"{window.location = 'approveClaim.jsp';" +
				  		"}" +
				  		"else{window.location = 'home.jsp';" +
				  		"}" +
				  		"</script>");
			}
	  	}
	  
	  
	  if(action.equals("rejectClaim"))
	  {
		
		  boolean flag=false;
		  System.out.println(request.getParameter("claimid"));
		  int claimId=Integer.parseInt(request.getParameter("claimid"));
		  String reason=request.getParameter("reason");
		  try {
			flag=cs.rejectClaimService(claimId,reason);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		  
		  if(flag)
			{
				out.print("<script>if(window.confirm(' "+request.getParameter("claimid")+"is rejected'))" +
				  		"{window.location = 'approveClaim.jsp';" +
				  		"}" +
				  		"else{window.location = 'home.jsp';" +
				  		"}" +
				  		"</script>");
			}
			else
			{
				out.print("<script>if(window.confirm(' "+request.getParameter("claimid")+"is not rejected'))" +
				  		"{window.location = 'approveClaim.jsp';" +
				  		"}" +
				  		"else{window.location = 'home.jsp';" +
				  		"}" +
				  		"</script>");
			}
		  
			
	  }
	  
	  if(action.equalsIgnoreCase("Open") || action.equalsIgnoreCase("Approved") || action.equalsIgnoreCase("Rejected"))
	  {
		  
		String utype=request.getSession(false).getAttribute("type").toString();
		  if(utype.equalsIgnoreCase("ADMIN"))
		  {
			  ArrayList<Claim> c=null;
				
				try {
					
					c=cs.getClaimViewDetailsService();
					
					request.setAttribute("claimStatus", action);
					request.setAttribute("arraylist", c);
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/ViewClaim.jsp");
					rd.forward(request, response);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		  else
		  {
			  out.print("<script>if(window.confirm('Only admin can View claim'))" +
				  		"{window.location = 'ViewClaim.jsp';" +
				  		"}" +
				  		"else{window.location = 'home.jsp';" +
				  		"}" +
				  		"</script>");
		  }
			
	  }
	  
	  
}
}



