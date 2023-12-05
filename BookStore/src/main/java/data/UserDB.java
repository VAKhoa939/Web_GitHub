package data;

import java.util.*;
import java.sql.*;
import jakarta.persistence.*;
import business.User;



public class UserDB 
{
	// JDBA functions
	public static int insert(User user) 
	{
		/*
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		String query = "INSERT INTO User (Email, FirstName, LastName) "
				+ "VALUES (?, ?, ?)";
		try 
		{
			ps = connection.prepareStatement(query);
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getFirstName());
			ps.setString(3, user.getLastName());
			return ps.executeUpdate();
		} 
		catch (SQLException e) 
		{
			System.out.println(e);
			return 0;
		}
		finally 
		{
			DBUtil.closePreparedStatement(ps);
			pool.freeConnection(connection);
		}
		*/
		return 0;
	}
	
	public static int update(User user) 
	{
		/*
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		String query = "UPDATE User SET "
				+ "FirstName = ?, "
				+ "LastName = ? "
				+ "WHERE Email = ?";
		try 
		{
			ps = connection.prepareStatement(query);
			ps.setString(1, user.getFirstName());
			ps.setString(2, user.getLastName());
			ps.setString(3, user.getEmail());
			return ps.executeUpdate();
		} 
		catch (SQLException e) 
		{
			System.out.println(e);
			return 0;
		} 
		finally 
		{
			DBUtil.closePreparedStatement(ps);
			pool.freeConnection(connection);
		}
		*/
		return 0;
	}
	
	public static int delete(User user) 
	{
		/*
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		String query = "DELETE FROM User "
				+ "WHERE Email = ?";
		try 
		{
			ps = connection.prepareStatement(query);
			ps.setString(1, user.getEmail());
			return ps.executeUpdate();
		} 
		catch (SQLException e) 
		{
			System.out.println(e);
			return 0;
		} 
		finally 
		{
			DBUtil.closePreparedStatement(ps);
			pool.freeConnection(connection);
		}
		*/
		return 0;
	}
	
	public static boolean emailExists(String email)
	{
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT Email FROM User "
				+ "WHERE Email = ?";
		try 
		{
			ps = connection.prepareStatement(query);
			ps.setString(1, email);
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

	public static User selectUser(String email)
	{
		/*
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT * FROM User "
				+ "WHERE Email = ?";
		try 
		{
			ps = connection.prepareStatement(query);
			ps.setString(1, email);
			rs = ps.executeQuery();
			User user = null;
			if (rs.next())
			{
				user = new User();
				user.setFirstName(rs.getString("FirstName"));
				user.setLastName(rs.getString("LastName"));
				user.setEmail(rs.getString("Email"));
			}
			return user;
		} 
		catch (SQLException e) 
		{
			System.out.println(e);
			return null;
		} 
		finally 
		{
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(ps);
			pool.freeConnection(connection);
		}
		*/
		return new User();
	}
	
	public static List<User> selectUsers()
	{
		/*
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT * FROM User";
		try 
		{
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			List<User> users = new ArrayList<User>();
			while (rs.next())
			{
				User user = new User();
				user.setFirstName(rs.getString("FirstName"));
				user.setLastName(rs.getString("LastName"));
				user.setEmail(rs.getString("Email"));
				users.add(user);
			}
			return users;
		} 
		catch (SQLException e) 
		{
			System.out.println(e);
			return null;
		} 
		finally 
		{
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(ps);
			pool.freeConnection(connection);
		}
		*/
		return new ArrayList<User>();
	}


	/*
	// JPA functions
	public static void insert(User user) 
	{
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try
		{
			em.persist(user);
			trans.commit();
		}
		catch (Exception e)
		{
			System.out.println(e);
			trans.rollback();
		}
		finally 
		{
			em.close();
		}
	}
	
	public static void update(User user) 
	{
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try
		{
			em.merge(user);
			trans.commit();
		}
		catch (Exception e)
		{
			System.out.println(e);
			trans.rollback();
		}
		finally 
		{
			em.close();
		}
	}
	
	public static void delete(User user) 
	{
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try
		{
			em.remove(em.merge(user));
			trans.commit();
		}
		catch (Exception e)
		{
			System.out.println(e);
			trans.rollback();
		}
		finally 
		{
			em.close();
		}
	}
	
	public static boolean emailExists(String email)
	{
		User u = selectUser(email);
		return u != null;
	}
	
	public static User selectUser(String email)
	{
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String qString = "SELECT u FROM User u "
				+ "WHERE u.email = :email";
		TypedQuery<User> q = em.createNamedQuery(qString, User.class);
		q.setParameter("email", email);
		try 
		{
			User user = q.getSingleResult();
			return user;
		} 
		catch (NoResultException e) 
		{
			System.out.println(e);
			return null;
		} 
		finally 
		{
			em.close();
		} 
	}

	public static List<User> selectUsers()
	{
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String qString = "SELECT u FROM User u ";
		TypedQuery<User> q = em.createNamedQuery(qString, User.class);
		List<User> users;
		try 
		{
			users = q.getResultList();
			if (users == null || users.isEmpty())
				users = null;
		} 
		finally 
		{
			em.close();
		} 
		return users;
	}
	*/
}

