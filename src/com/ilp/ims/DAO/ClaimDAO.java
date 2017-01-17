package com.ilp.ims.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import com.ilp.ims.Bean.Claim;
import com.ilp.ims.Util.DBConnection;

public class ClaimDAO {
	
	

	
/*------------------------------------------------------------------------------------------------
 insert data in claim
 ------------------------------------------------------------------------------------------------*/
	
	//------------------------get Policy type...................................................//
public static String getPolicyType(int pref) throws ClassNotFoundException, SQLException
{
	PreparedStatement ps = null;
	Connection con = null;
	con=DBConnection.getConnection();
	String type="";
	
	ps=con.prepareStatement("SELECT POLICY_TYPE FROM INSURANCE WHERE POLICY_REFERENCE_NUMBER=?");
	ps.setInt(1,pref);
	
	ResultSet rs=ps.executeQuery();
	
	if(rs.next())
		type=rs.getString(1);
	
	ps.close();
	
	return type;
		
}
	
	//------------------------insert data in claim detail table.............................//
public static int submissionOfInsuranceClaim(Claim c) throws ClassNotFoundException, SQLException
{
	
		int claimId=0;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		Connection con = null;
		
		java.util.Date utilDate = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		
		con=DBConnection.getConnection();
		
		ps=con.prepareStatement("INSERT INTO CLAIM_DETAILS VALUES(CLAIM_ID_SEQ.nextval,?,?,?,?,?,?,?,?,?)");
	
		ps.setInt(1, c.getPolicyReferenceId());
		ps.setDouble(2, c.getClaimAmount());
		ps.setDate(3, sqlDate);
		ps.setString(4, c.getBeneficiaryName());
		ps.setString(5, c.getBeneficiaryAddress());
		ps.setString(6, c.getBeneficiaryEmail());
		ps.setLong(7, c.getBeneficiaryContactNo());
		ps.setString(8, "open");
		ps.setString(9, " ");
		
		ps.executeUpdate();
		con.commit();
		ps.close();
		
		ps1=con.prepareStatement("SELECT CLAIM_ID_SEQ.currval FROM DUAL");
		ResultSet rs=ps1.executeQuery();
		
		if(rs.next())
		{
			claimId=rs.getInt(1);
		}
		ps1.close();
		rs.close();
		DBConnection.closeConnection(con);
		
		return claimId;
	}


	public static boolean checkPolicyRefNo(int pref) throws SQLException, ClassNotFoundException
	{
		int count=0;
		PreparedStatement ps = null;
		Connection con = null;
		con=DBConnection.getConnection();
		ps=con.prepareStatement("SELECT * FROM INSURANCE WHERE POLICY_REFERENCE_NUMBER=? AND POLICY_STATUS='ACTIVE'");
		ps.setInt(1, pref);
		ResultSet rs=ps.executeQuery();
		
		while(rs.next())
			++count;
		
	//	System.out.println("in dao"+count);
		ps.close();
		rs.close();
		DBConnection.closeConnection(con);
		
		if(count==1)
		return true;
		else
		return false;
	}
	
	public static boolean checkExistclaim(int pref) throws ClassNotFoundException, SQLException
	{
		int count=0;
		PreparedStatement ps = null;
		Connection con = null;
		con=DBConnection.getConnection();
		
		ps=con.prepareStatement("SELECT * FROM CLAIM_DETAILS WHERE POLICY_REFERENCE_NUMBER=? AND CLAIM_STATUS='open'");
		
		ps.setInt(1, pref);
		ResultSet rs=ps.executeQuery();
		
		while(rs.next())
			++count;
		
		System.out.println("in dao claim"+count);
		ps.close();
		rs.close();
		DBConnection.closeConnection(con);
		
		if(count==1)
		return false;
		else
		return true;
	}
	
/*	-------------------------------------end insert data------------------------------------------------*/

	
/*===========================================================================================================================
 									update claim details
=============================================================================================================================* */

	
	public static Claim getClimDetails(int claimId) throws ClassNotFoundException, SQLException
	{
		Claim c=null;
		System.out.println(claimId);
		PreparedStatement ps = null;
		Connection con = null;
		con=DBConnection.getConnection();
		
		ps=con.prepareStatement("SELECT * FROM CLAIM_DETAILS WHERE CLAIM_ID=?");
		ps.setInt(1, claimId);
		ResultSet rs=ps.executeQuery();
		
		
		if(rs.next())
		{
			c=new Claim();
			c.setClaimId(rs.getInt(1));
			c.setPolicyReferenceId(rs.getInt(2));
			c.setClaimAmount(rs.getDouble(3));
			c.setClaimSubmissionDate(rs.getDate(4));
			c.setBeneficiaryName(rs.getString(5));
			c.setBeneficiaryAddress(rs.getString(6));
			c.setBeneficiaryEmail(rs.getString(7));
			c.setBeneficiaryContactNo(rs.getLong(8));
		}
		else{
			   System.out.println("claim object not generated(in getdetails)");
		}
		ps.close();
		rs.close();
		DBConnection.closeConnection(con);
		return c;
		
	}
	
