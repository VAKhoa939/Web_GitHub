package business;

import java.io.Serializable;
import java.util.*;
import jakarta.persistence.*;

@Entity
public class Invoice implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long invoiceId;
	
	@OneToOne
	@JoinColumn(name = "cartId")
	private Cart cart;
	private String invoiceCode;
	private Date invoiceDate;
	
	public Invoice()
	{
		cart = new Cart();
		invoiceCode = "";
		invoiceDate = new Date();
	}

	public Invoice(Cart cart, String invoiceNum, Date invoiceDate) 
	{
		this.cart = cart;
		this.invoiceCode = invoiceNum;
		this.invoiceDate = invoiceDate;
	}
	
	public long getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(long invoiceId) {
		this.invoiceId = invoiceId;
	}

	public String getInvoiceCode() {
		return invoiceCode;
	}

	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}

	public Cart getCart() 
	{
		return cart;
	}

	public void setCart(Cart cart) 
	{
		this.cart = cart;
	}


	public Date getInvoiceDate() 
	{
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) 
	{
		this.invoiceDate = invoiceDate;
	}
	
	
}
