import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.Map;
@WebServlet("/Order")

/* 
	This is for shared requests. Add by GS.
*/

public class Order extends HttpServlet{
	private int orderid;
	private String username;
	private String fullname;
	private String creditcardno;
	private String billingaddress;
	private String ordercarid;
	private String startdate;
	private String enddate;
	private String startlocation;
	private String endlocation;
	private double totalcost;

	public Order(int orderid, String username, String fullname, String creditcardno, String billingaddress, String ordercarid, String startdate, String enddate, String startlocation, String endlocation, double totalcost)
	{
		this.orderid = orderid;
		this.username = username;
		this.fullname = fullname;
		this.creditcardno = creditcardno;
		this.billingaddress = billingaddress;
		this.ordercarid = ordercarid;
		this.startdate = startdate;
		this.enddate = enddate;
		this.startlocation = startlocation;
		this.endlocation = endlocation;
		this.totalcost = totalcost;
	}
	
	public Order(){
		
	}
	
	public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getCreditcardno() {
        return creditcardno;
    }
    public void setCreditcardno(String creditcardno) {
        this.creditcardno = creditcardno;
    }

    public String getBillingaddress() {
        return billingaddress;
    }
    public void setBillingaddress(String billingaddress) {
        this.billingaddress = billingaddress;
    }

    public String getOrdercarid() {
        return ordercarid;
    }
    public void setOrdercarid(String ordercarid) {
        this.ordercarid = ordercarid;
    }

    public String getStartdate(){
        return startdate;
    }
    public void setStartdate(String startdate){
        this.startdate = startdate;
    }

    public String getEnddate(){
        return enddate;
    }
    public void setEnddate(String enddate){
        this.enddate = enddate;
    }

    public String getStartlocation(){
        return startlocation;
    }
    public void setStartlocation(String startlocation){
        this.startlocation = startlocation;
    }

    public String getEndlocation(){
        return endlocation;
    }
    public void setEndlocation(String endlocation){
        this.endlocation = endlocation;
    }

    public double getTotalcost(){
        return totalcost;
    }
    public void setTotalcost(double totalcost){
        this.totalcost = totalcost;
    }

    // public String getReqcomment(){
        // return reqcomment;
    // }
    // public void setReqcomment(String reqcomment){
        // this.reqcomment = reqcomment;
    // }
}
