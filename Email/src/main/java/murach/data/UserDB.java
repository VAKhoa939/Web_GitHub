/*
// Chapter 12: JDBA
package murach.data;

import java.sql.*;
import java.util.*;
import murach.business.User;

public class UserDB 
{
	public static int insert(User user) 
	{
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
	}
	
	public static int update(User user) 
	{
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
	}
	
	public static int delete(User user) 
	{
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
	}
	
	public static List<User> selectUsers()
	{
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
	}
}
*/

// Chapter 13: JPA
package murach.data;

import java.util.*;
import jakarta.persistence.*;
import murach.business.Users;

public class UserDB
{
	public static void insert(Users user) 
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
	
	public static void update(Users user) 
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
	
	public static void delete(Users user) 
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
		Users u = selectUser(email);
		return u != null;
	}
	
	public static Users selectUser(String email)
	{
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String qString = "SELECT u FROM Users u "
				+ "WHERE u.email = :email";
		TypedQuery<Users> q = em.createQuery(qString, Users.class);
		q.setParameter("email", email);
		try 
		{
			Users user = q.getSingleResult();
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

	public static List<Users> selectUserList()
	{
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String qString = "SELECT u FROM Users u";
		TypedQuery<Users> q = em.createQuery(qString, Users.class);
		List<Users> users;
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
}
