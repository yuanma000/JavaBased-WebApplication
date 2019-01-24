import java.io.*;

import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import java.util.*;
import java.text.*;

import java.sql.*;

import java.io.IOException;
import java.io.*;



public class AjaxUtility {
	StringBuffer sb = new StringBuffer();
	boolean namesAdded = false;
	static Connection conn = null;
    static String message;
	public static String getConnection()
	{

		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/finalproject","root","sdfg4ESZ");							
			message="Successfull";
			return message;
		}
		catch(SQLException e)
		{
			 message="unsuccessful";
		     return message;
		}
		catch(Exception e)
		{
			 message="unsuccessful";
		     return message;
		}
	}
	
	public  StringBuffer readdata(String searchId)
	{	
		System.out.println("searchId is: "+searchId);
		HashMap<String,Car> data;
		data=getData();
		
 	    Iterator it = data.entrySet().iterator();	
        while (it.hasNext()) 
	    {
                    Map.Entry pi = (Map.Entry)it.next();
			if(pi!=null)
			{
				Car p=(Car)pi.getValue();                 
                if (p.getCarmodel().toLowerCase().startsWith(searchId))
                {
                		//System.out.println("car model is"+ p.getCarmodel());  
                        sb.append("<product>");
                        sb.append("<id>" + p.getId() + "</id>");
                        sb.append("<productName>" + p.getCarmodel() + "</productName>");
                        sb.append("</product>");
                }
			}
       }
	   
	   return sb;
	}
	
	public static HashMap<String,Car> getData()
	{
		HashMap<String,Car> hm=new HashMap<String,Car>();
		try
		{
			//System.out.println("Test1 Test1");
			getConnection();
			
		    String selectCar="select * from  carlists";
		    PreparedStatement pst = conn.prepareStatement(selectCar);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next())
			{	Car p = new Car(rs.getString("id"),rs.getString("carbrand"),rs.getString("carmodel"),rs.getDouble("price"),rs.getString("image"),rs.getString("carcondition"),rs.getDouble("discount"),rs.getString("owner"),rs.getString("cartype"),rs.getString("location"));
				hm.put(rs.getString("id"), p);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return hm;			
	}

}
