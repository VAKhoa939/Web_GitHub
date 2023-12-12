package business;

import java.io.Serializable;
import java.util.*;
import jakarta.persistence.*;

@Entity
public class Cart implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long cartId;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private ArrayList<LineItem> items;
	
	@OneToOne
	@JoinColumn(name = "userID")
	private User user;
	
	public Cart()
	{
		items = new ArrayList<LineItem>();
		user = new User();
	}

	public Cart(ArrayList<LineItem> items, User user) 
	{
		this.items = items;
		this.user = user;
	}

	public long getCartId() 
	{
		return cartId;
	}

	public void setCartId(long cartId) 
	{
		this.cartId = cartId;
	}
	

	public ArrayList<LineItem> getItems() 
	{
		return items;
	}

	public void setItems(ArrayList<LineItem> items) 
	{
		this.items = items;
	}

	public User getUser() 
	{
		return user;
	}

	public void setUser(User user) 
	{
		this.user = user;
	}
	
	public void addItem(LineItem item)
	{
		this.items.add(item);
	}
	
	public void removeItem(LineItem item)
	{
		String productCode = item.getProduct().getProductCode();
		
		for (int i = 0; i < items.size(); i++) 
		{
			LineItem temp = items.get(i);
			if (temp.getItemCode().equals(productCode)) 
			{
				this.items.remove(i);
				break;
			}
		}
	}
	
	public double getTotal()
	{
		double total = 0;
		for (LineItem item : items)
		{
			total += item.getTotal();
		}
		return total;
	}
	
	public String getTotalCurrencyFormat()
	{
		return getTotal() + "VND";
	}
}
