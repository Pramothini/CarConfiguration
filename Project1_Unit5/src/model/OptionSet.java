package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Pramothini Dhandapany Kanchanamala
 */
public class OptionSet implements Serializable{
	private static final long serialVersionUID = 1L;
	private ArrayList<Option> opts;
	private String name; 

	//constructors
	protected OptionSet(){
	}

	protected OptionSet(String name){
		this.name = name;
	}

	protected OptionSet(String n, int size) {
		opts = new ArrayList<Option>(size); 
		name = n;
		for(int i = 0; i<size ; i++){
			opts.add(i,new Option());
		}
	}


	/*
	 * getters and setters
	 */
	protected String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	protected ArrayList<Option> getOpts() {
		return opts;
	}
	protected Option getOpts(int i) {
		return opts.get(i);
	}
	
	protected Option getOpts(String name){
		for(int i = 0; i<=opts.size();i++){
			if(opts.get(i).getName().equals(name)){
				return opts.get(i);
			}
		}
		return null;	
	}

	protected void setOpts(ArrayList<Option> opts) {
		this.opts = opts;
	}
	protected void setOpt(int optindex, Option opt){
		opts.set(optindex,opt);
	}

	protected void setOpts(Option opt){
		for(int i = 0; i<=opts.size();i++){
			if(opts.get(i).getName().equals(opt.getName())){
				opts.get(i).setPrice(opt.price);
				return;	
			}
		}
	}

	protected Integer getPrice(String optname){
		for(int i = 0; i < getOpts().size() ; i++){
			if (getOpts().get(i).name == optname){
				return (int) getOpts().get(i).price;
			}
		}
		return null;
	}

	protected int findOpt(Option opt){
		for(int i = 0; i<=opts.size();i++){
			if(opts.get(i).getName().equals(opt.getName())){
				return i;
			}
		}
		return -1;

		}
	
	protected void print(){
		StringBuffer sb = new StringBuffer();
		sb.append("\nThe name of this Optionset is ");
		sb.append(getName());
		sb.append(" It has the following Options");
		System.out.println(sb);
		for(int i=0 ; i < getOpts().size() ; i++){
			if(getOpts(i) != null)
			getOpts(i).print();
			
		}
	}


		class Option implements Serializable{
			private static final long serialVersionUID = 1L;
			private String name;
			private float price; 

			protected Option(){

			}

			protected Option(String name){
				this.name = name;
			}

			protected Option(String name, float price){
				this.name = name;
				this.price = price;
			}

			protected String getName() {
				return name;
			}

			protected void setName(String name) {
				this.name = name;
			}

			protected float getPrice() {
				return price;
			}

			protected void setPrice(float price) {
				this.price = price;
			}

			protected void print(){
				StringBuffer sb = new StringBuffer();
				sb.append("name : ");
				sb.append(getName());
				sb.append("\t Additional cost :");
				sb.append(getPrice());
				sb.append("\n");
				System.out.print(sb);
			}

		}

	}
