package logic.boundary.controlgrafico;

import logic.bean.*;
import logic.control.controlapplicativo.LoginControl;
import logic.control.controlapplicativo.AddReportControl;

public class AddReportGuiControl {

	private AddReportBean bAR;
	private AddReportControl cAR;
	private ShowMessageBean bSM;
	
	private LoginControl loginControl;
	
	public AddReportGuiControl(AddReportBean bAR) {
		this.bAR = bAR;
		cAR = new AddReportControl();
		bSM = new ShowMessageBean();
		bSM.setGui(bAR.getGui());
	}
	
	public AddReportGuiControl(LoginBean bL) {
		bSM = new ShowMessageBean();
		bSM.setGui(bL.getGui());
	}
	
	public void login() {
		loginControl.validate();
	}
	
	public void insertQueueLenght() {
		//convert String to int
		int queueLen;		
		queueLen= Integer.parseInt(bAR.getQueueLen());
		
		//call the controller
		MessageBean res = cAR.tryToUpdate(queueLen, bAR.getAttraction());
		
		//comunico l'esito
		bSM.setMessage(res.getMessage());
		bSM.setType(res.getType());
		bSM.showMessage();
	}
	
}
