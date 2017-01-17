package com.ilp.ims.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.ilp.ims.Bean.Customer;
import com.ilp.ims.DAO.CustomerDAO;

public class CustomerService {
	
	CustomerDAO customerDAO = new CustomerDAO();
	
	public int registerCustomer(Customer customer) throws SQLException {		
		return customerDAO.registerCustomer(customer);		
	}
	public boolean checkEmail(String email) throws SQLException {
		return customerDAO.checkEmail(email);
	}
	
	public Customer getCustomerDetails(int customerId) {
		return customerDAO.getCustomerDetails(customerId);
	}
	
	public boolean updateCustomerDetails(Customer customer) throws SQLException {
		return customerDAO.updateCustomerDetails(customer);
	}
	
	public ArrayList<Customer> viewCustomerDetails(Date startDate,Date endDate) throws SQLException {
		return customerDAO.viewCustomerDetails(startDate,endDate);
	}
	
	public boolean checkPolicyStatus(int customerId) throws SQLException {
		return customerDAO.checkPolicyStatus(customerId);
	}
	public boolean removeCustomer(int customerId) throws SQLException {
		return customerDAO.removeCustomer(customerId);
	}
	
	public boolean checkCustomerId(int customerId) throws SQLException {
		return customerDAO.checkCustomerId(customerId);
	}

}
