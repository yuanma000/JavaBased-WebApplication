import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@WebServlet("/TopRatedCar")

public class TopRatedCar extends HttpServlet {

	// ArrayList <Mostsold> mostsold = new ArrayList <Mostsold> ();
    // ArrayList <Mostsoldzip> mostsoldzip = new ArrayList <Mostsoldzip> ();
	ArrayList <Bestrating> bestrated = new ArrayList <Bestrating> ();

	protected void doGet(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		// mostsold = MongoDBDataStoreUtilities.mostsoldProducts();
	
		// mostsoldzip = MongoDBDataStoreUtilities.mostsoldZip();
		bestrated = MongoDBDataStoreUtilities.topCars();

		

		String name = "Top Rated Cars";
		

		Utilities utility = new Utilities(request, pw);
		utility.printHtml("Header.html");
		// utility.printHtml("LeftNavigationBar.html");
		
		
		//Best Products
		pw.print("<div id='content'><div class='post'><h2>");
		pw.print("Top 5 Most Liked Rental Cars");
		pw.print("</h2><div class='entry'><table class='table' id='bestseller'>");
		Iterator itr2 = bestrated.iterator();
        while(itr2.hasNext()) {
         Bestrating best = (Bestrating)itr2.next();
 		pw.print("<tr>");
		pw.print("<td width='500px'>");
		pw.print(best.getCarname());
		pw.print("</td>");
		pw.print("<td>");
		pw.print(best.getRating());
		pw.print("</td>");
		pw.print("</tr>");
        }
		pw.print("</table></div></div></div>");	
		
		
		// Most Sold Products by Zipcode
		// pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		// pw.print("<a style='font-size: 24px;'>Most Sold Products by Zipcode</a>");
		// pw.print("</h2><div class='entry'><table id='bestseller'>");
		////
		//pw.println("test11");
		////
		// Iterator itr1 = mostsoldzip.iterator();
         // while(itr1.hasNext()) {
         // Mostsoldzip mostzip = (Mostsoldzip)itr1.next();
 		// pw.print("<tr>");
		// pw.println("<td border: 1px >");
		// pw.println(mostzip.getZipcode());
		//
		// pw.println("test1");
		//
		// pw.println("</td>");
		// pw.println("<td border: 1px >");
		// pw.println(mostzip.getCount());
		// pw.println("</td>");
		// pw.println("</tr>");
        // }
		// pw.print("</table></div></div></div>");	
		
		
		//Most Sold Products
		// pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		// pw.print("<a style='font-size: 24px;'>Most Sold Products</a>");
		// pw.print("</h2><div class='entry'><table id='bestseller'>");
		////
		//pw.println("test22");
		////
         // Iterator itr = mostsold.iterator();
        // while(itr.hasNext()) {
         // Mostsold most = (Mostsold)itr.next();
 		// pw.println("<tr>");
		// pw.println("<td border: 1px >");
		// pw.println(most.getProductname());
		////
		//pw.println("test2");
		////
		// pw.println("</td>");
		// pw.println("<td border: 1px >");
		// pw.println(most.getCount());
		// pw.println("</td>");
		// pw.println("</tr>");
        // }
		// pw.print("</table></div></div></div>");
		
	//	pw.print("</table></div></div></div>");	
		
		
		
		
		// utility.printHtml("Footer.html");
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
