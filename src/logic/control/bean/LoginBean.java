package logic.control.bean;

public class LoginBean {

	private String accessToken;
	private String refreshToken;
	private String userID;
	
	public String getAccessToken() {
		return accessToken;
	}
	
	public String getRefreshToken() {
		return refreshToken;
	}
	
	public String getUserID() {
		return userID;
	}
	
	public void setAccessToken(String aT) {
		accessToken = aT;
	}
	
	public void setRefreshToken(String rT) {
		refreshToken = rT;
	}
	
	public void setUserID(String uid) {
		userID = uid;
	}
}
