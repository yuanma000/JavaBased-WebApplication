import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

@WebServlet("/Utilities")

/* 
	Utilities class contains class variables of type HttpServletRequest, PrintWriter,String and HttpSession.

	Utilities class has a constructor with  HttpServletRequest, PrintWriter variables.
	  
*/

public class Utilities extends HttpServlet{
	HttpServletRequest req;
	PrintWriter pw;
	String url;
	HttpSession session; 
	public Utilities(HttpServletRequest req, PrintWriter pw) {
		this.req = req;
		this.pw = pw;
		this.url = this.getFullURL();
		this.session = req.getSession(true);
	}



	/*  Printhtml Function gets the html file name as function Argument, 
		If the html file name is Header.html then It gets Username from session variables.
		Account ,Cart Information ang Logout Options are Displayed*/

	public void printHtml(String file) {
		String result = HtmlToString(file);
		//to print the right navigation in header of username cart and logout etc
		if (file == "Header.html") {
				result=result+"<div id='menu' style='float: right;'><ul>";

			if (session.getAttribute("username")!=null){
				String usertype = session.getAttribute("usertype").toString();
				if(usertype.equals("customer"))
				{
				String username = session.getAttribute("username").toString();
				//username = Character.toUpperCase(username.charAt(0)) + username.substring(1);
				result = result
						+ "<div id='content' ><div class='post' style='padding: 0px 0px'><div class='entry' style='float: right'><ul class='nav'>"
						+ "<li><a href='ViewOrderCustomer'>ViewOrder</a></li>"
						+ "<li><a href='ViewShareReqCustomer'>ViewShareReq</a></li>"
						+ "<li><a>Hello,"+username+"</a></li>"
						+ "<li><a href='ViewOrderCustomer'>Account</a></li>"
						+ "<li><a href='Logout'>Logout<i class='fa fa-user'></i></a></li>"
						+ "</ul></div></div></div>";
				//result = result +"<li><a href='Cart'><span class='glyphicon'>Cart("+CartCount()+")</span></a></li></ul></div><div class='clear'></div></nav>";
				}
				else
				{
				String username = session.getAttribute("username").toString();
				//username = Character.toUpperCase(username.charAt(0)) + username.substring(1);
				result = result
				//style='overflow: visible'
						+ "<div id='content'><div class='post' style='padding: 0px 0px'><div class='entry' style='float: right'><ul class='nav'>"
						// + "<li><a href='ManagerHome'>Manager Home</a></li>"
						+ "<li><a href='CarList'>CarInventory</a></li>"
						+ "<li><a href='ViewShareReqManager'>CarSharing</a></li>"
						+ "<li><a href='AddCar'>AddCar</a></li>"
						// + "<li><a href='RemoveCar'>Remove A Car</a></li>"
						+ "<li><a href='ViewOrderManager'>Orders</a></li>"
						+ "<li><a href='SalesReports'>SalesReport</span></a></li>"
						+ "<li><a>Hello,"+username+"</a></li>"
						+ "<li><a href='Logout'>Logout<i class='fa fa-user'></i></a></li>"
						// + "<li style='float: right; width: 30%; margin-top: 5px; border: 1px solid white; padding: 5px; padding-right: 15px'>"
						+ "<li>"
						+ "<div name = 'autofillform'>"
						+ "<input type='text' value='' class='form-control'"
						+ "id='searchId' onkeyup='doCompletion()'"
						+ "placeholder='search for cars...' style='padding: 5px; font-size: 16px;' />"
						+ "<div id='auto-row'>"
						// + "<table id='complete-table' class='table' style='position: absolute; width: 315px;'></table>"
						+ "<table id='complete-table' class='table'></table>"
						+ "</div></li>"
						+ "</ul></div></div></div>";
				}
			}
			else

			{
				result = result
				+ "<div id='content' ><div class='post' style='padding: 0px 0px'><div class='entry' style='float: right'><ul class='nav'>"
				+ "<li><a href='Registration'>Sign Up <i class='fa fa-user-plus'></i></a></li>"
				// +"<li><strong style='color: white'>Chi-Ca-Go</strong></li>"
          		+ "<li><a href='Login'>Login <i class='fa fa-user'></i></a></li>"
				+ "</ul></div></div></div>";


			}
		
				pw.print(result);
		} else
				pw.print(result);
	}
	

