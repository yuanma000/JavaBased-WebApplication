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
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpSession;
import java.lang.Math;
import java.util.Calendar; 
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Date;
import java.time.LocalDate;

@WebServlet("/SearchCar")

public class SearchCar extends HttpServlet {

	/* Trending Page Displays all the Cars and their Information in webpage */

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		HashMap<String, Car> all = new HashMap<String, Car>();

		String lat = "";
		String lng = "";
		String latd = "";
		String lngd = "";
		
		String address="1600+Amphitheatre+Parkway,+Mountain+View,+CA";

	/* Checks the Cars type whether it is toyota or honda or  BMW*/
	
		if ((request.getParameter("pickuplocation").equals("Select a location")) || (request.getParameter("pickupdate").length() == 0) || (request.getParameter("dropoffdate").length() == 0))
		{
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please select dates and locations!");
			response.sendRedirect("Home");
			return;
		}
		
		String pickupdatestring = request.getParameter("pickupdate");
		int pickupyear = Integer.parseInt(pickupdatestring.substring(6, 10));
		int pickupmonth = Integer.parseInt(pickupdatestring.substring(0, 2));
		int pickupday = Integer.parseInt(pickupdatestring.substring(3, 5));
		//Start date and Start location
		int startdate = pickupyear*10000 + pickupmonth*100 + pickupday;
		String startlocation = request.getParameter("pickuplocation");
		

		String dropoffstring = request.getParameter("dropoffdate");
		int dropoffyear = Integer.parseInt(dropoffstring.substring(6, 10));
		int dropoffmonth = Integer.parseInt(dropoffstring.substring(0, 2));
		int dropoffday = Integer.parseInt(dropoffstring.substring(3, 5));

		//End date and End location
		int enddate = dropoffyear*10000 + dropoffmonth*100 + dropoffday;
		String endlocation = request.getParameter("dropofflocation");
		if (endlocation.equals("Same as pick up"))
		{
			endlocation = startlocation;
		}
		
		if (startdate > enddate)
		{
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Your selected pick-up date is after drop-off date! Please search again!");
			response.sendRedirect("Home");
			return;
		}
		
		
		DateFormat sdt = new SimpleDateFormat("MM/dd/yyyy");
		
		Date today = new Date();
		String todaydate = sdt.format(today);
		int todaydateyear = Integer.parseInt(todaydate.substring(6, 10));
		int todaydatemonth = Integer.parseInt(todaydate.substring(0, 2));
		int todaydateday = Integer.parseInt(todaydate.substring(3, 5));
		//Start date and Start location
		int todaydateint = todaydateyear*10000 + todaydatemonth*100 + todaydateday;
		

		
		if(startdate<todaydateint)
		{
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please select your pick-up on or after today!");
			response.sendRedirect("Home");
			return;
		}
		if(startdate==enddate)
		{
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please select a minimum duration of one day!");
			response.sendRedirect("Home");
			return;
		}
		// pw.print(todaydateint);
		
		//For testing
		// pw.print("StartDate:"+startdate);
		// pw.print("startlocation"+startlocation);
		// pw.print("EndDate"+enddate);
		// pw.print("EndLocation"+endlocation);
		
		try{
			all = MySqlDataStoreUtilities.searchAvailableCars(startdate,enddate,startlocation);
		}catch(Exception e){

		}
		
		String name = null;
		String ManufacturerName = request.getParameter("manufacturer");
		HashMap<String, Car> hm = new HashMap<String, Car>();
		hm.putAll(all);
		name="";

		Utilities utility = new Utilities(request, pw);
		utility.printHtml("Header.html");
		// utility.printHtml("LeftAPI.html");
		// utility.printHtml("LeftNavigationBar.html");
		
		pw.print("<div id='content'><div class='post'><h2>");
		
