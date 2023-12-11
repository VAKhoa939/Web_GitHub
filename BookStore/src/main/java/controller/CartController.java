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
				else if (action.equals("new"))
				{
					url = addToCart(request, response);
				}
				else if (action.equals("change"))
				{
					
				}
				else if (action.equals("remove"))
				{
					
				}
				else if (action.equals("checkout"))
				{
					url = "/invoice.jsp";
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

	private String addToCart(HttpServletRequest request, HttpServletResponse response)
	{
		HttpSession session = request.getSession();
		final Object lock = session.getId().intern();
		User user = (User) session.getAttribute("user");
		String url = "/product_detail.jsp";
		
		String productCode = request.getParameter("productCode");
		Cart cart = CartDB.selectCart(user.getUserId());
		boolean itemExists = false;
		for (LineItem item : cart.getLineItems())
		{
			if (item.getItemCode().equals(productCode))
			{
				itemExists = true;
				item.increase();
				LineItemDB.update(item);
				break;
			}
		}
		if (!itemExists)
		{
			Product product = ProductDB.selectProduct(productCode);
			LineItem item = new LineItem(1, product);
		}
		String message = "Product has been added to cart.";
		
		request.setAttribute("message", message);
		return url;
	}
}
