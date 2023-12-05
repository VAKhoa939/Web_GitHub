package data;

import java.util.*;
import java.sql.*;
import jakarta.persistence.*;
import business.Invoice;

public class InvoiceDB 
{
	// JDBA functions
	public static int insert(Invoice invoice) 
	{
		return 0;
	}
	
	public static int update(Invoice invoice) 
	{
		return 0;
	}
	
	public static int delete(Invoice invoice) 
	{
		return 0;
	}
	
	public static boolean invoiceCodeExists(String invoiceCode)
	{
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT InvoiceCode FROM Invoice "
				+ "WHERE InvoiceCode = ?";
		try 
		{
			ps = connection.prepareStatement(query);
			ps.setString(1, invoiceCode);
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

	public static Invoice selectInvoice(String invoiceCode)
	{
		return new Invoice();
	}
	
	public static List<Invoice> selectInvoices()
	{
		return new ArrayList<Invoice>();
	}

	// JPA functions
}
