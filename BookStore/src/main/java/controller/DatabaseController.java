package controller;

import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import javax.mail.MessagingException;
import java.io.*;

import java.util.*;
import business.*;
import util.*;
import data.*;


@WebServlet(urlPatterns="/bs")
public class DatabaseController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String url = "/admin.jsp";
        ServletContext sc = getServletContext();
        String requestURI = request.getRequestURI();
        String action = request.getParameter("action");
        log("action = " + action);

        if (requestURI.endsWith("/bs")) {
            action = request.getParameter("action");
            if (action == null || action.isEmpty()) {
                List<User> users = UserDB.selectUsers();
                request.setAttribute("users", users);
                url = "/admin.jsp";
            } else if (action.equals("update")) {
                String userName = request.getParameter("userName");
                String email = request.getParameter("email");
                String addr = request.getParameter("addr");  // Corrected parameter name
                String phoneNo = request.getParameter("phoneNo");

                String message;
                if (email == null || email.isEmpty()) {
                    message = "Failed to update the user because the email text box is empty.";
                } else {
                    User user = UserDB.selectUser(email);
                    if (userName != null && !userName.isEmpty()) {
                        user.setUserName(userName);
                    }
                    if (addr != null && !addr.isEmpty()) {
                        user.setAddr(addr);
                    }
                    if (phoneNo != null && !phoneNo.isEmpty()) {
                        user.setPhoneNo(phoneNo);
                    }

                    UserDB.update(user);
                    message = "Successfully updated the user.";
                }

                request.setAttribute("message", message);
            } else if (action.equals("updateProduct")) {
                String productCode = request.getParameter("productCode");
                String price = request.getParameter("price");
                
                String message;
                if (productCode == null || productCode.isEmpty() || price == null || price.isEmpty()) {
                    message = "Please provide both the product code and the new price.";
                } else {
                    try {
                        double price2 = Double.parseDouble(price);
                        Product product = ProductDB.selectProduct(productCode);
                        if (product != null) {
                            product.setPrice(price2);
                            ProductDB.update(product);
                            message = "Successfully updated the product.";
                        } else {
                            message = "Product not found.";
                        }
                    } catch (NumberFormatException e) {
                        message = "Invalid price format. Please enter a valid number.";
                    }
                }

                request.setAttribute("message", message);
            } else if (action.equals("updateDescription")) {
                String bookName = request.getParameter("bookName");
                String author = request.getParameter("author");
                String publisher = request.getParameter("publisher");
                String detail = request.getParameter("detail");
                String genre = request.getParameter("genre");
                String releaseDateStr = request.getParameter("releaseDate");
                
                String message;
                if (bookName == null || bookName.isEmpty() || author == null || author.isEmpty() ||
                    publisher == null || publisher.isEmpty() || detail == null || detail.isEmpty() ||
                    genre == null || genre.isEmpty() || releaseDateStr == null || releaseDateStr.isEmpty()) {
                    message = "Please fill out all fields for description update.";
                } else {
                    try {
                        Date releaseDate = new Date();
                        Description updatedDescription = new Description(bookName, author, publisher, detail, genre, releaseDate);
                        DescriptionDB.update(updatedDescription);
                        message = "Successfully updated the description.";
                    } catch (IllegalArgumentException e) {
                        message = "Invalid date format. Please enter a valid date (YYYY-MM-DD).";
                    }
                    request.setAttribute("message", message);
                }
            } else if (action.equals("updateLineItem")) {
                String quantityStr = request.getParameter("quantity");
                String itemCode= request.getParameter("itemCode");
                String message;
                if (quantityStr == null || quantityStr.isEmpty() || itemCode == null || itemCode.isEmpty()) {
                    message = "Please fill out all fields for line item update.";
                } else if (LineItemDB.itemCodeExists(itemCode)) {
                	message = "This item already exists !";
                }
                else {
                    int quantity = Integer.parseInt(quantityStr);
                    LineItem updatedLineItem = LineItemDB.selectLineItem(itemCode);
                    if (updatedLineItem != null) {
                    	updatedLineItem.setQuantity(quantity);
                        LineItemDB.update(updatedLineItem);
                        message = "Successfully updated the line item.";
                    } else {
                        message = "Line item not found for the given quantity and discount.";
                    }
                }
            } else if (action.equals("delete")) { 
                String email = request.getParameter("email");
                String message;
                if (email == null || email.isEmpty()) {
                    message = "Failed to delete the user because the email text box is empty.";
                } else if ("andi@murach.com".equalsIgnoreCase(email) || 
                           "joelmurach@yahoo.com".equalsIgnoreCase(email) || 
                           "mike@murach.com".equalsIgnoreCase(email)) {
                    message = "Cannot delete this user.";
                } else {
                    User user = UserDB.selectUser(email);
                    UserDB.delete(user);
                    message = "Successfully deleted the user and associated information.";
                }

                request.setAttribute("message", message);
            } else if (action.equals("deleteProduct")) {
                String productCode = request.getParameter("productCode");
                String message;
                if (productCode == null || productCode.isEmpty()) {
                    message = "Failed to delete the product because the product code is empty.";
                } else {
                    Product product = ProductDB.selectProduct(productCode);
                    if (product != null) {
                        ProductDB.delete(product);
                        message = "Successfully deleted the product.";
                    } else {
                        message = "Product not found.";
                    }
                }

                request.setAttribute("message", message);
                
            } else if (action.equals("deleteDescription")) {
                String bookName = request.getParameter("bookName");
                String message;
                if (bookName == null || bookName.isEmpty()) {
                    message = "Failed to delete description because the book name is empty.";
                } else {
                    Description description = DescriptionDB.selectDescription(bookName);
                    if (description != null) {
                        DescriptionDB.delete(description);
                        message = "Successfully deleted the description.";
                    } else {
                        message = "Description not found for the given book name.";
                    }
                }
                request.setAttribute("message", message);
            
            } else if (action.equals("insert")) {
                String userName = request.getParameter("userName");
                String email = request.getParameter("email");
                String addr = request.getParameter("addr");
                String phoneNo = request.getParameter("phoneNo");

                String message;
                if (userName == null || email == null || addr == null || phoneNo == null
                        || userName.isEmpty() || email.isEmpty() || addr.isEmpty() || phoneNo.isEmpty()) {
                    message = "Please fill out all four text boxes.";
                } else if (UserDB.emailExists(email)) {
                    message = "This email address already exists. Please enter another email address.";
                } else {
                    User user = new User(userName, email, addr, phoneNo, "");
                    UserDB.insert(user);
                    message = "Successfully inserted the user.";
                }

                request.setAttribute("message", message);
	        } else if (action.equals("insertProduct")) {
	            String productCode = request.getParameter("productCode");
	            String bookName = request.getParameter("bookName");
	            String priceStr = request.getParameter("price");
	            
	            String message;
	            if (productCode == null || productCode.isEmpty() || bookName == null || bookName.isEmpty() || priceStr == null || priceStr.isEmpty()) {
	                message = "Please provide both the product code and the price for insertion.";
	            } else {
	                try {
	                    double price = Double.parseDouble(priceStr);
	                    Product existingProduct = ProductDB.selectProduct(productCode);
	                    if (existingProduct == null) {
	                    	Description info = DescriptionDB.selectDescription(bookName);
	                        Product newProduct = new Product(productCode,info, price);
	                        ProductDB.insert(newProduct);
	                        message = "Successfully inserted the new product.";
	                    } else {
	                        message = "Product with the given code already exists.";
	                    }
	                } catch (NumberFormatException e) {
	                    message = "Invalid price format. Please enter a valid number.";
	                }
	            }
	            request.setAttribute("message", message);
	        } else if (action.equals("insertDescription")) {
	            String bookName = request.getParameter("bookName");
	            String author = request.getParameter("author");
	            String publisher = request.getParameter("publisher");
	            String detail = request.getParameter("detail");
	            String genre = request.getParameter("genre");
	            
	            String message;
	            if (bookName == null || bookName.isEmpty() || author == null || author.isEmpty() ||
	                publisher == null || publisher.isEmpty() || detail == null || detail.isEmpty() ||
	                genre == null || genre.isEmpty()) {
	                message = "Please fill out all fields for description insertion.";
	            } else if (DescriptionDB.bookNameExists(bookName)){
	            	message = "This book name already existed!";
	            }
	            else {
	                try {
	                    Date releaseDate = new Date();
	                    Description newDescription = new Description(bookName, author, publisher, detail, genre, releaseDate);
	                    DescriptionDB.insert(newDescription);
	                    message = "Successfully inserted the new description.";
	                } catch (IllegalArgumentException e) {
	                    message = "Invalid date format. Please enter a valid date (YYYY-MM-DD).";
	                }
	            }
	            request.setAttribute("message", message);
	        }
	        
	        
	        List<User> users = UserDB.selectUsers();
	        request.setAttribute("users", users);
	        List<Product> products = ProductDB.selectProducts();
	        request.setAttribute("products", products);
	        List<Description> descriptions = DescriptionDB.selectDescriptions();
            request.setAttribute("descriptions", descriptions);
	        sc.getRequestDispatcher(url).forward(request, response);
        	}
	    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doPost(request, response);
    }
}
