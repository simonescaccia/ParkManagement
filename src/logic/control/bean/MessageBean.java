package logic.control.bean;

public class MessageBean {

	private String message;
	private boolean type;
	
	public MessageBean() {
	}
	
	
	public MessageBean(String s, boolean t) {
		message = s;
		type = t;
	}
	
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
