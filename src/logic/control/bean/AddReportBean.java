package logic.control.bean;


public class AddReportBean {

	private int queueLen;
	private boolean isLast;
	private String attractionName;
	private String userID;
	
	public void setQueueLen(int s) {
		queueLen = s;
	}
	
	public void setIsLast(boolean b) {
		isLast = b;
	}
	
	public void setAttractionName(String a) {
		attractionName = a;
	}
	
	public void setUserID(String s) {
		userID = s;
	}
	
	public String getUserID() {
		return userID;
	}
	
	public String getAttractionName() {
		return attractionName;
	}
	
	public int getQueueLen() {
		return queueLen;
	}
	
	public boolean getIsLast() {
		return isLast;
	}
}
