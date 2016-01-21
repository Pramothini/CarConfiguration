package model;

import java.io.Serializable;
import java.util.ArrayList;

import model.OptionSet.Option;

/**
 * @author Pramothini Dhandapany Kanchanamala
 */
public class Automobile implements Serializable{
	private static final long serialVersionUID = 1L;
	private String make;
	private String model;
	private ArrayList<OptionSet> opsets ;
	private ArrayList<Option> choice;
	private int baseprice;

	//Constructors	
	public Automobile(){		
	}
	public Automobile(String make,String model,int baseprice,int OptionSetsize){
		opsets = new ArrayList<OptionSet>(OptionSetsize);
		this.make = make;
		this.model = model;
		this.baseprice = baseprice;
		choice = new ArrayList<Option>(OptionSetsize);
		for(int i=0;i<OptionSetsize;i++){
			choice.add(null);
		}
	}

	// Getters
	public int getBaseprice() {
		return baseprice;
	}	
	public String getMake() {
		return make;
	}

	public String getModel() {
		return model;
	}

	public ArrayList<OptionSet> getOpsets() {
		return opsets;
	}	
	/* get Opset by index value */
	public OptionSet getOpsets(int i){
		if (i< getOpsets().size())
			return getOpsets().get(i);
		else
			return null;	
	}
	public String getOptionChoice(String opsetName) {
		for(int i = 0; i < getOpsets().size(); i++){
			if(getOpsets(i) != null)
				if(getOpsets(i).getName().equals(opsetName)){
					if(choice.get(i) != null)
						return choice.get(i).getName();
				}

		}
		System.out.println("No option could be found for"+opsetName);
		return null;
	}

	public float getOptionChoicePrice(String opsetName){
		for(int i = 0; i < getOpsets().size(); i++){
			if(getOpsets(i) != null)
				if(getOpsets(i).getName().equals(opsetName)){
					if(choice.get(i) != null)
						return choice.get(i).getPrice();
				}

		}
		System.out.println("No option could be found for"+opsetName);
		return 0;
	}

	//setters
	public void setMake(String make) {
		this.make = make;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public void setBaseprice(int baseprice) {
		this.baseprice = baseprice;
	}
	public void setOpsets(ArrayList<OptionSet> opsets) {
		this.opsets = opsets;
	}
	public void setOpset(OptionSet os){
		opsets.add(os);
	}
	public void setOption(int opsetindex,int optionindex,String name,float price){		
		getOpsets(opsetindex).getOpts(optionindex).setName(name);
		getOpsets(opsetindex).getOpts(optionindex).setPrice(price);

	}
	public void setOptionChoice(String opsetName,String optionName){
		Option opt = findOption(opsetName,optionName);
		if(opt!=null){
			for(int i=0 ; i<getOpsets().size();i++){
				if(getOpsets().get(i) != null)
					if(getOpsets().get(i).getName().equals(opsetName)){
						choice.set(i, opt);
						return;
					}
			}
		}
	}


	//Finders
	/* Finds Opset with name */
	public OptionSet findOpset(String opsetsname){
		for(int i = 0; i < getOpsets().size(); i++){
			if(getOpsets(i) != null)
				if(getOpsets(i).getName().equals(opsetsname)){
					return getOpsets(i);
				}
		}
		return null;
	}

	/* Find Option with name(incontextofOptionSet) */
	public Option findOption(String opsetsname,String optname ){
		OptionSet os = findOpset(opsetsname);
		if(os != null){
			ArrayList<Option> opts = os.getOpts();
			for(int i = 0 ; i < opts.size() ; i++){
				if(os.getOpts(i) != null)
					if(os.getOpts(i).getName().trim().equalsIgnoreCase(optname)){
						return opts.get(i);
					}
			}
		}
		return null;
	}

	//delete
	/*delete an opset with name */
	public void delOpset(String opsetname){
		for(OptionSet opset : getOpsets() ){
			if(opset != null)
				if(opset.getName().equals(opsetname)){
					getOpsets().remove(opset);
					return;
				}
		}
	}

	/*delete an opset with index */
	public void delOpset(int opsetindex){
		setOpset(null);
		return;
	}

	/*delete an option with name */
	public void delOption(String opsetname,String optname){
		OptionSet os = findOpset(opsetname);
		if(os != null){
			for(int i=0 ; i< os.getOpts().size() ; i++){
				if(os.getOpts(i) != null){
					if(os.getOpts(i).getName().equals(optname)){
						os.setOpt(i,null);
						return;
					}	
				}
				else{
					System.out.println("Delete failed as Option "+optname+" is not present");
				}
			}
		}
		else
			System.out.println("Delete failed as OptionSet "+opsetname+" is not present");
	}

	/*delete an option with index */
	public void delOption(int opsetindex,int optindex){
		OptionSet os = getOpsets(opsetindex);
		if(os != null){
			os.setOpt(optindex, null);
		}
	}

	//update
	public void updateOpsetName(String newname, String oldname){
		OptionSet os = findOpset(oldname);
		if(os != null){
			os.setName(newname);
		}
	}

	public void updateOptionName(String opsetname, String newoptioname, String oldoptioname){
		Option opt = findOption(opsetname,oldoptioname);
		opt.setName(newoptioname);
	}

	public void updateOptionPrice(String opsetname,String optname,float price){
		Option opt = findOption(opsetname,optname);
		if(opt != null)
			opt.setPrice(price);
		else
			System.out.println("Update cannot be done as Option "+optname+" is not present");

	}
	
	/**
	 * Used to create Option sets
	 * @param opsetindex
	 * @param name
	 * @param size
	 */
	public void createOptionset(int opsetindex, String name, int size){
		opsets.add(opsetindex, new OptionSet(name,size));
	}

	/**
	 * Used to get the total price 
	 * @return - total price - (base price + additional cost of all the choices selected by users)
	 */
	public int getTotalPrice(){
		int price = 0;
		price += this.getBaseprice();
		for (Option option : choice){
			if(option != null)
				price += option.getPrice();
		}
		return price;
	}

	/**
	 * Used to print the automobile object
	 */
	public void print(){
		StringBuffer sb = new StringBuffer();
		sb.append("The Make and model of this Automobile is ");
		sb.append(getMake() + " "+ getModel());
		sb.append(". Baseprice is ");
		sb.append(getBaseprice());
		sb.append(". It has the following option sets :");
		System.out.println(sb);
		for(int i=0 ; i < getOpsets().size() ; i++){
			if(getOpsets(i) != null)
				getOpsets(i).print();
		}
		sb.setLength(0);
		sb.append("****************************************************************************************");
		System.out.println(sb);
	}
}



