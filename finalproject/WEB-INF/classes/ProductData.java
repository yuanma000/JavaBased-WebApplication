	
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


@WebServlet("/ProductData")
public class ProductData extends HttpServlet {
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
		
			response.setContentType("text/html");
			Car car= (Car)request.getAttribute("data");


			PrintWriter pw= response.getWriter();

			Utilities utility = new Utilities(request,pw);
			utility.printHtml("Header.html");

			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");

			pw.print("</h2><div class='entry'><table id='bestseller'>");

			pw.print("<tr>");
			pw.print("<td><div id='shop_item'>");

			pw.print("<li id='item'><img src='"
					+car.getImage() + "' alt='' /></li>");
			pw.print("<h3>" + car.getCarbrand() + " " + car.getCarmodel() + "</h3>");
			pw.print("<strong>$" + car.getPrice() + " Per Day</strong><ul>");
				
			pw.print("<li><form method='post' action='UpdateCar'>" +
			"<input type='hidden' name='id' value='"+car.getId()+"'>"+

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
			"<input type='hidden' name='id' value='"+car.getId()+"'>"+
			"<input type='submit' class='btn btn-danger' value='Delete Car' style='width: 190px'></form></li>");

			pw.print("<li><form method='post' action='CarDetails'>"+"<input type='hidden' name='id' value='"+car.getId()+"'>"+
			"<input type='submit' value='Car Details' class='btn btn-warning' style='width: 190px'></form></li>");
			pw.print("</ul></div></td>");
			pw.print("</tr>");
			pw.print("</table></div></div></div>");		
			//utility.printHtml("Footer.html");
		}catch(Exception e)
		{
				
		}
	}
		
	public void destroy()	{
      // do nothing.
	}


}