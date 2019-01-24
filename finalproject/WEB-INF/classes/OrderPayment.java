import java.io.IOException;
import java.io.*;


/* 
	OrderPayment class contains class variables username,ordername,price,image,address,creditcardno.

	OrderPayment  class has a constructor with Arguments username,ordername,price,image,address,creditcardno
	  
	OrderPayment  class contains getters and setters for username,ordername,price,image,address,creditcardno
*/

public class OrderPayment implements Serializable{
	private int orderId;
	private String userName;
	private String orderName;
	private double orderPrice;
	private String userAddress;
	private String creditCardNo;
	private String realName;
	private String startday;
	private String endday;
	private String startlocation;
	private String endlocation;
	public OrderPayment(int orderId,String userName,String orderName,double orderPrice,String userAddress,String creditCardNo, String realName
	,String startday,String endday,String startlocation,String endlocation){
		this.orderId=orderId;
		this.userName=userName;
		this.orderName=orderName;
	 	this.orderPrice=orderPrice;
		this.userAddress=userAddress;
	 	this.creditCardNo=creditCardNo;
	 	this.realName = realName;
	 	this.startday = startday;
	 	this.endday =endday;
	 	this.startlocation =startlocation;
	 	this.endlocation =endlocation;
		}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	public String getstartday() {
		return startday;
	}
	public void setstartday(String startday) {
		this.startday = startday;
	}
	public String getendday() {
		return endday;
	}
	public void setendday(String endday) {
		this.endday = endday;
	}
	public String startlocation() {
		return startlocation;
	}
	public void startlocation(String startlocation) {
		this.startlocation = startlocation;
	}
	public String getendlocation() {
		return endlocation;
	}
	public void setendlocation(String endlocation) {
		this.endlocation = endlocation;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getCreditCardNo() {
		return creditCardNo;
	}

	public void setCreditCardNo(String creditCardNo) {
		this.creditCardNo = creditCardNo;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}


	public double getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(double orderPrice) {
		this.orderPrice = orderPrice;
	}
	

}
