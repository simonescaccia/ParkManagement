package logic.exception;

public class PositionNotFoundException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PositionNotFoundException (String message){
		super("error :" + message);
	}
	
	public PositionNotFoundException (Throwable cause) {
		super(cause);
	}

	public PositionNotFoundException (String message, Throwable cause) {
		super(" +++ " + message + " +++ ", cause);
	}
	
}
