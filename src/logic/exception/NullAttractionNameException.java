package logic.exception;

public class NullAttractionNameException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NullAttractionNameException (String message){
		super("error :" + message);
	}
	
	public NullAttractionNameException (Throwable cause) {
		super(cause);
	}

	public NullAttractionNameException (String message, Throwable cause) {
		super(" +++ " + message + " +++ ", cause);
	}
	
}
