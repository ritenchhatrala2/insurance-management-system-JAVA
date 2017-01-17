package com.ilp.ims.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.ilp.ims.Bean.Customer;
import com.ilp.ims.Util.DBConnection;

public class CustomerDAO {
	
	Connection con=null;
	PreparedStatement ps=null;
	ResultSet rs=null;

//******************************* REGISTER CUSTOMER *****************************
	
	public int registerCustomer(Customer customer) throws SQLException {
		
		int result=0;
		java.util.Date utilDate = new java.util.Date();
		
		try{			
			con=DBConnection.getConnection();			
			ps=con.prepareStatement("INSERT  INTO  CUSTOMER  VALUES(CUSTOMER_ID_SEQ.nextVal ,?,?,?,?,?,?,?,?,?)"); 
			ps.setString(1, customer.getCustomerName());
			ps.setDate(2, new java.sql.Date(customer.getDob().getTime()));
			ps.setString(3, customer.getEmail());
			ps.setString(4, customer.getAddress());
			ps.setLong(5, customer.getContactNo());
			ps.setString(6, customer.getPhotoIdProof());
			ps.setString(7, customer.getPhotoIdNo());
			ps.setString(8, customer.getAddressProof());
			ps.setDate(9, new java.sql.Date(utilDate.getTime()));
			result=ps.executeUpdate();
			con.commit();
			
			if(result>0)
			{
				ps=con.prepareStatement("SELECT CUSTOMER_ID_SEQ.currval AS ID FROM DUAL");
				rs=ps.executeQuery();
				while(rs.next())
				{
					result=rs.getInt("ID");
				}
			}	
			
			}catch(Exception ex)
			{
				System.out.println(ex.getMessage());
			}
			finally
			{
				DBConnection.closeStatement(ps);
				DBConnection.closeConnection(con);
			}
			return result;
		
	}
	
//************************** CHECK EMAIL ID EXISTENCE ***********************
		
	public boolean checkEmail(String email) throws SQLException
	{		
		
		boolean check = false;
		try
		{
			con=DBConnection.getConnection();
			ps=con.prepareStatement("SELECT EMAIL_ID FROM CUSTOMER");
			ResultSet rs=ps.executeQuery();
			String s=null;
			
			while(rs.next()){			
				s=rs.getString("EMAIL_ID");
				if(s.equalsIgnoreCase(email))
					check = true;
			}
						
	    }
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		finally
		{
			DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(ps);
			DBConnection.closeConnection(con);
		}
		return check;
	}		
	
//********************************* GET CUSTOMER DETAILS **************************
	
	public Customer getCustomerDetails(int customerId) {
		
		Customer customer = new Customer();
		try
		{
			con=DBConnection.getConnection();
			ps=con.prepareStatement("SELECT CUSTOMER_ID,CUSTOMER_NAME,DOB,EMAIL_ID,ADDRESS,CONTACT_NUMBER,PHOTOID_PROOF,PHOTOID_NUMBER,ADDRESS_PROOF,REGISTRATION_DATE FROM CUSTOMER WHERE CUSTOMER_ID =?");
			ps.setInt(1, customerId);
			rs=ps.executeQuery();
			while(rs.next())
			{
				customer.setCustomerId(rs.getInt("CUSTOMER_ID"));
				customer.setCustomerName(rs.getString("CUSTOMER_NAME"));
				customer.setDob(rs.getDate("DOB"));
				customer.setEmail(rs.getString("EMAIL_ID"));
				customer.setAddress(rs.getString("ADDRESS"));
				customer.setContactNo(rs.getLong("CONTACT_NUMBER"));
				customer.setPhotoIdProof(rs.getString("PHOTOID_PROOF"));
				customer.setPhotoIdNo(rs.getString("PHOTOID_NUMBER"));
				customer.setAddressProof(rs.getString("ADDRESS_PROOF"));
				customer.setRegistrationDate(rs.getDate("REGISTRATION_DATE"));
			}
					
	    }
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return customer;
	}

//****************************** UPDATE CUSTOMER DETAILS ***********************************
	
