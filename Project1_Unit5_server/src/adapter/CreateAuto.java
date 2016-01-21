package adapter;


/**
 * @author Pramothini Dhandapany Kanchanamala
 */
public interface CreateAuto {
	/**
	 * Used to create an instance of Automobile and add it to Fleet
	 * @param filename
	 * @param filetype - 1 = text file ; 2 = properties file
	 */
	public void buildAuto(Object filename,int filetype);
	
	public void printAuto(String name);

}
