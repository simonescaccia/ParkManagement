package logic.entities.model;

import java.io.InputStream;

import logic.entities.factory.Factory;

public class ParkAttraction {
		
	private String name;
	private Queue queue;
	private Position position;
	private Category category;	
	private InputStream img;
	private String description;
	
	public Category getCategory() {
		Category c = Factory.getCategory();
		c.setId(this.category.getId());
		c.setName(this.category.getName());
		c.setImgC(this.category.getImgC());
		return c;
	}
	public void setCategory(Category category) {
		Category c = Factory.getCategory();
		c.setId(category.getId());
		c.setName(category.getName());
		c.setImgC(category.getImgC());
		this.category = c;
	}
	public InputStream getImg() {
		return img;
	}
	public void setImg(InputStream img) {
		this.img = img;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
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
		q.setIdQueue(this.queue.getIdQueue());
		return q;
	}
	public void setQueue(Queue queue) {
		Queue q = Factory.getQueue();
		q.setAvgWaitingTime(queue.getAvgWaitingTime());
		q.setLength(queue.getLength());
		q.setWaitingTime(queue.getWaitingTime());
		q.setIdQueue(queue.getIdQueue());
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
