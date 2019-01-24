

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/SubmitReview")

public class SubmitReview extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
	        Utilities utility= new Utilities(request, pw);
		storeReview(request, response);
	}
	protected void storeReview(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        try
                {
                response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
                Utilities utility = new Utilities(request,pw);
		if(!utility.isLoggedin()){
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to Submit Feedbacks");
			response.sendRedirect("Login");
			return;
		}
		
		String make=request.getParameter("carmake");
		String model=request.getParameter("carmodel");
		String carmakemodel = make + " " + model;
		String username=request.getParameter("username");
		String reviewtext=request.getParameter("reviewtext");
		String reviewRating=request.getParameter("reviewRating");

				

				
				
		String message=utility.storeReview(carmakemodel,reviewtext,username,reviewRating);				     
       		
		utility.printHtml("Header.html");
		// utility.printHtml("LeftNavigationBar.html");
		pw.print("<form name ='Cart' action='CheckOut' method='post'>");
                pw.print("<div id='content'><div class='post'>");
		pw.print("<div class='entry'>");
      		if(message.equals("Successfull")){
      		pw.print("<h2>Your Feedbacks for "+carmakemodel+" Submitted</h2>");
			pw.print("<h2>Thank You</h2>");}
                else{
				pw.print("<h2>Mongo Db is not up and running </h2>");}
                
		pw.print("</div></div></div>");		
		// utility.printHtml("Footer.html");
	                     	
                    }
              	catch(Exception e)
		{
                 System.out.println(e.getMessage());
		}  			
       
	 	
		}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
            }
}
