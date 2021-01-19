package logic.control.controlapplicativo;

import logic.control.bean.LoginBean;

public class LoginControl {

	private LoginBean lB;
	
	public LoginControl() {
		lB = new LoginBean();
	}
	
	public void setLoginBean(LoginBean lBean) {
		lB = lBean;
	}
	
	public LoginBean getLoginBean() {
		return lB;
	}	
	
	public static void validateOnDB(String idUser) {
		
		//verifica se l'utente è già stato inserito nel database
		//aggiungi il Park Visitor se non presente nel database
	}
	
	
}
