package controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.*;

import business.*;
import data.*;
import util.*;

@WebServlet("/account")
public class AccountController extends HttpServlet 
{
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		ServletContext sc = getServletContext();
		String requestURI = request.getRequestURI();
		String url = null;
		String action = null;
		
		if (requestURI.endsWith("/account"))
		{
			if (checkActiveSession(request, response))
			{
				action = request.getParameter("action");
				if (action == null || action.isEmpty())
				{
					url = "/account.jsp";
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
}
