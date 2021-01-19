package logic.exception;

public class NullLoginException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NullLoginException (String message){
		super("error :" + message);
	}
	
	public NullLoginException (Throwable cause) {
		super(cause);
	}

	public NullLoginException (String message, Throwable cause) {
		super(" +++ " + message + " +++ ", cause);
	}

}
