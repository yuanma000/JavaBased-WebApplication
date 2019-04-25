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
@WebServlet("/AddCar")
@MultipartConfig(maxFileSize = 16177215) 

public class AddCar extends HttpServlet {


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
		// utility.printHtml("LeftBar.html");
		pw.print("<form name ='AddCar' action='AddCar' method='post' enctype='multipart/form-data'>");
		pw.print("<div id='content'><div class='post'><h2>");
		pw.print("Add A Car");
		pw.print("</h2><div class='entry'>");
		if(request.getParameter("AddCar")==null)
		{

			pw.print("<table class='table'><tr><td>");

			pw.print("<h4>Car Id</h4></td><td><input class='form-control' name='itemId' type='text'></input>");
			pw.print("</td></tr><tr><td>");

			pw.print("<h4>Car Make</h4></td><td><input class='form-control' name='brand' type='text'></input>");
			pw.print("</td></tr><tr><td>");

			pw.print("<h4>Car Model</h4></td><td><input class='form-control' name='itemName' type='text'></input>");
			pw.print("</td></tr><tr><td>");

			pw.print("<h4>Car Owner</h4></td><td><input class='form-control' name='itemOwner' type='text'></input>");
			pw.print("</td></tr><tr><td>");

			pw.print("<h4>Car Model Year</h4></td><td><input class='form-control' name='itemCondition' type='text'></input>");
			pw.print("</td></tr><tr><td>");

			pw.print("<h4>Car Daily Cost</h4></td><td><input class='form-control' name='itemPrice' type='text'></input>");
			pw.print("</td></tr><tr><td>");

			pw.print("<h4>Car Discount</h4></td><td><input class='form-control' name='itemDiscount' type='text'></input>");
			pw.print("</td></tr><tr><td>");

			pw.print("<h4>Start Date</h4></td><td><input class='form-control' name='start' type='text' placeholder='format: yyyymmdd'></input>");
			pw.print("</td></tr><tr><td>");

			pw.print("<h4>End Date</h4></td><td><input class='form-control' name='end' type='text' placeholder='format: yyyymmdd'></input>");
			pw.print("</td></tr><tr><td>");

			//NEW NEW NEW NEW
			//pw.print("<h4>Enter CarImageName</h4></td><td><input name='itemImage' type='file'></input>");
			pw.print("<h4>Image Link</h4></td><td><input class='form-control' name='itemImage' type='text'></input>");
			pw.print("</td></tr><tr><td>");
			//NEW NEW NEW NEW

			// pw.print("<h3>Enter Car Type</h3></td><td><input name='cartype' type='text'></input>");
			pw.print("<h4>Car Type</h4></td><td><select class='form-control' name='cartype'>"+
				"<option value='Economy' selected>Economy</option>>"+
				"<option value='Compact' selected>Compact</option>>"+
				"<option value='Midsize' selected>Midsize</option>>"+
				"<option value='Standard' selected>Standard</option>>"+
				"<option value='Premium' selected>Premium</option>>"+
				"<option value='Midsize SUV' selected>Midsize SUV</option>>"+
				"<option value='Standard SUV' selected>Standard SUV</option>>"+
				"<option value='' selected>Select car type</option>>");
			pw.print("</td></tr><tr><td>");

			pw.print("<h4>Current Location</h4></td><td><select class='form-control' name='location'>"+
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
			pw.print("<td><input type='submit' name='AddCar' value='Add Car' class='btn btn-success' style='width: 200px'></td>");
			// pw.print("<td><input type='submit' name='AddCar' value='Update Car' class='btn btn-info' style='width: 200px'></td>");
			pw.print("</tr></table>");
		}
		if(request.getParameter("AddCar")!=null && request.getParameter("AddCar").equals("Add Car"))
		{
			if(request.getParameter("brand")!=null &&request.getParameter("itemId")!=null && request.getParameter("itemPrice")!=null 
			&& request.getParameter("itemCondition")!=null && request.getParameter("cartype")!=null && request.getParameter("location")!=null
			&& request.getParameter("itemDiscount")!=null && request.getParameter("itemOwner")!=null && request.getParameter("start")!=null && request.getParameter("end")!=null)
			{
				String brand = request.getParameter("brand");
				String id = request.getParameter("itemId");
				String name = request.getParameter("itemName");
				double price = 0.0;
				price = Double.parseDouble( request.getParameter("itemPrice"));

				String condition = request.getParameter("itemCondition");
				String cartype = request.getParameter("cartype");
				String owner = request.getParameter("itemOwner");
				String image = request.getParameter("itemImage");

				String location = request.getParameter("location").toString();
				double discount = 0.0;
				discount = Double.parseDouble( request.getParameter("itemDiscount"));
				int start = Integer.parseInt(request.getParameter("start"));
				int end = Integer.parseInt(request.getParameter("end"));
				MySqlDataStoreUtilities.InsertCar(id,name,price,image,cartype,condition,brand,discount,owner,location);
				MySqlDataStoreUtilities.InsertTime(id,start,end,location);
				// UIManager.put("OptionPane.okButtonText", "OK");
				// JOptionPane.showMessageDialog(null, "The Car is Added","Message",JOptionPane.INFORMATION_MESSAGE);
				// response.setHeader("Refresh", "0; URL=http://localhost/finalproject/Home");
				response.sendRedirect("http://localhost/finalproject/CarList");
			}
			else{
				pw.print("<h4 style='color:red'><br>Please input invalid item</h4>");
				if(request.getParameter("itemImage")!=null){
					System.out.println("itemImage is valid.");
				}else{
					System.out.println("itemImage is invalid.");
				}
			}
		
		
		}
		pw.print("</form></div></div></div>");		
	}

}
