package logic.bean;

import logic.controlgrafico.ControlGuiAddReport;

public class BeanLogin {

	private Object gui;
	
	public void setGui(Object gui) {
		this.gui = gui;
	}
	
	public Object getGui() {
		return gui;
	}
	
	public void login() {
		ControlGuiAddReport cGAR = new ControlGuiAddReport(this);
		cGAR.login();
	}
	
	
}
