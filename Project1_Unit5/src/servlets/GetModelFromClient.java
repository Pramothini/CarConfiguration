package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Automobile;
import client.DefaultSocketClient;

/**
 * Servlet implementation class GetModelFromClient
 * @author pramothinidk
 */
@WebServlet("/GetModelFromClient")
public class GetModelFromClient extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String models = null;
	DefaultSocketClient client;
	Automobile automobile = null;

	/**
	 * Creates a client socket to communicate with the server in port 4444
	 * @see HttpServlet#HttpServlet()
	 */
	public GetModelFromClient() {
		super();
		try{
			client = new DefaultSocketClient(new Socket("localhost", 4444));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Interacts with the Client (created in Unit 4), to get the list of available models.
	 * Creates a form that contains the model names and submits the user choice to the GetOptionsets servlet
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		if(client != null){
			session.setAttribute("client", client);
			if(client.openConnection()){
				models = client.upload();
				System.out.println("The models in servlet :"+models);
			}
		}
		else
			System.out.println("Client is null!");

		PrintWriter out = response.getWriter();
		out.println
		(ServletUtilities.headWithTitle("Car Configuration") +
				"<body>\n" +
				"<center>"+
				"<form action = \"GetOptionsets\" method = \"get\">");
		if(models == null || models == "")
			out.println("There are no models in the server!");
		else{
			out.println("Choose a model to configure it:");
			models = models.substring(1, models.length() - 1);
			String names[] = models.split(",");
			for(int x=0;x< models.split(", ").length;x++)
				out.println("<input type=radio name=selectedmodel value = "+names[x]+">"+names[x]); 
			out.println("<br/> <input type=\"submit\" value = \"ok\">");
			out.println("</form></center></body></html>");

		}
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