	public boolean updateCustomerDetails(Customer customer) throws SQLException {
		
		
		boolean update=false;
		try
		{
			con=DBConnection.getConnection();
			ps=con.prepareStatement("UPDATE CUSTOMER SET CUSTOMER_NAME=?,DOB=?,EMAIL_ID=?,ADDRESS=?,CONTACT_NUMBER=?,PHOTOID_PROOF=?,PHOTOID_NUMBER=?,ADDRESS_PROOF=?  WHERE CUSTOMER_ID =?");
		
			ps.setString(1, customer.getCustomerName());
			ps.setDate(2, new java.sql.Date(customer.getDob().getTime()));
			ps.setString(3, customer.getEmail());
			ps.setString(4, customer.getAddress());
			ps.setLong(5, customer.getContactNo());
			ps.setString(6, customer.getPhotoIdProof());
			ps.setString(7, customer.getPhotoIdNo());
			ps.setString(8, customer.getAddressProof());
			ps.setInt(9, customer.getCustomerId());
			if(ps.executeUpdate()>0)
			{
				System.out.println("Updation in DAO");
				con.commit();
				update = true;
			}
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		finally
		{
			DBConnection.closeStatement(ps);
			DBConnection.closeConnection(con);
		}
		return update;
	}
	
	
//****************************** VIEW CUSTOMER DETAILS *******************************
	
	public ArrayList<Customer> viewCustomerDetails(Date startDate,Date endDate) throws SQLException {
		
		ArrayList<Customer> custList = new ArrayList<Customer>();
		
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try
		{
			con=DBConnection.getConnection();
			ps=con.prepareStatement("SELECT * FROM CUSTOMER WHERE REGISTRATION_DATE BETWEEN ? AND ? ");

			ps.setDate(1, new java.sql.Date(startDate.getTime()));
			ps.setDate(2, new java.sql.Date(endDate.getTime()));
			rs=ps.executeQuery();
			
			while(rs.next())
			{
				Customer customer = new Customer();
				customer.setCustomerId(rs.getInt("CUSTOMER_ID"));
				customer.setCustomerName(rs.getString("CUSTOMER_NAME"));
				customer.setDob(rs.getDate("DOB"));
				customer.setEmail(rs.getString("EMAIL_ID"));
				customer.setAddress(rs.getString("ADDRESS"));
				customer.setContactNo(rs.getLong("CONTACT_NUMBER"));
				customer.setPhotoIdProof(rs.getString("PHOTOID_PROOF"));
				customer.setPhotoIdNo(rs.getString("PHOTOID_NUMBER"));
				customer.setAddressProof(rs.getString("ADDRESS_PROOF"));
				customer.setRegistrationDate(rs.getDate("REGISTRATION_DATE"));
				custList.add(customer);
				
			}			
			System.out.println(custList.size());
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		finally
		{
			DBConnection.closeStatement(ps);
			DBConnection.closeConnection(con);
		}
		
		return custList;
		
	}
	
//******************************* DELETE CUSTOMER *************************************
	
	public boolean checkPolicyStatus(int customerId) throws SQLException
	{		
		
		boolean status = true;
		try
		{
			con=DBConnection.getConnection();
			ps=con.prepareStatement("SELECT POLICY_STATUS FROM(SELECT INSURANCE.POLICY_STATUS, CUSTOMER.CUSTOMER_ID FROM INSURANCE INNER JOIN CUSTOMER ON CUSTOMER.CUSTOMER_ID=INSURANCE.CUSTOMER_ID) WHERE CUSTOMER_ID=?");
			ps.setInt(1, customerId);
			System.out.println("Customer to be deleted: "+ customerId);
			ResultSet rs=ps.executeQuery();
			String s=null;
			while(rs.next()){
				s=rs.getString("POLICY_STATUS");
				System.out.println("POLICY STATUS: "+s);
				if(s.equalsIgnoreCase("ACTIVE"))
				{
					status = false;
					break;
				}				
			}			
	    }
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			DBConnection.closeStatement(ps);
			DBConnection.closeConnection(con);
		}
		System.out.println(status);
		return status;
	}		
	
	public boolean removeCustomer(int customerId) throws SQLException {
		
		PreparedStatement dps=null;
		Customer customer = getCustomerDetails(customerId);
		java.util.Date utilDate = new java.util.Date();
		
		boolean remove = false;
		try
		{
			con=DBConnection.getConnection();
			
			ps=con.prepareStatement("INSERT INTO CUSTOMER_HISTORY VALUES(?,?,?,?,?,?,?,?,?,?,?)"); 
			
			ps.setInt(1, customer.getCustomerId());
			ps.setString(2, customer.getCustomerName());
			ps.setDate(3,  new java.sql.Date(customer.getDob().getTime()));
			ps.setString(4, customer.getEmail());
			ps.setString(5, customer.getAddress());
			ps.setLong(6, customer.getContactNo());
			ps.setString(7, customer.getPhotoIdProof());
			ps.setString(8, customer.getPhotoIdNo());
			ps.setString(9, customer.getAddressProof());
			ps.setDate(10, new java.sql.Date(customer.getRegistrationDate().getTime()));
			ps.setDate(11, new java.sql.Date(utilDate.getTime()));
			
			boolean flag = ps.execute();
				con.commit();			

			if(!flag)
			{
				dps=con.prepareStatement("DELETE FROM CUSTOMER WHERE CUSTOMER_ID=?");
				dps.setInt(1, customerId);
				
				remove = dps.execute();
				con.commit();
				dps.close();
			}
	    }
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		finally
		{
			DBConnection.closeStatement(ps);
			DBConnection.closeConnection(con);
		}
		System.out.println(remove);
		if(!remove)
		return true;
		else
		return false;
	}
	
	
//************************** CHECK CUSTOMER ID EXISTENCE ***********************
	
	public boolean checkCustomerId(int customerId) throws SQLException
	{		
		boolean check = false;
		try
		{
			con=DBConnection.getConnection();
			ps=con.prepareStatement("SELECT CUSTOMER_ID FROM CUSTOMER");
			ResultSet rs=ps.executeQuery();
			int s = 0;
			
			while(rs.next()){			
				s=rs.getInt(1);
				if(s==customerId)
				{	
					check = true;
					break;
				}
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		finally
		{
			DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(ps);
			DBConnection.closeConnection(con);
		}
		return check;
	}		
}
