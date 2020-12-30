package logic.control.bean;

import logic.boundary.desktop.controlgrafico.AddReportGuiControl;
import logic.boundary.desktop.view.AddReportView;

public class LoginBean {

	private AddReportView gui;
	
	public void setGui(AddReportView gui) {
		this.gui = gui;
	}
	
	public AddReportView getGui() {
		return gui;
	}
	
	public void login() {
		AddReportGuiControl cGAR = new AddReportGuiControl(this);
		cGAR.login();
	}
	
	
}
