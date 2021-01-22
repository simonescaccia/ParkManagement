package logic.entities.model;

import logic.entities.factory.Factory;

public class ParkAttraction {
		
	private String name;
	private Queue queue;
	private Position position;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Queue getQueue() {
		Queue q = Factory.getQueue();
		q.setAvgWaitingTime(this.queue.getAvgWaitingTime());
		q.setLength(this.queue.getLength());
		q.setWaitingTime(this.queue.getWaitingTime());
		return q;
	}
	public void setQueue(Queue queue) {
		Queue q = Factory.getQueue();
		q.setAvgWaitingTime(queue.getAvgWaitingTime());
		q.setLength(queue.getLength());
		q.setWaitingTime(queue.getWaitingTime());
		this.queue = q;
	}
	public Position getPosition() {
		Position p = Factory.getPosition();
		p.setLatitude(this.position.getLatitude());
		p.setLongitude(this.position.getLongitude());
		return p;
	}
	public void setPosition(Position position) {
		Position p = Factory.getPosition();
		p.setLatitude(position.getLatitude());
		p.setLongitude(position.getLongitude());
		this.position = p;
	}
	
	
}
