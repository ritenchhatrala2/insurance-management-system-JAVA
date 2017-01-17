package com.ilp.ims.Service;

import java.sql.SQLException;

import com.ilp.ims.Bean.Officer;

import com.ilp.ims.DAO.LogInDAO;

public class LogInService {
	
	public String checkLoginService(Officer officer) throws SQLException, ClassNotFoundException
	{
		String type=LogInDAO.checkLogin(officer);
		return type;
	}

}
