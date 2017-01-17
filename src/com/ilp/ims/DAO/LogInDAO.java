package com.ilp.ims.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ilp.ims.Bean.Officer;
import com.ilp.ims.Util.DBConnection;

public class LogInDAO {

	public static String checkLogin(Officer officer) throws SQLException, ClassNotFoundException
	{
		String type=null;
		PreparedStatement ps = null;
		Connection con = null;
		con=DBConnection.getConnection();
		
		ps=con.prepareStatement("SELECT OFFICER_TYPE FROM LOGIN where OFFICER_ID=? AND PASSWORD=?");
		ps.setInt(1,officer.getUserId());
		ps.setString(2,officer.getPassword());
		
		ResultSet rs=ps.executeQuery();
	
		int s=0;
		while(rs.next())
		{
			s++;
			type=rs.getString(1);
		}
		
		if(s==1)
		{
			return type;
		}
		else
			return type;
		
	}
	
}
