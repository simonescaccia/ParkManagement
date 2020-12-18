package logic.controlapplicativo;

import logic.bean.BeanMessage;
import logic.model.ParkAttraction;
import logic.model.ParkVisitor;

public class ControlAddReport {

	public BeanMessage tryToUpdate(int queueLen, String attraction) {
		
		BeanMessage bm = new BeanMessage();
		
		//get utente
		Login login = new Login();
		String username = login.validate();
		
		ParkVisitor parkVisitor = this.getParkVisitor(username);
		ParkAttraction parkAttraction = this.getParkAttraction(attraction);
		
		//controlli
		ControlVerifyConditionReport vCR = new ControlVerifyConditionReport();
		boolean b1;
		boolean b2;
		
		//This should be two threads
		b1 = vCR.verifyDistance(parkVisitor, parkAttraction);
		b2 = vCR.verifyCountDown(parkVisitor);
		
		if(b1 && b2) {
			vCR.calculateWaitingTime(parkAttraction, queueLen);
		} else if(!b1){
			bm.setMessage("Non sei molto vicino all'attrazione");
			bm.setType(false);
		}  else {
			bm.setMessage("Aspetta ancora un po'");
			bm.setType(false);
		}
		
		//ritorno l'esito al GuiController
		bm.setMessage("Errore #150");
		bm.setType(false);
		return bm;
	}
	
	protected ParkAttraction getParkAttraction(String attraction) {
		//dummy 
		return new ParkAttraction();
	}
	
	protected ParkVisitor getParkVisitor(String username) {
		//dummy 
		return new ParkVisitor();
	}	
	
}
