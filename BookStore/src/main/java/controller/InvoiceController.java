package controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.*;

import business.*;
import data.*;
import util.*;

@WebServlet("/invoice")
public class InvoiceController extends HttpServlet 
{
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		ServletContext sc = getServletContext();
		String requestURI = request.getRequestURI();
		String url = null;
		String action = null;
		
		if (requestURI.endsWith("/account"))
		{
			action = request.getParameter("action");
			if (checkActiveSession(request, response))
			{
				if (action == null || action.isBlank())
				{
					url = "/cart.jsp";
				}
				else if (action.equals("checkout"))
				{
					url = display(request, response);
				}
				else if (action.equals("confirm"))
				{
					url = "/thanks.jsp";
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
		User user;
		synchronized(lock)
		{
			user = (User) session.getAttribute("user");
		}

		if (user != null) 
		{
			return true;
		} 
		else 
		{
			Cookie[] cookies = request.getCookies();
			String userEmail = CookieUtil.getCookieValue(cookies, "userEmail");
			if (userEmail == null || userEmail.isBlank()) 
			{
				return false;
			}
			else
			{
				user = UserDB.selectUser(userEmail);
				synchronized(lock)
				{
					session.setAttribute("user", user);
				}
				return true;
			}
		}
	}
	
	private Cart getCart(User user) 
	{
		Cart cart;
		cart = CartDB.selectCart(user.getUserId());
		return cart;
	}
	
	private String display(HttpServletRequest request, HttpServletResponse response)
	{
		HttpSession session = request.getSession();
		final Object lock = session.getId().intern();
		User user;
		synchronized(lock)
		{
			user = (User) session.getAttribute("user");
		}
		String url = "/invoice.jsp";
		String action = request.getParameter("action");
		
		Cart cart = getCart(user);
		Invoice invoice = new Invoice(cart);
		int noInvoices = user.getNoInvoices() + 1;
		user.setNoInvoices(noInvoices);
		UserDB.update(user);
		synchronized(lock)
		{
			session.setAttribute("user", user);
		}
		InvoiceDB.insert(invoice);
		request.setAttribute("invoice", invoice);
		request.setAttribute("noInvoices", noInvoices);
		return url;
	}
}
