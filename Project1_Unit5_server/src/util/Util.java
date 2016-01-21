package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import exception.AutoException;
import adapter.BuildAuto;
import adapter.FixAuto;
import model.Automobile;

/**
 * @author Pramothini Dhandapany Kanchanamala
 */
public class Util {
	
	/**
	 * Used to parse a file and create an instance of Automobile
	 * @param (Properties)filename
	 * @param fileType - 1 = text file ; 2 = properties file
	 * @return Automobile
	 */
	public Automobile createAutomobile(Object fileName,int fileType) {
		Automobile automobile = null;
		if(fileType == 2)
			automobile = parseProperties((Properties)fileName,fileType);
		else if(fileType == 1){
			String filename = (String)fileName;
			FixAuto fixauto = new BuildAuto();
			PrintWriter writer = null;
			int baseprice = 0;
			String make = "";
			try {
				writer = new PrintWriter("logs.txt", "UTF-8");
				try{
					if(filename == "" || filename == null){
						throw new AutoException();
					}
				}
				catch(AutoException e){
					filename = (String) fixauto.fix(2,"Missing File Name");
					writer.println(new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new Date()) + " Error No: 2 Error msg: Missing file name."+
							" Error Fix :"+" Adding a default value as OptionSet3.txt \n");
				}
				FileReader file = new FileReader(filename);
				BufferedReader buff = new BufferedReader(file);
				boolean eof = false;
				String line = "";
				if((line = buff.readLine()) != null){
					try{
						if(line.split("-")[0].trim().equals(""))
							throw new AutoException();
						else
							make = line.split("-")[0].trim();
					}
					catch(AutoException e){
						make = (String) fixauto.fix(4,"Missing make of Automobile");
						writer.println(new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new Date()) + " Error No: 4 Error msg: Missing make name."+
								" Error Fix :"+" Adding a default value as Ford \n");
					}

					try{
						if(line.split("-")[2].trim().equals(""))
							throw new AutoException();
						else
							try{
								baseprice = Integer.parseInt(line.split("-")[2].trim());
							}
						catch(Exception e){
							baseprice = (int) fixauto.fix(1,"Price contains non numerical characters");		
							writer.println(new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new Date()) + " Error No: 1"+
									" Error msg: Price contains non numerical characters. Error Fix : Adding a default value as 1212\n");
						}
					}
					catch(AutoException e){
						baseprice = (int) fixauto.fix(5,"Missing price");
						writer.println(new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new Date()) + " Error No: 5"+
								" Error msg: Price is missing. Error Fix : Adding a default value as 1212\n");
					}

					automobile = new Automobile(make,line.split("-")[1].trim(),baseprice,(Integer.parseInt(line.split("-")[3].trim())));	
					for(int opsetindex=0; !eof ; opsetindex++) {
						int opsetsize = 0;
						String optionSetName = "";
						line = buff.readLine(); 
						if (line == null)
							eof = true; 
						else{
							try{
								optionSetName = line.split("-")[0].trim();
								if(optionSetName.equals(""))
									throw new AutoException(3,"Missing Optionset Name");
							}
							catch(AutoException e){
								optionSetName = (String) fixauto.fix(3,"Missing OptionSet name");;
								writer.println(new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new Date()) + " Error No: 3 Error msg: Missing OptionSet name"+
										" Error Fix :"+" Adding a default value as OptionSet \n");
							}
							automobile.createOptionset(opsetindex,optionSetName,(Integer.parseInt(line.split("-")[1].trim())));
							opsetsize = Integer.parseInt(line.split("-")[1].trim());
							for(int optionindex = 0 ; optionindex < opsetsize ; optionindex++){
								line = buff.readLine();
								int additionalPrice = 0;
								if (line == null){
									eof = true;
									break;
								}
								try{
									additionalPrice = Integer.parseInt(line.split("-")[1]);
								}
								catch(Exception e){								
									additionalPrice = (int) fixauto.fix(1,"Price contains non numerical characters");		
									writer.println(new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new Date()) + " Error No: 1"+
											" Error msg: Price contains non numerical characters Error Fix : Adding a default value as 1212\n");
								}
								automobile.setOption(opsetindex,optionindex,line.split("-")[0].trim(),additionalPrice);
								//System.out.println("opsetindex,optionindex,line,price"+ opsetindex + optionindex + line + automobile.getPrice(line));
							}
							line = buff.readLine(); 

						}
					}
					buff.close();
				}
			} catch (IOException e) {
				System.out.println("Error ­­ " + e.toString()); }
			finally{
				writer.close();
			}
		}
		return automobile;
	}

	/**
	 * 	
	 * Used to parse the properties file and create an instance of Automobile
	 * @param filename
	 * @param fileType - 1 = text file ; 2 = properties file
	 * @return Automobile created by parsing the properties file
	 */
	public Automobile parseProperties(Properties filename,int fileType){
		Automobile automobile = null;
		Properties props= filename; 
		String carMake = props.getProperty("CarMake"); //this is how you read a property. 
		//It is like getting a value from HashTable.

		if(!carMake.equals(null)) {
			String carModel = props.getProperty("CarModel");
			int noOfOptions = Integer.parseInt(props.getProperty("NoOfOptions"));
			int basePrice = Integer.parseInt(props.getProperty("BasePrice"));
			automobile = new Automobile(carMake,carModel,basePrice,noOfOptions);

			for(int optionIndex=0 ; optionIndex< noOfOptions ; optionIndex++){
				String option = props.getProperty("Option"+optionIndex);
				if(option != null){
					int noOfOptionValues = Integer.parseInt(props.getProperty("NoOfOption"+optionIndex+"Values"));
					//System.out.println("The number of option values are :"+noOfOptionValues);
					automobile.createOptionset(optionIndex,option,noOfOptionValues);
					for(int opsetIndex = 0; opsetIndex <= noOfOptionValues; opsetIndex++){
						String optionValue = props.getProperty("Option"+optionIndex+"Value"+opsetIndex);
						if(optionValue != null){
							String optionValueName = optionValue.split("-")[0].trim();
							int additionalPrice = Integer.parseInt(optionValue.split("-")[1].trim());
							//System.out.println("opsetIndex :"+opsetIndex +" "+" Option Value :"+optionValueName+" optionIndex :"+optionIndex);
							automobile.setOption(optionIndex,opsetIndex,optionValueName,additionalPrice);
						}
					}

				}

			}
		}
		return automobile;
	}

}

