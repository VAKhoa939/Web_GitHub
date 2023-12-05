package data;

import java.util.*;
import java.sql.*;
import jakarta.persistence.*;
import business.LineItem;

public class LineItemDB 
{
	// JDBA functions
	public static int insert(LineItem item) 
	{
		return 0;
	}
	
	public static int update(LineItem item) 
	{
		return 0;
	}
	
	public static int delete(LineItem item) 
	{
		return 0;
	}
	
	public static boolean itemCodeExists(String itemCode)
	{
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT ItemCode FROM LineItem "
				+ "WHERE ItemCode = ?";
		try 
		{
			ps = connection.prepareStatement(query);
			ps.setString(1, itemCode);
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

	public static LineItem selectLineItem(String itemCode)
	{
		return new LineItem();
	}
	
	public static List<LineItem> selectLineItems()
	{
		return new ArrayList<LineItem>();
	}

	// JPA functions
}
