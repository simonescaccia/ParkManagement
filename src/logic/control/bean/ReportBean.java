package logic.control.bean;

import java.sql.Timestamp;

public class ReportBean {

	private String attrName;
	private Timestamp date;
	private int lenQueue;
	
	public String getAttrName() {
		return attrName;
	}
	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public int getLenQueue() {
		return lenQueue;
	}
	public void setLenQueue(int lenQueue) {
		this.lenQueue = lenQueue;
	}
	
	
}