	/*  getFullURL Function - Reconstructs the URL user request  */

	public String getFullURL() {
		String scheme = req.getScheme();
		String serverName = req.getServerName();
		int serverPort = req.getServerPort();
		String contextPath = req.getContextPath();
		StringBuffer url = new StringBuffer();
		url.append(scheme).append("://").append(serverName);

		if ((serverPort != 80) && (serverPort != 443)) {
			url.append(":").append(serverPort);
		}
		url.append(contextPath);
		url.append("/");
		return url.toString();
	}

	/*  HtmlToString - Gets the Html file and Converts into String and returns the String.*/
	public String HtmlToString(String file) {
		String result = null;
		try {
			String webPage = url + file;
			URL url = new URL(webPage);
			URLConnection urlConnection = url.openConnection();
			InputStream is = urlConnection.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);

			int numCharsRead;
			char[] charArray = new char[1024];
			StringBuffer sb = new StringBuffer();
			while ((numCharsRead = isr.read(charArray)) > 0) {
				sb.append(charArray, 0, numCharsRead);
			}
			result = sb.toString();
		} 
		catch (Exception e) {
		}
		return result;
	} 

	/*  logout Function removes the username , usertype attributes from the session variable*/

	public void logout(){
		session.removeAttribute("username");
		session.removeAttribute("usertype");
	}
	
	/*  logout Function checks whether the user is loggedIn or Not*/

	public boolean isLoggedin(){
		if (session.getAttribute("username")==null)
			return false;
		return true;
	}

	/*  username Function returns the username from the session variable.*/
	
	public String username(){
		if (session.getAttribute("username")!=null)
			return session.getAttribute("username").toString();
		return null;
	}
	
	/*  usertype Function returns the usertype from the session variable.*/
	public String usertype(){
		if (session.getAttribute("usertype")!=null)
			return session.getAttribute("usertype").toString();
		return null;
	}
	
	/*  getUser Function checks the user is a customer or retailer or manager and returns the user class variable.*/
	public User getUser(){
		String usertype = usertype();
		HashMap<String, User> hm=new HashMap<String, User>();
		//String TOMCAT_HOME = System.getProperty("catalina.home");
			try
			{		
				//FileInputStream fileInputStream=new FileInputStream(new File(TOMCAT_HOME+"\\webapps\\csj\\UserDetails.txt"));
				//ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);	      
				//hm= (HashMap)objectInputStream.readObject();
				hm=MySqlDataStoreUtilities.selectUser();
			}
			catch(Exception e)
			{
			}	
		User user = hm.get(username());
		return user;
	}
	
	/*get order size*/
	public int getOrderSize()
	{	
		int size=0;
		try
		{
			size = MySqlDataStoreUtilities.getOrderListSize();
		}
		catch(Exception e)
		{
			
		}
			
		return size;		
	}
	
	//store order
	public void storeOrder(int orderId, String username, String fullname, String creditcardno, String billingaddress, String ordercarid, String startday, String endday, String startlocation, String endlocation, double totalcost)
	{
		try
		{	
			MySqlDataStoreUtilities.insertOrder(orderId, username, fullname, creditcardno, billingaddress, ordercarid, startday, endday, startlocation, endlocation, totalcost);
		}
		catch(Exception e)
		{
			System.out.println("Error: Store order failed.");
		}	
	}
	
	public void modifydateandloc(String ordercarid, int pickupdateint, int dropoffdateint, String loc)
	{
		try
		{	
			MySqlDataStoreUtilities.modifydateandloc(ordercarid, pickupdateint, dropoffdateint, loc);
		}
		catch(Exception e)
		{
			System.out.println("Error: Store order failed.");
		}	
	}
	
		public String storeReview(String carmakemodel, String reviewtext, String username, String reviewRating)
		{
			String message=MongoDBDataStoreUtilities.insertReview(carmakemodel,reviewtext,username,reviewRating);
			if(!message.equals("Successfull"))
			{ return "UnSuccessfull";
			}
			else
			{
			HashMap<String, ArrayList<Review>> reviews= new HashMap<String, ArrayList<Review>>();
			try
			{
				reviews=MongoDBDataStoreUtilities.selectReview();
			}
			catch(Exception e)
			{
				
			}
			if(reviews==null)
			{
				reviews = new HashMap<String, ArrayList<Review>>();
			}
				// if there exist product review already add it into same list for productname or create a new record with product name
				
			if(!reviews.containsKey(carmakemodel)){	
				ArrayList<Review> arr = new ArrayList<Review>();
				reviews.put(carmakemodel, arr);
			}
			ArrayList<Review> listReview = reviews.get(carmakemodel);		
			Review review = new Review(carmakemodel,reviewtext,username,reviewRating);
			listReview.add(review);	
				
				// add Reviews into database
			
			return "Successfull";	
			}
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
// 	/*  getCustomerOrders Function gets  the Orders for the user*/
// 	public ArrayList<OrderItem> getCustomerOrders(){
// 		ArrayList<OrderItem> order = new ArrayList<OrderItem>(); 
// 		if(OrdersHashMap.orders.containsKey(username()))
// 			order= OrdersHashMap.orders.get(username());
// 		return order;
// 	}

// 	/*  getOrdersPaymentSize Function gets  the size of OrderPayment */
// 	public int getOrderPaymentSize(){
// 		HashMap<Integer, ArrayList<OrderPayment>> orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
// 		//String TOMCAT_HOME = System.getProperty("catalina.home");
// 			try
// 			{
// 				//FileInputStream fileInputStream = new FileInputStream(new File(TOMCAT_HOME+"\\webapps\\csj\\PaymentDetails.txt"));
// 				//ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);	      
// 				//orderPayments = (HashMap)objectInputStream.readObject();
// 				orderPayments =MySqlDataStoreUtilities.selectOrder();
// 			}
// 			catch(Exception e)
// 			{
			
// 			}
// 			int size=0;
// 			for(Map.Entry<Integer, ArrayList<OrderPayment>> entry : orderPayments.entrySet()){
// 					 size=entry.getKey();
					 
// 			}
// 			return size;		
// 	}

// 	/*  CartCount Function gets  the size of User Orders*/
// 	public int CartCount(){
// 		if(isLoggedin())
// 		return getCustomerOrders().size();
// 		return 0;
// 	}
	
// 	/* StoreProduct Function stores the Purchased product in Orders HashMap according to the User Names.*/

// 	public void storeProduct(String name,String type,String maker, String acc){
// 		if(!OrdersHashMap.orders.containsKey(username())){	
// 			ArrayList<OrderItem> arr = new ArrayList<OrderItem>();
// 			OrdersHashMap.orders.put(username(), arr);
// 		}
// 		ArrayList<OrderItem> orderItems = OrdersHashMap.orders.get(username());
// 		if(type.equals("wearable") || type.equals("wearables")){
// 			Wearable wearable;
// 			wearable = SaxParserDataStore.wearables.get(name);
// 			OrderItem orderitem = new OrderItem(wearable.getName(), wearable.getPrice(), wearable.getImage(), wearable.getRetailer(),wearable.getDiscount());
// 			orderItems.add(orderitem);
// 		}
// 		if(type.equals("phone") || type.equals("phones")){
// 			Phone phone = null;
// 			phone = SaxParserDataStore.phones.get(name);
// 			OrderItem orderitem = new OrderItem(phone.getName(), phone.getPrice(), phone.getImage(), phone.getRetailer(),phone.getDiscount());
// 			orderItems.add(orderitem);
// 		}
// 		if(type.equals("laptop") || type.equals("laptops")){
// 			Laptop laptop = null;
// 			laptop = SaxParserDataStore.laptops.get(name);
// 			OrderItem orderitem = new OrderItem(laptop.getName(), laptop.getPrice(), laptop.getImage(), laptop.getRetailer(),laptop.getDiscount());
// 			orderItems.add(orderitem);
// 		}
// 		if(type.equals("voice") || type.equals("voices")){
// 			Voice voice = null;
// 			voice = SaxParserDataStore.voices.get(name);
// 			OrderItem orderitem = new OrderItem(voice.getName(), voice.getPrice(), voice.getImage(), voice.getRetailer(),voice.getDiscount());
// 			orderItems.add(orderitem);
// 		}
		
// 		if(type.equals("accessories")){	
// 			Accessory accessory = SaxParserDataStore.accessories.get(name); 
// 			OrderItem orderitem = new OrderItem(accessory.getName(), accessory.getPrice(), accessory.getImage(), accessory.getRetailer(),accessory.getDiscount());
// 			orderItems.add(orderitem);
// 		}
		
// 	}
// 	// store the payment details for orders
// 	public void storePayment(int orderId,
// 		String orderName,double orderPrice,String userAddress,String creditCardNo, String realName, String name, String date){
// 		HashMap<Integer, ArrayList<OrderPayment>> orderPayments= new HashMap<Integer, ArrayList<OrderPayment>>();
// 		HashMap<String, Product> product=new HashMap<String, Product>();
// 		//String TOMCAT_HOME = System.getProperty("catalina.home");
// 			// get the payment details file 
// 			try
// 			{
// 				product=MySqlDataStoreUtilities.selectProduct();
// 				orderPayments=MySqlDataStoreUtilities.selectOrder();
// 			}
// 			catch(Exception e)
// 			{
			
// 			}
// 			if(orderPayments==null)
// 			{
// 				orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
// 			}
// 			// if there exist order id already add it into same list for order id or create a new record with order id
			
// 			if(!orderPayments.containsKey(orderId)){	
// 				ArrayList<OrderPayment> arr = new ArrayList<OrderPayment>();
// 				orderPayments.put(orderId, arr);
// 			}
// 		ArrayList<OrderPayment> listOrderPayment = orderPayments.get(orderId);	
// 		int num = product.get(orderName).getNum();
// 		OrderPayment orderpayment = new OrderPayment(orderId,name,orderName,orderPrice,userAddress,creditCardNo,realName,date);
// 		listOrderPayment.add(orderpayment);	
// 		num --;
// 			// add order details into file

// 			try
// 			{	
// 				FileOutputStream fileOutputStream = new FileOutputStream(new File(TOMCAT_HOME+"\\webapps\\csj\\PaymentDetails.txt"));
// 				ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
//             	objectOutputStream.writeObject(orderPayments);
// 				objectOutputStream.flush();
// 				objectOutputStream.close();       
// 				fileOutputStream.close();
// 				//MySqlDataStoreUtilities.deleteProduct(orderName);
// 				MySqlDataStoreUtilities.insertOrder(orderId,name,orderName,orderPrice,userAddress,creditCardNo,realName,date);
// 				MySqlDataStoreUtilities.updateProductNum(orderName,num);
// 			}
// 			catch(Exception e)
// 			{
// 				System.out.println("inside exception file not written properly");
// 			}	
// 	}
// 	//New
// 	public String storeReview(String productname,String producttype,String productmaker,String reviewrating,String reviewdate,String  reviewtext,String reatilerpin,String price,String city, String retailername, String retailerstate, String productonsale, String manufacturerrebate, String userage, String usergender, String useroccupation){
// 	String message=MongoDBDataStoreUtilities.insertReview(productname,username(),producttype,productmaker,reviewrating,reviewdate,reviewtext,reatilerpin,price,city,retailername,retailerstate,productonsale,manufacturerrebate,userage,usergender,useroccupation);
// 		if(!message.equals("Successfull"))
// 		{ 
// 			//System.out.println("TEST1");
// 			return "UnSuccessfull";
// 		}
// 		else
// 		{
// 		HashMap<String, ArrayList<Review>> reviews= new HashMap<String, ArrayList<Review>>();
// 		try
// 		{
// 			reviews=MongoDBDataStoreUtilities.selectReview();
// 		}
// 		catch(Exception e)
// 		{
			
// 		}
// 		if(reviews==null)
// 		{
// 			reviews = new HashMap<String, ArrayList<Review>>();
// 		}
// 			// if there exist product review already add it into same list for productname or create a new record with product name
			
// 		if(!reviews.containsKey(productname)){	
// 			ArrayList<Review> arr = new ArrayList<Review>();
// 			reviews.put(productname, arr);
// 		}
// 		ArrayList<Review> listReview = reviews.get(productname);		
// 		Review review = new Review(productname,username(),producttype,productmaker,reviewrating,reviewdate,reviewtext,reatilerpin,price,city,retailername,retailerstate,productonsale,manufacturerrebate,userage,usergender,useroccupation);
// 		listReview.add(review);	
			
// 			// add Reviews into database
		
// 		return "Successfull";	
// 		}
// 	}//store review end
	
	
	
	
	
	
	
	
	
	
	
	
	
// 	/* getConsoles Functions returns the Hashmap with all wearable in the store.*/

// 	public HashMap<String, Wearable> getWearables(){
// 			HashMap<String, Wearable> hm = new HashMap<String, Wearable>();
// 			hm.putAll(SaxParserDataStore.wearables);
// 			return hm;
// 	}
	
// 	/* getGames Functions returns the  Hashmap with all Phones in the store.*/

// 	public HashMap<String, Phone> getPhones(){
// 			HashMap<String, Phone> hm = new HashMap<String, Phone>();
// 			hm.putAll(SaxParserDataStore.phones);
// 			return hm;
// 	}
	
// 	/* getTablets Functions returns the Hashmap with all Laptops in the store.*/

// 	public HashMap<String, Laptop> getLaptops(){
// 			HashMap<String, Laptop> hm = new HashMap<String, Laptop>();
// 			hm.putAll(SaxParserDataStore.laptops);
// 			return hm;
// 	}
// 	/* getTablets Functions returns the Hashmap with all voice in the store.*/

// 	public HashMap<String, Voice> getVoices(){
// 			HashMap<String, Voice> hm = new HashMap<String, Voice>();
// 			hm.putAll(SaxParserDataStore.voices);
// 			return hm;
// 	}
	
// 	/* getProducts Functions returns the Arraylist of wearable in the store.*/

// 	public ArrayList<String> getProducts(){
// 		ArrayList<String> ar = new ArrayList<String>();
// 		for(Map.Entry<String, Wearable> entry : getWearables().entrySet()){			
// 			ar.add(entry.getValue().getName());
// 		}
// 		return ar;
// 	}
	
// 	/* getProducts Functions returns the Arraylist of Phones in the store.*/

// 	public ArrayList<String> getProductsPhone(){		
// 		ArrayList<String> ar = new ArrayList<String>();
// 		for(Map.Entry<String, Phone> entry : getPhones().entrySet()){
// 			ar.add(entry.getValue().getName());
// 		}
// 		return ar;
// 	}
	
// 	/* getProducts Functions returns the Arraylist of Laptops in the store.*/

// 	public ArrayList<String> getProductsLaptops(){		
// 		ArrayList<String> ar = new ArrayList<String>();
// 		for(Map.Entry<String, Laptop> entry : getLaptops().entrySet()){
// 			ar.add(entry.getValue().getName());
// 		}
// 		return ar;
// 	}
// 	/* getProducts Functions returns the Arraylist of voice in the store.*/

// 	public ArrayList<String> getProductsVoices(){		
// 		ArrayList<String> ar = new ArrayList<String>();
// 		for(Map.Entry<String, Voice> entry : getVoices().entrySet()){
// 			ar.add(entry.getValue().getName());
// 		}
// 		return ar;
// 	}
	

 }
