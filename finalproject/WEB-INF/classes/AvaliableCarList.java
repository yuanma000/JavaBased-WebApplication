import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
// import java.sql.Blob;
import java.io.InputStream;
import javax.servlet.ServletOutputStream;

@WebServlet("/CarList")

public class AvaliableCarList extends HttpServlet {

	/* Trending Page Displays all the Cars and their Information in webpage */

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		HashMap<String, Car> all = new HashMap<String, Car>();

	/* Checks the Cars type whether it is toyota or honda or  BMW*/
		try{
			all = MySqlDataStoreUtilities.getCars();
		}catch(Exception e){

		}

		String name = null;
		String ManufacturerName = request.getParameter("manufacturer");
		HashMap<String, Car> hm = new HashMap<String, Car>();
		hm.putAll(all);
		name="";


		/* Header, Left Navigation Bar are Printed.

		All the cars and laptop information are dispalyed in the Content Section

		and then Footer is Printed*/
		//ServletOutputStream out = response.getOutputStream();
		Utilities utility = new Utilities(request, pw);
		utility.printHtml("Header.html");
		//utility.printHtml("LeftNavigationBar.html");
		utility.printHtml("");
		utility.printHtml("Footer.html");
	}
}
