import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.Map;
@WebServlet("/ShareReq")

/* 
	This is for shared requests. Add by GS.
*/

public class ShareReq extends HttpServlet{
	// private int reqid;
	private String requsername;
	private String reqbrand;
	private String reqmodel;
	private String reqyear;
	private String reqvin;
	private String reqmileage;
	private String reqcolor;
	private String reqcartype;
	private String reqlocation;
	private String reqstatus;
	// private String reqcomment;

	public ShareReq(String requsername,String reqcartype,String reqbrand,String reqmodel,String reqyear,
	String reqvin,String reqmileage,String reqcolor,String reqlocation, String reqstatus)
	{
		// this.reqid = reqid;
		this.requsername = requsername;
		this.reqcartype = reqcartype;
		this.reqbrand = reqbrand;
		this.reqmodel = reqmodel;
		this.reqyear = reqyear;
		this.reqvin = reqvin;
		this.reqmileage = reqmileage;
		this.reqcolor = reqcolor;
		this.reqlocation = reqlocation;
		this.reqstatus = reqstatus;
		// this.reqcomment = reqcomment;
	}
	
	public ShareReq(){
		
	}
	
	// public int getReqid() {
        // return reqid;
    // }

    // public void setId(int id) {
        // this.id = id;
    // }

    public String getRequsername() {
        return requsername;
    }
    public void setRequsername(String requsername) {
        this.requsername = requsername;
    }

    public String getReqcartype() {
        return reqcartype;
    }
    public void setReqcartype(String reqcartype) {
        this.reqcartype = reqcartype;
    }

    public String getReqbrand() {
        return reqbrand;
    }
    public void setReqbrand(String reqbrand) {
        this.reqbrand = reqbrand;
    }

    public String getReqmodel() {
        return reqmodel;
    }
    public void setReqmodel(String reqmodel) {
        this.reqmodel = reqmodel;
    }

    public String getReqyear() {
        return reqyear;
    }
    public void setReqyear(String reqyear) {
        this.reqyear = reqyear;
    }

    public String getReqvin(){
        return reqvin;
    }
    public void setReqvin(String reqvin){
        this.reqvin = reqvin;
    }

    public String getReqmileage(){
        return reqmileage;
    }
    public void setReqmileage(String reqmileage){
        this.reqmileage = reqmileage;
    }

    public String getReqcolor(){
        return reqcolor;
    }
    public void setReqcolor(String reqcolor){
        this.reqcolor = reqcolor;
    }

    public String getReqlocation(){
        return reqlocation;
    }
    public void setReqlocation(String reqlocation){
        this.reqlocation = reqlocation;
    }

    public String getReqstatus(){
        return reqstatus;
    }
    public void setReqstatus(String reqstatus){
        this.reqstatus = reqstatus;
    }

    // public String getReqcomment(){
        // return reqcomment;
    // }
    // public void setReqcomment(String reqcomment){
        // this.reqcomment = reqcomment;
    // }
}
