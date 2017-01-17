package com.ilp.ims.Bean;

import java.util.Date;

public class Claim {
	
	private int customerId;
	private int policyReferenceId;
	private int claimId;
	private double claimAmount;
	private Date claimSubmissionDate;
	private String beneficiaryName;
	private String beneficiaryAddress;
	private String beneficiaryEmail;
	private Long beneficiaryContactNo;
	private String claimStatus;
	private String rejectionReason;
	
	public Claim(int customerId, int policyReferenceId, int claimId,
			double claimAmount, Date claimSubmissionDate,
			String beneficiaryName, String beneficiaryAddress,
			String beneficiaryEmail, Long beneficiaryContactNo,
			String claimStatus, String rejectionReason) 
	{
		this.customerId = customerId;
		this.policyReferenceId = policyReferenceId;
		this.claimId = claimId;
		this.claimAmount = claimAmount;
		this.claimSubmissionDate = claimSubmissionDate;
		this.beneficiaryName = beneficiaryName;
		this.beneficiaryAddress = beneficiaryAddress;
		this.beneficiaryEmail = beneficiaryEmail;
		this.beneficiaryContactNo = beneficiaryContactNo;
		this.claimStatus = claimStatus;
		this.rejectionReason = rejectionReason;
	}
	
	public Claim() 
	{
		
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getPolicyReferenceId() {
		return policyReferenceId;
	}
	public void setPolicyReferenceId(int policyReferenceId) {
		this.policyReferenceId = policyReferenceId;
	}
	public int getClaimId() {
		return claimId;
	}
	public void setClaimId(int claimId) {
		this.claimId = claimId;
	}
	public double getClaimAmount() {
		return claimAmount;
	}
	public void setClaimAmount(double claimAmount) {
		this.claimAmount = claimAmount;
	}
	public Date getClaimSubmissionDate() {
		return claimSubmissionDate;
	}
	public void setClaimSubmissionDate(Date claimSubmissionDate) {
		this.claimSubmissionDate = claimSubmissionDate;
	}
	public String getBeneficiaryName() {
		return beneficiaryName;
	}
	public void setBeneficiaryName(String beneficiaryName) {
		this.beneficiaryName = beneficiaryName;
	}
	public String getBeneficiaryAddress() {
		return beneficiaryAddress;
	}
	public void setBeneficiaryAddress(String beneficiaryAddress) {
		this.beneficiaryAddress = beneficiaryAddress;
	}
	public String getBeneficiaryEmail() {
		return beneficiaryEmail;
	}
	public void setBeneficiaryEmail(String beneficiaryEmail) {
		this.beneficiaryEmail = beneficiaryEmail;
	}
	public Long getBeneficiaryContactNo() {
		return beneficiaryContactNo;
	}
	public void setBeneficiaryContactNo(Long beneficiaryContactNo) {
		this.beneficiaryContactNo = beneficiaryContactNo;
	}
	public String getClaimStatus() {
		return claimStatus;
	}
	public void setClaimStatus(String claimStatus) {
		this.claimStatus = claimStatus;
	}
	public String getRejectionReason() {
		return rejectionReason;
	}
	public void setRejectionReason(String rejectionReason) {
		this.rejectionReason = rejectionReason;
	}
	
	

}
