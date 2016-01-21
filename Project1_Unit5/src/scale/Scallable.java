package scale;

/**
 * @author Pramothini Dhandapany Kanchanamala
 * API that associates EditOptions class and BuildAuto class 
 */
public interface Scallable {
	public void editOptionFunc(String fleetname,String opsetname,String optname,Object newValue,
			int operation,boolean sync);
}
