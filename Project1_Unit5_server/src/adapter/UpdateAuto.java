package adapter;

/**
 * @author Pramothini Dhandapany Kanchanamala
 */
public interface UpdateAuto {
	public void updateOptionSetName(String fleetname,String newname, String oldname);
	public void updateOptionPrice(String fleetname,String opsetname,String optname,float price);	
}
