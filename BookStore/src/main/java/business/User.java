package business;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class User implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long userId;
	private String userName;
	private String email;
	private String addr;
	private String phoneNo;
	private String password;

	public User()
	{
		userName = "";
		email = "";
		addr = "";
		phoneNo = "";
		password = "";
	}

	public User(String userName, String email, String addr, String phoneNo) 
	{
		this.userName = userName;
		this.email = email;
		this.addr = addr;
		this.phoneNo = phoneNo;
	}
	
	public long getUserId() 
	{
		return userId;
	}

	public void setUserId(long userId) 
	{
		this.userId = userId;
	}

	public String getUserName() 
	{
		return userName;
	}

	public void setUserName(String userName) 
	{
		this.userName = userName;
	}

	public String getEmail() 
	{
		return email;
	}

	public void setEmail(String email) 
	{
		this.email = email;
	}

	public String getAddr() 
	{
		return addr;
	}

	public void setAddr(String addr) 
	{
		this.addr = addr;
	}

	public String getPhoneNo() 
	{
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) 
	{
		this.phoneNo = phoneNo;
	}

	public String getPassword() 
	{
		return password;
	}

	public void setPassword(String password) 
	{
		this.password = password;
	}
}
