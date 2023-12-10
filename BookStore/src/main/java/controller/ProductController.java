package controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.*;
import business.*;
import data.*;

@WebServlet("/detail")
public class ProductController extends HttpServlet 
{
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		ServletContext sc = getServletContext();
		String requestURI = request.getRequestURI();
		String url = null;
		String action = null;

		if (requestURI.endsWith("/detail"))
		{
			url = displayProductDetail(request, response);
			sc.getRequestDispatcher(url).forward(request, response);
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}

	private String displayProductDetail(HttpServletRequest request, HttpServletResponse response)
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
		
		request.setAttribute("product", product);
		return url;
	}
}
