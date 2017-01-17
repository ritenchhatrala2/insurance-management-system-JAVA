package com.ilp.ims.Service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ilp.ims.Bean.Claim;
import com.ilp.ims.DAO.ClaimDAO;

public class ClaimService {
	
	//--------------Add Claim --------------------------------------------------------//
	
	public String getPolicyType(int pref) throws ClassNotFoundException, SQLException
	{
		String type=ClaimDAO.getPolicyType(pref);
		return type;
	}
	
	
	public int submissionOfInsuranceClaimService(Claim c) throws ClassNotFoundException, SQLException
	{
		int claimId=ClaimDAO.submissionOfInsuranceClaim(c);
		return claimId;
	}
	
	public boolean checkPolicyRefNoService(int pref) throws SQLException, ClassNotFoundException
	{
		boolean status=ClaimDAO.checkPolicyRefNo(pref);
		return status;
	}

	public boolean checkExistclaimService(int pref) throws ClassNotFoundException, SQLException
	{
		boolean status=ClaimDAO.checkExistclaim(pref);
		return status;
	}
	
	//--------------Update Claim----------------------------------------------------------//
	
	public Claim getClimDetailsService(int claimId) throws ClassNotFoundException, SQLException
	{
		Claim claimDetail=ClaimDAO.getClimDetails(claimId);
		return claimDetail;
	}
	
	public String getPolicyStatusClaimId(int claimId) throws SQLException, ClassNotFoundException
	{
		String type=ClaimDAO.getPolicyStatusClaimId(claimId);
		return type;
	}
	
	public boolean updateClaimDetailsService(Claim claim) throws ClassNotFoundException, SQLException
	{
		boolean status=ClaimDAO.updateClaimDetails(claim);
		return status;
	}
	
	public boolean checkClaimIdService(int id) throws ClassNotFoundException, SQLException
	{
		boolean status=ClaimDAO.checkClaimId(id);
		return status;
	}
	
	public boolean checkClaimStatusService(int claimId) throws SQLException, ClassNotFoundException
	{
		boolean status=ClaimDAO.checkClaimStatus(claimId);
		return status;
	}
	
	//---------------Delete Claim---------------------------------------------------------//
	
	public boolean cancellationOfClaimService(int claimId) throws ClassNotFoundException, SQLException
	{
		boolean status=ClaimDAO.cancellationOfClaim(claimId);
		return status;
	}
	
	//--------------approve Claim---------------------------------------------------------//
	
	public  boolean approvalClaimService(int claimId) throws ClassNotFoundException, SQLException
	{
		boolean status=ClaimDAO.approvalClaim(claimId);
		return status;
	}
	
	public String getClaimStatusService(int claimId) throws ClassNotFoundException, SQLException
	{
		String claimStatus=ClaimDAO.getClaimStatus(claimId);
		return claimStatus;
	}
	
	
	//--------------reject claim------------------------------------------------------//
	public boolean rejectClaimService(int claimId,String reason) throws SQLException, ClassNotFoundException
	{
		boolean status=ClaimDAO.rejectClaim(claimId,reason);
		return status;
	}
	
	public  ArrayList<Claim> getClaimViewDetailsService() throws ClassNotFoundException, SQLException
	{  
		ArrayList<Claim> ar=ClaimDAO.getClaimViewDetails();
		return ar;
	}
	
	
}
