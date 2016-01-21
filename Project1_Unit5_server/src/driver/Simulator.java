package driver;

import scale.Scallable;
import adapter.*;

/**
 * @author Pramothini Dhandapany Kanchanamala
 */
public class Simulator {
	public static void main(String args[]){
		try {
			CreateAuto createAuto0 = new BuildAuto();

			createAuto0.buildAuto("OptionSet1.txt",1);
			createAuto0.printAuto("FordWagon00");
			
			Scallable scale0 = new BuildAuto();
			Scallable scale1 = new BuildAuto();
			
			System.out.println("Two threads are running. First Thread is going to update the price of an option \n and the "
					+ "the second Thread is going to delete the same option .\n"
					+ "If the option is deleted before the update , then update cannot be done");
			
			System.out.println("Executing two threads with synchronization....");
			System.out.println("-------------------------------------------------");
			scale0.editOptionFunc("FordWagon00","Transmission", "automatic",12.0f,1,true);
			scale0.editOptionFunc("FordWagon00","Transmission", "automatic",0.0f,3,true);
			
			Thread.sleep(5000);
			
			System.out.println("Executing two threads without synchronization....");
			System.out.println("-------------------------------------------------");
			scale1.editOptionFunc("FordWagon00","Brakes", "Standard",21.0f,1,false);
			scale1.editOptionFunc("FordWagon00","Brakes", "Standard",0.0f,3,false);
			
		}
		catch(Exception e){
			System.out.print("Error:"+e);
		}
	}
}
