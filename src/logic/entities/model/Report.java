package logic.entities.model;

import java.sql.Time;
import java.sql.Timestamp;

public class Report {

	private Timestamp date;
	private boolean isLast;
	private int lengthQueue;
	private ParkAttraction parkAttraction;
	private ParkVisitor parkVisitor;
	private Time waitingTime;
	
	
	public Time getWaitingTime() {
		return waitingTime;
	}
	public void setWaitingTime(Time waitingTime) {
		this.waitingTime = waitingTime;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public boolean getIsLast() {
		return isLast;
	}
	public void setIsLast(boolean isLast) {
		this.isLast = isLast;
	}
	public int getLengthQueue() {
		return lengthQueue;
	}
	public void setLengthQueue(int lengthQueue) {
		this.lengthQueue = lengthQueue;
	}
	public ParkAttraction getParkAttraction() {
		return parkAttraction;
	}
	public void setParkAttraction(ParkAttraction parkAttraction) {
		this.parkAttraction = parkAttraction;
	}
	public ParkVisitor getParkVisitor() {
		return parkVisitor;
	}
	public void setParkVisitor(ParkVisitor parkVisitor) {
		this.parkVisitor = parkVisitor;
	} 
	
	
}
