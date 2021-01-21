package logic.exception;

public class ParkVisitorNotFoundException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ParkVisitorNotFoundException (String message){
		super("error :" + message);
	}
	
	public ParkVisitorNotFoundException (Throwable cause) {
		super(cause);
	}

	public ParkVisitorNotFoundException (String message, Throwable cause) {
		super(" +++ " + message + " +++ ", cause);
	}
}
