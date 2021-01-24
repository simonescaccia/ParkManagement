package logic.entities.model;

import java.io.InputStream;

public class Category {

	private int id;
	private String name;
	private InputStream imgC;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public InputStream getImgC() {
		return imgC;
	}
	public void setImgC(InputStream imgC) {
		this.imgC = imgC;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
