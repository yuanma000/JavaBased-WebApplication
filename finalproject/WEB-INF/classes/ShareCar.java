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
@WebServlet("/ShareCar")
@MultipartConfig(maxFileSize = 16177215) 

public class ShareCar extends HttpServlet {


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

		Utilities utility = new Utilities(request, pw);
		if(!utility.isLoggedin())
		{
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to Share Your Car");
			response.sendRedirect("Login");
			return;
		}
		utility.printHtml("Header.html");
		// utility.printHtml("LeftBar.html");
		pw.print("<form name ='ShareCar' action='ShareCar' method='post' enctype='multipart/form-data'>");
		pw.print("<div id='content'><div class='post'><h2>");
		pw.print("Car Sharing Application");
		pw.print("</h2><div class='entry'>");
		if(request.getParameter("Sharecar")==null)
		{
			//Testing error msg
			//pw.print("<div class='alert alert-danger' role='alert'>testtest</div>");

			pw.print("<table class='table'>");

			// pw.print("<tr><td><h4>Car Id</h4></td><td><input class='form-control' name='itemId' type='text'></input>");
			// pw.print("</td></tr>");
			
			//Will delete later, for testing
			pw.print("<tr><td><h4>User Name</h4></td><td><input class='form-control' name='requsername' type='text'></input>");
			pw.print("</td></tr>");

			pw.print("<tr><td><h4>Car Make</h4></td><td><input class='form-control' name='reqbrand' type='text'></input>");
			pw.print("</td></tr>");

			pw.print("<tr><td><h4>Car Model</h4></td><td><input class='form-control' name='reqmodel' type='text'></input>");
			pw.print("</td></tr>");

			pw.print("<tr><td><h4>Car Model Year</h4></td><td><input class='form-control' name='reqyear' type='text'></input>");
			pw.print("</td></tr>");
			
			pw.print("<tr><td><h4>VIN Number</h4></td><td><input class='form-control' name='reqvin' type='text'></input>");
			pw.print("</td></tr>");
			
			pw.print("<tr><td><h4>Mileage</h4></td><td><input class='form-control' name='reqmileage' type='text'></input>");
			pw.print("</td></tr>");
			
			pw.print("<tr><td><h4>Color</h4></td><td><input class='form-control' name='reqcolor' type='text'></input>");
			pw.print("</td></tr>");

			// pw.print("<tr><td><h4>Car Owner</h4></td><td><input class='form-control' name='itemOwner' type='text'></input>");
			// pw.print("</td></tr>");

			// pw.print("<tr><td><h4>Car Daily Cost</h4></td><td><input class='form-control' name='itemPrice' type='text'></input>");
			// pw.print("</td></tr>");

			// pw.print("<tr><td><h4>Car Discount</h4></td><td><input class='form-control' name='itemDiscount' type='text'></input>");
			// pw.print("</td></tr>");

			pw.print("<tr><td><h4>Start Date</h4></td><td><input class='form-control' name='start' type='text' placeholder='format: yyyymmdd'></input>");
			pw.print("</td></tr>");

			pw.print("<tr><td><h4>End Date</h4></td><td><input class='form-control' name='end' type='text' placeholder='format: yyyymmdd'></input>");
			pw.print("</td></tr>");

			//NEW NEW NEW NEW
			//pw.print("<h4>Enter CarImageName</h4></td><td><input name='itemImage' type='file'></input>");
			// pw.print("<tr><td><h4>Image Link</h4></td><td><input class='form-control' name='itemImage' type='text'></input>");
			// pw.print("</td></tr>");
			//NEW NEW NEW NEW

			// pw.print("<h3>Enter Car Type</h3></td><td><input name='cartype' type='text'></input>");
			pw.print("<tr><td><h4>Car Type</h4></td><td><select class='form-control' name='reqcartype'>"+
				"<option value='Economy' selected>Economy</option>>"+
				"<option value='Compact' selected>Compact</option>>"+
				"<option value='Midsize' selected>Midsize</option>>"+
				"<option value='Standard' selected>Standard</option>>"+
				"<option value='Premium' selected>Premium</option>>"+
				"<option value='Midsize SUV' selected>Midsize SUV</option>>"+
				"<option value='Standard SUV' selected>Standard SUV</option>>"+
				"<option value='' selected>Select car type</option>>");
			pw.print("</td></tr><tr><td>");

			pw.print("<h4>Current Location</h4></td><td><select class='form-control' name='reqlocation'>"+
				"<option value='Chicago - IIT Campus' selected>Chicago - IIT Campus</option>"+
				"<option value='Chicago - ORD Airport' selected>Chicago - ORD Airport</option>"+
				"<option value='Evanston - Northwestern Campus' selected>Evanston - Northwestern Campus</option>"+
				"<option value='Champaign - UIUC Campus' selected>Champaign - UIUC Campus</option>"+
				"<option value='St. Louis - Enterprise Center' selected>St. Louis - Enterprise Center</option>"+
				"<option value='Madison - Kohl Center' selected>Madison - Kohl Center</option>"+
				"<option value='Indianapolis - Hyatt Regency' selected>Indianapolis - Hyatt Regency</option>"+
				"<option value='' selected>Select current location</option>");
			pw.print("</td></tr>");
			pw.print("<tr><td></td>");
			pw.print("<td><input type='submit' name='ShareCar' value='Submit Car Sharing Request' class='btn btn-success' style='width: 300px'></td></tr></table>");
		}
		
		
		//Submit car sharing req
		if(request.getParameter("ShareCar")!=null && request.getParameter("ShareCar").equals("Submit Car Sharing Request"))
		{
			if(request.getParameter("reqbrand")!=null &&request.getParameter("reqmodel")!=null && request.getParameter("reqyear")!=null 
			&& request.getParameter("reqvin")!=null && request.getParameter("reqmileage")!=null && request.getParameter("reqcolor")!=null
			&& request.getParameter("reqcartype")!=null && request.getParameter("reqlocation")!=null)
			{
				String requsername = request.getParameter("requsername");
				String reqbrand = request.getParameter("reqbrand");
				String reqmodel = request.getParameter("reqmodel");
				String reqyear = request.getParameter("reqyear");
				String reqvin = request.getParameter("reqvin");
				String reqmileage = request.getParameter("reqmileage");
				String reqcolor = request.getParameter("reqcolor");
				String reqcartype = request.getParameter("reqcartype");
				String reqlocation = request.getParameter("reqlocation").toString();

				// discount = Double.parseDouble( request.getParameter("itemDiscount"));
				// int start = Integer.parseInt(request.getParameter("start"));
				// int end = Integer.parseInt(request.getParameter("end"));
				
				 MySqlDataStoreUtilities.InsertShareReq(requsername,reqcartype,reqbrand,reqmodel,reqyear,reqvin,reqmileage,reqcolor,reqlocation);
				// MySqlDataStoreUtilities.InsertTime(id,start,end,location);
				// UIManager.put("OptionPane.okButtonText", "OK");
				// JOptionPane.showMessageDialog(null, "The Car is Added","Message",JOptionPane.INFORMATION_MESSAGE);
				// response.setHeader("Refresh", "0; URL=http://localhost/finalproject/Home");
			}
			else
			{
				pw.print("<div class='alert alert-danger' role='alert'>testtest</div>");
				
				pw.print("<h4 style='color:red'><br>Please input valid item</h4>");
			}
		
		
		}
		
