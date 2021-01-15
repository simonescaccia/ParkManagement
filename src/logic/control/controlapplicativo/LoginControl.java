package logic.control.controlapplicativo;

import logic.control.bean.LoginBean;

public class LoginControl {
	
	private LoginBean loginBean;
	
	public LoginControl() {
		loginBean = new LoginBean();
	}
	
	public LoginBean getLoginBean() {
		return loginBean;
	}
	
	public void setLoginBean(LoginBean lB) {
		loginBean = lB;
	}
	
	
	protected void addUser() {
		//this method call ParkVisitorDao to insert a new user
	}
	
	public String getParkVisitor() {
		//dummy dummy
		return "ParkVisitor";
	}
	
	public static void validateOnDB(String idUser) {
		
		//verifica se l'utente è già stato inserito nel database
		//aggiungi il Park Visitor se non presente nel database
	}
	
	
}
