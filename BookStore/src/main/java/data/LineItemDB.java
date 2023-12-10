package data;

import java.util.*;
import javax.persistence.*;
import business.LineItem;

public class LineItemDB 
{
	public static void insert(LineItem item) 
	{
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try
		{
			em.persist(item);
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
	
	public static void update(LineItem item) 
	{
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try
		{
			em.merge(item);
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
	
	public static void delete(LineItem item) 
	{
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try
		{
			em.remove(em.merge(item));
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
	
	public static boolean itemCodeExists(String itemCode)
	{
		LineItem u = selectLineItem(itemCode);
		return u != null;
	}
	
	public static LineItem selectLineItem(String itemCode)
	{
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String qString = "SELECT u FROM LineItem u "
				+ "WHERE u.itemCode = :itemCode";
		TypedQuery<LineItem> q = em.createNamedQuery(qString, LineItem.class);
		q.setParameter("itemCode", itemCode);
		try 
		{
			LineItem lineItem = q.getSingleResult();
			return lineItem;
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
	
	public static List<LineItem> selectLineItems()
	{
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String qString = "SELECT u FROM LineItem u ";
		TypedQuery<LineItem> q = em.createNamedQuery(qString, LineItem.class);
		List<LineItem> lineItems;
		try 
		{
			lineItems = q.getResultList();
			if (lineItems == null || lineItems.isEmpty())
				lineItems = null;
		} 
		finally 
		{
			em.close();
		} 
		return lineItems;
	}
}