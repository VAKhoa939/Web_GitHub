package data;

import java.util.*;
import java.sql.*;
import jakarta.persistence.*;
import business.Description;
import business.User;

public class DescriptionDB 
{
	// JDBA functions
	public static int insert(Description description) 
	{
		return 0;
	}
	
	public static int update(Description description) 
	{
		return 0;
	}
	
	public static int delete(Description description) 
	{
		return 0;
	}
	
	public static boolean bookNameExists(String bookName)
	{
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT BookName FROM Description "
				+ "WHERE BookName = ?";
		try 
		{
			ps = connection.prepareStatement(query);
			ps.setString(1, bookName);
			rs = ps.executeQuery();
			return rs.next();
		} 
		catch (SQLException e) 
		{
			System.out.println(e);
			return false;
		} 
		finally 
		{
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(ps);
			pool.freeConnection(connection);
		} 
	}

	public static Description selectDescription(String bookName)
	{
		return new Description();
	}

	// JPA functions
}
