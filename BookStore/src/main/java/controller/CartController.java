package controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.*;
import business.*;
import data.*;
import util.*;

@WebServlet("/cart")
public class CartController extends HttpServlet 
{
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		ServletContext sc = getServletContext();
		String requestURI = request.getRequestURI();
		String url = null;
		String action = null;
		
		if (requestURI.endsWith("/cart"))
		{
			if (checkActiveSession(request, response))
			{
				action = request.getParameter("action");
				if (action == null || action.isEmpty())
				{
					url = "/cart.jsp";
				}
				else if (action.equals("add"))
				{
					url = addQuantity(request, response);
				}
			}
			else
			{
				url = "/login.jsp";
			}
			sc.getRequestDispatcher(url).forward(request, response);
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}
	
	private boolean checkActiveSession(HttpServletRequest request, HttpServletResponse response)
	{
		HttpSession session = request.getSession();
		final Object lock = session.getId().intern();
		User user = (User) session.getAttribute("user");

		if (user != null) 
		{
			return true;
		} 
		else 
		{
			try 
			{
				Cookie[] cookies = request.getCookies();
				Long userID = Long.parseLong(CookieUtil.getCookieValue(cookies, "userIDCookie"));
				
				user = UserDB.selectUser(userID);
				if (user != null) 
				{
					session.setAttribute("user", user);
					return true;
				}
			} 
			catch (NumberFormatException ex) 
			{
				System.out.println(ex);
			}
		}
		return false;
	}

	private String addQuantity(HttpServletRequest request, HttpServletResponse response)
	{
		String url = null;
		url = "/product_detail.jsp";
		String productCode = request.getParameter("productCode");
		//Product product = ProductDB.selectProduct(productCode);
		
		List<Product> products = new ArrayList<Product>();
		products.add(new Product("Adventuretime", new Description("Adventuretime", "", "", "It's Adventure Time!", "", new Date()), 50000));
		products.add(new Product("Adventuretime0", new Description("Adventuretime", "", "", "0", "", new Date()), 50000));
		products.add(new Product("Adventuretime1", new Description("Adventuretime", "", "", "1", "", new Date()), 50000));
		products.add(new Product("Adventuretime2", new Description("Adventuretime", "", "", "2", "", new Date()), 50000));
		products.add(new Product("Adventuretime3", new Description("Adventuretime", "", "", "3", "", new Date()), 50000));
		products.add(new Product("Adventuretime4", new Description("Adventuretime", "", "", "4", "", new Date()), 50000));
		
		Product product = new Product();
		for (Product p : products)
		{
			if (p.getProductCode().equals(productCode))
			{
				product = p;
				break;
			}
		}
		
		String message = "Product has been added to cart.";
		
		request.setAttribute("message", message);
		request.setAttribute("product", product);
		return url;
	}
}
