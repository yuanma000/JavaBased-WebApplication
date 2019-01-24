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
// import java.sql.Blob;
import java.io.InputStream;
import javax.servlet.ServletOutputStream;

@WebServlet("/ViewShareReqCustomer")

public class ViewShareReqCustomer extends HttpServlet {

	/* Trending Page Displays all the Cars and their Information in webpage */

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
				Utilities utility = new Utilities(request, pw);
		if(!utility.isLoggedin())
		{
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to View Your Car Sharing Requests");
			response.sendRedirect("Login");
			return;
		}
		HttpSession session = request.getSession();	
	    String userName = session.getAttribute("username").toString();
		// pw.print("userName:"+userName);
		HashMap<String, ShareReq> all = new HashMap<String, ShareReq>();

		try{
			all = MySqlDataStoreUtilities.getShareReqs(userName);
		}catch(Exception e){

		}
		
		
		//String name = null;
		//String ManufacturerName = request.getParameter("manufacturer");
		HashMap<String, ShareReq> hm = new HashMap<String, ShareReq>();
		hm.putAll(all);
		//name="";


		/* Header, Left Navigation Bar are Printed.

		All the cars and laptop information are dispalyed in the Content Section

		and then Footer is Printed*/
		//ServletOutputStream out = response.getOutputStream();
		// Utilities utility = new Utilities(request, pw);
		utility.printHtml("Header.html");
		// utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content'><div class='post'>");
		
		//Test only
		// pw.print("<a style='font-size: 24px;'>Start: " + startdate + startlocation + " </a>");
		// pw.print("<a style='font-size: 24px;'>End: " + enddate + endlocation + " </a>");
		//
		
		pw.print("<h2>Your Car Sharing Requests</h2><div class='entry'><table class='table'>");
		int i = 1;
		int size = hm.size();
		// pw.print("<a style='font-size: 24px;'>Size" + size + "</a>");
		
		
		
		// `reqcartype` varchar(45) DEFAULT NULL,
  // `reqbrand` varchar(45) DEFAULT NULL,
  // `reqmodel` varchar(45) DEFAULT NULL,
  // `reqyear` varchar(45) DEFAULT NULL,
  // `reqvin` varchar(45) NOT NULL,
  // `reqmileage` varchar(45) DEFAULT NULL,
  // `reqcolor` varchar(45) DEFAULT NULL,
  // `reqlocation` varchar(45) DEFAULT NULL,
  // `reqstatus` varchar(45) DEFAULT NULL,
  // `reqcomment` varchar(200) DEFAULT NULL,
		
		pw.print("<tr>");
		// pw.print("<td>User Name</td>");
		pw.print("<td><u>Car Type</u></td>");
		pw.print("<td><u>Make</u></td>");
		pw.print("<td><u>Model</u></td>");
		pw.print("<td><u>Year</u></td>");
		pw.print("<td><u>VIN</u></td>");
		pw.print("<td><u>Mileage</u></td>");
		pw.print("<td><u>Color</u></td>");
		pw.print("<td><u>Location</u></td>");
		pw.print("<td><u>Status</u></td></tr>");
		
		
		for (Map.Entry<String, ShareReq> entry : hm.entrySet()) {
			ShareReq sharereq = entry.getValue();
			
			
			
			pw.print("<tr><td>" + sharereq.getReqcartype() + "</td>");
			pw.print("<td>" + sharereq.getReqbrand() + "</td>");
			pw.print("<td>" + sharereq.getReqmodel() + "</td>");
			pw.print("<td>" + sharereq.getReqyear() + "</td>");
			pw.print("<td>" + sharereq.getReqvin() + "</td>");
			pw.print("<td>" + sharereq.getReqmileage() + "</td>");
			pw.print("<td>" + sharereq.getReqcolor() + "</td>");
			pw.print("<td>" + sharereq.getReqlocation() + "</td>");
			pw.print("<td>" + sharereq.getReqstatus() + "</td></tr>");
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			// if (i % 3 == 1)
				// pw.print("<tr>");
			// pw.print("<td><div id='shop_item'>");

			// pw.print("<h3>" + sharereq.getReqvin() + "</h3>");
			// pw.print("<strong>$" + sharereq.getReqyear() + "</strong><ul>");


			// pw.print("<li><form method='post' action='UpdateCar'>" +
					// "<input type='hidden' name='id' value='"+entry.getKey()+"'>"+

					// "<input type='hidden' name='carbrand' value='"+car.getCarbrand()+"'>"+
					// "<input type='hidden' name='carmodel' value='"+car.getCarmodel()+"'>"+
					// "<input type='hidden' name='price' value='"+car.getPrice()+"'>"+
					// "<input type='hidden' name='image' value='"+car.getImage()+"'>"+
					// "<input type='hidden' name='carcondition' value='"+car.getCarcondition()+"'>"+
					// "<input type='hidden' name='discount' value='"+car.getDiscount()+"'>"+
					// "<input type='hidden' name='owner' value='"+car.getOwner()+"'>"+
					// "<input type='hidden' name='cartype' value='"+car.getCartype()+"'>"+
					// "<input type='hidden' name='location' value='"+car.getLocation()+"'>"+
					// "<input type='submit' class='btn btn-success' value='Update Information'></form></li>");
			

			// pw.print("<li><form method='get' action='DeleteCar'>"+
					// "<input type='hidden' name='id' value='"+entry.getKey()+"'>"+
					// "<input type='submit' class='btn btn-danger' value='Delete this Car'></form></li>");

			// pw.print("<li><form method='post' action='CarDetails'>"+"<input type='hidden' name='id' value='"+entry.getKey()+"'>"+
				    // "<input type='submit' value='CarDetails' class='btn btn-warning'></form></li>");
			
			
			// pw.print("</ul></div></td>");
			// if (i % 3 == 0 || i == size)
				// pw.print("</tr>");
			// i++;
		}
		pw.print("</table></div></div></div>");
		// utility.printHtml("Footer.html");
	}
}
