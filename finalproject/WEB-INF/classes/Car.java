import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.Map;
@WebServlet("/Car")

/* 
	Car class contains class variables name,price,image,manufacturer,brand,condition,discount.

	Car class has a constructor with Arguments name,price,image,manufacturer,brand,condition,discount.
	  
	Car class contains getters and setters for name,price,image,manufacturer,brand,condition,discount.
*/

public class Car extends HttpServlet{
	private String id;
	private String carbrand;
	private String carmodel;
	private double price;
	private String image;
	private String carcondition;
	private double discount;
	private String owner;
    private String cartype;
    private String location;
    
	// private String pickuplocation;
	// private String returnlocation;

	public Car(String id, String carbrand, String carmodel, double price, String image, String carcondition,
		double discount,String owner, String cartype, String location){
		this.id =id;
		this.carbrand = carbrand;
		this.carmodel=carmodel;
		this.price=price;
		this.image=image;
		this.carcondition=carcondition;
		this.discount = discount;
		// this.starttime = starttime;
		// this.endtime = endtime;
		this.owner = owner;
        this.cartype = cartype;
        this.location = location;
		// this.pickuplocation = pickuplocation;
		// this.returnlocation = returnlocation;
	}
	
	public Car(){
		
	}
	
	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCarbrand() {
        return carbrand;
    }

    public void setCarbrand(String carbrand) {
        this.carbrand = carbrand;
    }

    public String getCarmodel() {
        return carmodel;
    }

    public void setCarmodel(String carmodel) {
        this.carmodel = carmodel;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCarcondition() {
        return carcondition;
    }

    public void setCarcondition(String carcondition) {
        this.carcondition = carcondition;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getCartype(){
        return cartype;
    }

    public void setCartype(String cartype){
        this.cartype = cartype;
    }

    public String getLocation(){
        return location;
    }
    public void setLocation(String location){
        this.location = location;
    }
}
