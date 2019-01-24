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
import java.util.*;
import java.io.*;
import javax.swing.*;
@WebServlet("/ViewShareReqManager")

public class ViewShareReqManager extends HttpServlet {

	/* Trending Page Displays all the Cars and their Information in webpage */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
						Utilities utility = new Utilities(request, pw);
		if(!utility.isLoggedin())
		{
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to View Car Sharing Requests");
			response.sendRedirect("Login");
			return;
		}
		
		// HashMap<String, ShareReq> all = new HashMap<String, ShareReq>();
		HashMap<String, ShareReq> newReqs = new HashMap<String, ShareReq>();
		HashMap<String, ShareReq> approvedReqs = new HashMap<String, ShareReq>();
		HashMap<String, ShareReq> declinedReqs = new HashMap<String, ShareReq>();

		try
		{
			// all = MySqlDataStoreUtilities.getShareReqs();
			newReqs = MySqlDataStoreUtilities.getNewShareReqs();
			approvedReqs = MySqlDataStoreUtilities.getApprovedShareReqs();
			declinedReqs = MySqlDataStoreUtilities.getDeclinedShareReqs();
		}
		catch(Exception e)
		{

		}
		
		
		//String name = null;
		//String ManufacturerName = request.getParameter("manufacturer");
		
		// HashMap<String, ShareReq> hm = new HashMap<String, ShareReq>();
		// hm.putAll(all);
		
		HashMap<String, ShareReq> newhm = new HashMap<String, ShareReq>();
		newhm.putAll(newReqs);
		
		HashMap<String, ShareReq> approvedhm = new HashMap<String, ShareReq>();
		approvedhm.putAll(approvedReqs);
		
		HashMap<String, ShareReq> declinedhm = new HashMap<String, ShareReq>();
		declinedhm.putAll(declinedReqs);
		
		
		//name="";


		/* Header, Left Navigation Bar are Printed.

		All the cars and laptop information are dispalyed in the Content Section

		and then Footer is Printed*/
		//ServletOutputStream out = response.getOutputStream();
		// Utilities utility = new Utilities(request, pw);
		utility.printHtml("Header.html");
		// utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content'><div class='post'>");
		
		pw.print("<h2>New Sharing Requests</h2><div class='entry'><table class='table'>");
		// int i = 1;
		// int size = newhm.size();

		pw.print("<tr>");
		pw.print("<td><u>Username<u></td>");
		pw.print("<td><u>Car Type</u></td>");
		pw.print("<td><u>Make</u></td>");
		pw.print("<td><u>Model</u></td>");
		pw.print("<td><u>Year</u></td>");
		pw.print("<td><u>VIN</u></td>");
		pw.print("<td><u>Mileage</u></td>");
		// pw.print("<td><u>Color</u></td>");
		pw.print("<td><u>Location</u></td>");
		pw.print("<td><u>Status</u></td>");
		pw.print("<td><u>Action</u></td>");
		pw.print("</tr>");
		
		for (Map.Entry<String, ShareReq> entry : newhm.entrySet()) {
			ShareReq sharereq = entry.getValue();
			
			pw.print("<tr>");
			pw.print("<td>" + sharereq.getRequsername() + "</td>");
			pw.print("<td>" + sharereq.getReqcartype() + "</td>");
			pw.print("<td>" + sharereq.getReqbrand() + "</td>");
			pw.print("<td>" + sharereq.getReqmodel() + "</td>");
			pw.print("<td>" + sharereq.getReqyear() + "</td>");
			pw.print("<td>" + sharereq.getReqvin() + "</td>");
			pw.print("<td>" + sharereq.getReqmileage() + "</td>");
			// pw.print("<td>" + sharereq.getReqcolor() + "</td>");
			pw.print("<td>" + sharereq.getReqlocation() + "</td>");
			pw.print("<td>" + sharereq.getReqstatus() + "</td>");
			pw.print("<form name ='ViewShareReqManager' action='ViewShareReqManager' method='get'>");
			pw.print("<input type='hidden' name='pin' value='"+ sharereq.getReqvin() +"'>");
			pw.print("<td width='150px'><input type='submit' name='req' value='Approve' class='btn btn-success btn-xs'> <input type='submit' name='req'value='Decline' class='btn btn-danger btn-xs'></td></form>");
			
			pw.print("</tr>");
		}
		pw.print("</table></div>");
		
		//Approved
		pw.print("<h2>Approved Sharing Requests</h2><div class='entry'><table class='table'>");
		// int i = 1;
		// int size = approvedhm.size();

		pw.print("<tr>");
		pw.print("<td><u>Username<u></td>");
		pw.print("<td><u>Car Type</u></td>");
		pw.print("<td><u>Make</u></td>");
		pw.print("<td><u>Model</u></td>");
		pw.print("<td><u>Year</u></td>");
		pw.print("<td><u>VIN</u></td>");
		pw.print("<td><u>Mileage</u></td>");
		// pw.print("<td><u>Color</u></td>");
		pw.print("<td><u>Location</u></td>");
		pw.print("<td><u>Status</u></td>");
		// pw.print("<td><u>Action</u></td>");
		pw.print("</tr>");
		
