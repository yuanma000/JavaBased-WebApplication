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
import java.util.Calendar; 
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.*;

@WebServlet("/CheckOut")

//once the user clicks buy now button page is redirected to checkout page where user has to give checkout information

public class CheckOut extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
	        Utilities Utility = new Utilities(request, pw);
	    if(!Utility.isLoggedin())
		{
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to Pay");
			response.sendRedirect("Login");
			return;
		}
		HttpSession session = request.getSession();	
	    String userName = session.getAttribute("username").toString();
	        Utility.printHtml("Header.html");
        pw.print("<form name ='CheckOut' action='Payment' method='post'>");
		pw.print("<div id='content'><div class='post'>");
		pw.print("<h2>Order Details</h2>");
		pw.print("<div class='entry'>");
		pw.print("<table  class='table'><tr><td>Username:</td><td>");
		pw.print(userName);
		pw.print("</td></tr>");
		
// for each order iterate and display the order name price
String carmake = request.getParameter("carmake");
String carmodel = request.getParameter("carmodel");

double price =Double.parseDouble(request.getParameter("price"));
//String price = request.getParameter("price");
String startday = request.getParameter("pickupdate");
String endday = request.getParameter("dropoffdate");
String startlocation = request.getParameter("startlocation");
String endlocation = request.getParameter("endlocation");
SimpleDateFormat sdf= new SimpleDateFormat("MM/dd/yyyy");
//get car Id, carid
String carid = request.getParameter("carid");
String cartype = request.getParameter("cartype");
//get startdate enddate in int
int pickupdateint = Integer.parseInt(request.getParameter("pickupdateint"));
int dropoffdateint = Integer.parseInt(request.getParameter("dropoffdateint"));

