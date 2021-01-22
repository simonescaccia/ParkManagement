package logic.exception;

public class VideoAdsNotFoundException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VideoAdsNotFoundException (String message){
		super("error :" + message);
	}
	
	public VideoAdsNotFoundException (Throwable cause) {
		super(cause);
	}

	public VideoAdsNotFoundException (String message, Throwable cause) {
		super(" +++ " + message + " +++ ", cause);
	}
}
