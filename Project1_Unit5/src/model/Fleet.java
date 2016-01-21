package model;

import java.util.LinkedHashMap;
/**
 * Class contains CRUD options for Automobile class
 * @author Pramothini Dhandapany Kanchanamala
 */
public class Fleet {
	private LinkedHashMap<String,Automobile> auto;
	
	/** Create - using constructors */
	public Fleet(){
		auto = new LinkedHashMap<String, Automobile>();
	}
	public Fleet(String name,Automobile a1){
		if(auto == null)
			auto = new LinkedHashMap<String, Automobile>();
		auto.put(a1.getMake()+a1.getModel(), a1);	
	}
	
	/**
	 * Read
	 * @param name - determines which automobile object to print
	 */
	public void printFleet(String name){
		if(auto.get(name)!=null)
			auto.get(name).print();
	}
	
	/**
	 * Update
	 * Updates Option Set Name 
	 */
	public void updateOptionSetName(String fleetname,String newname, String oldname){
		if(auto.get(fleetname) !=null)
			auto.get(fleetname).updateOpsetName(newname, oldname);
	}
	/**  updates option price  */
	public void updateOptionPrice(String fleetname,String opsetname,String optname,float price){
		if(auto.get(fleetname) !=null)
			auto.get(fleetname).updateOptionPrice(opsetname, optname, price);
	}
	/**  sets choice of user  */
	public void chooseOptions(String fleetname,String opsetname,String optname){
		if(auto.get(fleetname) !=null){
			auto.get(fleetname).setOptionChoice(opsetname, optname);
			System.out.println("the option that was set for "+opsetname+" is ..."+auto.get(fleetname).getOptionChoice("Brakes"));
			System.out.println(" The price for the set option is "+auto.get(fleetname).getOptionChoicePrice("Brakes"));
			System.out.println("Total price is :"+auto.get(fleetname).getTotalPrice());
		}
	}
	
	/**
	 * Delete
	 * deletes an option set based on its name
	 */
	public void delOpset(String fleetname,String opsetname){
		if(auto.get(fleetname) !=null)
			auto.get(fleetname).delOpset(opsetname);
	}
	/** deletes an option based on its name */
	public void delOption(String fleetname,String opsetname,String optname){
		if(auto.get(fleetname) !=null)
			auto.get(fleetname).delOption(opsetname,optname);
	}
	
	
}
