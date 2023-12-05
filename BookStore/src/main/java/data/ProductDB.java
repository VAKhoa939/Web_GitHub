package data;

import java.util.*;
import java.sql.*;
import jakarta.persistence.*;
import business.Product;

public class ProductDB 
{
	// JDBA functions
	public static int insert(Product product) 
	{
		return 0;
	}
	
	public static int update(Product product) 
	{
		return 0;
	}
	
	public static int delete(Product product) 
	{
		return 0;
	}
	
	public static boolean productCodeExists(String productCode)
	{
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT ProductCode FROM Product "
				+ "WHERE ProductCode = ?";
		try 
		{
			ps = connection.prepareStatement(query);
			ps.setString(1, productCode);
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

	public static Product selectProduct(String productCode)
	{
		return new Product();
	}
	
	public static List<Product> selectProducts()
	{
		return new ArrayList<Product>();
	}

	// JPA functions
}
