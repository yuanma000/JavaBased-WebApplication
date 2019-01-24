import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
// import java.sql.Blob;
import java.io.InputStream;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletOutputStream;

@WebServlet("/CarList")

public class CarList extends HttpServlet {

	/* Trending Page Displays all the Cars and their Information in webpage */

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		if(!utility.isLoggedin())
		{
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to View Your Car Inventory");
			response.sendRedirect("Login");
			return;
		}
		HashMap<String, Car> all = new HashMap<String, Car>();

	/* Checks the Cars type whether it is toyota or honda or  BMW*/
		try{
			all = MySqlDataStoreUtilities.getCars();
		}catch(Exception e){

		}
		
		//Test only
		// String pickupdatestring = request.getParameter("pickupdate");
		// int pickupyear = Integer.parseInt(pickupdatestring.substring(6, 10));
		// int pickupmonth = Integer.parseInt(pickupdatestring.substring(0, 2));
		// int pickupday = Integer.parseInt(pickupdatestring.substring(3, 5));
		// int startdate = pickupyear*10000 + pickupmonth*100 + pickupday;
		// String startlocation = request.getParameter("pickuplocation");
		//
		// String dropoffstring = request.getParameter("dropoffdate");
		// int dropoffyear = Integer.parseInt(dropoffstring.substring(6, 10));
		// int dropoffmonth = Integer.parseInt(dropoffstring.substring(0, 2));
		// int dropoffday = Integer.parseInt(dropoffstring.substring(3, 5));
		// int enddate = dropoffyear*10000 + dropoffmonth*100 + dropoffday;
		// String endlocation = request.getParameter("dropofflocation");
		//Test only
		
		String name = null;
		String ManufacturerName = request.getParameter("manufacturer");
		HashMap<String, Car> hm = new HashMap<String, Car>();
		hm.putAll(all);
		name="";


		/* Header, Left Navigation Bar are Printed.

		All the cars and laptop information are dispalyed in the Content Section

		and then Footer is Printed*/
		//ServletOutputStream out = response.getOutputStream();
		
		utility.printHtml("Header.html");
		// utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content'><div class='post'><h2>");
		
		//Test only
		// pw.print("<a style='font-size: 24px;'>Start: " + startdate + startlocation + " </a>");
		// pw.print("<a style='font-size: 24px;'>End: " + enddate + endlocation + " </a>");
		//
		
		pw.print("Car Inventory");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		int i = 1;
		int size = hm.size();
		for (Map.Entry<String, Car> entry : hm.entrySet()) {
			Car car = entry.getValue();
			if (i % 3 == 1)
				pw.print("<tr>");
			pw.print("<td><div id='shop_item'>");
			pw.print("<li id='item'><img src='"
					+car.getImage() + "' alt='' /></li>");
			pw.print("<h3>" + car.getCarbrand() + " " + car.getCarmodel() + "</h3>");
			pw.print("<strong>$" + car.getPrice() + " Per Day</strong><ul>");


			pw.print("<li><form method='post' action='UpdateCar'>" +
					"<input type='hidden' name='id' value='"+entry.getKey()+"'>"+

					"<input type='hidden' name='carbrand' value='"+car.getCarbrand()+"'>"+
					"<input type='hidden' name='carmodel' value='"+car.getCarmodel()+"'>"+
					"<input type='hidden' name='price' value='"+car.getPrice()+"'>"+
					"<input type='hidden' name='image' value='"+car.getImage()+"'>"+
					"<input type='hidden' name='carcondition' value='"+car.getCarcondition()+"'>"+
					"<input type='hidden' name='discount' value='"+car.getDiscount()+"'>"+
					"<input type='hidden' name='owner' value='"+car.getOwner()+"'>"+
					"<input type='hidden' name='cartype' value='"+car.getCartype()+"'>"+
					"<input type='hidden' name='location' value='"+car.getLocation()+"'>"+
					"<input type='submit' class='btn btn-success' value='Update Information' style='width: 190px'></form></li>");
			

			pw.print("<li><form method='get' action='DeleteCar'>"+
					"<input type='hidden' name='id' value='"+entry.getKey()+"'>"+
					"<input type='submit' class='btn btn-danger' value='Delete Car' style='width: 190px'></form></li>");

			pw.print("<li><form method='post' action='CarDetails'>"+"<input type='hidden' name='id' value='"+entry.getKey()+"'>"+
				    "<input type='submit' value='Car Details' class='btn btn-warning' style='width: 190px'></form></li>");
			
			pw.print("<li><form name ='ViewFeedbacks' action='ViewReview' method='post'>");
			pw.print("<input style='width:190px' type='submit' class='btn btn-primary' value='View Feedbacks'>");
			pw.print("<input type='hidden' name='carmakemodel' value='"+car.getCarbrand()+" "+car.getCarmodel()+"'></form></li>");
			
			pw.print("</ul></div></td>");
			if (i % 3 == 0 || i == size)
				pw.print("</tr>");
			i++;
		}
		pw.print("</table></div></div></div>");
		// utility.printHtml("Footer.html");
	}
}
