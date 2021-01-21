package logic.control.bean;

import logic.exception.NullAttractionNameException;
import logic.exception.NullLoginException;

public class AddReportBean {

	private int queueLen;
	private boolean isLast;
	private String attractionName;
	private String userID;
	private PositionBean positionBean;
	
	public PositionBean getPositionBean() {
		return positionBean;
	}

	public void setPositionBean(PositionBean positionBean) {
		this.positionBean = positionBean;
	}

	public void setQueueLen(int s) {
		queueLen = s;
	}
	
	public void setIsLast(boolean b) {
		isLast = b;
	}
	
	public void setAttractionName(String a) throws NullAttractionNameException{
		if(a == null || a.equals("")) {
			throw new NullAttractionNameException("Attraction name is null");
		}
		attractionName = a;
	}
	
	public void setUserID(String s) throws NullLoginException{
		if(s == null) {
			throw new NullLoginException("Login richiesto");
		}
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
