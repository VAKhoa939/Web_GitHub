package data;

import java.util.*;
import java.sql.*;
import jakarta.persistence.*;
import business.Cart;

public class CartDB 
{
	// JDBA functions
	public static int insert(Cart cart) 
	{
		return 0;
	}
	
	public static int update(Cart cart) 
	{
		return 0;
	}
	
	public static int delete(Cart cart) 
	{
		return 0;
	}
	
	public static boolean cartCodeExists(String cartCode)
	{
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT CartCode FROM Cart "
				+ "WHERE CartCode = ?";
		try 
		{
			ps = connection.prepareStatement(query);
			ps.setString(1, cartCode);
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

	public static Cart selectCart(String cartCode)
	{
		return new Cart();
	}
	
	public static List<Cart> selectCarts()
	{
		return new ArrayList<Cart>();
	}

	// JPA functions
}
