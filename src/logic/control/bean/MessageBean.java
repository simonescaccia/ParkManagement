package logic.control.bean;

public class MessageBean {

	private String message;
	private boolean type;
	
	public void setMessage(String s) {
		message = s;
	}
	
	public void setType(boolean b) {
		type = b;
	}
	
	public boolean getType() {
		return type;
	}
	
	public String getMessage() {
		return message;
	}
}