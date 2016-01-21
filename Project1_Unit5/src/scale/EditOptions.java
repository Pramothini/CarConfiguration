package scale;

import model.Fleet;

/**
 *  Class is used to edit Options for a given model in its own thread.
 *  @author Pramothini Dhandapany Kanchanamala
 */
public class EditOptions extends Thread{
	private Fleet fleet;
	private Object newValue;
	private String optionName;
	private String opsetName;
	private String fleetName;
	boolean sync; // toggle option to turn synchronize on and off - (for testing)
	int operation; //1 = editprice; 2 = edit Option set Name; 3 = delete option

	/**
	 * Constructor
	 * @param fleet - LinkedHashMap instance of Automobile in proxyAutomotive class instance.
	 * @param fleetName - name of the fleet
	 * @param optionName - Name of the option that is to be edited
	 * @param opsetName - Name of the option set that is to be edited
	 * @param newValue - Value that has to replace the old value
	 * @param operation - Denotes the function that has to be performed
	 * @param sync - used to toggle synchronize on and off - (for testing)
	 */
	public EditOptions(Fleet fleet,String fleetName,String optionName,
			String opsetName,Object newValue,int operation, boolean sync){
		this.fleet = fleet;
		this.optionName = optionName;
		this.newValue = newValue;
		this.opsetName = opsetName;
		this.fleetName = fleetName;
		this.operation = operation;
		this.sync = sync;
	}


	/**
	 * makes the thread to wait for a random time
	 */
	void randomWait() { 
		try {
			Thread.sleep((long)(500*Math.random())); 		
		} 
		catch(InterruptedException e) {
			System.out.println("Interrupted!"); }
	}

	/**
	 * method used to perform functions based on the operation value
	 */
	void runOperation(){
		switch(operation){	
		case 1:
			System.out.println("Thread is going to update the price of Optionset - "+opsetName+", Option - "+optionName+" to "+(Float)newValue);
			fleet.updateOptionPrice(fleetName,opsetName, optionName, (Float)newValue);
			break;
		case 2:
			System.out.println("Thread is going to update name of Optionset - "+opsetName+" as - "+(String)newValue);
			fleet.updateOptionSetName(fleetName,(String)newValue,opsetName);
			break;
		case 3:
			System.out.println("Thread is going to delete Option - "+optionName +" in the optionset "+opsetName);
			fleet.delOption(fleetName, opsetName, optionName);
		}
		fleet.printFleet(fleetName);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Thread#run()
	 * Reason why synchronized is in Edit Options:
	 * 	Since both update and delete are operating on the same option, update has to be executed before delete.
	 * 	If the CRUD methods were synchronized individually, we still cannot guarantee that update will be executed before delete.
	 *  Therefore synchronized is in Edit Options
	 */
	public void run(){
		/*
		 * Since the threads are synchronized,update will always be performed before delete.
		 * This is because, the first thread will lock the fleet object until it has updated and printed
		 * Thus second thread has to wait for the first thread to release the fleet object before it can perform delete function
		 */
		if(sync){
			synchronized(fleet){
				System.out.println("Since the threads are synchronized,update will always be performed before delete.");
				randomWait();
				System.out.println("Thread started !");
				runOperation();
			}

		}
		/*
		 * Since the threads are not synchronized, there is no guarantee that update will be performed before delete
		 * This is because, there is no locking of fleet object and so both threads are free to use it simultaneously.
		 */
		else{
			System.out.println("Since the threads are not synchronized, there is no guarantee that update will be performed before delete");
			randomWait();
			System.out.println("Thread started !");
			runOperation();
		}

	}

}

