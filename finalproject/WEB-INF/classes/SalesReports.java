import com.google.gson.Gson;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.Map;
import javax.swing.*;
@WebServlet("/SalesReports")
public class SalesReports extends HttpServlet {

    //static DBCollection myReviews;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        displayPage(request, response, pw);
    }

    protected void displayPage(HttpServletRequest request, HttpServletResponse response, PrintWriter pw)
            throws ServletException, IOException {

        Utilities utility = new Utilities(request, pw);
        String username=utility.username();
		String usertype=utility.usertype();
        HashMap<Integer, ArrayList<OrderPayment>> orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
       try
				{
					orderPayments=MySqlDataStoreUtilities.selectOrder();
				}
				catch(Exception e)
				{
			
				}
        HashMap<String, Double> ordersale = new HashMap<String, Double>();	
		ordersale = getsales(orderPayments);
        utility.printHtml("Header.html");
		pw.print("<div id='content'><div class='post'>");
        pw.print("<h2>Sales Report</h2>"+ "<div class='entry'>");
        //new
        pw.print("<table  class='table'>");
		pw.print("<tr>");
		pw.print("<td><u>Car Model</u></td>");
		pw.print("<td><u>Daily Cost</u></td>");
		pw.print("<td><u>Total Rental Days</u></td>");
		pw.print("<td><u>Total Sales</u></td></tr>");
		//print table
        Iterator<Map.Entry<Integer, ArrayList<OrderPayment>>> iterator2 = orderPayments.entrySet().iterator();
					while (iterator2.hasNext()) {
					Map.Entry<Integer, ArrayList<OrderPayment>> entry = iterator2.next();
					Integer key = entry.getKey();
					ArrayList<OrderPayment> value = entry.getValue();
					for (OrderPayment oi : orderPayments.get(key)) 
					{
						double sales = 0;
						if(ordersale.containsKey(oi.getOrderName()))
							{
								double income = ordersale.get(oi.getOrderName());
								double price = MySqlDataStoreUtilities.getCarprice(oi.getOrderName());
								String brand = MySqlDataStoreUtilities.getCarbrand(oi.getOrderName());
								String name = MySqlDataStoreUtilities.getCarmodel(oi.getOrderName());
								double temp = income/price;
								int day = (int)temp;
								pw.print("<tr>");	
								pw.print("<td>"+brand+" "+name+"</td><td>"+"$"+price+"</td><td>"+day+"</td><td>"+"$"+income+"</td>");
								pw.print("</tr>");
								ordersale.remove(oi.getOrderName());
							}
					}
					}
		pw.print("</table>");   
        
        pw.println("<script type='text/javascript' src=\"https://www.gstatic.com/charts/loader.js\"></script>");
        pw.println("<script type='text/javascript'>");
        pw.println("google.charts.load('current',{packages: ['corechart', 'bar']});");
        pw.println("google.charts.setOnLoadCallback(drawChart);");
        pw.println("function drawChart() {");
        pw.println("var data = google.visualization.arrayToDataTable([");
		pw.println("['Car Model', 'Total Sales (USD)'],");
        ordersale = getsales(orderPayments);
        Iterator<Map.Entry<Integer, ArrayList<OrderPayment>>> iterator3 = orderPayments.entrySet().iterator();
					while (iterator3.hasNext()) {
					Map.Entry<Integer, ArrayList<OrderPayment>> entry = iterator3.next();
					Integer key = entry.getKey();
					for (OrderPayment oi : orderPayments.get(key)) 
					{
						double sales = 0;
						if(ordersale.containsKey(oi.getOrderName()))
							{	
								double price = ordersale.get(oi.getOrderName());
								//sales = oi.getOrderPrice()*num;
								String name =MySqlDataStoreUtilities.getCarmodel(oi.getOrderName());
								System.out.print(name);
								pw.println("['"+name+"',"+price +"],");
								ordersale.remove(oi.getOrderName());
							}
					}
					}
        pw.println("]);");
        pw.println(" var options = {'title':'Sales Report Bar Chart', 'width':850, 'height':650};");
        pw.println(" var chart = new google.visualization.BarChart(document.getElementById('chart_div'));");
        pw.println("  chart.draw(data, options);}</script>");
        pw.println("<div id='chart_div'></div><br></br><br></br>");

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

			/*HashMap<Integer, ArrayList<OrderPayment>> orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
			try
				{
					orderPayments=MySqlDataStoreUtilities.selectOrder();
				}
				catch(Exception e)
				{
			
				}
			HashMap<String, Integer> ordersale = new HashMap<String, Integer>();	
			ordersale = getsales(orderPayments);
				
            String reviewJson = new Gson().toJson(ordersale);

            response.setContentType("application/JSON");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(reviewJson);*/

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
	private static HashMap<String, Double>  getsales (HashMap<Integer, ArrayList<OrderPayment>> orderPayments)
	{
		HashMap<String, Double> ordersale = new HashMap<String, Double>();
        Iterator<Map.Entry<Integer, ArrayList<OrderPayment>>> iterator = orderPayments.entrySet().iterator();
					while (iterator.hasNext()) {
					Map.Entry<Integer, ArrayList<OrderPayment>> entry = iterator.next();
					Integer key = entry.getKey();
					ArrayList<OrderPayment> value = entry.getValue();
					for (OrderPayment oi : orderPayments.get(key)) 
					{
						if(ordersale.containsKey(oi.getOrderName()))
						{
							double now = ordersale.get(oi.getOrderName()); 
							now =now + oi.getOrderPrice();
							ordersale.put(oi.getOrderName(),now);
						}
							
						else
							ordersale.put(oi.getOrderName(),oi.getOrderPrice());
					}
					}
			return ordersale;
	}

}
