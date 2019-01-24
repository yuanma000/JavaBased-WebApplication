import java.io.IOException;
import java.io.*;


/* 
	Review class contains class variables username,productname,reviewtext,reviewdate,reviewrating

	Review class has a constructor with Arguments username,productname,reviewtext,reviewdate,reviewrating
	  
	Review class contains getters and setters for username,productname,reviewtext,reviewdate,reviewrating
*/

public class Review implements Serializable{
	
	private String carmakemodel;
	private String reviewtext;
	private String username;
	private String reviewRating;
	
	// private String productName;
	// private String productType;
	// private String price;
	// private String retailername;
	// private String retailerpin;
	// private String retailercity;
	// private String retailerstate;
	// private String productonsale;
	// private String productMaker;
	// private String manurebate;
	// private String userName;
	// private String userAge;
	// private String userGender;
	// private String userOccupation;
	
	// private String reviewDate;
	// private String reviewText;
	
	
	
	// public Review(String productName,String userName,String productType,
				  // String productMaker,String reviewRating,String reviewDate,
				  // String reviewText,String retailerpin,String price,
				  // String retailercity,String retailername,String retailerstate,
				  // String productonsale,String manurebate,String userAge,
				  // String userGender,String userOccupation){
					  
	public Review(String carmakemodel,String reviewtext,String username,String reviewRating)
	{
		this.carmakemodel=carmakemodel;
		this.reviewtext=reviewtext;
		this.username=username;
		this.reviewRating=reviewRating;
		
		// this.productName=productName;
		// this.userName=userName;
		// this.productType=productType;
		// this.productMaker=productMaker;
	 	// this.reviewRating=reviewRating;
		// this.reviewDate=reviewDate;
	 	// this.reviewText=reviewText;
		// this.retailerpin=retailerpin;
		// this.price= price;
		// this.retailercity= retailercity;
		
		//Newly added
		// this.retailername=retailername;
		//this.retailerzipcode=retailerzipcode;
		// this.retailerstate=retailerstate;
		// this.productonsale=productonsale;
		// this.manurebate=manurebate;
		// this.userAge=userAge;
		// this.userGender=userGender;
		// this.userOccupation=userOccupation;
	}

	// public Review(String productName, String retailerpin, String reviewRating, String reviewText) {
       // this.productName = productName;
       // this.retailerpin = retailerpin;
       // this.reviewRating = reviewRating;
       // this.reviewText = reviewText;
    // }
	
	public String getCarmakemodel() {
		return carmakemodel;
	}
	public void setCarmakemodel(String carmakemodel) {
		this.carmakemodel = carmakemodel;
	}
	
	public String getReviewtext() {
		return reviewtext;
	}
	public void setReviewtext(String reviewtext) {
		this.reviewtext = reviewtext;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	//1. ProductModelName: Samsung Galaxy 6
	// public String getProductName() {
		// return productName;
	// }
	// public void setProductName(String productName) {
		// this.productName = productName;
	// }
	
	//2. ProductCategory: phone 
	// public String getProductType() {
		// return productType;
	// }
	// public void setProductType(String productType) {
		// this.productType = productType;
	// }
	
	//3. ProductPrice: $499 
	// public String getPrice() {
		// return price;
	// }
	// public void setPrice(String price) {
		// this.price = price;
	// }
	
	//4. RetailerName: SmartPortables
	// public String getRetailerName() {
		// retailername = "SmartPortables";
		// return retailername;
	// }
	// public void setRetailerName(String retailername) {
		// retailername = "SmartPortables";
		// this.retailername = retailername;
	// }
	
	//5. RetailerZip: 60616
	// public String getRetailerPin() {
		// return retailerpin;
	// }
	// public void setRetailerPin(String retailerpin) {
		// this.retailerpin = retailerpin;
	// }
	
	//6. RetailerCity: Chicago
	// public String getRetailerCity() {
		// return retailercity;
	// }
	// public void setRetailerCity(String retailercity) {
		// this.retailercity = retailercity;
	// }
	
	//7. RetailerState: IL
	// public String getRetailerState() {
		// return retailerstate;
	// }
	// public void setRetailerState(String retailerstate) {
		// this.retailerstate = retailerstate;
	// }
	
	//8. ProductOnSale: Yes
	// public String getProductOnSale() {
		// return productonsale;
	// }
	// public void setProductOnSale(String productonsale) {
		// this.productonsale = productonsale;
	// }
	
	//9. ManufacturerName: Samsung
	// public String getProductMaker() {
		// return productMaker;
	// }
	// public void setProductMaker(String productMaker) {
		// this.productMaker = productMaker;
	// }
	
	//10. ManufacturerRebate: Yes
	// public String getManuRebate() {
		// return manurebate;
	// }
	// public void setManuRebate(String manurebate) {
		// this.manurebate = manurebate;
	// }
	
	//11. UserID: whksa8
	// public String getUserName() {
		// return userName;
	// }
	// public void setUserName(String userName) {
		// this.userName = userName;
	// }
	
	//12. UserAge: 24
	// public String getUserAge() {
		// return userAge;
	// }
	// public void setUserAge(String userAge) {
		// this.userAge = userAge;
	// }
	
	//13. UserGender: Male
	// public String getUserGender() {
		// return userGender;
	// }
	// public void setUserGender(String userGender) {
		// this.userGender = userGender;
	// }
	
	//14. UserOccupation: accountant
	// public String getUserOccupation() {
		// return userOccupation;
	// }
	// public void setUserOccupation(String userOccupation) {
		// this.userOccupation = userOccupation;
	// }
	
	//15. ReviewRating: 4
	public String getReviewRating() {
		return reviewRating;
	}
	public void setReviewRating(String reviewRating) {
		this.reviewRating = reviewRating;
	}
	
	//16. ReviewDate: 12/15/2015
	// public String getReviewDate() {
		// return reviewDate;
	// }
	// public void setReviewDate(String reviewDate) {
		// this.reviewDate = reviewDate;
	// }
	
	//17. ReviewText: It has excellent video/audio clarity , however, it overheats after 5 hours of use 
	// public String getReviewText() {
		// return reviewText;
	// }
	// public void setReviewText(String reviewText) {
		// this.reviewText = reviewText;
	// }
}
