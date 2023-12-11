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
	
	public LineItem()
	{
		quantity = 0;
		product = new Product();
	}

	public LineItem(int quantity, Product product) 
	{
		this.quantity = quantity;
		this.product = product;
		itemCode = this.product.getProductCode();
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

	public void setItem(Product product) 
	{
		this.product = product;
	}

	
	public double getTotal()
	{
		return 0;
	}
	
	public String getTotalCurrencyFormat()
	{
		return "";
	}
	
	public void increase()
	{
		quantity++;
	}
	
	public void decrease()
	{
		quantity--;
		if (quantity < 0)
			quantity = 0;
	}
}
