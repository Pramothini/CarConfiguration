package server;

import java.util.ArrayList;

import model.Automobile;
import adapter.BuildAuto;
import adapter.CreateAuto;

public class BuildCarModelOptions implements AutoServer{
	
	/**
	 * Since createAuto is an External API, we use it to call the buildAuto method in the proxyAutomobile Class
	 * buildAuto method creates an instance of Automobile and adds it to Fleet
	 * @param properties
	 * @param filetype - 1 = text file ; 2 = properties file
	 */
	public void buildAuto(Object properties,int filetype){
		CreateAuto create = new BuildAuto();
		create.buildAuto(properties, filetype);
	}

	/**
	 * Used to get an ArrayList of all available model names
	 * @return
	 */
	public ArrayList<String> getAutomobiles() {
		BuildAuto buildAuto = new BuildAuto();
		return buildAuto.getAutomobiles();

	}
	
	/**
	 * Used to get an Automobile object
	 * @param name of the Automobile object
	 * @return Automobile object
	 */
	public Automobile getAutoByName(String selectedAuto){
		BuildAuto buildauto = new BuildAuto();
		return buildauto.getAutoByName(selectedAuto);
	}
}
