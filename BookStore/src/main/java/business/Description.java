package business;

import java.io.Serializable;
import java.util.*;
import jakarta.persistence.*;

public class Description implements Serializable
{
	private String bookName;
	private String author;
	private String publisher;
	private Date releaseDate;
	
	public Description()
	{
		bookName = "";
		author = "";
		publisher = "";
		releaseDate = new Date();
	}

	public Description(String bookName, String author, String publisher, Date releaseDate) 
	{
		this.bookName = bookName;
		this.author = author;
		this.publisher = publisher;
		this.releaseDate = releaseDate;
	}

	public String getBookName() 
	{
		return bookName;
	}

	public void setBookName(String bookName) 
	{
		this.bookName = bookName;
	}

	public String getAuthor() 
	{
		return author;
	}

	public void setAuthor(String author) 
	{
		this.author = author;
	}

	public String getPublisher() 
	{
		return publisher;
	}

	public void setPublisher(String publisher) 
	{
		this.publisher = publisher;
	}

	public Date getReleaseDate() 
	{
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) 
	{
		this.releaseDate = releaseDate;
	}
}