	public static String getPolicyStatusClaimId(int claimId) throws SQLException, ClassNotFoundException
	{
		PreparedStatement ps = null;
		Connection con = null;
		con=DBConnection.getConnection();
		String type="";
		
		ps=con.prepareStatement("SELECT POLICY_TYPE FROM(" +
				"SELECT INSURANCE.POLICY_TYPE,CLAIM_DETAILS.CLAIM_ID FROM INSURANCE " +
				"INNER JOIN CLAIM_DETAILS ON INSURANCE.POLICY_REFERENCE_NUMBER = CLAIM_DETAILS.POLICY_REFERENCE_NUMBER)" +
				" WHERE CLAIM_ID=?");
		ps.setInt(1,claimId);
		
		ResultSet rs=ps.executeQuery();
		
		if(rs.next())
			type=rs.getString(1);
		
		ps.close();
		
		return type;	
	}
	
	public static boolean updateClaimDetails(Claim claim) throws ClassNotFoundException, SQLException
	{
		boolean status=false;
		PreparedStatement ps = null;
		Connection con = null;
		con=DBConnection.getConnection();	
		
		ps=con.prepareStatement("UPDATE CLAIM_DETAILS SET CLAIM_AMOUNT = ?,BENEFICIARY_NAME = ?,BENEFICIARY_ADDRESS = ?,BENEFICIARY_EMAIL = ?,BENEFICIARY_CONTACT_NO = ? WHERE CLAIM_ID=?");
		ps.setDouble(1, claim.getClaimAmount());
		ps.setString(2, claim.getBeneficiaryName());
		ps.setString(3, claim.getBeneficiaryAddress());
		ps.setString(4, claim.getBeneficiaryEmail());
		ps.setLong(5, claim.getBeneficiaryContactNo());
		ps.setInt(6, claim.getClaimId());
		
	    status=ps.execute();
	    con.commit();
	    ps.close();
	    DBConnection.closeConnection(con);
	
	    return status;
	    
		
	}
	public static boolean checkClaimStatus(int claimId) throws SQLException, ClassNotFoundException
	{
		boolean status=false;
		PreparedStatement ps = null;
		Connection con = null;
		con=DBConnection.getConnection();
		
		ps=con.prepareStatement("SELECT CLAIM_STATUS FROM CLAIM_DETAILS where CLAIM_ID=?");
		ps.setInt(1, claimId);
		
		ResultSet rs=ps.executeQuery();
		String s=null;
		
		if(rs.next())
			s=rs.getString(1);
		
		if(s.equals("open"))
		{
			status=true;
		}
		else
			status=false;
		
		return status;
		
	}
	
	
	public static boolean checkClaimId(int id) throws ClassNotFoundException, SQLException
	{
		boolean status=false;
		PreparedStatement ps = null;
		Connection con = null;
		con=DBConnection.getConnection();
		
		ps=con.prepareStatement("SELECT CLAIM_ID FROM CLAIM_DETAILS where CLAIM_ID=?");
		ps.setInt(1, id);
		
		ResultSet rs=ps.executeQuery();
		int claimId=0;
		
		if(rs.next())
			claimId=rs.getInt(1);
		
		if(claimId==id)
		{
			status=true;
		}
		else
			status=false;
		
		return status;
	
	}
	
	
	/*==============================================================================================================
	 * 					cancellation of claim 
	 * ===============================================================================================================*/
	
