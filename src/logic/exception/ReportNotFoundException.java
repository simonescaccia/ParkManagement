package logic.exception;

public class ReportNotFoundException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ReportNotFoundException (String message){
		super("error :" + message);
	}
	
	public ReportNotFoundException (Throwable cause) {
		super(cause);
	}

	public ReportNotFoundException (String message, Throwable cause) {
		super(" +++ " + message + " +++ ", cause);
	}
		
}
