package logic.bean;

import logic.controlgrafico.AddReportGuiControl;

public class LoginBean {

	private Object gui;
	
	public void setGui(Object gui) {
		this.gui = gui;
	}
	
	public Object getGui() {
		return gui;
	}
	
	public void login() {
		AddReportGuiControl cGAR = new AddReportGuiControl(this);
		cGAR.login();
	}
	
	
}
