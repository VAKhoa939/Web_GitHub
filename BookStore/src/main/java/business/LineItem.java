package business;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class LineItem implements Serializable
{
	private long itemId;
	private String itemCode;
	private int quantity;
	private Product item;
	private double discount;
	
	public LineItem()
	{
		quantity = 0;
		item = new Product();
		discount = 0;
	}

	public LineItem(int quantity, Product item, double discount) 
	{
		this.quantity = quantity;
		this.item = item;
		this.discount = discount;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getItemId() 
	{
		return itemId;
	}

	public void setItemId(long itemId) 
	{
		this.itemId = itemId;
	}

	public String getItemCode() 
	{
		return itemCode;
	}

	public void setItemCode(String itemCode) 
	{
		this.itemCode = itemCode;
	}

	public int getQuantity() 
	{
		return quantity;
	}

	public void setQuantity(int quantity) 
	{
		this.quantity = quantity;
	}

	public Product getItem() 
	{
		return item;
	}

	public void setItem(Product item) 
	{
		this.item = item;
	}

	public double getDiscount() 
	{
		return discount;
	}

	public void setDiscount(double discount) 
	{
		this.discount = discount;
	}
	
	public double getTotal()
	{
		return 0;
	}
	
	public String getTotalCurrencyFormat()
	{
		return "";
	}
}
