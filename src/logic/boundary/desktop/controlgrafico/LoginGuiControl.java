package logic.boundary.desktop.controlgrafico;

import logic.boundary.desktop.view.GenericView;
import logic.boundary.desktop.view.LoginGoogleView;
import logic.control.bean.LoginBean;
import logic.control.controlapplicativo.LoginControl;

public class LoginGuiControl {
	
	private GenericView gV; 
	private LoginBean lB;
	
	public LoginGuiControl(GenericView gView, LoginBean lBean) {
		gV = gView;
		lB = lBean;
	}
	
	public void login() {
		LoginGoogleView lGV = new LoginGoogleView();
		lGV.loginDesktop(this);
	}
	
	public void setLoginState(LoginBean lBean) {
		//verificare se l'utente � gi� stato inserito nel database e aggiungere il Park Visitor se non presente nel database 		
		LoginControl.validateOnDB(lBean.getUserID());	
		lB.setAccessToken(lBean.getAccessToken());
		lB.setUserID(lBean.getUserID());
		gV.loginOn();
	}
	
	public void logout() {
		
		String aT = lB.getAccessToken();
		LoginGoogleView lGV = new LoginGoogleView();
		if(lGV.revokeTokenDesktop(aT)){
			lB.setAccessToken(null);
			lB.setUserID(null);
			gV.loginOff();
		}
	}
}
