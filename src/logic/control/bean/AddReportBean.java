package logic.control.bean;


public class AddReportBean {

	private String queueLen;
	private boolean isLast;
	private String attractionName;
	
	public void setQueueLen(String s) {
		queueLen = s;
	}
	
	public void setIsLast(boolean b) {
		isLast = b;
	}
	
	public void setAttractionName(String a) {
		attractionName = a;
	}
	
	public String getAttractionName() {
		return attractionName;
	}
	
	public String getQueueLen() {
		return queueLen;
	}
	
	public boolean getIsLast() {
		return isLast;
	}
}
