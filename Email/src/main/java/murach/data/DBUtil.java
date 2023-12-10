package murach.data;


// Chap 12: JDBA
//import java.sql.*;
import jakarta.persistence.*;

public class DBUtil 
{
	/*
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
	*/
	//Chap 13: JPA
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("EmailListPU");
	
	public static EntityManagerFactory getEmFactory()
	{
		return emf;
	}
}

/*
//Chap 13: JPA
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DBUtil 
{
}
*/