package com.ilp.ims.Bean;

import java.util.Date;

public class Insurance {
	private int policyReferenceNumber;
	private int policyId;
	private int customerId;
	private Date policyCommencementDate;
	private Date policyEndDate;
	private String paymentMode;
	private String paymentFrequency;
	private double premiumAmount;
	private String policyStatus;
	private int policyTenure;
	private String policyType;

	public int getPolicyReferenceNumber() {
		return policyReferenceNumber;
	}

	public void setPolicyReferenceNumber(int policyReferenceNumber) {
		this.policyReferenceNumber = policyReferenceNumber;
	}

	public int getPolicyId() {
		return policyId;
	}

	public void setPolicyId(int policyId) {
		this.policyId = policyId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public Date getPolicyCommencementDate() {
		return policyCommencementDate;
	}

	public void setPolicyCommencementDate(Date policyCommencementDate) {
		this.policyCommencementDate = policyCommencementDate;
	}

	public Date getPolicyEndDate() {
		return policyEndDate;
	}

	public void setPolicyEndDate(Date policyEndDate) {
		this.policyEndDate = policyEndDate;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getPaymentFrequency() {
		return paymentFrequency;
	}

	public void setPaymentFrequency(String paymentFrequency) {
		this.paymentFrequency = paymentFrequency;
	}

	public double getPremiumAmount() {
		return premiumAmount;
	}

	public void setPremiumAmount(double premiumAmount) {
		this.premiumAmount = premiumAmount;
	}

	public String getPolicyStatus() {
		return policyStatus;
	}

	public void setPolicyStatus(String policyStatus) {
		this.policyStatus = policyStatus;
	}

	public int getPolicyTenure() {
		return policyTenure;
	}

	public void setPolicyTenure(int policyTenure) {
		this.policyTenure = policyTenure;
	}

	public String getPolicyType() {
		return policyType;
	}

	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}

}
