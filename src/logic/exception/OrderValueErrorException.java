package logic.exception;

public class OrderValueErrorException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OrderValueErrorException (String message){
		super("error :" + message);
	}
	
	public OrderValueErrorException (Throwable cause) {
		super(cause);
	}

	public OrderValueErrorException (String message, Throwable cause) {
		super(" +++ " + message + " +++ ", cause);
	}
}
