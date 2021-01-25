package logic.control.bean;

import java.sql.Timestamp;

public class ReportBean {

	private ParkAttractionBean parkAttraction;
	private Timestamp date;
	private int lenQueue;
	
	public ParkAttractionBean getParkAttraction() {
		return parkAttraction;
	}
	public void setParkAttraction(ParkAttractionBean pA) {
		this.parkAttraction = pA;
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
