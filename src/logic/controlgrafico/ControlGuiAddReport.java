package logic.controlgrafico;

import logic.bean.*;
import logic.controlapplicativo.ControlAddReport;

public class ControlGuiAddReport {

	private BeanAddReport bAR;
	private ControlAddReport cAR;
	private BeanShowMessage bSM;
	
	public ControlGuiAddReport(BeanAddReport bAR) {
		this.bAR = bAR;
		cAR = new ControlAddReport();
		bSM = new BeanShowMessage();
		bSM.setGui(bAR.getGui());
	}
	
	public void insertQueueLenght() {
		//convert String to int
		int foo;		
		foo = Integer.parseInt(bAR.getQueueLen());
		
		//call the controller
		BeanMessage res = cAR.tryToUpdate(foo, bAR.getAttraction());
		
		//comunico l'esito
		bSM.setMessage(res.getMessage());
		bSM.setType(res.getType());
		bSM.showMessage();
	}
	
}
