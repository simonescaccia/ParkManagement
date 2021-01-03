package logic.control.controlapplicativo;

import logic.control.bean.MessageBean;
import logic.entity.model.ParkAttraction;
import logic.entity.model.ParkVisitor;

public class AddReportControl {

	public MessageBean tryToUpdate(int queueLen, String attraction, boolean isLast) {
		
		MessageBean bm = new MessageBean();
		
		//get utente
		LoginControl loginControl = new LoginControl();
		
		// dummy dummy
		String username = loginControl.getParkVisitor();
		
		ParkVisitor parkVisitor = this.searchParkVisitor(username);
		ParkAttraction parkAttraction = this.searchParkAttraction(attraction);
		
		//controlli
		VerifyConditionReportControl vCR = new VerifyConditionReportControl();
		boolean b1;
		boolean b2;
		
		//This should be two threads
		b1 = vCR.verifyDistance(parkVisitor, parkAttraction);
		b2 = vCR.verifyCountDown(parkVisitor);
		
		if(b1 && b2) {
			calculateWaitingTime(parkAttraction, queueLen);
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
		bm.setMessage("Inserimento completato");
		bm.setType(true);
		return bm;
	}
	
	protected ParkAttraction searchParkAttraction(String attraction) {
		//dummy 
		return new ParkAttraction();
	}
	
	protected ParkVisitor searchParkVisitor(String username) {
		//dummy 
		return new ParkVisitor();
	}	
	
	protected void calculateWaitingTime(ParkAttraction attraction, int queuLEN) {
		//dummy method
	}
	
}
