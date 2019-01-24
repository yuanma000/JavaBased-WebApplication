

import java.io.IOException;
import java.io.PrintWriter;
/*
import com.mongodb.MongoClient;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;*/
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@WebServlet("/CarDetails")

public class CarDetails extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
	        Utilities utility= new Utilities(request, pw);
		details(request, response);
	}
	
	protected void details(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        try
                {           
                response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
                Utilities utility = new Utilities(request,pw);
		// if(!utility.isLoggedin()){
			// HttpSession session = request.getSession(true);				
			// session.setAttribute("login_msg", "Please Login to view car details");
			// response.sendRedirect("Login");
			// return;
		// }

		String id=request.getParameter("id");
		//System.out.println("The Car Id is:" +id);	
		HashMap<String, Car> hm = MySqlDataStoreUtilities.selectOneCar(id);
		Car car = hm.get(id);


        utility.printHtml("Header.html");
		//utility.printHtml("LeftNavigationBar.html");
	
                pw.print("<div id='content'><div class='post'><h2>");
		pw.print("Car Details");
		pw.print("</h2><div class='entry'>");
			
			//if there are no reviews for product print no review else iterate over all the reviews using cursor and print the reviews in a table
		if(car==null)
		{
		pw.println("<h2>MySQL Database server is not up and running</h2>");
		}
		else
		{
            if(car.getId()==null){
				pw.println("<h2>Car not found.</h2>");
			}else{	
					pw.print("<table class='table'>");
					pw.print("<tr>");
					pw.print("<td> Car ID: </td>");
					pw.print("<td>" +car.getId()+ "</td>");
					pw.print("</tr>");

					pw.print("<tr>");//new
					pw.print("<td> Car Owner: </td>");
					pw.print("<td>" +car.getOwner()+ "</td>");
					pw.print("</tr>");


					pw.print("<tr>");//new
					pw.print("<td> Car Make: </td>");
					pw.print("<td>" +car.getCarbrand()+ "</td>");
					pw.print("</tr>");

					pw.print("<tr>");//new
					pw.print("<td> Car Model: </td>");
					pw.print("<td>" +car.getCarmodel()+ "</td>");
					pw.print("</tr>");

					pw.print("<tr>");
					pw.print("<td> Daily Cost: </td>");
					pw.print("<td>" +car.getPrice()+ " Per Day</td>");
					pw.print("</tr>");

					pw.print("<tr>");//new
					pw.print("<td> Discount: </td>");
					pw.print("<td>" +car.getDiscount()+ "</td>");
					pw.print("</tr>");

					pw.print("<tr>");//new
					pw.print("<td> Car Model Year: </td>");
					pw.print("<td>" +car.getCarcondition()+ "</td>");
					pw.print("</tr>");

					// pw.print("<tr>");//new
					// pw.print("<td> Current Location: </td>");
					// pw.print("<td>" +car.getLocation()+ "</td>");
					// pw.print("</tr>");

					pw.print("<tr>");//new
					pw.print("<td> Car Type: </td>");
					pw.print("<td>" +car.getCartype()+ "</td>");
					pw.print("</tr>");

					pw.println("</table>");

					pw.print("<table class='gridtable'>");
					// pw.print("<tr>Car Image:");
					// pw.print("<td> Car Image: </td>");
					// pw.print("</tr>");
					pw.print("<tr id='item'><img height='400px' src='"
					+car.getImage() + "' alt='' /></tr>");
											
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
