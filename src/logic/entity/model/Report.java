package logic.entity.model;

import java.time.LocalDateTime;

public class Report {

	private LocalDateTime date;
	private boolean isLast;
	private int lengthQueue;
	private ParkAttraction parkAttraction;
	private ParkVisitor parkVisitor;
	
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public boolean isLast() {
		return isLast;
	}
	public void setLast(boolean isLast) {
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
