package server;

import java.net.*;
import java.io.*;

/**
 * Server will have following capabilities:
			● Host all Automobiles in a single data structure ­ LinkedHashMap.
			● Receive a Properties object, when client parses it and builds an Automobile Object on
			Server.
			○ Properties object contains Optionsets (including Options) for one Automobile
			(~ to the contents of Textfile from Assignment 1). It does not contain any car
			configuration information.
			● Respond to client request for configuring a car by passing an instance of Automobile
			object. The object would be serialized to the client.
 * @author pramothinidk
 */
public class Server {
	ServerSocket serverSocket = null;
	DefaultSocketClient clientSocket = null; 

	/**
	 * Creates a server socket that listens at port 4444
	 */
	public void createServer(){
		try {
			serverSocket = new ServerSocket(4444);
			System.out.println("Listening on port 4444..");
		} 
		catch (IOException e) {
			System.err.println("Could not listen on port: 4444.");
			System.exit(1); 
		}
	}

	/**
	 * Creates a client socket to communicate with the client
	 */
	public void createClient(){
		try {
			clientSocket = new DefaultSocketClient(serverSocket.accept());
			clientSocket.start();
		} 
		catch (IOException e) {
			System.err.println("Accept failed.");
			System.exit(1);
		}

	}
	
	/**
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		Server s = new Server();
		s.createServer();
		s.createClient();
	}
}


