package server;
import java.net.*;
import java.util.ArrayList;
import java.util.Properties;
import java.io.*;

/**
 * @author pramothinidk
 *
 */
public class DefaultSocketClient extends Thread implements SocketClientInterface,SocketClientConstants {
	private BufferedReader reader;
	private PrintWriter writer;
	private Socket sock = null;
	private String strHost;
	private Properties properties;
	private int iPort;

	public DefaultSocketClient(Socket sock){
		this.sock = sock;
	}

	public DefaultSocketClient(String strHost, int iPort) {       
		setPort (iPort);
		setHost (strHost);
	}//constructor

	public void run(){
		if (openConnection()){
			handleSession();
			closeSession();
		}
	}//run

	public boolean openConnection(){

		try {
			if(sock == null)  //modified as sock might get assigned in the constructor
				sock = new Socket(strHost, iPort);                    
		}
		catch(IOException socketError){
			if (DEBUG) System.err.println
			("Unable to connect to " + strHost);
			return false;
		}
		try {
			writer = new PrintWriter(sock.getOutputStream(), true);
			reader = new BufferedReader
					(new InputStreamReader(sock.getInputStream()));
		}
		catch (Exception e){
			if (DEBUG) System.err.println
			("Unable to obtain stream to/from " + strHost);
			return false;
		}
		return true;
	}

	public void handleSession(){
		BuildCarModelOptions buildCarModelOptions = new BuildCarModelOptions();
		while(true){
			String clientmsg;
			try {
				//System.out.println("going to read the msg from client ");
				clientmsg = reader.readLine();
				//System.out.println("finished reading msg from client and msg is :"+clientmsg);
				if(clientmsg != null && clientmsg != ""){
					 /** Accept properties object from client socket over an ObjectStream and create an Automobile */
					if(clientmsg.equals("upload")){
						ObjectInputStream inStream = new ObjectInputStream(new BufferedInputStream(sock.getInputStream()));
						properties = (Properties) inStream.readObject();
						buildCarModelOptions.buildAuto(properties,2);
						writer.println("Server : automobile created successfully");
						//need to pass all the available to the client, so that the servlet can
						//get the available model names from client
						ArrayList<String> allAutomobiles = buildCarModelOptions.getAutomobiles();
						writer.println(allAutomobiles.toString());
					}
					
					else if(clientmsg.equals("configure")){
						ArrayList<String> allAutomobiles = buildCarModelOptions.getAutomobiles();
						writer.println(allAutomobiles.toString());
						//System.out.println("all Automobiles : "+allAutomobiles.toString());
						String autoName = reader.readLine();
						//System.out.println("Server : Configuring Automobile name :"+autoName);
						Object automobile = buildCarModelOptions.getAutoByName(autoName);
						if(automobile != null){
							ObjectOutputStream outStream = new ObjectOutputStream(new BufferedOutputStream(sock.getOutputStream()));
							outStream.writeObject(automobile);
							outStream.flush();
						}
						else{
							System.out.println("Server : Problem in building automobile");
						}

					}
					else if(clientmsg.equals("quit")){
						break;
					}
					else{
						System.out.println("Invalid input!");
					}
				}
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}       
     

	public void closeSession(){
		try {
			writer = null;
			reader = null;
			sock.close();
		}
		catch (IOException e){
			if (DEBUG) System.err.println
			("Error closing socket to " + strHost);
		}       
	}

	public void setHost(String strHost){
		this.strHost = strHost;
	}

	public void setPort(int iPort){
		this.iPort = iPort;
	}


	public Socket getSock() {
		return sock;
	}

	public void setSock(Socket sock) {
		this.sock = sock;
	}


}
