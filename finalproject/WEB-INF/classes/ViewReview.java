

import java.io.IOException;
import java.io.PrintWriter;
import com.mongodb.MongoClient;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@WebServlet("/ViewReview")

public class ViewReview extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
	        Utilities utility= new Utilities(request, pw);
		review(request, response);
	}
	
	protected void review(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        try
                {           
                response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
                Utilities utility = new Utilities(request,pw);
		// if(!utility.isLoggedin()){
			// HttpSession session = request.getSession(true);				
			// session.setAttribute("login_msg", "Please Login to View Feedbacks");
			// response.sendRedirect("Login");
			// return;
		// }
		
		String carmakemodel=request.getParameter("carmakemodel");
		
		//1.
		// String productName=request.getParameter("name");
		 
		HashMap<String, ArrayList<Review>> hm= MongoDBDataStoreUtilities.selectReview();
		
		String reviewtext = "";
		String username = "";
		String reviewRating = "";
		
		// 2.
		// String producttype = "";
		// 3.
		// String price = "";
		// 4.
		// String retailername = "";
		// 5.
		// String retailerpin = "";
		// 6.
		// String city = "";
		// 7.
		// String retailerstate = "";
		// 8.
		// String productonsale = "";
		// 9.
		// String productmaker = "";
		// 10.
		// String manurebate = "";
		// 11.
		// String userName = "";
		// 12.
		// String userAge = "";
		// 13.
		// String userGender = "";
		// 14.
		// String userOccupation = "";
		// 15.
		// String reviewRating = "";
		// 16.
		// String reviewDate = "";
		// 17.
		// String reviewText = "";	
			
                utility.printHtml("Header.html");
		// utility.printHtml("LeftNavigationBar.html");
	
                pw.print("<div id='content'><div class='post'><h2>");
		pw.print("Feedbacks for " + carmakemodel);
		pw.print("</h2><div class='entry'>");
			
			//if there are no reviews for product print no review else iterate over all the reviews using cursor and print the reviews in a table
		if(hm==null)
		{
		pw.println("<h2>Mongo Db server is not up and running</h2>");
		}
		else
		{
                if(!hm.containsKey(carmakemodel)){
				pw.println("<h2>There are no feedbacks for this car model.</h2>");
			}else{
		for (Review r : hm.get(carmakemodel)) 
				 {		
		pw.print("<table class='table'>");
		
				pw.print("<tr>");
				pw.print("<td>Car Model: </td>");
				carmakemodel = r.getCarmakemodel();
				pw.print("<td>" +carmakemodel+ "</td>");
				pw.print("</tr>");
				
				pw.print("<tr>");
				pw.print("<td>Username: </td>");
				username = r.getUsername();
				pw.print("<td>" +username+ "</td>");
				pw.print("</tr>");
				
				pw.print("<tr>");
				pw.print("<td>Feedbacks: </td>");
				reviewtext = r.getReviewtext();
				pw.print("<td>" +reviewtext+ "</td>");
				pw.print("</tr>");
				
				
				// pw.print("<tr>");
				// pw.print("<td> Retailer Name: </td>");
				// retailername = r.getRetailerName();
				// pw.print("<td>" +retailername+ "</td>");
				// pw.print("</tr>");
				
				// pw.print("<tr>");
				// pw.print("<td> Retailer Zip: </td>");
				// retailerpin = r.getRetailerPin();
				// pw.print("<td>" +retailerpin+ "</td>");
				// pw.print("</tr>");
				
				// pw.print("<tr>");
				// pw.print("<td> Retailer City: </td>");
				// city = r.getRetailerCity();
				// pw.print("<td>" +city+ "</td>");
				// pw.print("</tr>");
				
				// pw.print("<tr>");
				// pw.print("<td> Retailer State: </td>");
				// retailerstate = r.getRetailerState();
				// pw.print("<td>" +retailerstate+ "</td>");
				// pw.print("</tr>");
				
				// pw.print("<tr>");
				// pw.print("<td> Product On Sale: </td>");
				// productonsale = r.getProductOnSale();
				// pw.print("<td>" +productonsale+ "</td>");
				// pw.print("</tr>");
				
				// pw.print("<tr>");
				// pw.print("<td> Manufacturer Name: </td>");
				// productmaker = r.getProductMaker();
				// pw.print("<td>" +productmaker+ "</td>");
				// pw.print("</tr>");
				
				// pw.print("<tr>");
				// pw.print("<td> Manufacturer Rebate: </td>");
				// manurebate = r.getManuRebate();
				// pw.print("<td>" +manurebate+ "</td>");
				// pw.print("</tr>");
				
				// pw.print("<tr>");
				// pw.print("<td> Reviewer's UserID: </td>");
				// userName = r.getUserName();
				// pw.print("<td>" +userName+ "</td>");
				// pw.print("</tr>");
				
				// pw.print("<tr>");
				// pw.print("<td> Reviewer's Age: </td>");
				// userAge = r.getUserAge();
				// pw.print("<td>" +userAge+ "</td>");
				// pw.print("</tr>");
				
				// pw.print("<tr>");
				// pw.print("<td> Reviewer's Gender: </td>");
				// userGender = r.getUserGender();
				// pw.print("<td>" +userGender+ "</td>");
				// pw.print("</tr>");
				
				// pw.print("<tr>");
				// pw.print("<td> Reviewer's Occupation: </td>");
				// userOccupation = r.getUserOccupation();
				// pw.print("<td>" +userOccupation+ "</td>");
				// pw.print("</tr>");
				
				pw.println("<tr>");
				pw.println("<td>Rating: </td>");
				reviewRating = r.getReviewRating().toString();
				pw.print("<td>" +reviewRating+ "</td>");
				pw.print("</tr>");
				
				// pw.print("<tr>");
				// pw.print("<td> Review Date: </td>");
				// reviewDate = r.getReviewDate().toString();
				// pw.print("<td>" +reviewDate+ "</td>");
				// pw.print("</tr>");	
				
				// pw.print("<tr>");
				// pw.print("<td> Review Text: </td>");
				// reviewText = r.getReviewText();
				// pw.print("<td>" +reviewText+ "</td>");
				// pw.print("</tr>");
				
				pw.println("</table>");
				pw.println("&nbsp");
				}	
					
							
		}
		}	       
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
