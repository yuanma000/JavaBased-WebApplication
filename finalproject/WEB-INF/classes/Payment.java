import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Random;
import java.util.Calendar; 
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Date;
@WebServlet("/Payment")

public class Payment extends HttpServlet {
	
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		

		Utilities utility = new Utilities(request, pw);
		if(!utility.isLoggedin())
		{
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to Pay");
			response.sendRedirect("Login");
			return;
		}
		// get the payment details like credit card no address processed from cart servlet
		String ordercarid = request.getParameter("carid");
		String username = request.getParameter("username");
		String fullname = request.getParameter("fullname");
		String creditcardno=request.getParameter("creditcardno");
		String billingaddress= request.getParameter("billingaddress");
		double totalcost=Double.parseDouble(request.getParameter("totalcost"));
		String startday = request.getParameter("startday");
		String endday = request.getParameter("endday");
		String startlocation = request.getParameter("startlocation");
		String endlocation = request.getParameter("endlocation");
		//get as int
		int pickupdateint = Integer.parseInt(request.getParameter("pickupdateint"));
		int dropoffdateint = Integer.parseInt(request.getParameter("dropoffdateint"));

		//For testing
		// pw.print("StartDate:"+startday);
		// pw.print("startlocation"+startlocation);
		// pw.print("EndDate"+endday);
		// pw.print("EndLocation"+endlocation);
		
		
		Calendar now = Calendar.getInstance();
		Date today =  now.getTime();
        now.add(Calendar.DATE, 14);
        Date date = now.getTime();
        now = Calendar.getInstance(); 
        now.add(Calendar.DATE, 9);
        Date late = now.getTime();
        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd");
		String time = sdFormat.format(date);
		String input = sdFormat.format(today);
		int num = 0;



		Date canceldate = now.getTime();
		Date startd = now.getTime();
		DateFormat sdt = new SimpleDateFormat("MM/dd/yyyy");
		try
		{
		canceldate = sdt.parse(startday);
		startd = sdt.parse(startday);
		}
		catch(Exception e)
		{
			
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(canceldate);
		cal.add(Calendar.DATE, -5);
		canceldate = cal.getTime();
		
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(today);
		cal1.add(Calendar.DATE, 5);
		Date fiveday = cal1.getTime();
		
		// pw.print("StartDate:"+startd);
		// pw.print("CancelDate:"+canceldate);
		// pw.print("Today:"+fiveday);
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");  
		String canceldatestring = dateFormat.format(canceldate); 
		
		if(!billingaddress.isEmpty() && !creditcardno.isEmpty() &&! fullname.isEmpty())
		{

			int orderId = utility.getOrderSize()+1;
			//String ordercarid = "test";
			utility.storeOrder(orderId, username, fullname, creditcardno, billingaddress, ordercarid, startday, endday, startlocation, endlocation, totalcost);
			utility.modifydateandloc(ordercarid, pickupdateint, dropoffdateint, endlocation);

			//remove the order details from cart after processing
			String cancel = sdFormat.format(late);
			utility.printHtml("Header.html");
			pw.print("<div id='content'><div class='post'><h2>");
			pw.print("Order");
			pw.print("</h2><div class='entry'>");
			//MySqlDataStoreUtilities.storePayment();
			//String test =request.getParameter("Total");
			pw.print("<h2>Your Order is stored");
			pw.print("<br>Order No: "+(orderId));
			//compare dates
			if(startd.compareTo(fiveday) > 0)
			{
				pw.print("<h4 style='color:red'><br>Free cancellation until 5 days prior to the rental start date.</h4>");
				pw.print("<h4 style='color:red'><br>For your order, you can cancel before: "+canceldatestring+"</h4>");
			}
			else
			{
				pw.print("<h4 style='color:red'><br>You order starts within 5 days, it cannot be cancelled.</h4>");
			}
			//pw.print("<h4 style='color:red'><br>Test :"+test+ "</h4>");
			pw.print("</h2></div></div></article></section><div class='clear'></div></section>");		
			//utility.printHtml("Footer.html");
		}else
		{
			utility.printHtml("Header.html");
			pw.print("<div id='content'><div class='post'><h2>");
			pw.print("Order");
			pw.print("</h2><div class='entry'>");
			pw.print("<h4 style='color:red'>Please enter valid address and credit card number.</h4>");
			pw.print("<a href='javascript:history.go(-1)' style='width:200px' class='btn btn-info'><font color='ffffff'>Go Back</font></a></td>");
			pw.print("</div></div></div>");		
			utility.printHtml("Footer.html");
		}	
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		
		
	}
}
