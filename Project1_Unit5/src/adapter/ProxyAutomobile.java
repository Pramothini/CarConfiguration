package adapter;

import scale.EditOptions;
import exception.AutoException;
import util.Util;
import model.*;

/**
 * @author Pramothini Dhandapany Kanchanamala
 */
public abstract class ProxyAutomobile {
	static Fleet fleet;

	/**
	 * Used to create an instance of Automobile and add it to Fleet
	 * @param filename
	 * @param filetype - 1 = text file ; 2 = properties file
	 */
	public void buildAuto(String filename,int filetype) {
		Util util = new Util();
		Automobile a1;
		a1 = util.createAutomobile(filename,filetype);
		fleet = new Fleet(a1.getMake()+a1.getModel(), a1);
	}
	
	/**
	 * Searches and prints the properties of a given Automodel.
	 */
	public void printAuto(String name){
		if(fleet !=null){
			fleet.printFleet(name);
			//chooseOptions(fleet.get(name).getMake()+fleet.get(name).getModel());
		}

	}

	/**
	 * Searches the Model for a given OptionSet and sets the name of OptionSet to newName
	 */
	public void updateOptionSetName(String fleetname,String newname, String oldname){
		if(fleet !=null)
			fleet.updateOptionSetName(fleetname, newname, oldname);
	}

	/**
	 * Searches the Model for a given OptionSet and Option name, and sets the price to newPrice.
	 */
	public void updateOptionPrice(String fleetname,String opsetname,String optname,float price){
		if(fleet !=null)
			fleet.updateOptionPrice(fleetname, opsetname, optname, price);
	}


	/**
	 * API to Call the run() method in EditOptions class
	 * @param fleetname - Name of the key in the fleet object
	 * @param opsetname - Name of the option set that has to be edited
	 * @param optname - Name of the optin that has to be edited
	 * @param newValue - Value that replaces the old value
	 * @param operation - denotes the type of edit operation to perform
	 * @param sync - used to toggle synchronize on and off - (for testing)
	 */
	public void editOptionFunc(String fleetname,String opsetname,String optname,Object newValue,
			int operation,boolean sync){
		EditOptions editOpt = new EditOptions(fleet,fleetname, optname, opsetname, newValue, operation, sync);
		editOpt.start();
	}

	/**
	 * API to call the fix() in AutoException Class
	 * @param errno - denotes the unique number for the error
	 * @param errmsg - explains the message
	 * @return
	 */
	public Object fix(int errno, String errmsg){
		AutoException handleErr = new AutoException(errno,errmsg);
		return handleErr.fix(errno,errmsg);
	}
	
	/**
	 * Used to set the options
	 * @param fleetname
	 * @param opsetname
	 * @param optname
	 */
	public void chooseOptions(String fleetname,String opsetname,String optname){
		if(fleet !=null)
			fleet.chooseOptions(fleetname, opsetname, optname);
	}
	



}
