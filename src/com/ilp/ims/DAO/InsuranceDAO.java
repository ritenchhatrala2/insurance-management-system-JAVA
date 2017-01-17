package com.ilp.ims.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.ilp.ims.Bean.*;
import com.ilp.ims.Util.DBConnection;

public class InsuranceDAO {
	int check = 0;
	Connection conn = null;
	PreparedStatement pst1 = null;
	PreparedStatement pst2 = null;
	PreparedStatement pst3 = null;
	PreparedStatement pst4 = null;
	ResultSet rs = null;
		
	// Health Policy Registration
	public int registerHealthInsurancePolicy(HealthInsurance healthInsurance) throws ClassNotFoundException, SQLException {
		try {
			InsuranceDAO idao = new InsuranceDAO();
			idao.makePolicyInactive();
			conn = DBConnection.getConnection();
			pst1 = conn.prepareStatement("INSERT INTO INSURANCE VALUES(POLICY_REFERENCE_NUMBER_SEQ.NEXTVAL,?,?,?,?,?,?,?,?,'ACTIVE','HEALTH')");
			pst1.setInt(1, healthInsurance.getCustomerId());
			pst1.setInt(2, healthInsurance.getPolicyId());
			pst1.setDate(3, new java.sql.Date(healthInsurance
					.getPolicyCommencementDate().getTime()));
			pst1.setDate(4, new java.sql.Date(healthInsurance
					.getPolicyEndDate().getTime()));
			pst1.setString(5, healthInsurance.getPaymentMode());
			pst1.setString(6, healthInsurance.getPaymentFrequency());
			pst1.setInt(7, healthInsurance.getPolicyTenure());
			pst1.setDouble(8, healthInsurance.getPremiumAmount());
			int x= pst1.executeUpdate();
			conn.commit();
			if(x>0){
			pst2 = conn
					.prepareStatement("Select POLICY_REFERENCE_NUMBER_SEQ.CURRVAL from dual");
			rs = pst2.executeQuery();
			while (rs.next()) {
				check = rs.getInt(1);
			}
			}
			else if(x==0)
				check = 0;

		}catch(Exception e)
		{
			check = -2;
		}
		finally {
			DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(pst3);
			DBConnection.closeStatement(pst2);
			DBConnection.closeStatement(pst1);
			DBConnection.closeConnection(conn);
		}
		return check;
	}

	// Vehicle Policy Registration
	public int registerVehicleInsurancePolicy(VehicleInsurance vehicleInsurance)
			throws ClassNotFoundException, SQLException {
		try {
			InsuranceDAO idao = new InsuranceDAO();
			idao.makePolicyInactive();
			int x=0,y=0;
			conn = DBConnection.getConnection();
			pst1 = conn
					.prepareStatement("INSERT INTO INSURANCE VALUES(POLICY_REFERENCE_NUMBER_SEQ.NEXTVAL,?,?,?,?,?,?,1,?,'ACTIVE','VEHICLE')");
			pst1.setInt(1, vehicleInsurance.getCustomerId());
			pst1.setInt(2, vehicleInsurance.getPolicyId());
			pst1.setDate(3, new java.sql.Date(vehicleInsurance
					.getPolicyCommencementDate().getTime()));
			pst1.setDate(4, new java.sql.Date(vehicleInsurance
					.getPolicyEndDate().getTime()));
			pst1.setString(5, vehicleInsurance.getPaymentMode());
			pst1.setString(6, vehicleInsurance.getPaymentFrequency());
			pst1.setDouble(7, vehicleInsurance.getPremiumAmount());
			x=pst1.executeUpdate();
			if(x>0){
			pst2 = conn
					.prepareStatement("INSERT INTO VEHICLE_INSURANCE VALUES(POLICY_REFERENCE_NUMBER_SEQ.CURRVAL,?,?,?)");
			pst2.setString(1, vehicleInsurance.getVehicleNo());
			pst2.setString(2, vehicleInsurance.getLicenseNo());
			pst2.setString(3, vehicleInsurance.getVehicleType());
			y = pst2.executeUpdate();
			conn.commit();
			if(y>0){
			pst3 = conn
					.prepareStatement("Select POLICY_REFERENCE_NUMBER_SEQ.CURRVAL from DUAL");
			rs = pst3.executeQuery();

			while (rs.next()) {
				check = rs.getInt(1);
			}
			}
			}
			if(x==0||y==0)
				check = 0;
		} catch (Exception e) {
			check = -2;
		}
		finally {
			DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(pst4);
			DBConnection.closeStatement(pst3);
			DBConnection.closeStatement(pst2);
			DBConnection.closeStatement(pst1);
			DBConnection.closeConnection(conn);
		}
		return check;
	}

