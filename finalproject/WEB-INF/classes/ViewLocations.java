import java.io.IOException;
import java.io.PrintWriter;
import java.io.*;
import java.util.*;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.InputStream;
import javax.xml.transform.stream.StreamResult;
import javax.swing.*;
import java.sql.*;
import java.util.*;
import java.io.*;
@WebServlet("/ViewLocations")
public class ViewLocations extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request,pw);
		utility.printHtml("Header.html");
		pw.print("<form name ='ViewLocations' action='ViewLocations' method='post' enctype='multipart/form-data'>");
		pw.print("<div id='content'><div class='post'><h2>");
		pw.print("Locations");
		pw.print("</h2><div class='entry'>");
		pw.print("<table class='table'><tr><td>");

		
			pw.print("<h3>Chicago - IIT Campus</h3>10 W 35th St, Chicago, IL 60616 <a href='https://www.google.com/maps/place/Illinois+Institute+of+Technology/@41.8348731,-87.6270059,15z/data=!4m2!3m1!1s0x0:0x91cbe4661afb6f1a?ved=2ahUKEwjKg7f15dneAhUJzIMKHWMsDSYQ_BIwDnoECAQQCA target='_blank''><u>View on Google Map</u></a></td><td>");
			pw.print("</td></tr><tr><td>");

			pw.print("<h3>Chicago - ORD Airport</h3>10255 W Zemke Rd, Chicago, IL 60666 <a href='https://www.google.com/maps/place/Advantage+Rent+A+Car/@41.9928586,-87.884332,17.04z/data=!4m5!3m4!1s0x0:0xf0487b39d2527133!8m2!3d41.9930792!4d-87.8812668'><u>View on Google Map</u></a></td><td>");
			pw.print("</td></tr><tr><td>");

			pw.print("<h3>Evanston - Northwestern Campus</h3>633 Clark St, Evanston, IL 60208 <a href='https://www.google.com/maps/place/Northwestern+University/@42.0564594,-87.675267,15z/data=!4m5!3m4!1s0x0:0x2c37b567fad56106!8m2!3d42.0564594!4d-87.675267'><u>View on Google Map</u></a></td><td>");
			pw.print("</td></tr><tr><td>");

			pw.print("<h3>Champaign - UIUC Campus</h3>601 S 6th St, Champaign, IL 61820 <a href='https://www.google.com/maps/place/Campus+Center/@40.109098,-88.2322278,17z/data=!4m8!1m2!2m1!1sChampaign+-+UIUC+Campus+center!3m4!1s0x880cd73f5f6ae609:0x607d9537217db96e!8m2!3d40.1111611!4d-88.2308648'><u>View on Google Map</u></a></td><td>");
			pw.print("</td></tr><tr><td>");

			pw.print("<h3>St. Louis - Enterprise Center</h3>1401 Clark Ave, St. Louis, MO 63103 <a href='https://www.google.com/maps/place/Enterprise+Center/@38.633947,-90.2370582,14z/data=!4m8!1m2!2m1!1sSt.+Louis+Enterprise+Center!3m4!1s0x87d8b313f33f9ffd:0x16d092e190f89a21!8m2!3d38.6268402!4d-90.2026783'><u>View on Google Map</u></a></td><td>");
			pw.print("</td></tr><tr><td>");

			pw.print("<h3>Madison - Kohl Center</h3>601 W Dayton St, Madison, WI 53703 <a href='https://www.google.com/maps/place/Kohl+Center/@43.069396,-89.39672,15z/data=!4m5!3m4!1s0x0:0xb19ae981ef8f344b!8m2!3d43.069396!4d-89.39672'><u>View on Google Map</u></a></td><td>");
			pw.print("</td></tr><tr><td>");

			pw.print("<h3>Indianapolis - Hyatt Regency</h3>1 S Capitol Ave, Indianapolis, IN 46204 <a href='https://www.google.com/maps/place/Hyatt+Regency+Indianapolis/@39.766276,-86.161008,15z/data=!4m5!3m4!1s0x0:0xb65b1bb198f721c!8m2!3d39.766276!4d-86.161008'><u>View on Google Map</u></a></td><td>");
			pw.print("</td></tr><tr><td>");
			pw.print("</td></tr></table>");
	
		pw.print("</form></div></div></div>");		
		
	}
				
}
