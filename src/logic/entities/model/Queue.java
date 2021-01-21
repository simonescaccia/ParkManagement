package logic.entities.model;

import java.sql.Time;

public class Queue {

	private int length;
	private Time waitingTime;
	private double avgWaitingTime;
	
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public Time getWaitingTime() {
		return waitingTime;
	}
	public void setWaitingTime(Time waitingTime) {
		this.waitingTime = waitingTime;
	}
	public double getAvgWaitingTime() {
		return avgWaitingTime;
	}
	public void setAvgWaitingTime(double avgWaitingTime) {
		this.avgWaitingTime = avgWaitingTime;
	}
	
}
