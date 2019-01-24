import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import java.io.IOException;
import java.io.PrintWriter;
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
import javax.xml.transform.stream.StreamResult;
import javax.swing.*;
import java.sql.*;
import java.util.*;
import java.io.*;
@WebServlet("/UpdateCar")
@MultipartConfig(maxFileSize = 16177215) 

public class UpdateCar extends HttpServlet {


	private static final long serialVersionUID = 1L;

	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter pw = response.getWriter();

        try{
       		doPost(request,response);
        }catch(Exception e){

        }

    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		
		Utilities utility = new Utilities(request,pw);
		utility.printHtml("Header.html");
		// utility.printHtml("LeftNavigationBar.html");
		pw.print("<form name ='UpdateCar' action='UpdateCar' method='post' enctype='multipart/form-data'>");
		pw.print("<div id='content'><div class='post'><h2>");
		pw.print("Update Car");
		pw.print("</h2><div class='entry'>");
		if(request.getParameter("UpdateCar")==null)
		{

			pw.print("<table class='table'><tr><td>");

			pw.print("<h3>Car Id</h3></td><td><input class='form-control' name='itemId' type='text' value='"+request.getParameter("id")+"'></input>");
			pw.print("</td></tr><tr><td>");

			pw.print("<h3>Car Make</h3></td><td><input class='form-control' name='brand' type='text' value='"+request.getParameter("carbrand")+"'></input>");
			pw.print("</td></tr><tr><td>");

			pw.print("<h3>Car Model</h3></td><td><input class='form-control' name='itemName' type='text' value='"+request.getParameter("carmodel")+"'></input>");
			pw.print("</td></tr><tr><td>");

			pw.print("<h3>Car Owner</h3></td><td><input class='form-control' name='itemOwner' type='text' value='"+request.getParameter("owner")+"'></input>");
			pw.print("</td></tr><tr><td>");

			pw.print("<h3>Car Model Year</h3></td><td><input class='form-control' name='itemCondition' type='text' value='"+request.getParameter("carcondition")+"'></input>");
			pw.print("</td></tr><tr><td>");

			pw.print("<h3>Car Daily Cost</h3></td><td><input class='form-control' name='itemPrice' type='text'  value='"+request.getParameter("price")+"'></input>");
			pw.print("</td></tr><tr><td>");

			pw.print("<h3>Car Discount</h3></td><td><input class='form-control' name='itemDiscount' type='text' value='"+request.getParameter("discount")+"'></input>");
			pw.print("</td></tr><tr><td>");
			
			int starttime = MySqlDataStoreUtilities.selectStartdate(request.getParameter("id"));
			int endtime = MySqlDataStoreUtilities.selectEnddate(request.getParameter("id"));

			pw.print("<h3>Start Date</h3></td><td><input class='form-control' name='start' type='text' value='"+starttime+"'></input>");
			pw.print("</td></tr><tr><td>");

			pw.print("<h3>End Date</h3></td><td><input class='form-control' name='end' type='text' value='"+endtime+"'></input>");
			pw.print("</td></tr><tr><td>");

			//NEW NEW NEW NEW
			//pw.print("<h3>Enter CarImageName</h3></td><td><input name='itemImage' type='file'></input>");
			pw.print("<h3>Image Link</h3></td><td><input class='form-control' name='itemImage' type='text' value='"+request.getParameter("image")+"'></input>");
			pw.print("</td></tr><tr><td>");
			//NEW NEW NEW NEW

			// pw.print("<h3>Enter Car Type</h3></td><td><input name='cartype' type='text'></input>");
			pw.print("<h3>Enter Car Type</h3></td><td><select class='form-control' name='cartype' class='input'>"+
				"<option value='Economy' selected>Economy</option>>"+
				"<option value='Compact' selected>Compact</option>>"+
				"<option value='Midsize' selected>Midsize</option>>"+
				"<option value='Standard' selected>Standard</option>>"+
				"<option value='Premium' selected>Premium</option>>"+
				"<option value='Midsize SUV' selected>Midsize SUV</option>>"+
				"<option value='Standard SUV' selected>Standard SUV</option>>"+
				"<option value='' selected>Select car type</option>>");
			pw.print("</td></tr><tr><td>");

			pw.print("<h3>Current Location</h3></td><td><select class='form-control' name='location' class='input'>"+
				"<option value='Chicago - IIT Campus' selected>Chicago - IIT Campus</option>"+
				"<option value='Chicago - ORD Airport' selected>Chicago - ORD Airport</option>"+
				"<option value='Evanston - Northwestern Campus' selected>Evanston - Northwestern Campus</option>"+
				"<option value='Champaign - UIUC Campus' selected>Champaign - UIUC Campus</option>"+
				"<option value='St. Louis - Enterprise Center' selected>St. Louis - Enterprise Center</option>"+
				"<option value='Madison - Kohl Center' selected>Madison - Kohl Center</option>"+
				"<option value='Indianapolis - Hyatt Regency' selected>Indianapolis - Hyatt Regency</option>"+
				"<option value='' selected>Select current location</option>");
			pw.print("</td></tr>");
			pw.print("<tr>");
			pw.print("<td></td>");
			pw.print("<td><input type='submit' name='UpdateCar' value='Update Car' class='btn btn-info' style='width: 200px'></td>");
			// pw.print("<td><input type='submit' name='AddCar' value='Update Car' class='btn btn-info' style='width: 200px'></td>");
			pw.print("</tr></table>");
				
				
				
				
				
				
				
			
		}

		if(request.getParameter("UpdateCar")!=null && request.getParameter("UpdateCar").equals("Update Car"))
		{
			// System.out.println("I am in");
			if(request.getParameter("brand")!=null &&request.getParameter("itemId")!=null&& request.getParameter("itemName")!=null && request.getParameter("itemPrice")!=null 
			&& request.getParameter("itemCondition")!=null && request.getParameter("cartype")!=null && request.getParameter("location")!=null
			&& request.getParameter("itemDiscount")!=null && request.getParameter("itemOwner")!=null && request.getParameter("start")!=null && request.getParameter("end")!=null)
			{
				String brand = request.getParameter("brand");
				String id = request.getParameter("itemId");
				String name = request.getParameter("itemName");
				double price = 0.0;
				price = Double.parseDouble( request.getParameter("itemPrice"));
				System.out.println("Price is:"+price);
				String condition = request.getParameter("itemCondition");
				String cartype = request.getParameter("cartype");
				String owner = request.getParameter("itemOwner");
				String image = request.getParameter("itemImage");

				int start = Integer.parseInt(request.getParameter("start"));
				int end = Integer.parseInt(request.getParameter("end"));

				String location = request.getParameter("location").toString();
				double discount = 0.0;
				discount = Double.parseDouble( request.getParameter("itemDiscount"));
				MySqlDataStoreUtilities.deleteCar(id);
				MySqlDataStoreUtilities.deleteTime(id);
				MySqlDataStoreUtilities.InsertCar(id,name,price,image,cartype,condition,brand,discount,owner,location);
				response.sendRedirect("http://localhost/finalproject/CarList");
				MySqlDataStoreUtilities.InsertTime(id,start,end,location);
				
			}
			else
				pw.print("<h4 style='color:red'><br>Please input valid item</h4>");
		
		
		
		}
		
		//pw.print("<h4 style='color:red'><br>Your can cancell before "+cancel+"</h4>");
		pw.print("</form></div></div></div>");		
		// utility.printHtml("Footer.html");
		
	}

}
