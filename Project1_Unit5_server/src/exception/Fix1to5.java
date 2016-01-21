package exception;

import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Helper class for AutoException
 * @author Pramothini Dhandapany Kanchanamala
 */
public class Fix1to5 {

	int fix1(int errno, String errmsg){
		System.out.println("LOG: "+new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new Date()) + " Error No: "+errno+" Error msg: "+errmsg +"Error Fix :"+" Adding a default value as 1212");	
		return 1212;
	}

	String fix2(int errno, String errmsg){
		System.out.println("LOG: "+new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new Date()) + " Error No: "+errno+" Error msg: "+errmsg +"Error Fix :"+" Adding a default value as OptionSet3.txt");	
		return "OptionSet3.txt";
	}

	String fix3(int errno, String errmsg){
		System.out.println("LOG: "+new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new Date()) + " Error No: "+errno+" Error msg: "+errmsg +"Error Fix :"+" Adding a default value as OptionSet");	
		return "OptionSet";
	}

	String fix4(int errno, String errmsg){
		System.out.println("LOG: "+new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new Date()) + " Error No: "+errno+" Error msg: "+errmsg +"Error Fix :"+" Adding a default value as Ford");	
		return "Ford";
	}

	int fix5(int errno, String errmsg){
		System.out.println("LOG: "+new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new Date()) + " Error No: "+errno+" Error msg: "+errmsg +"Error Fix :"+" Adding a default value as 1212");	
		return 1212;
	}
}
