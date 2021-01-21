package logic.exception;

public class DBFailureException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DBFailureException (String message){
		super("error :" + message);
	}
	
	public DBFailureException (Throwable cause) {
		super(cause);
	}

	public DBFailureException (String message, Throwable cause) {
		super(" +++ " + message + " +++ ", cause);
	}
	
}
