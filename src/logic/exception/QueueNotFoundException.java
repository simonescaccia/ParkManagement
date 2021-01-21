package logic.exception;

public class QueueNotFoundException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public QueueNotFoundException (String message){
		super("error :" + message);
	}
	
	public QueueNotFoundException (Throwable cause) {
		super(cause);
	}

	public QueueNotFoundException (String message, Throwable cause) {
		super(" +++ " + message + " +++ ", cause);
	}
}
