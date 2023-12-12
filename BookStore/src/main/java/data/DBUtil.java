package data;

import jakarta.persistence.*;

public class DBUtil 
{
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("bookStorePU");
	
	public static EntityManagerFactory getEmFactory()
	{
		return emf;
	}
}
