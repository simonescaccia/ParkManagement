package logic.control.bean;

import java.sql.Time;
import java.sql.Timestamp;

import logic.exception.NullAttractionNameException;
import logic.exception.NullLoginException;

public class FeedbackBean {

	private Time timeFeedback;
	private String attrName;
	private Timestamp date;
	private String userID;
	

	public Time getTimeFeedback() {
		return timeFeedback;
	}
	public void setTimeFeedback(Time timeFeedback) {
		this.timeFeedback = timeFeedback;
	}
	public String getAttrName() {
		return attrName;
	}
	public void setAttrName(String attrName) throws NullAttractionNameException {
		if(attrName == null || attrName.equals("")) {
			throw new NullAttractionNameException("Attraction name is null");
		}
		this.attrName = attrName;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) throws NullLoginException {
		if(userID == null) {
			throw new NullLoginException("Login richiesto");
		}
		this.userID = userID;
	}
	
	
}
