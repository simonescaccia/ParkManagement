package logic.exception;

public class ConnectionDBFailedException extends DBFailureException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ConnectionDBFailedException (String message){
		super(message);
	}
	
	public ConnectionDBFailedException (Throwable cause) {
		super(cause);
	}

	public ConnectionDBFailedException (String message, Throwable cause) {
		super(" +++ " + message + " +++ ", cause);
	}
}
