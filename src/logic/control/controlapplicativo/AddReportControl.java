package logic.control.controlapplicativo;

import logic.control.bean.AddReportBean;
import logic.control.bean.MessageBean;
import logic.entity.dao.ParkAttractionDAO;
import logic.entity.dao.ParkVisitorDAO;
import logic.entity.model.ParkAttraction;
import logic.entity.model.ParkVisitor;

public class AddReportControl {

	public MessageBean addQueueReport(AddReportBean aRB) {
		
		MessageBean bm = new MessageBean();	
		
		ParkVisitor parkVisitor = ParkVisitorDAO.selectParkVisitor(aRB.getUserID());
		ParkAttraction parkAttraction = ParkAttractionDAO.selectAttractionByName(aRB.getAttractionName());
		
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
	
	protected void calculateWaitingTime(ParkAttraction attraction, int queuLEN) {
		//dummy method
	}
	
}
