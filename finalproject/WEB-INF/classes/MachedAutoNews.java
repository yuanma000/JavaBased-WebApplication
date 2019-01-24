import java.io.IOException;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.InputStream;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletOutputStream;

@WebServlet("/MachedAutoNews")

public class MachedAutoNews extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		utility.printHtml("Header.html");

			HashMap<String,Car> selectedproducts=new HashMap<String,Car>();
		try
			{
				
		pw.print("<div id='content'>");
		pw.print("<div class='post'>");
		
		pw.print("<div class='entry'>");
		pw.print("<h2>Mached Auto News from Twitter @Automotive_News</h2>");
		pw.print("<h2>Want to know more about our rental cars?</h2>");
		pw.print("<h2>See below for latest automotive news!</h2>");
		pw.print("<br> <br>");
		// pw.print("<br> <br>");
		// pw.print("<h1>We beat our competitors in all aspects. Price-Match Guaranteed</h2>");
		
			String line=null;
			String TOMCAT_HOME = System.getProperty("catalina.home");

			HashMap<String,Car> productmap=MySqlDataStoreUtilities.getCars();
			
			for(Map.Entry<String, Car> entry : productmap.entrySet())
			{
				
			if(selectedproducts.size()<2 && !selectedproducts.containsKey(entry.getKey()))
			{		
				
				
			BufferedReader reader = new BufferedReader(new FileReader (new File(TOMCAT_HOME+"\\webapps\\finalproject\\DealMatches.txt")));
			line=reader.readLine();
//		

			if(line==null)
			{
				pw.print("<h3 align='center'>No News Found</h3>");
				break;
			}	
			else
			{	
			do {	
			      
				  // if(line.contains(entry.getKey()))
				  if(line.contains(entry.getValue().getCarbrand()))
				  {
				 
					pw.print("<h3>"+line+"</h3>");
					pw.print("<br>");
					selectedproducts.put(entry.getValue().getCarbrand(),entry.getValue());
					// selectedproducts.put(entry.getKey(),entry.getValue());
					break;
				  }
				  
			    }while((line = reader.readLine()) != null);
			
			 }
			 }
			}
			}
			catch(Exception e)
			{
			pw.print("<h2 align='center'>No Offers Found</h2>");
			}
		pw.print("</div>");
		pw.print("</div>");
		pw.print("<div class='post'>");
		pw.print("<h2>");
		pw.print("Car Matches");
		pw.print("</h2>");
		pw.print("<div class='entry'>");
		if(selectedproducts.size()==0)
		{
		pw.print("<h2 align='center'>No Deals Found</h2>");	
		}
		else
		{
		pw.print("<table id='bestseller'>");
		int i = 1;
		int size = selectedproducts.size();
		
		for(Map.Entry<String, Car> entry : selectedproducts.entrySet()){
			
			Car car = entry.getValue();
			if (i % 3 == 1)
			
		pw.print("<tr>");
		pw.print("<td><div id='shop_item'>");
			pw.print("<li id='item'><img src='"
					+car.getImage() + "' alt='' /></li>");
			pw.print("<h3>" + car.getCarbrand() + " " + car.getCarmodel() + "</h3>");
			pw.print("<strong>$" + car.getPrice() + " Per Day</strong><ul>");
		
		pw.print("<li><form method='post' action='CarDetails'>"+"<input type='hidden' name='id' value='"+car.getId()+"'>"+
				    "<input type='submit' value='View Details' class='btn btn-primary' style='width: 190px'></form></li>");
		
		pw.print("<li><form name ='ViewFeedbacks' action='ViewReview' method='post'>");
			pw.print("<input style='width:190px' type='submit' class='btn btn-primary' value='View Feedbacks'>");
			pw.print("<input type='hidden' name='carmakemodel' value='"+car.getCarbrand()+" "+car.getCarmodel()+"'></form></li>");
		
		pw.print("</ul></div></td>");
		if (i % 3 == 0 || i == size)
				pw.print("</tr>");
			i++;
		}
		pw.print("</tr></table>");
		}
		pw.print("</div></div></div>");
		
	}
}