		//Update Car
		// if(request.getParameter("AddCar")!=null && request.getParameter("AddCar").equals("Update A Car"))
		// {
		// 	// System.out.println("I am in");
		// 	if(request.getParameter("brand")!=null &&request.getParameter("itemId")!=null&& request.getParameter("itemName")!=null && request.getParameter("itemPrice")!=null 
		// 	&& request.getParameter("itemCondition")!=null && request.getParameter("cartype")!=null && request.getParameter("location")!=null
		// 	&& request.getParameter("itemDiscount")!=null && request.getParameter("itemOwner")!=null && request.getParameter("start")!=null && request.getParameter("end")!=null)
		// 	{
		// 		String brand = request.getParameter("brand");
		// 		String id = request.getParameter("itemId");
		// 		String name = request.getParameter("itemName");
		// 		double price = 0.0;
		// 		price = Double.parseDouble( request.getParameter("itemPrice"));

		// 		//NEW NEW NEW
		// 		String image = request.getParameter("itemImage");
		// 		// InputStream image = null;
		// 		// Part filePart = request.getPart("itemImage");
		// 		// image = filePart.getInputStream();
		// 		//NEW NEW NEW
		// 		String condition = request.getParameter("itemCondition");
		// 		String cartype = request.getParameter("cartype");
		// 		String location = request.getParameter("location").toString();
		// 		int start = Integer.parseInt(request.getParameter("start"));
		// 		int end = Integer.parseInt(request.getParameter("end"));
		// 		String owner = request.getParameter("itemOwner");
		// 		double discount = 0.0;
		// 		discount = Double.parseDouble( request.getParameter("itemDiscount"));
		// 		MySqlDataStoreUtilities.deleteCar(id);
		// 		MySqlDataStoreUtilities.deleteTime(id);
		// 		MySqlDataStoreUtilities.InsertCar(id,name,price,image,cartype,condition,brand,discount,owner,location);
		// 		MySqlDataStoreUtilities.InsertTime(id,start,end,location);
				
		// 	}
		// 	else
		// 		pw.print("<h4 style='color:red'><br>Please input valid item</h4>");
		
		
		
		// }
		
		//pw.print("<h4 style='color:red'><br>Your can cancell before "+cancel+"</h4>");
		pw.print("</form></div></div></div>");		
		// utility.printHtml("Footer.html");
		
	}

}
