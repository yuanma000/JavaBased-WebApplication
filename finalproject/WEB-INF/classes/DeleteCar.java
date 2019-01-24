import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.*;
@WebServlet("/DeleteCar")

public class DeleteCar extends HttpServlet {

	/* Games Page Displays all the Games and their Information in Game Speed */

	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        System.out.println("1.The id is: "+request.getParameter("id"));
        try{
       		doPost(request,response);
        }catch(Exception e){

        }
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("2.The id is: "+request.getParameter("id"));

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request,pw);

		utility.printHtml("Header.html");
		// utility.printHtml("LeftNavigationBar.html");
		String id = request.getParameter("id");
		//details(request, response);


			    try{           
            response.setContentType("text/html");
			//PrintWriter pw = response.getWriter();
           // Utilities utility = new Utilities(request,pw);
			if(!utility.isLoggedin()){
				HttpSession session = request.getSession(true);				
				session.setAttribute("login_msg", "Please Login to view car details");
				response.sendRedirect("Login");
				return;
			}

			//String id=request.getParameter("id");
			//System.out.println("The Car Id is:" +id);	
			HashMap<String, Car> hm = MySqlDataStoreUtilities.selectOneCar(id);
			Car car = hm.get(id);


	       // utility.printHtml("Header.html");
			//utility.printHtml("LeftNavigationBar.html");
		
	                pw.print("<div id='content'><div class='post'><h2>");
			pw.print("Delete Car");
			pw.print("</h2><div class='entry'>");
			
			//if there are no reviews for product print no review else iterate over all the reviews using cursor and print the reviews in a table
		if(car==null)
		{
		pw.println("<h2>MySQL Database server is not up and running</h2>");
		}
		else
		{
            if(car.getId()==null){
				pw.println("<h2>There is no such car.</h2>");
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
					pw.print("<td>" +car.getPrice()+ "</td>");
					pw.print("</tr>");

					pw.print("<tr>");//new
					pw.print("<td> Discount: </td>");
					pw.print("<td>" +car.getDiscount()+ "</td>");
					pw.print("</tr>");

					pw.print("<tr>");//new
					pw.print("<td> Car Model Year: </td>");
					pw.print("<td>" +car.getCarcondition()+ "</td>");
					pw.print("</tr>");

					pw.print("<tr>");//new
					pw.print("<td> Current Location: </td>");
					pw.print("<td>" +car.getLocation()+ "</td>");
					pw.print("</tr>");

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


		//utility.printHtml("Footer.html");
	                     	
	    }
	    catch(Exception e)
		{
	        System.out.println(e.getMessage());
		}  


		pw.print("<form name ='DeleteCar' action='DeleteCar' method='get'>");
		if(request.getParameter("DeleteCar")==null)
		{
			// id = request.getParameter("id");
			pw.print("<input type='hidden' name='id' value ='"+request.getParameter("id")+"'>");
			pw.print("<table class='table'>");
			pw.print("<tr><td>");
			pw.print("<a href='javascript:history.go(-1)' style='width:200px' class='btn btn-info'><font color='ffffff'>Go Back</font></a></td>");
			pw.print("<td><input type='submit' name='DeleteCar' value='Delete' class='btn btn-danger' style='width:200px'></td>");
			//pw.print("<td><button type='button' class='btn btn-info'>"'Back'"</button></td>");
			pw.print("</tr></table>");
			
		}
		
		if(request.getParameter("DeleteCar")!=null)
		{
			if(request.getParameter("id")!=null)
			{
				//String id = request.getParameter("id");
				//String type = request.getParameter("carBrand");
				//String name = request.getParameter("itemName");
				MySqlDataStoreUtilities.deleteCar(id);
				MySqlDataStoreUtilities.deleteTime(id);
				response.sendRedirect("http://localhost/finalproject/CarList");
				// pw.print("<h4 style='color:red'>The car is deleted.</h4>");
			}
			else{
				pw.print("<h4 style='color:red'><br>Please input valid car</h4>");
			}
		
		
		}
		
		//pw.print("<h4 style='color:red'><br>Your can cancell before "+cancel+"</h4>");
		pw.print("</form>");		
		// utility.printHtml("Footer.html");
		
	}


	// protected void details(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
	// }

}
