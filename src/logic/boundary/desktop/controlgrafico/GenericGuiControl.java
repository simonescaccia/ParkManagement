package logic.boundary.desktop.controlgrafico;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import logic.boundary.desktop.view.GenericView;
import logic.boundary.desktop.view.LoginGoogleView;
import logic.control.bean.LoginBean;
import logic.control.bean.MessageBean;
import logic.control.controlapplicativo.LoginControl;

public class GenericGuiControl {

	protected GenericView gV;
	protected LoginBean loginBean;
	
	public void setLoginBean(LoginBean lB) {
		loginBean = lB;
	}
	
	public LoginBean getLoginBean() {
		return loginBean;
	}
	
	public void showMessage(MessageBean mB) {
		String env = "user.dir";
		try {
			FileInputStream iconM;
			if(mB.getType()) {
				iconM = new FileInputStream(System.getProperty(env)+"\\img\\success-icon2.png");
			} else {
				iconM = new FileInputStream(System.getProperty(env)+"\\img\\error-flat2.png");
			}
			Image imgI = new Image(iconM);
			ImageView imgIV = new ImageView(imgI);
			gV.getIconMessage().setGraphic(imgIV);
		} catch (FileNotFoundException e) {
			if(mB.getType()) {
				gV.getIconMessage().setText("OK!");
			} else {
				gV.getIconMessage().setText("Err");
			}
		}
		gV.getLabelMessage().setText(mB.getMessage());	
	}
	
	public void login(GenericView bgView) {
		this.gV = bgView;
		LoginGoogleView lGV = new LoginGoogleView();
		lGV.loginDesktop(this);
	}
	
	public void logout(GenericView bgView) {
		
		String aT = loginBean.getAccessToken();
		LoginGoogleView lGV = new LoginGoogleView();
		if(lGV.revokeTokenDesktop(aT)){
			loginBean.setAccessToken(null);
			loginBean.setRefreshToken(null);
			loginBean.setUserID(null);
			bgView.loginOff();
		}
	}
	
	public void setLoginState(LoginBean lB) {
		
		//verificare se l'utente è già stato inserito nel database e aggiungere il Park Visitor se non presente nel database 		
		LoginControl.validateOnDB(lB.getUserID());	
		loginBean = lB;
		gV.loginOn();
		
	}
	
}
