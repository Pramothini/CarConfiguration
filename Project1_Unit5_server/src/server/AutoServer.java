package server;

import java.util.ArrayList;

import model.Automobile;

public interface AutoServer {
	/**
	 * Used to create an instance of Automobile and add it to Fleet
	 * @param filename
	 * @param filetype - 1 = text file ; 2 = properties file
	 */
	public void buildAuto(Object filename,int filetype);
	
	/**
	 * Used to get an ArrayList of all available model names
	 * @return
	 */
	public ArrayList<String> getAutomobiles();
	
	/**
	 * Used to get an Automobile object
	 * @param name of the Automobile object
	 * @return Automobile object
	 */
	public Automobile getAutoByName(String name);
}
