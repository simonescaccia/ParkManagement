package logic.control.bean;

import logic.exception.NullLoginException;

public class UserBean {

	private String userID;

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) throws NullLoginException {
		if(userID == null) {
			throw new NullLoginException("Login richiesto");
		}
		this.userID = userID;
	}
	
	
}
