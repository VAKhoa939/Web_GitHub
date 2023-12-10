package business;

import java.io.Serializable;
import jakarta.persistence.*;

@Entity
public class Product implements Serializable
{
	@Id
	private String productCode;
	
	@OneToOne
	@JoinColumn(name = "descId")
	private Description infor;
	private double price;
	
	public Product()
	{
		productCode = "";
		infor = new Description();
		price = 0;
	}

	public Product(String productCode, Description infor, double price) 
	{
		this.productCode = productCode;
		this.infor = infor;
		this.price = price;
	}

	public String getProductCode() 
	{
		return productCode;
	}

	public void setProductCode(String productCode) 
	{
		this.productCode = productCode;
	}

	public Description getInfor() 
	{
		return infor;
	}

	public void setInfor(Description infor) 
	{
		this.infor = infor;
	}

	public double getPrice() 
	{
		return price;
	}

	public void setPrice(double price) 
	{
		this.price = price;
	}
	
	public String getCurrencyFormat()
	{
		return price + " VND";
	}
	
	public String getlmageURL() 
	{
		String imageURL = "/images/" + productCode + ".png";
		return imageURL;
	}
}
