import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

@WebServlet("/Registration")

public class Registration extends HttpServlet {
	private String error_msg;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		displayRegistration(request, response, pw, false);
	}

	/*   Username,Password,Usertype information are Obtained from HttpServletRequest variable and validates whether
		 the User Credential Already Exists or else User Details are Added to the Users HashMap */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String repassword = request.getParameter("repassword");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");

		String usertype = "customer";
		if(!utility.isLoggedin())
			usertype = request.getParameter("usertype");

		//if password and repassword does not match show error message

		if(!password.equals(repassword))
		{
			error_msg = "Passwords doesn't match!";
		}
		else
		{

			//get the userdata from sql database to hashmap
			HashMap<String, User> hm=new HashMap<String, User>();
			
			try
			{
				hm=MySqlDataStoreUtilities.selectUser();
			}
			catch(Exception e)
			{
				
			}

			// if the user already exist show error that already exist

			if(hm.containsKey(username))
				error_msg = "Username already exist as " + usertype;
			else
			{
				/*create a user object and store details into hashmap
				store the user hashmap into file  */

				User user = new User(username,password,usertype,email,phone);
				hm.put(username, user);
				MySqlDataStoreUtilities.insertUser(username,password,repassword,usertype,email,phone);					
				HttpSession session = request.getSession(true);				
				session.setAttribute("login_msg", "Your "+usertype+" account has been created. Please login");
				if(!utility.isLoggedin()){
					response.sendRedirect("Login"); return;
				} else {
					response.sendRedirect("Home"); return;
				}
			}
		}

		//display the error message
		if(utility.isLoggedin()){
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", error_msg);
			response.sendRedirect("Home"); return;
		}
		displayRegistration(request, response, pw, true);
		
	}


	/*  displayRegistration function displays the Registration page of New User */
	
	protected void displayRegistration(HttpServletRequest request,
			HttpServletResponse response, PrintWriter pw, boolean error)
			throws ServletException, IOException {
		Utilities utility = new Utilities(request, pw);
		utility.printHtml("Header.html");
		pw.print("<div id='content'><div class='post'>");
		pw.print("<h2>Sign Up</h2><div class='entry'>");
		if (error)
			pw.print("<h4 style='color:red'>"+error_msg+"</h4>");
		pw.print("<div align='center'><form method='post' action='Registration'>"
				+ "<table style='width:500px'><tr><td>"
				+ "<h3>User Type</h3></td><td><select name='usertype' class='form-control'><option value='customer' selected>Customer</option><option value='manager'>Manager</option></select>"
				+ "</td></tr><tr><td>"
				+ "<h3>Username</h3></td><td><input type='text' name='username' value='' class='form-control' required>"
				+ "</td></tr><tr><td>"
				+ "<h3>Email</h3></td><td><input type ='email' name='email' value='' class='form-control' required>"
				+ "</td></tr><tr><td>"
				+ "<h3>Phone Number</h3></td><td><input type ='text' name='phone' value='' class='form-control' required>"
				+ "</td></tr><tr><td>"
				+ "<h3>Password</h3></td><td><input type='password' name='password' value='' class='form-control' required>"
				+ "</td></tr><tr><td>"
				+ "<h3>Re-Password</h3></td><td><input type='password' name='repassword' value='' class='form-control' required>"
				+ "</td></tr></table>"
				+ "<input type='submit' class='btn btn-success' name='ByUser' value='Create User' style='width: 150px'>"
				+ "</form>" + "</div></div></div></div>");
		// utility.printHtml("Footer.html");
	}
}
