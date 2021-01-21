package logic.exception;

public class ParkAttractionNotFoundException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ParkAttractionNotFoundException (String message){
		super("error :" + message);
	}
	
	public ParkAttractionNotFoundException (Throwable cause) {
		super(cause);
	}

	public ParkAttractionNotFoundException (String message, Throwable cause) {
		super(" +++ " + message + " +++ ", cause);
	}
	
}
