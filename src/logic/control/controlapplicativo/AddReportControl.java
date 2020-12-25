package logic.control.controlapplicativo;

import logic.bean.MessageBean;
import logic.entity.model.ParkAttraction;
import logic.entity.model.ParkVisitor;

public class AddReportControl {

	public MessageBean tryToUpdate(int queueLen, String attraction) {
		
		MessageBean bm = new MessageBean();
		
		//get utente
		LoginControl loginControl = new LoginControl();
		String username = loginControl.validate();
		
		ParkVisitor parkVisitor = this.getParkVisitor(username);
		ParkAttraction parkAttraction = this.getParkAttraction(attraction);
		
		//controlli
		VerifyConditionReportControl vCR = new VerifyConditionReportControl();
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
		
		//update Attraction
		
		
		//logic videoAds
		
		
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
