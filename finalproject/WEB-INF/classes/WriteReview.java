import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.xml.transform.stream.StreamResult;
import javax.servlet.ServletOutputStream;
import javax.swing.*;
import java.sql.*;
import java.util.*;
import java.io.*;
@WebServlet("/WriteReview")
@MultipartConfig(maxFileSize = 16177215) 

public class WriteReview extends HttpServlet {


	private static final long serialVersionUID = 1L;

	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("text/html");
        PrintWriter pw = response.getWriter();

        try{
       		doPost(request,response);
        }catch(Exception e){

        }

    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
		Utilities utility = new Utilities(request, pw);
		if(!utility.isLoggedin())
		{
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to Write Feedbacks");
			response.sendRedirect("Login");
			return;
		}
		
		utility.printHtml("Header.html");
		HttpSession session = request.getSession();	
		String userName = session.getAttribute("username").toString();
		String make=request.getParameter("carbrand");
		String model=request.getParameter("carmodel");
		
		
		// utility.printHtml("LeftBar.html");
		// pw.print("<form name ='AddCar' action='AddCar' method='post' enctype='multipart/form-data'>");
		pw.print("<form name ='WriteReview' action='SubmitReview' method='post'>");
		pw.print("<div id='content'><div class='post'><h2>");
		pw.print("Write Feedbacks for " + make + " " + model);
		pw.print("</h2><div class='entry'>");

			pw.print("<table class='table'>");
			
			pw.print("<tr><td><h4>Your Rating: </h4></td><td align='right'><select class='form-control' name='reviewRating'>"+
				"<option value='0' selected>0</option>>"+
				"<option value='1' selected>1</option>>"+
				"<option value='2' selected>2</option>>"+
				"<option value='3' selected>3</option>>"+
				"<option value='4' selected>4</option>>"+
				"<option value='5' selected>5</option>>");
			pw.print("</td></tr>");
			
			pw.print("<tr><td><h4>Your Feedback: </h4></td><td align='right'><textarea name='reviewtext' rows='8' cols='92'> </textarea>");
			pw.print("</td></tr>");
				pw.print("<input type='hidden' name='carmake' value='"+make+"'>");
				pw.print("<input type='hidden' name='carmodel' value='"+model+"'>");
				pw.print("<input type='hidden' name='username' value='"+userName+"'>");

			
			pw.print("<tr><td></td><td align='right'><input type='submit' name='SubmitReview' value='Submit Your Feedback' class='btn btn-success' style='width: 300px'></td>");
			// pw.print("<td><input type='submit' name='AddCar' value='Update Car' class='btn btn-info' style='width: 200px'></td>");
			pw.print("</tr></table>");
		
		pw.print("</form></div></div></div>");		
		// utility.printHtml("Footer.html");
		
	}

}