	// Update policy
	public VehicleInsurance updatePolicyDetails(int policyReferenceNumber,
			String paymentMode, String paymentFrequency) throws Exception {
		VehicleInsurance vinsurance = new VehicleInsurance();
		try {
			InsuranceDAO idao = new InsuranceDAO();
			idao.makePolicyInactive();
			conn = DBConnection.getConnection();
			pst1 = conn.prepareStatement("UPDATE INSURANCE SET PAYMENT_MODE=?,"
					+ "PAYMENT_FREQUENCY=? WHERE POLICY_REFERENCE_NUMBER=?");
			pst1.setString(1, paymentMode);
			pst1.setString(2, paymentFrequency);
			pst1.setInt(3, policyReferenceNumber);
			int x=pst1.executeUpdate();
			conn.commit();
			InsuranceDAO insuiranceDAO = new InsuranceDAO();
			if(x>0)
				vinsurance = insuiranceDAO.viewPolicy(policyReferenceNumber);
			else
				vinsurance=null;
		} catch (Exception e) {
		} finally {
			DBConnection.closeStatement(pst2);
			DBConnection.closeStatement(pst1);
			DBConnection.closeConnection(conn);
		}

		return vinsurance;
	}

	// view single policy to update
	public VehicleInsurance viewPolicy(int policyReferenceNumber)
			throws SQLException {
		VehicleInsurance insurance = new VehicleInsurance();
		try {
			InsuranceDAO idao = new InsuranceDAO();
			idao.makePolicyInactive();
			conn = DBConnection.getConnection();
			String query = "SELECT INSURANCE.POLICY_REFERENCE_NUMBER,CUSTOMER_ID,POLICY_ID, POLICY_COMMENCEMENT_DATE, POLICY_END_DATE, "
					+ "PAYMENT_MODE, PAYMENT_FREQUENCY, POLICY_TENURE, POLICY_PREMIUM_AMOUNT, POLICY_STATUS, POLICY_TYPE,VEHICLE_NO, "
					+ "LICENSE_NO, VEHICLE_TYPE FROM INSURANCE LEFT JOIN VEHICLE_INSURANCE ON "
					+ "VEHICLE_INSURANCE.POLICY_REFERENCE_NUMBER = INSURANCE.POLICY_REFERENCE_NUMBER WHERE INSURANCE.POLICY_REFERENCE_NUMBER=? AND POLICY_STATUS='ACTIVE'";
			pst1 = conn.prepareStatement(query);
			pst1.setInt(1, policyReferenceNumber);
			rs = pst1.executeQuery();
			int x1=0;
			while (rs.next()) {
				x1++;
				insurance.setPolicyReferenceNumber(policyReferenceNumber);
				insurance.setCustomerId(rs.getInt(2));
				insurance.setPolicyId(rs.getInt(3));
				insurance.setPolicyCommencementDate((Date) rs.getDate(4));
				insurance.setPolicyEndDate((Date) rs.getDate(5));
				insurance.setPaymentMode(rs.getString(6));
				insurance.setPaymentFrequency(rs.getString(7));
				insurance.setPolicyTenure(rs.getInt(8));
				insurance.setPremiumAmount(rs.getDouble(9));
				insurance.setPolicyStatus(rs.getString(10));
				insurance.setPolicyType(rs.getString(11));
				insurance.setVehicleNo(rs.getString(12));
				insurance.setLicenseNo(rs.getString(13));
				insurance.setVehicleType(rs.getString(14));
			}
			if(x1==0)
				insurance=null;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(pst2);
			DBConnection.closeStatement(pst1);
			DBConnection.closeConnection(conn);

		}
		return insurance;
	}

	
	// View Policies of a customer for deletion
		public ArrayList<VehicleInsurance> viewPoliciesToDelete(int customerId,String otype)
				throws SQLException {
			ArrayList<VehicleInsurance> list = new ArrayList<VehicleInsurance>();
			String query="";
			try {
				InsuranceDAO idao = new InsuranceDAO();
				idao.makePolicyInactive();
				conn = DBConnection.getConnection();
				if(otype.equalsIgnoreCase("Insurance officer")){
				 query= "SELECT INSURANCE.POLICY_REFERENCE_NUMBER,CUSTOMER_ID,POLICY_ID, POLICY_COMMENCEMENT_DATE, POLICY_END_DATE, "
						+ "PAYMENT_MODE, PAYMENT_FREQUENCY, POLICY_TENURE, POLICY_PREMIUM_AMOUNT, POLICY_STATUS, POLICY_TYPE,VEHICLE_NO, "
						+ "LICENSE_NO, VEHICLE_TYPE FROM INSURANCE LEFT JOIN VEHICLE_INSURANCE ON "
						+ "VEHICLE_INSURANCE.POLICY_REFERENCE_NUMBER = INSURANCE.POLICY_REFERENCE_NUMBER WHERE CUSTOMER_ID=? AND POLICY_TYPE='HEALTH'";
				}
				if(otype.equalsIgnoreCase("Field officer")){
					query = "SELECT INSURANCE.POLICY_REFERENCE_NUMBER,CUSTOMER_ID,POLICY_ID, POLICY_COMMENCEMENT_DATE, POLICY_END_DATE, "
							+ "PAYMENT_MODE, PAYMENT_FREQUENCY, POLICY_TENURE, POLICY_PREMIUM_AMOUNT, POLICY_STATUS, POLICY_TYPE,VEHICLE_NO, "
							+ "LICENSE_NO, VEHICLE_TYPE FROM INSURANCE LEFT JOIN VEHICLE_INSURANCE ON "
							+ "VEHICLE_INSURANCE.POLICY_REFERENCE_NUMBER = INSURANCE.POLICY_REFERENCE_NUMBER WHERE CUSTOMER_ID=? AND POLICY_TYPE='VEHICLE'";
					}
				pst1 = conn.prepareStatement(query);
				pst1.setInt(1, customerId);
				rs = pst1.executeQuery();
				int x1=0;
				while (rs.next()) {
					x1++;
					VehicleInsurance insurance = new VehicleInsurance();
					insurance.setPolicyReferenceNumber(rs.getInt(1));
					insurance.setCustomerId(rs.getInt(2));
					insurance.setPolicyId(rs.getInt(3));
					insurance.setPolicyCommencementDate((Date) rs.getDate(4));
					insurance.setPolicyEndDate((Date) rs.getDate(5));
					insurance.setPaymentMode(rs.getString(6));
					insurance.setPaymentFrequency(rs.getString(7));
					insurance.setPolicyTenure(rs.getInt(8));
					insurance.setPremiumAmount(rs.getDouble(9));
					insurance.setPolicyStatus(rs.getString(10));
					insurance.setPolicyType(rs.getString(11));
					insurance.setVehicleNo(rs.getString(12));
					insurance.setLicenseNo(rs.getString(13));
					insurance.setVehicleType(rs.getString(14));
					list.add(insurance);
				}
				
				if(x1==0)
					list=null;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBConnection.closeResultSet(rs);
				DBConnection.closeStatement(pst2);
				DBConnection.closeStatement(pst1);
				DBConnection.closeConnection(conn);
			}
			return list;
		}

