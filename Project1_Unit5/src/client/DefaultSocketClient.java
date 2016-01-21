package client;
import java.net.*;
import java.util.Properties;
import java.io.*;

import model.Automobile;

/**
 * Client will have following capabilities:
		Upload Properties file.
		Configure a car.
 * @author pramothinidk
 *
 */
public class DefaultSocketClient extends Thread implements SocketClientInterface,SocketClientConstants {
	private BufferedReader reader;
	private BufferedReader socketReader;
	private PrintWriter out;
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
			reader = new BufferedReader
					(new InputStreamReader(System.in));
			socketReader = new BufferedReader
					(new InputStreamReader(sock.getInputStream()));
			out = new PrintWriter(sock.getOutputStream(), true);
		}
		catch (Exception e){
			if (DEBUG) System.err.println
			("Unable to obtain stream to/from " + strHost);
			return false;
		}
		return true;
	}

	/**
	 * Handles the interaction between the server and the user
	 */
	public void handleSession(){
		String strInput = "";
		while(true){
			System.out.println("What do you want to do? upload or configure or quit");
			try { 
				strInput = reader.readLine();
				System.out.println("You chose :"+strInput);
				if(strInput.equals("upload")){
					upload();
				}
				else if(strInput.equals("configure")){
					out.println(strInput);
					SelectCarOption select = new SelectCarOption(reader,socketReader,out);
					select.promptForAvailableModels();
					select.selectModel();
					ObjectInputStream inStream = new ObjectInputStream(new BufferedInputStream(sock.getInputStream()));
					Automobile automobile = (Automobile) inStream.readObject();
					automobile.selectOptions();
					System.out.println("\n The options that you selected now :");
					automobile.printSelectedOptions();
				}
				else if(strInput.equals("quit")){
					out.println(strInput);
					break;
				}
				else{
					System.out.println("Invalid input!");
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}       


	public void closeSession(){
		try {
			out = null;
			reader = null;
			sock.close();
		}
		catch (IOException e){
			if (DEBUG) System.err.println
			("Error closing socket to " + strHost);
		}       
	}

	/**
	 * update() is customized so that the servlets can interact with it
	 * @return a list of available model names from the server
	 */
	public String upload(){
		String strInput = "";
		String serverResponse = "";
		while(true){
			try {
				System.out.println("Do you want to upload a file (y/n)");
				strInput = reader.readLine();
				System.out.println("You chose :"+strInput);
				if(strInput.equals("y")){
					String filename = "";
					CarModelOptionsIO carModelOptionsIO = new CarModelOptionsIO();
					out.println("upload");
					System.out.println("Enter file path :");
					filename = reader.readLine();
					properties = carModelOptionsIO.createPropertiesObject(filename);
					ObjectOutputStream outStream = new ObjectOutputStream(new BufferedOutputStream(sock.getOutputStream()));
					outStream.writeObject(properties);
					outStream.flush();
					serverResponse = socketReader.readLine();
					System.out.println("serverResponse :"+serverResponse);
					serverResponse = socketReader.readLine();
					System.out.println("Available models from server :"+serverResponse);
				}
				else{
					return serverResponse;
				}

			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}

	/**
	 * configure() is customized so that the servlets can interact with it
	 * servlet passes an automobile name and gets back the corresponding automobile object from this method
	 * This method communicates with the server to get the automobile object
	 * @param automobilename - The name of the automobile
	 * @return an automobile object
	 */
	public Automobile configure(String automobilename){
		out.println("configure");
		Automobile automobile = null;
		String serverResponse = "";
		try {
			serverResponse = socketReader.readLine();
			System.out.println("Available models from server :"+serverResponse);
			out.println(automobilename);
			ObjectInputStream inStream;
			inStream = new ObjectInputStream(new BufferedInputStream(sock.getInputStream()));
			automobile = (Automobile) inStream.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return automobile;
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


	public static void main (String arg[]){
		DefaultSocketClient d;
		try{
			d = new DefaultSocketClient(new Socket("localhost", 4444));
			d.start();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


}
