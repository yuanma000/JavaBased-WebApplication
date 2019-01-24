import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.io.OutputStream;
// import java.sql.Blob;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


import java.sql.*;
import java.util.*;
import java.util.Map;
import javax.swing.*;

                	
public class MySqlDataStoreUtilities
{
static Connection conn = null;

public static void getConnection()
{


	try{
	Class.forName("com.mysql.jdbc.Driver").newInstance();
	//MySQL password, change here
	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/finalproject?autoReconnect=true&useSSL=false","root","sdfg4ESZ");
		// conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/finalproject","root","sdfg4ESZ");
	System.out.println("The conn in MySqlDataStoreUtilities is"+ conn);							
	}
	catch(Exception e){
		System.out.println("Cannot connect to mysqldatabase.");
	}							
	

}

//new new new new
public static HashMap<String,Car> getCars(){
	HashMap<String,Car> hm=new HashMap<String,Car>();
	try{
		getConnection();

		String sql = "select * from carlists";
		PreparedStatement pst = conn.prepareStatement(sql);
		// pst.setString(1,"model");
		ResultSet rs = pst.executeQuery();

		while(rs.next()){

			Car car = new Car(rs.getString("id"),rs.getString("carbrand"),rs.getString("carmodel"),
				rs.getDouble("price"),rs.getString("image"),rs.getString("carcondition"),
				rs.getDouble("discount"),rs.getString("owner"),rs.getString("cartype"),rs.getString("location"));
			hm.put(rs.getString("id"),car);
			//car.setId(rs.getString("id"));
		}

	}catch(Exception e){

	}
	return hm;
}
//new new new new

public static void InsertCar(String id,String name,double price, String image, String cartype, String condition, String brand, double discount,String owner,String location)
{
	try{
		
		
		getConnection();
		
		
		
		//modify
		String insertProductQurey = "INSERT INTO  carlists(id,carbrand,carmodel,price,image,carcondition,discount,owner,location,cartype)" +
		"VALUES (?,?,?,?,?,?,?,?,?,?);";
			
			PreparedStatement pst = conn.prepareStatement(insertProductQurey);
			pst.setString(1,id);
			pst.setString(2,brand);
			pst.setString(3,name);
			pst.setDouble(4,price);
			pst.setString(5,image);
			pst.setString(6,condition);
			pst.setDouble(7,discount);
			pst.setString(8,owner);
			pst.setString(9,location);
			pst.setString(10,cartype);
			pst.executeUpdate();
		
		
	}catch(Exception e)
	{
  		e.printStackTrace();
	}
}

//Needs mod
public static void InsertTime(String id,int start,int end,String location)
{
	try{
		
		
		getConnection();
		
		
		
		//modify
		String insertProductQurey = "INSERT INTO dateandlocation(id,startdate,enddate,location)" +
		"VALUES (?,?,?,?);";
			
			PreparedStatement pst = conn.prepareStatement(insertProductQurey);
			pst.setString(1,id);
			pst.setInt(2,start);
			pst.setInt(3,end);
			pst.setString(4,location);
			pst.executeUpdate();
		
		
	}catch(Exception e)
	{
  		e.printStackTrace();
	}
}

public static HashMap<String,Car> selectOneCar(String id)
{	
	//System.out.println("TEST1 TEST1");
	//HashMap<String,User> hm=new HashMap<String,User>();
	HashMap<String,Car> hm=new HashMap<String,Car>();
	try 
	{
		getConnection();
		String sql="select * from  carlists";
		//sql = sql +id;
		// PreparedStatement pst = conn.prepareStatement(sql);
		//pst.setString(1,id);
		Statement stmt=conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		//System.out.println("TEST3 TEST3");
		while(rs.next()){
			if(rs.getString("id").equals(id)){
				Car car = new Car(rs.getString("id"),rs.getString("carbrand"),rs.getString("carmodel"),
					rs.getDouble("price"),rs.getString("image"),rs.getString("carcondition"),
					rs.getDouble("discount"),rs.getString("owner"),rs.getString("cartype"),rs.getString("location"));
					// System.out.println("The id is: "+car.getId());
					hm.put(rs.getString("id"), car);
			}
		}

	}
	catch(Exception e)
	{
		System.out.println("TEST2 TEST2");
	}
	return hm;			
}


public static String deleteCar(String id)
{   String msg = "Car is deleted successfully";
	try
	{
		
		getConnection();
		String deletecarQuery ="Delete from carlists where id=?";
		PreparedStatement pst = conn.prepareStatement(deletecarQuery);
		pst.setString(1,id);
		pst.executeUpdate();

	}
	catch(Exception e)
	{
			msg = "Car cannot be deleted";
	}
	return msg;
}
public static String deleteTime(String Id)
{   String msg = "Car is deleted successfully";
	try
	{		
		getConnection();
		String deletecarQuery ="Delete from dateandlocation where Id=?";
		PreparedStatement pst = conn.prepareStatement(deletecarQuery);
		pst.setString(1,Id);
		pst.executeUpdate();
	}
	catch(Exception e)
	{
			msg = "Car cannot be deleted";
	}
	return msg;
}

public static void insertUser(String username,String password,String repassword,String usertype,String email, String phone)
{
	try
	{	

		getConnection();
		String insertIntoCustomerRegisterQuery = "INSERT INTO Registration(username,password,repassword,usertype,email,phone) "
		+ "VALUES (?,?,?,?,?,?);";	
				
		PreparedStatement pst = conn.prepareStatement(insertIntoCustomerRegisterQuery);
		pst.setString(1,username);
		pst.setString(2,password);
		pst.setString(3,repassword);
		pst.setString(4,usertype);
		pst.setString(5,email);
		pst.setString(6,phone);
		pst.execute();
	}
	catch(Exception e)
	{
	
	}	
}

public static int selectStartdate(String id)
{
	int startdate = 0;
	try
	{
		getConnection();
		String sql ="Select startdate from dateandlocation where id='" + id + "'";
		PreparedStatement pst = conn.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();

		while(rs.next()){
			startdate = rs.getInt("startdate");
		}
	}
	catch(Exception e)
	{

	}
	return startdate;
}

public static int selectEnddate(String id)
{
	int enddate = 0;
	try
	{
		getConnection();
		String sql ="Select enddate from dateandlocation where id='" + id + "'";
		PreparedStatement pst = conn.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();

		while(rs.next()){
			enddate = rs.getInt("enddate");
		}
	}
	catch(Exception e)
	{

	}
	return enddate;
}

//Get orders customer
public static HashMap<Integer,Order> getOrdersC(String userName)
{
	HashMap<Integer,Order> hm=new HashMap<Integer,Order>();
	try{
		getConnection();

		String sql = "select * from orderlists where username='" + userName + "'";
		PreparedStatement pst = conn.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();

		while(rs.next()){

			Order order = new Order(rs.getInt("orderid"),rs.getString("username"),rs.getString("fullname"),rs.getString("creditcardno")
			,rs.getString("billingaddress"),rs.getString("ordercarid"),rs.getString("startdate"),rs.getString("enddate"),
			rs.getString("startlocation"),rs.getString("endlocation"),rs.getDouble("totalcost"));
			hm.put(rs.getInt("orderid"),order);
		}

	}
	catch(Exception e)
	{

	}
	return hm;
}

//Get orders Manager
public static HashMap<Integer,Order> getOrdersM()
{
	HashMap<Integer,Order> hm=new HashMap<Integer,Order>();
	try{
		getConnection();

		String sql = "select * from orderlists";
		PreparedStatement pst = conn.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();

		while(rs.next()){

			Order order = new Order(rs.getInt("orderid"),rs.getString("username"),rs.getString("fullname"),rs.getString("creditcardno")
			,rs.getString("billingaddress"),rs.getString("ordercarid"),rs.getString("startdate"),rs.getString("enddate"),
			rs.getString("startlocation"),rs.getString("endlocation"),rs.getDouble("totalcost"));
			hm.put(rs.getInt("orderid"),order);
		}

	}
	catch(Exception e)
	{

	}
	return hm;
}

//Get Share Reqs
public static HashMap<String,ShareReq> getShareReqs(String userName)
{
	HashMap<String,ShareReq> hm=new HashMap<String,ShareReq>();
	try{
		getConnection();

		String sql = "select * from sharereqlists where requsername='" + userName + "'";
		PreparedStatement pst = conn.prepareStatement(sql);
		// pst.setString(1,"model");
		ResultSet rs = pst.executeQuery();

		while(rs.next()){

			ShareReq sharereq = new ShareReq(rs.getString("requsername"),rs.getString("reqcartype"),rs.getString("reqbrand")
			,rs.getString("reqmodel"),rs.getString("reqyear"),rs.getString("reqvin"),rs.getString("reqmileage"),
			rs.getString("reqcolor"),rs.getString("reqlocation"),rs.getString("reqstatus"));
			hm.put(rs.getString("reqvin"),sharereq);
		}

	}
	catch(Exception e)
	{

	}
	return hm;
}

//Get Share Reqs (New)
public static HashMap<String,ShareReq> getNewShareReqs()
{
	HashMap<String,ShareReq> hm=new HashMap<String,ShareReq>();
	try{
		getConnection();

		String sql = "select * from sharereqlists where reqstatus = 'Submitted'";
		PreparedStatement pst = conn.prepareStatement(sql);
		// pst.setString(1,"model");
		ResultSet rs = pst.executeQuery();

		while(rs.next()){

			ShareReq sharereq = new ShareReq(rs.getString("requsername"),rs.getString("reqcartype"),rs.getString("reqbrand")
			,rs.getString("reqmodel"),rs.getString("reqyear"),rs.getString("reqvin"),rs.getString("reqmileage"),
			rs.getString("reqcolor"),rs.getString("reqlocation"),rs.getString("reqstatus"));
			hm.put(rs.getString("reqvin"),sharereq);
		}

	}
	catch(Exception e)
	{

	}
	return hm;
}

//Get Share Reqs (Approved)
public static HashMap<String,ShareReq> getApprovedShareReqs()
{
	HashMap<String,ShareReq> hm=new HashMap<String,ShareReq>();
	try{
		getConnection();

		String sql = "select * from sharereqlists where reqstatus = 'Approved'";
		PreparedStatement pst = conn.prepareStatement(sql);
		// pst.setString(1,"model");
		ResultSet rs = pst.executeQuery();

		while(rs.next()){

			ShareReq sharereq = new ShareReq(rs.getString("requsername"),rs.getString("reqcartype"),rs.getString("reqbrand")
			,rs.getString("reqmodel"),rs.getString("reqyear"),rs.getString("reqvin"),rs.getString("reqmileage"),
			rs.getString("reqcolor"),rs.getString("reqlocation"),rs.getString("reqstatus"));
			hm.put(rs.getString("reqvin"),sharereq);
		}

	}
	catch(Exception e)
	{

	}
	return hm;
}

//Get Share Reqs (New)
public static HashMap<String,ShareReq> getDeclinedShareReqs()
{
	HashMap<String,ShareReq> hm=new HashMap<String,ShareReq>();
	try{
		getConnection();

		String sql = "select * from sharereqlists where reqstatus = 'Declined'";
		PreparedStatement pst = conn.prepareStatement(sql);
		// pst.setString(1,"model");
		ResultSet rs = pst.executeQuery();

		while(rs.next()){

			ShareReq sharereq = new ShareReq(rs.getString("requsername"),rs.getString("reqcartype"),rs.getString("reqbrand")
			,rs.getString("reqmodel"),rs.getString("reqyear"),rs.getString("reqvin"),rs.getString("reqmileage"),
			rs.getString("reqcolor"),rs.getString("reqlocation"),rs.getString("reqstatus"));
			hm.put(rs.getString("reqvin"),sharereq);
		}

	}
	catch(Exception e)
	{

	}
	return hm;
}

//Add Share Req
public static void InsertShareReq(String requsername,String reqcartype,String reqbrand,String reqmodel,String reqyear,String reqvin,String reqmileage,String reqcolor,String reqlocation)
{
	try
	{
		getConnection();
		String reqstatus = "Submitted";

		//modify
		String insertShareReqQurey = "INSERT INTO  sharereqlists(requsername,reqcartype,reqbrand,reqmodel,reqyear,reqvin,reqmileage,reqcolor,reqlocation,reqstatus)" +
		"VALUES (?,?,?,?,?,?,?,?,?,?);";
			
			PreparedStatement pst = conn.prepareStatement(insertShareReqQurey);
			pst.setString(1,requsername);
			pst.setString(2,reqcartype);
			pst.setString(3,reqbrand);
			pst.setString(4,reqmodel);
			pst.setString(5,reqyear);
			pst.setString(6,reqvin);
			pst.setString(7,reqmileage);
			pst.setString(8,reqcolor);
			pst.setString(9,reqlocation);
			pst.setString(10,reqstatus);
			pst.executeUpdate();

	}
	catch(Exception e)
	{
  		e.printStackTrace();
	}
}

public static HashMap<String,User> selectUser()
{	
	HashMap<String,User> hm=new HashMap<String,User>();
	try 
	{
		getConnection();
		Statement stmt=conn.createStatement();
		String selectCustomerQuery="select * from  Registration";
		ResultSet rs = stmt.executeQuery(selectCustomerQuery);
		while(rs.next())
		{	User user = new User(rs.getString("username"),rs.getString("password"),rs.getString("usertype"),rs.getString("email"),rs.getString("phone"));
				hm.put(rs.getString("username"), user);
		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}


public static HashMap<String,Car> searchAvailableCars(int startdate,int enddate,String location){

	// ArrayList<String> results = new ArrayList<String>();
	HashMap<String, Car> hm = new HashMap<String, Car>();
	try{
		getConnection();
		Statement stmt=conn.createStatement();
		String selectCustomerQuery="select * from  dateandlocation";
		ResultSet rs = stmt.executeQuery(selectCustomerQuery);
		//first, iterate all record in timeandlocation
		while(rs.next()){

			if(startdate >=Integer.parseInt(rs.getString("startdate")) && enddate <= Integer.parseInt(rs.getString("enddate")) 
				&& startdate <= enddate && location.equals(rs.getString("location")))
				{
					try
					{
						getConnection();

						String sql = "select * from carlists where id =" + rs.getString("id");
						PreparedStatement pst = conn.prepareStatement(sql);
						// pst.setString(1,"model");
						ResultSet rs1 = pst.executeQuery();

						while(rs1.next()){

						Car car = new Car(rs.getString("id"),rs1.getString("carbrand"),rs1.getString("carmodel"),
						rs1.getDouble("price"),rs1.getString("image"),rs1.getString("carcondition"),
						rs1.getDouble("discount"),rs1.getString("owner"),rs1.getString("cartype"),rs1.getString("location"));
						hm.put(rs1.getString("id"),car);
					}

					}
					catch(Exception e)
					{

					}
			}
		}

	}catch(Exception e){

	}
	return hm;
}

public static HashMap<String,Car> searchBetterCars(int startdate,int enddate,String location, double price, String cartype){

	// ArrayList<String> results = new ArrayList<String>();
	HashMap<String, Car> hmm = new HashMap<String, Car>();
	// System.out.println(cartype);
	try{
		getConnection();
		Statement stmt=conn.createStatement();
		String selectCustomerQuery="select * from  dateandlocation";
		ResultSet rs2 = stmt.executeQuery(selectCustomerQuery);
		//first, iterate all record in timeandlocation
		while(rs2.next()){

			if(startdate >=Integer.parseInt(rs2.getString("startdate")) && enddate <= Integer.parseInt(rs2.getString("enddate")) 
				&& startdate <= enddate && location.equals(rs2.getString("location")))
				{
					try
					{
						getConnection();

						// String sql = "select * from carlists where price >"+ Double.toString(price) +" order by price asc limit 3";
						String sql = "select * from carlists where cartype ='" + cartype + "'" + " and price >"+ Double.toString(price) +" order by price asc limit 3";
						// String sql = "select * from carlists where cartype ='" + cartype + "' and where price >"+ Double.toString(price);
						PreparedStatement pst = conn.prepareStatement(sql);
						// pst.setString(1,"model");
						ResultSet rs3 = pst.executeQuery();

						while(rs3.next()){

						Car car = new Car(rs2.getString("id"),rs3.getString("carbrand"),rs3.getString("carmodel"),
						rs3.getDouble("price"),rs3.getString("image"),rs3.getString("carcondition"),
						rs3.getDouble("discount"),rs3.getString("owner"),rs3.getString("cartype"),rs3.getString("location"));
						hmm.put(rs3.getString("id"),car);
					}

					}
					catch(Exception e)
					{

					}
			}
		}

	}catch(Exception e){

	}
	return hmm;
}


	/*get order size*/
	public static int getOrderListSize()
	{	
		int size = 0;
		try
		{
			getConnection();
			String sql = "select max(orderid) as maxid from orderlists";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs1 = pst.executeQuery();
			
			while(rs1.next())
			{
				size = rs1.getInt("maxid");
			}
		}
		catch(Exception e)
		{
			
		}
			
		return size;		
	}
	
	//insert order
	public static void insertOrder(int orderId, String username, String fullname, String creditcardno, String billingaddress, String ordercarid, String startday, String endday, String startlocation, String endlocation, double totalcost)
	{
		try
		{
			getConnection();

			String insertShareReqQurey = "INSERT INTO  orderlists(orderId, username, fullname, creditcardno, billingaddress, ordercarid, startdate, enddate, startlocation, endlocation, totalcost)" +
			"VALUES (?,?,?,?,?,?,?,?,?,?,?);";
				
				PreparedStatement pst = conn.prepareStatement(insertShareReqQurey);
				pst.setInt(1,orderId);
				pst.setString(2,username);
				pst.setString(3,fullname);
				pst.setString(4,creditcardno);
				pst.setString(5,billingaddress);
				pst.setString(6,ordercarid);
				pst.setString(7,startday);
				pst.setString(8,endday);
				pst.setString(9,startlocation);
				pst.setString(10,endlocation);
				pst.setDouble(11,totalcost);
				pst.executeUpdate();

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	//modifydateandloc
	public static void modifydateandloc(String ordercarid, int pickupdateint, int dropoffdateint, String loc)
	{
		try
		{
			//get original avaiable startdate and enddate as ostart and oend
			getConnection();
			Statement stmt=conn.createStatement();
			String getoriginal = "select startdate, enddate, location from dateandlocation where id=" + ordercarid + " and startdate<=" + pickupdateint + " and enddate>=" + dropoffdateint;
			ResultSet rs = stmt.executeQuery(getoriginal);
			int ostart = 0;
			int oend = 0;
			String oloc = "";
			while(rs.next())
			{
				ostart = rs.getInt("startdate");
				oend = rs.getInt("enddate");
				oloc = rs.getString("location");
			}
			
			int newenddate = pickupdateint - 1;
			int newstartdate = dropoffdateint + 1;
			
			//Insert new available duration part 1
			getConnection();
			String insertShareReqQurey1 = "INSERT INTO  dateandlocation(id, startdate, enddate, location)" +
			"VALUES (?,?,?,?);";
				
				PreparedStatement pst1 = conn.prepareStatement(insertShareReqQurey1);
				pst1.setString(1,ordercarid);
				pst1.setInt(2,ostart);
				pst1.setInt(3,newenddate);
				pst1.setString(4,oloc);
				pst1.executeUpdate();
			
			
			//Insert new available duration part 2
			getConnection();
			String insertShareReqQurey2 = "INSERT INTO  dateandlocation(id, startdate, enddate, location)" +
			"VALUES (?,?,?,?);";
				
				PreparedStatement pst2 = conn.prepareStatement(insertShareReqQurey2);
				pst2.setString(1,ordercarid);
				pst2.setInt(2,newstartdate);
				pst2.setInt(3,oend);
				pst2.setString(4,loc);
				pst2.executeUpdate();
			
			
			//Delete original available duration
			getConnection();
			//delete from dateandlocation where id ="124" and startdate <= 20180101 and enddate >= 20180102;
			//INSERT INTO `finalproject`.`dateandlocation` (`id`, `startdate`, `enddate`, `location`) VALUES ('124', '20170101', '20200101', 'Chicago - IIT Campus');
			String sql = "delete from dateandlocation where id=" + ordercarid + " and startdate<=" + pickupdateint + " and enddate>=" + dropoffdateint;
			PreparedStatement pst3 = conn.prepareStatement(sql);
			pst3.executeUpdate();
		}
		catch(Exception e)
		{
			
		}
	}
	
	
	public static HashMap<Integer, ArrayList<OrderPayment>> selectOrder()
{	

	HashMap<Integer, ArrayList<OrderPayment>> orderPayments=new HashMap<Integer, ArrayList<OrderPayment>>();
		
	try
	{					

		getConnection();
        //select the table 
		String selectOrderQuery ="select * from orderlists";			
		PreparedStatement pst = conn.prepareStatement(selectOrderQuery);
		ResultSet rs = pst.executeQuery();	
		ArrayList<OrderPayment> orderList=new ArrayList<OrderPayment>();
		while(rs.next())
		{
			if(!orderPayments.containsKey(rs.getInt("orderId")))
			{	
				ArrayList<OrderPayment> arr = new ArrayList<OrderPayment>();
				orderPayments.put(rs.getInt("orderId"), arr);
			}
			ArrayList<OrderPayment> listOrderPayment = orderPayments.get(rs.getInt("orderId"));		
			
			OrderPayment order= new OrderPayment(rs.getInt("orderId"),rs.getString("username"),rs.getString("ordercarid"),rs.getDouble("totalcost"),rs.getString("billingaddress"),rs.getString("creditcardno"),
			rs.getString("fullname"),rs.getString("startdate"),rs.getString("enddate"),rs.getString("startlocation"),rs.getString("endlocation"));
			listOrderPayment.add(order);
					
		}

	}
	catch(Exception e)
	{
	e.printStackTrace();
	}
	return orderPayments;
}

public static String getCarmodel(String id){
	try{
		getConnection();
		String sql ="select * from carlists where id =?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1,id);
		ResultSet rs = pst.executeQuery();
		
		while(rs.next()){
		Car	car = new Car(rs.getString("id"),rs.getString("carbrand"),rs.getString("carmodel"),
				rs.getDouble("price"),rs.getString("image"),rs.getString("carcondition"),
				rs.getDouble("discount"),rs.getString("owner"),rs.getString("cartype"),rs.getString("location"));
				return car.getCarmodel();
		}
		

	}catch(Exception e){
		e.printStackTrace();
	}
	return id;
}

public static String getCarbrand(String id){
	try{
		getConnection();
		String sql ="select * from carlists where id =?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1,id);
		ResultSet rs = pst.executeQuery();
		
		while(rs.next()){
		Car	car = new Car(rs.getString("id"),rs.getString("carbrand"),rs.getString("carmodel"),
				rs.getDouble("price"),rs.getString("image"),rs.getString("carcondition"),
				rs.getDouble("discount"),rs.getString("owner"),rs.getString("cartype"),rs.getString("location"));
				return car.getCarbrand();
		}
		

	}catch(Exception e){
		e.printStackTrace();
	}
	return id;
}



	public static double getCarprice(String id){
	try{
		getConnection();
		String sql ="select * from carlists where id =?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1,id);
		ResultSet rs = pst.executeQuery();
		
		while(rs.next()){
		Car	car = new Car(rs.getString("id"),rs.getString("carbrand"),rs.getString("carmodel"),
				rs.getDouble("price"),rs.getString("image"),rs.getString("carcondition"),
				rs.getDouble("discount"),rs.getString("owner"),rs.getString("cartype"),rs.getString("location"));
		return car.getPrice();
		}
		

	}catch(Exception e){
		e.printStackTrace();
	}
	return 0.0;
}
	public static void deleteOrder(String orderId, String start, String end, String location, String car)
	{
		try
		{
			int before=0,after=0;
			getConnection();
			int id = Integer.parseInt(orderId);
			int pickupyear = Integer.parseInt(start.substring(6, 10));
			int pickupmonth = Integer.parseInt(start.substring(0, 2));
			int pickupday = Integer.parseInt(start.substring(3, 5))-1;
			//Start date and Start location
			int startdate = pickupyear*10000 + pickupmonth*100 + pickupday;
			
			pickupyear = Integer.parseInt(end.substring(6, 10));
			pickupmonth = Integer.parseInt(end.substring(0, 2));
			pickupday = Integer.parseInt(end.substring(3, 5))+1;
			int enddate = pickupyear*10000 + pickupmonth*100 + pickupday;
			
			//get the before data
			getConnection();
			Statement stmt=conn.createStatement();
			String getoriginal = "select startdate, enddate, location from dateandlocation where id=" + car + " and startdate >=0  and enddate =" + startdate;
			ResultSet rs = stmt.executeQuery(getoriginal);
			while(rs.next())
			{
				startdate = rs.getInt("startdate");
				//oend = rs.getInt("enddate");
				location = rs.getString("location");
			}
			// get the after data
			getConnection();
			stmt=conn.createStatement();
			getoriginal = "select startdate, enddate, location from dateandlocation where id=" + car + " and startdate=0"+ enddate +"  and enddate >0";
			rs = stmt.executeQuery(getoriginal);
			while(rs.next())
			{
				enddate= rs.getInt("enddate");
				//location = rs.getString("location");
			}
			//Delete original available duration
			getConnection();
			String sql = "delete from dateandlocation where id=" + car + " and startdate ="+startdate+"  and enddate >0";
			PreparedStatement pst3 = conn.prepareStatement(sql);
			pst3.executeUpdate();
			getConnection();
			 sql = "delete from dateandlocation where id=" + car + " and startdate > 0  and enddate ="+enddate;
			pst3 = conn.prepareStatement(sql);
			pst3.executeUpdate();
			
			
			//insert merge time
			getConnection();
			String insertShareReqQurey2 = "INSERT INTO  dateandlocation(id, startdate, enddate, location)" +
			"VALUES (?,?,?,?);";
				
				PreparedStatement pst2 = conn.prepareStatement(insertShareReqQurey2);
				pst2.setString(1,car);
				pst2.setInt(2,startdate);
				pst2.setInt(3,enddate);
				pst2.setString(4,location);
				pst2.executeUpdate();
			
			//Delete order
			getConnection();
			String deleteOrderQuery ="Delete from orderlists where OrderId= ?";
			PreparedStatement pst = conn.prepareStatement(deleteOrderQuery);
			pst.setInt(1,id);
			pst.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
//Decline Share Reqs
public static void declineReq(String pin)
{
	try{
		getConnection();

		String query = "update sharereqlists set reqstatus = ? where reqvin = ?";
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		preparedStmt.setString(1, "Declined");
		preparedStmt.setString(2, pin);

      // execute the java preparedstatement
      preparedStmt.executeUpdate();
	}
	catch(Exception e)
	{

	}
}
//Decline Share Reqs
public static void approveReq(String pin)
{
	try{
		getConnection();

		String query = "update sharereqlists set reqstatus = ? where reqvin = ?";
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		preparedStmt.setString(1, "Approved");
		preparedStmt.setString(2, pin);

      // execute the java preparedstatement
      preparedStmt.executeUpdate();
	}
	catch(Exception e)
	{

	}
}	
	
	
	
}	