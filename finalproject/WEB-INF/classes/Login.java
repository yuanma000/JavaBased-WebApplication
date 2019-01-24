import java.io.*;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Login")

public class Login extends HttpServlet {

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		/* User Information(username,password,usertype) is obtained from HttpServletRequest,
		Based on the Type of user(customer,retailer,manager) respective hashmap is called and the username and 
		password are validated and added to session variable and display Login Function is called */

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String usertype = request.getParameter("usertype");
		HashMap<String, User> hm;
		hm=new HashMap<String, User>();
		// String TOMCAT_HOME = System.getProperty("catalina.home");
		//user details are validated using a file 
		//if the file containts username and passoword user entered user will be directed to home page
		//else error message will be shown
		try
		{		
		  hm=MySqlDataStoreUtilities.selectUser();
		}
		catch(Exception e)
		{
				
		}
		User user = hm.get(username);
		if(user!=null)
		{
		 String user_password = user.getPassword();
		 String user_usertype = user.getUsertype();
		 
		 if (password.equals(user_password) && usertype.equals(user_usertype)) 
			{
			HttpSession session = request.getSession(true);
			session.setAttribute("username", user.getName());
			session.setAttribute("usertype", user.getUsertype());
			if (user_usertype.equals("customer"))
			{
			response.sendRedirect("Home");
			}
			if (user_usertype.equals("manager"))
			{
			response.sendRedirect("CarList");
			}
			return;
			}
		}
		displayLogin(request, response, pw, true);
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		displayLogin(request, response, pw, false);
	}


	/*  Login Screen is Displayed, Registered User specifies credentials and logins into the Game Speed Application. */
	protected void displayLogin(HttpServletRequest request,
			HttpServletResponse response, PrintWriter pw, boolean error)
			throws ServletException, IOException {

		Utilities utility = new Utilities(request, pw);
		utility.printHtml("Header.html");
		// utility.printHtml("LeftNavigationBar.html");
		// pw.print("<div id='content'><div class='post' style='float: center; width: 100%'>");
		pw.print("<div id='content'><div class='post'>");
		pw.print("<h2>Login</h2><div class='entry'>");
		if(error)
			pw.print("<h4 style='color:red'>Please check your username, password and user type!</h4>");
		HttpSession session = request.getSession(true);
		if(session.getAttribute("login_msg")!=null){			
			pw.print("<h4 style='color:blue'>"+session.getAttribute("login_msg")+"</h4>");
			session.removeAttribute("login_msg");
		}
		pw.print("<div align='center'><form method='post' action='Login'>"
				+ "</td></tr><tr><td></td><td>"
				+ "<table style='width:500px'>"
				+ "<tr><td><h3>User Type</h3></td><td><select class='form-control' name='usertype'><option value='customer' selected>Customer</option><option value='manager'>Manager</option>"
				+ "</td></tr>"
				+ "<tr><td><h3>Username</h3></td><td><input type='text' name='username' value='' class='form-control' required>"
				+ "</td></tr><tr><td>"
				+ "<h3>Password</h3></td><td><input type='password' name='password' value='' class='form-control' required>"
				+ "</td></tr>"
				+ "<tr><td><h2> </h2></td></tr>"
				+ "<tr><td><input type='submit' class='btn btn-success' value='Login' style='width: 150px'>"
				+ "</td><td>"
				+ "<h4><a href='Registration'>New User? Sign Up here!</a></h4>"
				+ "</td></tr></table>"
				+ "</form>" + "</div></div></div></div>");
		// utility.printHtml("Footer.html");
	}

}
