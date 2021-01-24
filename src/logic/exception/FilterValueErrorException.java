package logic.exception;

public class FilterValueErrorException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FilterValueErrorException (String message){
		super("error :" + message);
	}
	
	public FilterValueErrorException (Throwable cause) {
		super(cause);
	}

	public FilterValueErrorException (String message, Throwable cause) {
		super(" +++ " + message + " +++ ", cause);
	}
}
