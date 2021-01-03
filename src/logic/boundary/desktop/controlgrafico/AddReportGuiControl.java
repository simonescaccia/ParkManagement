package logic.boundary.desktop.controlgrafico;

import logic.control.bean.AddReportBean;
import logic.control.bean.MessageBean;
import logic.boundary.desktop.view.AddReportView;
import logic.control.controlapplicativo.AddReportControl;

public class AddReportGuiControl {

	private AddReportBean aRB;
	private AddReportControl aRC;
	private MessageBean mB;
	private AddReportView aRV;
	
	public AddReportGuiControl(AddReportBean aRB, AddReportView aRV) {
		this.aRB = aRB;
		this.aRV = aRV;
		aRC = new AddReportControl();
		mB = new MessageBean();
	}
	
	public void insertQueueLenght() {
		
		//convert String to int
		int queueLen;
		try {
			queueLen= Integer.parseInt(aRB.getQueueLen());
			//call the controller
			MessageBean res = aRC.tryToUpdate(queueLen, aRB.getAttractionName(), aRB.getIsLast());
			
			//comunico l'esito
			mB.setMessage(res.getMessage());
			mB.setType(res.getType());
			
		} catch (NumberFormatException e) {
			//return failure
			mB.setMessage("Inserire un numero da 0 a 100");
			mB.setType(false);
			
		}
		
		aRV.showMessage(mB);
		
	}
	
}
