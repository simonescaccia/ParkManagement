package logic.exception;

public class CategoryNotFoundException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CategoryNotFoundException (String message){
		super("error :" + message);
	}
	
	public CategoryNotFoundException (Throwable cause) {
		super(cause);
	}

	public CategoryNotFoundException (String message, Throwable cause) {
		super(" +++ " + message + " +++ ", cause);
	}
}
