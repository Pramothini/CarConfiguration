package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Automobile;
import client.DefaultSocketClient;

/**
 * Servlet implementation class GetOptionsets
 * @author pramothinidk
 */
@WebServlet("/GetOptionsets")
public class GetOptionsets extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DefaultSocketClient client;
	Automobile automobile = null;

	/**
	 * constructor
	 * @see HttpServlet#HttpServlet()
	 */
	public GetOptionsets() {
		super();
	}

	/**
	 * Interacts with the Client (created in Unit 4), to get the data for the list of available OptionSets.
	 * Creates a form that lists all the available optionsets for each option.
	 * Submits the options that the user selects to ShowSelectedChoices.jsp
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		client = (DefaultSocketClient) session.getAttribute("client");
		if(client != null){
			automobile = client.configure(request.getParameter("selectedmodel"));
			session.setAttribute("automobile", automobile);
			client.closeSession();
			System.out.println("Client is not null! :");
		}
		else{
			System.out.println("Client is null!");
		}
		out.println
		(ServletUtilities.headWithTitle("Car Configuration") +
				"<body>\n" +
				"<center>"+
				"<form action = \"ShowSelectedChoices.jsp\" method = \"get\">");
		if(automobile != null){
			out.println("<h1> Basic Car Choice </h1>");
			out.println(
					"<table border = \"1\">"+
							"<tr>"+
							"<td> <b> Make/Model </td>"+
							"<td>"+request.getParameter("selectedmodel")+"</td> </tr>");
			for(String opsetName : automobile.getOptionsetNames()){
				out.println(
						"<tr>"+
								"<td> <b>"+ opsetName + " </b> </td>"+
								"<td> <select name = \"" +opsetName +"\">");
				for(String optName : automobile.getOptionNames(opsetName))
					out.println("<option>"+ optName +"</option>");
				out.println("</td> </tr>");
			}
			out.println("</table> <br/> <input type=\"submit\" value = \"ok\">");
			out.println("</form></center></body></html>");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
