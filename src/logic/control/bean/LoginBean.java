package logic.control.bean;

public class LoginBean {

	private String accessToken;
	private String userID;
	
	public String getAccessToken() {
		return accessToken;
	}
	
	public String getUserID() {
		return userID;
	}
	
	public void setAccessToken(String aT) {
		accessToken = aT;
	}
	
	public void setUserID(String uid) {
		userID = uid;
	}
}