	public static boolean cancellationOfClaim(int claimId) throws ClassNotFoundException, SQLException
	{
		boolean status=false;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		Connection con = null;
		con=DBConnection.getConnection();	
		
		Claim c=getClimDetails(claimId);
		
		java.util.Date utilDate = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		
		java.sql.Date regDate = new java.sql.Date(c.getClaimSubmissionDate().getTime());
		
		
		
		ps=con.prepareStatement("INSERT INTO CLAIM_DETAILS_HISTORY VALUES(?,?,?,?,?,?,?,?,?,?,?)");
		
		ps.setInt(1, c.getClaimId());
		ps.setInt(2, c.getPolicyReferenceId());
		ps.setDouble(3, c.getClaimAmount());
		ps.setDate(4, regDate);
		ps.setString(5, c.getBeneficiaryName());
		ps.setString(6, c.getBeneficiaryAddress());
		ps.setString(7, c.getBeneficiaryEmail());
		ps.setLong(8, c.getBeneficiaryContactNo());
		ps.setString(9, "open");
		ps.setString(10, " ");
		ps.setDate(11, sqlDate);
		
		boolean flag=ps.execute();
		con.commit();
		ps.close();
		
		if(!flag)
		{
			ps1=con.prepareStatement("DELETE FROM CLAIM_DETAILS WHERE CLAIM_ID=?");
			ps1.setInt(1, claimId);
			
			status=ps1.execute();
			con.commit();
			ps1.close();
		}
		
		con.close();
		
		if(!status)
		return true;
		else
		return false;
		
		
	}
	/*==================================================================================================
										Aprove/Reject functions
	====================================================================================================*/
	public static boolean approvalClaim(int claimId) throws ClassNotFoundException, SQLException
	{
		boolean status=false;
		PreparedStatement ps = null;
		
		Connection con = null;
		con=DBConnection.getConnection();
		
		ps=con.prepareStatement("UPDATE CLAIM_DETAILS SET CLAIM_STATUS = ?,CLAIM_REJECTION_REASON='' WHERE CLAIM_ID=?");
		
		ps.setString(1, "approved");
		ps.setInt(2, claimId);
		
		status=ps.execute();
		con.commit();
		
		if(!status)
		return true;
		else
		return false;
	
	}
	
	public static boolean rejectClaim(int claimId,String reason) throws SQLException, ClassNotFoundException
	{
		Claim c=new Claim();
		boolean status=false;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		Connection con = null;
		con=DBConnection.getConnection();
		
		ps=con.prepareStatement("UPDATE CLAIM_DETAILS SET CLAIM_STATUS = ? ,CLAIM_REJECTION_REASON=? WHERE CLAIM_ID=?");
		ps.setString(1, "rejected");
		ps.setString(2, reason);
		ps.setInt(3, claimId);
		
		status=ps.execute();
		con.commit();
		
		ps.close();
		con.close();
		
		if(!status)
		return true;
		else
		return false;
		
		
	}
	
	public static String getClaimStatus(int claimId) throws ClassNotFoundException, SQLException
	{
		PreparedStatement ps = null;
		Connection con = null;
		con=DBConnection.getConnection();
		
		ps=con.prepareStatement("SELECT CLAIM_STATUS FROM CLAIM_DETAILS where CLAIM_ID=?");
		ps.setInt(1, claimId);
		
		ResultSet rs=ps.executeQuery();
		String claimStatus="null";
		
		if(rs.next())
			claimStatus=rs.getString(1);
		
		return claimStatus;
	}
	
	
	public static ArrayList<Claim> getClaimViewDetails() throws ClassNotFoundException, SQLException
	{   
		ArrayList<Claim> listOfClaim=new ArrayList<Claim>();
		PreparedStatement ps = null;
		Connection con = null;
		con=DBConnection.getConnection();
		
		ps=con.prepareStatement("SELECT * FROM CLAIM_DETAILS ");
		
		
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{
			Claim c=new Claim();
			c.setClaimId(rs.getInt(1));
			c.setPolicyReferenceId(rs.getInt(2));
			c.setClaimAmount(rs.getDouble(3));
			c.setClaimSubmissionDate(rs.getDate(4));
			c.setBeneficiaryName(rs.getString(5));
			c.setBeneficiaryAddress(rs.getString(6));
			c.setBeneficiaryEmail(rs.getString(7));
			c.setBeneficiaryContactNo(rs.getLong(8));
			c.setClaimStatus(rs.getString(9));
			listOfClaim.add(c);
		}
		
		return listOfClaim;
	}
	
}
