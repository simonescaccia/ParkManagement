package logic.control.bean;

import java.io.InputStream;
import java.util.List;

public class ParkAttractionBean {

	private int distanceFromUser;
	private String name;
	private String categoryName;
	private InputStream imgC;
	private List<ReportBean> listOfReports;
	
	
	public List<ReportBean> getListOfReports() {
		return listOfReports;
	}
	public void setListOfReports(List<ReportBean> listOfReports) {
		this.listOfReports = listOfReports;
	}
	public InputStream getImgC() {
		return imgC;
	}
	public void setImgC(InputStream imgC) {
		this.imgC = imgC;
	}
	private int waitingTime;
	private InputStream img;
	
	public int getDistanceFromUser() {
		return distanceFromUser;
	}
	public void setDistanceFromUser(int distanceFromUser) {
		this.distanceFromUser = distanceFromUser;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public int getWaitingTime() {
		return waitingTime;
	}
	public void setWaitingTime(int waitingTime) {
		this.waitingTime = waitingTime;
	}
	public InputStream getImg() {
		return img;
	}
	public void setImg(InputStream img) {
		this.img = img;
	}

	
	
	
}
