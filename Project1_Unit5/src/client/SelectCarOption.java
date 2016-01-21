package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author pramothinidk
 */
public class SelectCarOption {

	private BufferedReader reader;
	private BufferedReader socketReader;
	private PrintWriter out;

	SelectCarOption(BufferedReader reader,BufferedReader socketReader,PrintWriter out){
		this.reader = reader;
		this.socketReader = socketReader;
		this.out = out;
	}

	/**
	 * Prompts the user for available models.
	 * @return 
	 */
	public String promptForAvailableModels(){
		String serverResponse = null;
		try {
			serverResponse = socketReader.readLine();
			System.out.println("Available models from server :"+serverResponse);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return serverResponse;
	}

	/**
	 * Allows the user to select a model and informs the server about the selected model
	 */
	public void selectModel(){
		try {
			System.out.println("Enter the automobile name to configure it :");
			String autoName;
			autoName = reader.readLine();
			System.out.println("You chose :"+autoName);
			out.println(autoName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
