package murach.email;

import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import javax.mail.MessagingException;
import java.io.*;

import java.util.*;
import murach.business.*;
import murach.util.*;
import murach.data.UserDB;

@WebServlet(urlPatterns="/emailList", initParams={@WebInitParam(name="custServEmail", value="custServ@murach.com")})
public class EmailListServlet extends HttpServlet 
{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		
		String url = "/index.jsp";
		// get current action
		String action = request.getParameter("action");
		log("action = " + action);		// debugging message of "action" variable
		if (action == null)
		{
			action = "join";
		}
		
		// perform action and set URL to appropriate page
		if (action.equals("join"))
		{
			url = "/index.jsp";		// the "join" page
		}
		else if (action.equals("add"))
		{
			// synchronize access to the session object
			final Object lock = request.getSession().getId().intern();
			HttpSession session = request.getSession();
			// get parameters from the request
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String email = request.getParameter("email");
			
			// validate the parameters
			String message;
			if (firstName == null || lastName == null || email == null
					|| firstName.isEmpty() || lastName.isEmpty() || email.isEmpty())
			{
				message = "Please fill out all three text boxes.";
				url = "/index.jsp";		// the "join" page
			}
			else
			{
				if (UserDB.emailExists(email))
				{
					message = "This email address already exists. "
							+ "Please enter another email address.";
					url = "/index.jsp";		// the "join" page
				}
				else
				{
					// store data in User object
					User user = new User(firstName, lastName, email);
					message = "";
					UserDB.insert(user);		// save User object in db
					/*
					User userA = new User("John", "Smith", "jsmith@gmail.com");
					User userB = new User("Joel", "Murach", "joelh@murach.com");
					ArrayList<User> users = new ArrayList<>();
					
					users.add(userA);
					users.add(userB);
					users.add(user);
					int curUserIndex = users.size() - 1;
					*/
					
					synchronized (lock)
					{
						session.setAttribute("user", user);
						//session.setAttribute("users", users);
						//session.setAttribute("curUserIndex", curUserIndex);
					}
					
					// send an email to user
					String to = email;
					String from = "anhkhoa.93.14@gmail.com";
					String subject = "Welcome to our email list";
					String body = "Dear " + firstName + ",\n\n"
							+ "Thanks for joining our email list. We'll make sure to send "
							+ "you announcements about new products and promotions.\n"
							+ "Have a great day and thanks again!\n\n"
							+ "Kelly Slivkoff\n"
							+ "Mike Murach & Associates";
					boolean isBodyHTML = false;
					
					try 
					{
						MailUtilGmail.sendMail(to, from, subject, body, isBodyHTML);
					} 
					catch (MessagingException e) 
					{
						String errorMessage = "ERROR: Unable to send email. "
							+ "Check Tomcat logs for details.<br>"
							+ "ERROR MESSAGE: " + e.getMessage();
						request.setAttribute("errorMessage", errorMessage);
						this.log(
							"Unable to send email. \n"
							+ "Here is the email you tried to send: \n"
							+ "======================================\n"
							+ "TO: " + email + "\n"
							+ "FROM: " + from + "\n"
							+ "SUBJECT: " + subject + "\n"
							+ "\n"
							+ body + "\n\n");
					};

					Date curDate = new Date();
					request.setAttribute("curDate", curDate);
					
					url = "/thanks.jsp";		// the "thanks" page
				}
			}
			// set User object and message in request object
			request.setAttribute("message", message);
			// validate the parameters
		}
		else if (action.equals("manage"))
		{
			List<User> users = UserDB.selectUsers();
			request.setAttribute("users", users);
			url = "/user.jsp";		// the "admin" page
		}
		else if (action.equals("update"))
		{
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String email = request.getParameter("email");
			String message;
			if (email == null || email.isEmpty())
			{
				message = "Failed to update the user because the email text box is empty.";
			}
			else
			{
				User user = UserDB.selectUser(email);
				if (firstName != null && firstName.isEmpty() == false)
				{
					user.setFirstName(firstName);
				}
				if (lastName != null && lastName.isEmpty() == false)
				{
					user.setLastName(lastName);
				}
				/*
				if (UserDB.update(user) != 0)
				{
					message = "Successfully updated the user.";
				}
				else
				{
					message = "Failed to update the user due to a SQL error.";
				}
				*/
				
				UserDB.update(user);
				message = "Successfully updated the user.";
				
			}
			List<User> users = UserDB.selectUsers();
			request.setAttribute("users", users);
			request.setAttribute("message", message);
			url = "/user.jsp";		// the "admin" page
		}
		else if (action.equals("delete"))
		{
			String email = request.getParameter("email");
			String message;
			if (email == null || email.isEmpty())
			{
				message = "Failed to delete the user because the email text box is empty.";
			}
			else if(email.equals("andi@murach.com") || email.equals("joelmurach@yahoo.com") || email.equals("mike@murach.com"))
			{
				message = "Cannot delete this user.";
			}
			else
			{
				User user = UserDB.selectUser(email);
				/*
				if (UserDB.delete(user) != 0)
				{
					message = "Successfully deleted the user.";
				}
				else
				{
					message = "Failed to delete the user due to a SQL error.";
				}
				*/
				UserDB.delete(user);
				message = "Successfully updated the user.";
			}
			List<User> users = UserDB.selectUsers();
			request.setAttribute("users", users);
			request.setAttribute("message", message);
			url = "/user.jsp";		// the "admin" page
		}
		/*
		// get an int value for the current year and set that in request object
		GregorianCalendar currentDate = new GregorianCalendar();
		int currentYear = currentDate.get(Calendar.YEAR);
		request.setAttribute("currentYear", currentYear);
		*/
		// forward request and response object to specified URL
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		doPost(request, response);
	}
}