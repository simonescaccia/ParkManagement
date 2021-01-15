package logic.control.controlapplicativo;

import logic.control.bean.AddReportBean;
import logic.control.bean.MessageBean;
import logic.entity.model.ParkAttraction;
import logic.entity.model.ParkVisitor;

public class AddReportControl {

	public MessageBean tryToUpdate(AddReportBean aRB) {
		
		MessageBean bm = new MessageBean();	
		
		ParkVisitor parkVisitor = this.searchParkVisitor(aRB.getUserID());
		ParkAttraction parkAttraction = this.searchParkAttraction(aRB.getAttractionName());
		
		//controlli
		VerifyConditionReportControl vCR = new VerifyConditionReportControl();
		boolean b1;
		boolean b2;
		
		//This should be two threads
		b1 = vCR.verifyDistance(parkVisitor, parkAttraction);
		b2 = vCR.verifyCountDown(parkVisitor);
		
		if(b1 && b2) {
			calculateWaitingTime(parkAttraction, aRB.getQueueLen());
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
