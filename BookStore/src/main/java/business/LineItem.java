package business;

import java.io.Serializable;
import jakarta.persistence.*;

@Entity
public class LineItem implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long itemId;
	private String itemCode;
	private int quantity;

	@OneToOne
	@JoinColumn(name = "productId")
	private Product product;
	private double discount;
	
	public LineItem()
	{
		quantity = 0;
		product = new Product();
		discount = 0;
	}

	public LineItem(int quantity, Product item, double discount) 
	{
		this.quantity = quantity;
		this.product = item;
		this.discount = discount;
	}
	
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
		return product;
	}

	public void setItem(Product item) 
	{
		this.product = item;
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