		pw.print("Available Cars");
		pw.print("<h3>Searched Dates: " + request.getParameter("pickupdate") + " - " + request.getParameter("dropoffdate") + "</h3>");
		pw.print("<h3>Pick-up Location: " + request.getParameter("pickuplocation") + "</h3>");
		pw.print("<h3>Drop-off Location: " + request.getParameter("dropofflocation") + "</h3>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		int i = 1;
		int size = hm.size();
		for (Map.Entry<String, Car> entry : hm.entrySet()) {
			Car car = entry.getValue();
			if (i % 3 == 1)
				pw.print("<tr>");
			pw.print("<td><div id='shop_item'>");
			pw.print("<li id='item'><img src='"+car.getImage() + "' alt='' /></li>");
			pw.print("<h3>" + car.getCarbrand() + " "+ car.getCarmodel() + "</h3>");
			
			pw.print("<strong>$" + car.getPrice() + " Per Day</strong><ul>");
			pw.print("<li><form name ='SearchCar' action='CheckOut' method='post'>");
			pw.print("<input style='width:190px' type='submit' class='btn btn-success' value='Rent This Car'>"
					+"<input type='hidden' name='carid' value='"+car.getId()+"'>"
					+"<input type='hidden' name='carmake' value='"+car.getCarbrand()+"'>"
					+"<input type='hidden' name='carmodel' value='"+car.getCarmodel()+"'>"
					+"<input type='hidden' name='price' value='"+car.getPrice()+"'>"
					+"<input type='hidden' name='cartype' value='"+car.getCartype()+"'>"
					+"<input type='hidden' name='pickupdate' value='"+pickupdatestring+"'>"
					+"<input type='hidden' name='dropoffdate' value='"+dropoffstring+"'>"
					+"<input type='hidden' name='pickupdateint' value='"+startdate+"'>"
					+"<input type='hidden' name='dropoffdateint' value='"+enddate+"'>"
					+"<input type='hidden' name='startlocation' value='"+startlocation+"'>"
					+"<input type='hidden' name='endlocation' value='"+endlocation+"'>"
					+"</form></li>");
			// pw.print("&nbsp");
			
			pw.print("<li><form name ='SearchCar' action='CarDetails' method='post'>");
			pw.print("<input style='width:190px' type='submit' class='btn btn-info' value='View Car Details'>");
			pw.print("<input type='hidden' name='id' value='"+car.getId()+"'></form></li>");
			
			// pw.print("&nbsp");
			
			pw.print("<li><form name ='WriteFeedbacks' action='WriteReview' method='post'>");
			pw.print("<input style='width:190px' type='submit' class='btn btn-primary' value='Write Feedbacks'>");
			pw.print("<input type='hidden' name='carbrand' value='"+car.getCarbrand()+"'>");
			pw.print("<input type='hidden' name='carmodel' value='"+car.getCarmodel()+"'></form></li>");
			
			// pw.print("&nbsp");
			
			pw.print("<li><form name ='ViewFeedbacks' action='ViewReview' method='post'>");
			pw.print("<input style='width:190px' type='submit' class='btn btn-primary' value='View Feedbacks'>");
			pw.print("<input type='hidden' name='carmakemodel' value='"+car.getCarbrand()+" "+car.getCarmodel()+"'></form></li>");
			
			pw.print("</ul></div></td>");
			if (i % 3 == 0 || i == size)
				pw.print("</tr>");
			i++;
		}

		if(startlocation.equals("Chicago - IIT Campus")){
			lat="41.8348";
			lng="-87.6270";
		}else if(startlocation.equals("Chicago - ORD Airport")){
			lat="41.9928";
			lng="-87.8843";
		}else if(startlocation.equals("Evanston - Northwestern Campus")){
			lat="42.0564";
			lng="-87.6752";
		}else if(startlocation.equals("Champaign - UIUC Campus")){
			lat="40.1090";
			lng="-88.2322";
		}else if(startlocation.equals("St. Louis - Enterprise Center")){
			lat="38.6339";
			lng="-90.2370";
		}else if(startlocation.equals("Madison - Kohl Center")){
			lat="43.0693";
			lng="-89.3967";
		}else if(startlocation.equals("Indianapolis - Hyatt Regency")){
			lat="39.7662";
			lng="-86.1610";
		}
		
		if(endlocation.equals("Chicago - IIT Campus")){
			latd="41.8348";
			lngd="-87.6270";
		}else if(endlocation.equals("Chicago - ORD Airport")){
			latd="41.9928";
			lngd="-87.8843";
		}else if(endlocation.equals("Evanston - Northwestern Campus")){
			latd="42.0564";
			lngd="-87.6752";
		}else if(endlocation.equals("Champaign - UIUC Campus")){
			latd="40.1090";
			lngd="-88.2322";
		}else if(endlocation.equals("St. Louis - Enterprise Center")){
			latd="38.6339";
			lngd="-90.2370";
		}else if(endlocation.equals("Madison - Kohl Center")){
			latd="43.0693";
			lngd="-89.3967";
		}else if(endlocation.equals("Indianapolis - Hyatt Regency")){
			latd="39.7662";
			lngd="-86.1610";
		}
		
		pw.print("<div align='center'>");
		pw.print("<div id='map' style='height:400px;width:840px;'></div>");
		pw.print("<script>var marker;");
		//init the map
		pw.print("function initMap(){");
		pw.print("bounds = new google.maps.LatLngBounds();;");
		pw.print("map = new google.maps.Map(document.getElementById('map'),{");
		// pw.print("center:{lat:41.9138, lng:-87.75565},");
		// pw.print("center:{lat:"+latcs+", lng:"+lngcs+"},");
		// pw.print("zoom:10");
		pw.print("});");


		//marker2
		if(endlocation.equals(startlocation))
		{
					//set the marker
		pw.print("marker = new google.maps.Marker({");
		pw.print("position:{lat:"+lat+", lng:"+lng+"},");
		pw.print("map:map,");
		pw.print("title:'Pick-up and Drop-off Location: "+startlocation+"'");
		pw.print("});");
		//
		pw.print("loc = new google.maps.LatLng(marker.position.lat(), marker.position.lng());");
		pw.print("bounds.extend(loc);;");
		//
		}
		else
		{
					//set the marker
		pw.print("marker = new google.maps.Marker({");
		pw.print("position:{lat:"+lat+", lng:"+lng+"},");
		pw.print("map:map,");
		pw.print("title:'Pick-up Location: "+startlocation+"'");
		pw.print("});");
		//
		pw.print("loc = new google.maps.LatLng(marker.position.lat(), marker.position.lng());");
		pw.print("bounds.extend(loc);;");
		//
			System.out.println("xxxxxxxxxxxx");
		pw.print("marker = new google.maps.Marker({");
		pw.print("position:{lat:"+latd+", lng:"+lngd+"},");
		pw.print("map:map,");
		pw.print("title:'Drop-off Location: "+endlocation+"'");
		pw.print("});");
				//
		pw.print("loc = new google.maps.LatLng(marker.position.lat(), marker.position.lng());");
		pw.print("bounds.extend(loc);;");
		}

		//
		pw.print("map.fitBounds(bounds);");
		pw.print("map.panToBounds(bounds);");
		pw.print("}");
		pw.print("</script>");
		pw.print("<script async defer src='https://maps.googleapis.com/maps/api/js?key=AIzaSyCCGzseBNCKwfOCfk_uTfslaxxB_0yTl3E&language=en&callback=initMap'>");
		pw.print("</script>");
		pw.print("</div>");
		
		pw.print("&nbsp");

		pw.print("</table></div></div></div>");
		// utility.printHtml("Footer.html");
	}
}
