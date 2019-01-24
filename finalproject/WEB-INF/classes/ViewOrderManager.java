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
import java.util.Calendar; 
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Date;

@WebServlet("/ViewOrderManager")

public class ViewOrderManager extends HttpServlet {

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
		HashMap<Integer, Order> all = new HashMap<Integer, Order>();

		try{
			all = MySqlDataStoreUtilities.getOrdersM();
		}catch(Exception e){

		}
		
		
		//String name = null;
		//String ManufacturerName = request.getParameter("manufacturer");
		HashMap<Integer, Order> hm = new HashMap<Integer, Order>();
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
		
		pw.print("<h2>All Car Rental Orders</h2><div class='entry'><table class='table'>");
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
		pw.print("<td><u>Order ID</u></td>");
		pw.print("<td><u>Username</u></td>");
		pw.print("<td><u>Rental Car</u></td>");
		pw.print("<td><u>Start Date</u></td>");
		pw.print("<td><u>End Date</u></td>");
		pw.print("<td><u>Pick-up Location</u></td>");
		pw.print("<td><u>Drop-off Location</u></td>");
		pw.print("<td><u>Total Cost</u></td>");
		// pw.print("<td><u>Action</u></td>");
		pw.print("<td><u>Action</u></td></tr>");
		
		
		for (Map.Entry<Integer, Order> entry : hm.entrySet()) {
			Order order = entry.getValue();
			
			double number = order.getTotalcost();
			String total = String.format ("%,.2f", number);
			
			String carid = order.getOrdercarid();
			
			
			HashMap<String, Car> hmcar = MySqlDataStoreUtilities.selectOneCar(carid);
			Car car = hmcar.get(carid);
			
			
			
			pw.print("<tr><td>" + order.getOrderid() + "</td>");
			pw.print("<td>" + order.getUsername() + "</td>");
			pw.print("<td>" + car.getCarbrand() + " " + car.getCarmodel() + "</td>");
			pw.print("<td>" + order.getStartdate() + "</td>");
			pw.print("<td>" + order.getEnddate() + "</td>");
			pw.print("<td>" + order.getStartlocation() + "</td>");
			pw.print("<td>" + order.getEndlocation() + "</td>");
			pw.print("<td>" + "$" + total + "</td>");
			// pw.print("<td>" + order.getEnddate() + "</td>");
			
			
			Calendar now = Calendar.getInstance();
			String startday = order.getStartdate();
			Date startd = now.getTime();
			DateFormat sdt = new SimpleDateFormat("MM/dd/yyyy");
			try
			{
			startd = sdt.parse(startday);
			}
			catch(Exception e)
			{
				
			}
			String endday = order.getEnddate();
			Date endd = now.getTime();
			DateFormat sdt2 = new SimpleDateFormat("MM/dd/yyyy");
			try
			{
			endd = sdt2.parse(endday);
			}
			catch(Exception e)
			{
				
			}
			
			Date today =  now.getTime();
		
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(today);
			cal1.add(Calendar.DATE, 5);
			Date fiveday = cal1.getTime();
			
			
			
			if(startd.compareTo(fiveday) > 0)
			{
				pw.print("<form name ='ViewOrderManager' action='ViewOrderManager' method='get'>");
				pw.print("<input type='hidden' name='id' value='"+order.getOrderid() +"'>");
				pw.print("<input type='hidden' name='start' value='"+order.getStartdate() +"'>");
				pw.print("<input type='hidden' name='end' value='"+ order.getEnddate() +"'>");
				pw.print("<input type='hidden' name='location' value='"+ order.getStartlocation() +"'>");
				pw.print("<input type='hidden' name='car' value='"+ order.getOrdercarid() +"'>");
				pw.print("<td width='150px'><input type='submit' name='Order'value='Cancel' class='btn btn-danger btn-xs'></td></tr></form>");
			}
			else if(endd.compareTo(today) < 0)
			{
				pw.print("<td width='150px'>Past</td></tr>");
			}
			else if((startd.compareTo(today) < 0)&&(endd.compareTo(today) > 0))
			{
				pw.print("<td width='150px'>Currently In Rental</td></tr>");
			}
			else
			{
				pw.print("<td width='150px'>Start within 5 days</td></tr>");
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
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
		pw.print("</table>");
		if(request.getParameter("Order")!=null && request.getParameter("Order").equals("Cancel"))
		{
			if(request.getParameter("id") != null)
			{
				String orderId=request.getParameter("id");
				String start=request.getParameter("start");
				String end=request.getParameter("end");
				String location=request.getParameter("location");
				String car = request.getParameter("car");
				//pw.print("<h4 style='color:red'>"+orderId+start+end+location+car+"</h4>");
				
				try
				{
		
					MySqlDataStoreUtilities.deleteOrder(orderId,start,end,location,car);
					pw.print("<h4 style='color:red'>The order is canceled</h4>");
					response.setHeader("Refresh", "1; URL=http://localhost/finalproject/ViewOrderManager");
				}
				catch(Exception e)
				{
			
				}
			}else
			{
				pw.print("<h4 style='color:red'>Please select any product</h4>");
			}
		}
		pw.print("</div></div></div>");
		// utility.printHtml("Footer.html");
	}
}