		for (Map.Entry<String, ShareReq> entry : approvedhm.entrySet()) {
			ShareReq sharereq = entry.getValue();
			
			
			pw.print("<tr>");
			pw.print("<td>" + sharereq.getRequsername() + "</td>");
			pw.print("<td>" + sharereq.getReqcartype() + "</td>");
			pw.print("<td>" + sharereq.getReqbrand() + "</td>");
			pw.print("<td>" + sharereq.getReqmodel() + "</td>");
			pw.print("<td>" + sharereq.getReqyear() + "</td>");
			pw.print("<td>" + sharereq.getReqvin() + "</td>");
			pw.print("<td>" + sharereq.getReqmileage() + "</td>");
			// pw.print("<td>" + sharereq.getReqcolor() + "</td>");
			pw.print("<td>" + sharereq.getReqlocation() + "</td>");
			pw.print("<td>" + sharereq.getReqstatus() + "</td>");
			
			// pw.print("<td width='150px'><input type='submit' value='Approve' class='btn btn-success btn-xs'> <input type='submit' value='Decline' class='btn btn-danger btn-xs'></td>");
			
			pw.print("</tr>");
		}
		pw.print("</table></div>");
		
		//Declined
		pw.print("<h2>Declined Sharing Requests</h2><div class='entry'><table class='table'>");
		// int i = 1;
		// int size = declinedhm.size();

		pw.print("<tr>");
		pw.print("<td><u>Username<u></td>");
		pw.print("<td><u>Car Type</u></td>");
		pw.print("<td><u>Make</u></td>");
		pw.print("<td><u>Model</u></td>");
		pw.print("<td><u>Year</u></td>");
		pw.print("<td><u>VIN</u></td>");
		pw.print("<td><u>Mileage</u></td>");
		// pw.print("<td><u>Color</u></td>");
		pw.print("<td><u>Location</u></td>");
		pw.print("<td><u>Status</u></td>");
		// pw.print("<td><u>Action</u></td>");
		pw.print("</tr>");
		
		for (Map.Entry<String, ShareReq> entry : declinedhm.entrySet()) {
			ShareReq sharereq = entry.getValue();
			
			pw.print("<tr>");
			pw.print("<td>" + sharereq.getRequsername() + "</td>");
			pw.print("<td>" + sharereq.getReqcartype() + "</td>");
			pw.print("<td>" + sharereq.getReqbrand() + "</td>");
			pw.print("<td>" + sharereq.getReqmodel() + "</td>");
			pw.print("<td>" + sharereq.getReqyear() + "</td>");
			pw.print("<td>" + sharereq.getReqvin() + "</td>");
			pw.print("<td>" + sharereq.getReqmileage() + "</td>");
			// pw.print("<td>" + sharereq.getReqcolor() + "</td>");
			pw.print("<td>" + sharereq.getReqlocation() + "</td>");
			pw.print("<td>" + sharereq.getReqstatus() + "</td>");
			
			// pw.print("<td width='150px'><input type='submit' value='Approve' class='btn btn-success btn-xs'> <input type='submit' value='Decline' class='btn btn-danger btn-xs'></td>");
			
			pw.print("</tr>");
		}
		pw.print("</table></div>");
		
		pw.print("</div></div>");
		if(request.getParameter("req")!=null && request.getParameter("req").equals("Decline"))
		{
			if(request.getParameter("pin") != null)
			{
				String pin=request.getParameter("pin");
				
				
				try
				{
					MySqlDataStoreUtilities.declineReq(pin);
					// UIManager.put("OptionPane.okButtonText", "OK");
					// JOptionPane.showMessageDialog(null, "Car Sharing Request Has Been Declined","Message",JOptionPane.INFORMATION_MESSAGE);
					// response.setHeader("Refresh", "2; URL=http://localhost/finalproject/ViewShareReqManager");
					response.sendRedirect("http://localhost/finalproject/ViewShareReqManager");
				}
				catch(Exception e)
				{
			
				}
			}else
			{
			}
		}
		if(request.getParameter("req")!=null && request.getParameter("req").equals("Approve"))
		{
			if(request.getParameter("pin") != null)
			{
				String pin=request.getParameter("pin");
				
				
				try
				{
					MySqlDataStoreUtilities.approveReq(pin);
					// UIManager.put("OptionPane.okButtonText", "OK");
					// JOptionPane.showMessageDialog(null, "Car Sharing Request Has Been Approved","Message",JOptionPane.INFORMATION_MESSAGE);
					// response.setHeader("Refresh", "2; URL=http://localhost/finalproject/ViewShareReqManager");
					response.sendRedirect("http://localhost/finalproject/ViewShareReqManager");
				}
				catch(Exception e)
				{
			
				}
			}else
			{
			}
		}
		
		
		
		
		
		// utility.printHtml("Footer.html");
	}
}
