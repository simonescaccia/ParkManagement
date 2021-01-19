package logic.boundary.desktop.controlgrafico;

import logic.boundary.desktop.view.GenericView;
import logic.boundary.desktop.view.LoginGoogleView;
import logic.control.bean.LoginBean;
import logic.control.controlapplicativo.LoginControl;

public class LoginGuiControl {
	
	private GenericView gV;
	private LoginControl lC;
	
	public LoginGuiControl() {
		lC = new LoginControl();
	}
	
	public LoginControl getLoginControl() {
		return lC;
	}
	
	public void login(GenericView gView) {
		gV = gView;
		LoginGoogleView lGV = new LoginGoogleView();
		lGV.loginDesktop(this);
	}
	
	public void setLoginState(LoginBean lBean) {
		//verificare se l'utente è già stato inserito nel database e aggiungere il Park Visitor se non presente nel database 		
		LoginControl.validateOnDB(lBean.getUserID());	
		lC.getLoginBean().setAccessToken(lBean.getAccessToken());
		lC.getLoginBean().setUserID(lBean.getUserID());
		gV.loginOn();
	}
	
	public void logout(GenericView gView) {
		
		String aT = lC.getLoginBean().getAccessToken();
		LoginGoogleView lGV = new LoginGoogleView();
		if(lGV.revokeTokenDesktop(aT)){
			lC.getLoginBean().setAccessToken(null);
			lC.getLoginBean().setUserID(null);
			gView.loginOff();
		}
	}
}
