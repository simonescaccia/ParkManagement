package logic.entity.model;

import java.time.LocalTime;

public class Queue {

	private int length;
	private LocalTime waitingTime;
	private LocalTime avgWaitingTime;
	
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public LocalTime getWaitingTime() {
		return waitingTime;
	}
	public void setWaitingTime(LocalTime waitingTime) {
		this.waitingTime = waitingTime;
	}
	public LocalTime getAvgWaitingTime() {
		return avgWaitingTime;
	}
	public void setAvgWaitingTime(LocalTime avgWaitingTime) {
		this.avgWaitingTime = avgWaitingTime;
	}
	
	
	
	
}
