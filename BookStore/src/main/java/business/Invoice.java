package business;

import java.io.Serializable;
import java.util.*;
import jakarta.persistence.*;

public class Invoice implements Serializable
{
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

	public Cart getCart() 
	{
		return cart;
	}

	public void setCart(Cart cart) 
	{
		this.cart = cart;
	}

	public String getInvoiceNum() 
	{
		return invoiceCode;
	}

	public void setInvoiceNum(String invoiceCode) 
	{
		this.invoiceCode = invoiceCode;
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
