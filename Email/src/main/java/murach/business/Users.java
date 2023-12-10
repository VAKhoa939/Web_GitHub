package murach.business;

import java.io.Serializable;
import jakarta.persistence.*;

@Entity
public class Users implements Serializable 
{
	// Field annotation
	/*
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	*/
	private long userId;
	private String firstName;
	private String lastName;
	private String email;
	
	public Users()
	{
		firstName = "";
		lastName = "";
		email = "";
	}

	public Users(String firstName, String lastName, String email) 
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getUserId() 
	{
		return userId;
	}

	public void setUserId(long userId) 
	{
		this.userId = userId;
	}

	public String getFirstName() 
	{
		return firstName;
	}

	public void setFirstName(String firstName) 
	{
		this.firstName = firstName;
	}

	public String getLastName() 
	{
		return lastName;
	}

	public void setLastName(String lastName) 
	{
		this.lastName = lastName;
	}

	public String getEmail() 
	{
		return email;
	}

	public void setEmail(String email) 
	{
		this.email = email;
	}
}