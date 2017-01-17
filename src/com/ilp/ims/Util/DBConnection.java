package com.ilp.ims.Util;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {
	
	static final String DRIVER_NAME="oracle.jdbc.driver.OracleDriver";
	static final String URL="jdbc:oracle:thin:@172.26.132.40:1521:orclilp";
	static final String USERNAME="ja40e";
	static final String PASSWORD="ja40e";
	
	public static void closeConnection(Connection con) throws SQLException
	{
		if(con!=null)
		con.close();
	}
	
	public static void closeStatement(PreparedStatement ps) throws SQLException
	{
		if(ps!=null)
		ps.close();
	}
	
	public static void closeResultSet(ResultSet rs) throws SQLException
	{
		if(rs!=null)
		rs.close();
	}
	
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException
	{
		Class.forName(DRIVER_NAME);
		Connection con=DriverManager.getConnection(URL, USERNAME,PASSWORD);
		return con;
	}
	

}
