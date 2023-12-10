package data;

import java.util.*;
import javax.persistence.*;
import business.Cart;

public class CartDB 
{
	public static void insert(Cart cart) 
	{
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try
		{
			em.persist(cart);
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
	
	public static void update(Cart cart) 
	{
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try
		{
			em.merge(cart);
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
	
	public static void delete(Cart cart) 
	{
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try
		{
			em.remove(em.merge(cart));
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
	
	public static boolean cartCodeExists(String cartCode)
	{
		Cart u = selectCart(cartCode);
		return u != null;
	}
	
	public static Cart selectCart (String cartCode)
	{
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String qString = "SELECT u FROM Cart u "
				+ "WHERE u.cart = :cart";
		TypedQuery<Cart> q = em.createNamedQuery(qString, Cart.class);
		q.setParameter("cartCode", cartCode);
		try 
		{
			Cart cart = q.getSingleResult();
			return cart;
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

	public static List<Cart> selectCarts()
	{
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String qString = "SELECT u FROM Cart u ";
		TypedQuery<Cart> q = em.createNamedQuery(qString, Cart.class);
		List<Cart> carts;
		try 
		{
			carts = q.getResultList();
			if (carts == null || carts.isEmpty())
				carts = null;
		} 
		finally 
		{
			em.close();
		} 
		return carts;
	}
}