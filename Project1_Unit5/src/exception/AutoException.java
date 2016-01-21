package exception;


/**
 * @author Pramothini Dhandapany Kanchanamala
 */
public class AutoException extends Exception{

	private static final long serialVersionUID = 1L;
	private int errorno;
	private String errormsg;

	/**
	 * Constructor
	 */
	public AutoException(){
		super();
	}

	/**
	 * Constructor
	 * @param errorno
	 */
	public AutoException(int errorno){
		super();
		this.errorno = errorno;
	}

	/**
	 * Constructor
	 * @param errorno
	 * @param errormsg
	 */
	public AutoException(int errorno,String errormsg){
		super();
		this.errorno = errorno;
		this.errormsg = errormsg;
	}

	/**
	 * Used to handle the exceptions
	 * @param errorno
	 * @param errmsg
	 * @return - a solution for the error
	 */
	public Object fix(int errorno, String errmsg)
	{
		Fix1to5 f1 = new Fix1to5();
		switch(this.getErrorno()){
		case 1: 
			return f1.fix1(errorno,errmsg);

		case 2: 
			return f1.fix2(errorno,errmsg);

		case 3: 
			return f1.fix3(errorno,errmsg);

		case 4: 
			return f1.fix4(errorno,errmsg);

		case 5: 
			return f1.fix5(errorno,errmsg);
		}
		return 0;
	}

	public int getErrorno() {
		return errorno;
	}

	public void setErrorno(int errorno) {
		this.errorno = errorno;
	}

	public String getErrormsg() {
		return errormsg;
	}

	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}


}
