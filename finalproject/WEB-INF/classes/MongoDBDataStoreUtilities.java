import com.mongodb.MongoClient;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.AggregationOutput;
import java.util.*;
                	
public class MongoDBDataStoreUtilities
{
static DBCollection feedbacks;

public static DBCollection getConnection()
{
MongoClient mongo;
mongo = new MongoClient("localhost", 27017);

DB db = mongo.getDB("CarReviews");
 feedbacks= db.getCollection("feedbacks");	
return feedbacks; 
}


public static String insertReview(String carmakemodel,String reviewtext,String username,String reviewRating)
{
	try
		{		
			getConnection();
			
			BasicDBObject doc = new BasicDBObject("title", "feedbacks").
			
				append("carmakemodel", carmakemodel).
				append("reviewtext", reviewtext).
				append("username", username).
				append("reviewRating",Integer.parseInt(reviewRating));
			
				// append("userName", username).
				// append("productName", productname).
				// append("productType", producttype).
				// append("productMaker", productmaker).
				// append("reviewRating",Integer.parseInt(reviewrating)).
				// append("reviewDate", reviewdate).
				// append("reviewText", reviewtext).
				// append("retailerpin", retailerpin).
				// append("retailercity", retailercity).
				// append("price",(int) Double.parseDouble(price)).
				// append("retailername", retailername).
				// append("retailerstate", retailerstate).
				// append("productonsale", productonsale).
				// append("manurebate", manurebate).
				// append("userAge", userAge).
				// append("userGender", userGender).
				// append("userOccupation", userOccupation);
			feedbacks.insert(doc);
			return "Successfull";
		}
		catch(Exception e)
		{
			
		return "UnSuccessfull";
		}	
		
}

public static HashMap<String, ArrayList<Review>> selectReview()
{	
	HashMap<String, ArrayList<Review>> reviews=null;
	
	try
		{

	getConnection();
	DBCursor cursor = feedbacks.find();
	reviews=new HashMap<String, ArrayList<Review>>();
	while (cursor.hasNext())
	{
			BasicDBObject obj = (BasicDBObject) cursor.next();				
	
		   if(!reviews.containsKey(obj.getString("carmakemodel")))
			{	
				ArrayList<Review> arr = new ArrayList<Review>();
				reviews.put(obj.getString("carmakemodel"), arr);
			}
			ArrayList<Review> listReview = reviews.get(obj.getString("carmakemodel"));
			
			Review review =new Review(obj.getString("carmakemodel"),obj.getString("reviewtext"),obj.getString("username"),obj.getString("reviewRating"));
			
			// Review review =new Review(obj.getString("productName"),obj.getString("userName"),obj.getString("productType"),obj.getString("productMaker"),
				// obj.getString("reviewRating"),obj.getString("reviewDate"),obj.getString("reviewText"),obj.getString("retailerpin"),obj.getString("price"),
				// obj.getString("retailercity"),obj.getString("retailername"),obj.getString("retailerstate"),obj.getString("productonsale"),
				// obj.getString("manurebate"),obj.getString("userAge"),obj.getString("userGender"),obj.getString("userOccupation"));
			//add to review hashmap
			listReview.add(review);
		
			}
 		return reviews;
		}
		catch(Exception e)
		{
		 reviews=null;
		 return reviews;	
		}	

     
	}
	
public static  ArrayList <Bestrating> topCars()
{
	ArrayList <Bestrating> Bestrate = new ArrayList <Bestrating> ();
	try
	{
		getConnection();
		int retlimit =5;
		DBObject sort = new BasicDBObject();
		sort.put("reviewRating",-1);
		DBCursor cursor = feedbacks.find().limit(retlimit).sort(sort);
		while(cursor.hasNext())
		{
			BasicDBObject obj = (BasicDBObject) cursor.next();		  		   
			String carname = obj.get("carmakemodel").toString();
			String rating = obj.get("reviewRating").toString();
			Bestrating best = new Bestrating(carname,rating);
			Bestrate.add(best);
		}
	}
	catch(Exception e)
	{ 
	System.out.println(e.getMessage());
	}
	return Bestrate;
}

}	