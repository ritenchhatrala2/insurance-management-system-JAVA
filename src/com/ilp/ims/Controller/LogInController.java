package com.ilp.ims.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ilp.ims.Bean.Officer;
import com.ilp.ims.Service.LogInService;


public class LogInController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LogInController() {
        super();
        
    }

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
		PrintWriter out=response.getWriter();
	    LogInService ls=new LogInService();
		
		String type="";
		
		String action=request.getParameter("action");
		
		
		if(action.equals("login"))
		{	
			String userId=request.getParameter("userid");
			String pass=request.getParameter("password");
			String numRegex = "[0-9]+";
			boolean flagValidate=false;
			
			if(userId == "" || (!userId.matches(numRegex)))
			{
				request.setAttribute("userId", "userId");
				flagValidate=true;
			}
			if(pass=="" || pass==null)
			{
				request.setAttribute("pass", "pass");
				flagValidate=true;
			}
			
			if(flagValidate)
			{
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login.jsp");
				rd.forward(request, response);
			}
			Officer obj=new Officer();
			try
			   {
			     obj.setUserId(Integer.parseInt(request.getParameter("userid")));
			   }
			catch (NumberFormatException e) {
				e.printStackTrace();
			   }
			obj.setPassword(pass);
			try {
				type=ls.checkLoginService(obj);
			    } 
			    catch (ClassNotFoundException e) {
				e.printStackTrace();
			    } 
			     catch (SQLException e) {
				 e.printStackTrace();
			       } 
			
			System.out.println(type);
			HttpSession session = request.getSession();
			
			if(type!=null)
			{
				session.setAttribute("userid",userId);
				session.setAttribute("type", type);
				response.sendRedirect("home.jsp");
			}
			else
			{
				request.setAttribute("check",1);
				RequestDispatcher rs= request.getRequestDispatcher("Login.jsp");
				rs.include(request,response);
			}
		}
		
		if(action.equals("logout"))
		{
			
				
		
			System.out.println("in logout");
			HttpSession session=request.getSession(false);
			
			if(session!=null)
			{
				System.out.println("in session");
				session.removeAttribute("type");
				session.removeAttribute("username");
				session.invalidate();
				
				response.sendRedirect(getServletContext().getContextPath()+"/Login.jsp");
				
				
			}
		
		}
	}
	
	

}