double totalprice = 0;
long days = 0;
String total = "";
try {
	//The code you are trying to exception handle
	Date start = sdf.parse(startday);
	Date end = sdf.parse(endday);
	long diff = end.getTime() - start.getTime();
	days = diff / (1000 * 60 * 60 * 24);
	totalprice = days*price;
	total = String.format ("%,.2f", totalprice);
}
catch (Exception e) {
	//The handling for the code
}
		pw.print("<tr>");
		pw.print("<td> Car Make:</td><td>");
		pw.print(carmake+"</td></tr><tr><td>");
		pw.print("Car Model:</td><td>");
		pw.print(carmodel+"</td></tr><tr><td>");
		pw.print("Daily Cost:</td><td>"+ "$" + price+"</td></tr><tr><td>");
		pw.print("Start Date:</td><td>"+ startday+"</td></tr><tr><td>");
		pw.print("End Date:</td><td>"+ endday+"</td></tr><tr><td>");
		pw.print("Pick-up Location:</td><td>"+ startlocation+"</td></tr><tr><td>");
		pw.print("Drop-off Location:</td><td>"+ endlocation+"</td></tr><tr><td>");
		pw.print("Rental Days:</td><td>"+ days);
		pw.print("</td></tr>");

		pw.print("<tr><td>");
        pw.print("Total Cost:</td><td>"+ "$" + total);
		pw.print("<input type='hidden' name='Total' value='"+total+"'>");
		pw.print("</td></tr></table>");
		//Submit Order
		pw.print("<h2>Confirm Your Order</h2>");
		pw.print("<table  class='table'><tr></tr><tr></tr>");
		pw.print("<tr><td></td><td>");
	    pw.print("Full Legal Name</td>");
		pw.print("<td><input style='width:300px' class='form-control' type='text' name='fullname'>");
        pw.print("</td></tr>");
		pw.print("<tr><td></td><td>");
     	pw.print("Credit Card Number</td>");
		pw.print("<td><input style='width:300px' class='form-control' type='text' name='creditcardno'>");
		pw.print("</td></tr>");
		pw.print("<tr><td></td><td>");
	    pw.print("Billing Address</td>");
		pw.print("<td><input style='width:300px' class='form-control' type='text' name='billingaddress'>");
        pw.print("</td></tr>");
		pw.print("<tr><td></td><td>");
		pw.print("<input type='hidden' name='carid' value='"+carid+"'>");
		pw.print("<input type='hidden' name='startday' value='"+startday+"'>");
		pw.print("<input type='hidden' name='endday' value='"+endday+"'>");
		pw.print("<input type='hidden' name='pickupdateint' value='"+pickupdateint+"'>");
		pw.print("<input type='hidden' name='dropoffdateint' value='"+dropoffdateint+"'>");
		pw.print("<input type='hidden' name='username' value ='"+userName+"'>");
		pw.print("<input type='hidden' name='startlocation' value='"+startlocation+"'>");
		pw.print("<input type='hidden' name='endlocation' value='"+endlocation+"'>");
		pw.print("<input type='hidden' name='totalcost' value ='"+totalprice+"'>");
		//<A HREF="javascript:history.go(-1);">
		pw.print("<a href='javascript:history.go(-1)' style='width:200px' class='btn btn-info'><font color='ffffff'>Go Back</font></a></td>");
		// pw.print("<input style='width:200px' type='submit' name='submit' class='btn btn-info' value='Go Back'>");
		pw.print("<td><input style='width:200px' type='submit' name='submit' class='btn btn-success' value='Submit Order'>");
        pw.print("</td></tr></table></form>");




		HashMap<String, Car> all = new HashMap<String, Car>();
		try{
			all = MySqlDataStoreUtilities.searchBetterCars(pickupdateint,dropoffdateint,startlocation,price,cartype);
		}catch(Exception e){

		}
		String name = null;
		HashMap<String, Car> hm = new HashMap<String, Car>();
		hm.putAll(all);

		pw.print("You Have Better Choices With Same Car Type");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		int i = 1;
		int size = hm.size();
		if(size==0)
		{
			pw.print("You Have Selected The Best Choice!");
		}
		// System.out.println("The size is: "+size);//////////////////////////////////
		for (Map.Entry<String, Car> entry : hm.entrySet()) {

			Car car = entry.getValue();
			//System.out.println("cartype is: "+cartype);
			// System.out.println("targettype is: "+car.getCartype());
			// if(car.getCartype().equals(cartype)){
				// System.out.println("The car id is: "+car.getId());/////////////////////////////
				if (i % 3 == 1)
					pw.print("<tr>");
				pw.print("<td><div id='shop_item'>");
				pw.print("<li id='item'><img src='"
						+car.getImage() + "' alt='' /></li>");
				pw.print("<h3>" + car.getCarbrand() + " " + car.getCarmodel() + "</h3>");
				pw.print("<strong>$" + car.getPrice() + " Per Day</strong><ul>");

				pw.print("<li><form method='post' action='CarDetails'>"+"<input type='hidden' name='id' value='"+entry.getKey()+"'>"+
					    "<input type='submit' value='Car Details' class='btn btn-warning' style='width: 190px'></form></li>");
				
				pw.print("<li><form name ='ViewFeedbacks' action='ViewReview' method='post'>");
				pw.print("<input style='width:190px' type='submit' class='btn btn-primary' value='View Feedbacks'>");
				pw.print("<input type='hidden' name='carmakemodel' value='"+car.getCarbrand()+" "+car.getCarmodel()+"'></form></li>");

				pw.print("<li><form name ='RentInstead' action='CheckOut' method='post'>");
				pw.print("<input style='width:190px' type='submit' class='btn btn-success' value='Rent This Car Instead'>"
					+"<input type='hidden' name='carid' value='"+car.getId()+"'>"
					+"<input type='hidden' name='carmake' value='"+car.getCarbrand()+"'>"
					+"<input type='hidden' name='carmodel' value='"+car.getCarmodel()+"'>"
					+"<input type='hidden' name='price' value='"+car.getPrice()+"'>"
					+"<input type='hidden' name='cartype' value='"+car.getCartype()+"'>"
					+"<input type='hidden' name='pickupdate' value='"+startday+"'>"
					+"<input type='hidden' name='dropoffdate' value='"+endday+"'>"
					+"<input type='hidden' name='pickupdateint' value='"+pickupdateint+"'>"
					+"<input type='hidden' name='dropoffdateint' value='"+dropoffdateint+"'>"
					+"<input type='hidden' name='startlocation' value='"+startlocation+"'>"
					+"<input type='hidden' name='endlocation' value='"+endlocation+"'>"
					+"</form></li>");
				
				pw.print("</ul></div></td>");
				if (i % 3 == 0 || i == size)
					pw.print("</tr>");
				i++;



			// }
		}
		pw.print("</table></div></div></div>");



		pw.print("</div></div></div>");		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	    {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		try{
       		doPost(request,response);
        }catch(Exception e){

        }
	    }
}
