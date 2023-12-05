package data;

import java.sql.*;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DBUtil 
{
	// JDBA functions
	public static void closeStatement(Statement s)
	{
		try
		{
			if (s != null)
			{
				s.close();
			}
		}
		catch (SQLException e)
		{
			System.out.println(e);
		}
	}

	public static void closePreparedStatement(Statement ps)
	{
		try
		{
			if (ps != null)
			{
				ps.close();
			}
		}
		catch (SQLException e)
		{
			System.out.println(e);
		}
	}

	public static void closeResultSet(ResultSet rs)
	{
		try
		{
			if (rs != null)
			{
				rs.close();
			}
		}
		catch (SQLException e)
		{
			System.out.println(e);
		}
	}
	
	/*
	// JPA functions
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("emailListPU");
	
	public static EntityManagerFactory getEmFactory()
	{
		return emf;
	}
	*/
}
