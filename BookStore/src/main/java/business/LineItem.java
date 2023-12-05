package business;

import java.io.Serializable;
import jakarta.persistence.*;

public class LineItem implements Serializable
{
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
