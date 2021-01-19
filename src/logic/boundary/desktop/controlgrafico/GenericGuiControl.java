package logic.boundary.desktop.controlgrafico;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import logic.boundary.desktop.view.GenericView;
import logic.control.bean.MessageBean;

public abstract class GenericGuiControl {

	protected GenericView gV;
	protected LoginGuiControl lGC;
	
	protected GenericGuiControl() {
		lGC = new LoginGuiControl();
	}
	
	public void setLoginGuiControl(LoginGuiControl lGControl) {
		lGC = lGControl;
	}
	
	public LoginGuiControl getLoginGuiControl() {
		return lGC;
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
	
	public void login(){
		lGC.login(this.gV);
	}
	
	public void logout(){
		lGC.logout(this.gV);
	}
	
}