	// terminate a policy
	public boolean[] terminatePolicy(String[] policyIdlist) throws Exception {

		VehicleInsurance vehicleInsurance = new VehicleInsurance();
		InsuranceDAO insuiranceDAO = new InsuranceDAO();
		boolean[] checks = new boolean[policyIdlist.length];
		Date date2 = new Date();
		int ix=0;
		try {
			insuiranceDAO.makePolicyInactive();
			conn = DBConnection.getConnection();
			for (String ListOfPolicies : policyIdlist) {
				vehicleInsurance = insuiranceDAO.viewPolicy(Integer.parseInt(ListOfPolicies));
				if (vehicleInsurance.getVehicleNo() == null
						|| vehicleInsurance.getVehicleNo() == "")
					vehicleInsurance.setVehicleNo("NA");
				if (vehicleInsurance.getLicenseNo() == null
						|| vehicleInsurance.getLicenseNo() == "")
					vehicleInsurance.setLicenseNo("NA");
				if (vehicleInsurance.getVehicleType() == null
						|| vehicleInsurance.getVehicleType() == "")
					vehicleInsurance.setVehicleType("NA");
				long diff = Math.abs(vehicleInsurance
						.getPolicyCommencementDate().getTime()
						- date2.getTime());

				long diffDays = diff / (24 * 60 * 60 * 1000);
				if (diffDays < 15) {
					pst2 = conn
							.prepareStatement("DELETE FROM INSURANCE WHERE POLICY_REFERENCE_NUMBER=?");
					pst2.setInt(1, vehicleInsurance.getPolicyReferenceNumber());
					int i = pst2.executeUpdate();
					conn.commit();
					if (i > 0) {
						pst1 = conn
								.prepareStatement("INSERT INTO INSURANCE_HISTORY VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
						pst1.setInt(1,
								vehicleInsurance.getPolicyReferenceNumber());
						pst1.setInt(2, vehicleInsurance.getPolicyId());
						pst1.setInt(3, vehicleInsurance.getCustomerId());
						pst1.setDate(4, new java.sql.Date(vehicleInsurance
								.getPolicyCommencementDate().getTime()));
						pst1.setDate(5, new java.sql.Date(vehicleInsurance
								.getPolicyEndDate().getTime()));
						pst1.setString(6, vehicleInsurance.getPaymentMode());
						pst1.setString(7,
								vehicleInsurance.getPaymentFrequency());
						pst1.setInt(8, vehicleInsurance.getPolicyTenure());
						pst1.setDouble(9, vehicleInsurance.getPremiumAmount());
						pst1.setString(10, "INACTIVE");
						pst1.setString(11, vehicleInsurance.getPolicyType());
						pst1.setDate(12,
								new java.sql.Date((new Date()).getTime()));
						pst1.setString(13, vehicleInsurance.getVehicleNo());
						pst1.setString(14, vehicleInsurance.getLicenseNo());
						pst1.setString(15, vehicleInsurance.getVehicleType());
						pst1.executeUpdate();
						conn.commit();
						checks[ix++]=true;
					}
				} else {
					checks[ix++]= false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeStatement(pst3);
			DBConnection.closeStatement(pst1);
			DBConnection.closeStatement(pst2);
			DBConnection.closeConnection(conn);
		}
		return checks;
	}
	
	
	//showing all available policies
	
	public ArrayList<Policy> allPolicies() throws SQLException {
		ArrayList<Policy> list = new ArrayList<Policy>();
		try {
			InsuranceDAO idao = new InsuranceDAO();
			idao.makePolicyInactive();
			conn = DBConnection.getConnection();
			pst1 = conn.prepareStatement("SELECT * FROM POLICY_DETAILS");
			rs = pst1.executeQuery();
			int x1=0;
			while (rs.next()) {
				x1++;
				Policy policy = new Policy();
				policy.setPolicyId(rs.getInt(1));
				policy.setPolicyName(rs.getString(2));
				policy.setPolicyDescription(rs.getString(3));
				policy.setPolicyType(rs.getString(4));
				list.add(policy);
			}
			if(x1==0)
				list=null;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(pst1);
			DBConnection.closeConnection(conn);
		}
		return list;
	}
	
	
	// making policy inactive
	
	public void makePolicyInactive() throws Exception
	{
		try{
			conn=DBConnection.getConnection();
			pst1 = conn.prepareStatement("UPDATE INSURANCE SET POLICY_STATUS='INACTIVE' WHERE POLICY_END_DATE<=TO_DATE(CURRENT_DATE,'dd/MM/yy')");
			pst1.executeUpdate();
			conn.commit();
		}
		finally{
			DBConnection.closeStatement(pst1);
			DBConnection.closeConnection(conn);
		}
	}
	
	
	//returning policy type
	public String viewPolicyType(int policyId) throws Exception
	{
		String type1=null;
		try{
			InsuranceDAO idao = new InsuranceDAO();
			idao.makePolicyInactive();
			conn=DBConnection.getConnection();
			pst1 = conn.prepareStatement("SELECT POLICY_TYPE FROM POLICY_DETAILS WHERE POLICY_ID=?");
			pst1.setInt(1, policyId);
			rs = pst1.executeQuery();
			int rx=0;
			while(rs.next())
			{
				rx++;
				type1=rs.getString(1);
			}
			if(rx==0)
				type1=null;
		}
		catch(SQLException e){
			type1=null;
		}
		finally{
			DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(pst1);
			DBConnection.closeConnection(conn);
		}
		return type1;
	}
	
	
	
	//checking policy id
		public boolean checkPolicyId(int custId, int policyId) throws Exception
		{
			boolean exists=false;
			try{
				InsuranceDAO idao = new InsuranceDAO();
				idao.makePolicyInactive();
				conn=DBConnection.getConnection();
				pst1 = conn.prepareStatement("SELECT POLICY_REFERENCE_NUMBER FROM INSURANCE WHERE CUSTOMER_ID=? AND POLICY_ID=? AND POLICY_STATUS='ACTIVE'");
				pst1.setInt(1, custId);
				pst1.setInt(2, policyId);
				rs = pst1.executeQuery();
				int rx=0;
				while(rs.next())
				{
					rx++;
				}
				if(rx>0)
					exists=true;
			}
			catch(SQLException e){
				exists=true;
			}
			finally{
				DBConnection.closeResultSet(rs);
				DBConnection.closeStatement(pst1);
				DBConnection.closeConnection(conn);
			}
			return exists;
		}
	
}
