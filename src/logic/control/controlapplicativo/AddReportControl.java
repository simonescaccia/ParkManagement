package logic.control.controlapplicativo;

import logic.control.bean.AddReportBean;
import logic.control.bean.MessageBean;
import logic.entities.dao.ParkAttractionDAO;
import logic.entities.dao.ParkVisitorDAO;
import logic.entities.model.ParkAttraction;
import logic.entities.model.ParkVisitor;
import logic.exception.ParkAttractionNotFoundException;
import logic.exception.ParkVisitorNotFoundException;

public class AddReportControl {

	public MessageBean addQueueReport(AddReportBean aRB) {
		
		MessageBean mB = new MessageBean();	
		
		ParkVisitor parkVisitor;
		ParkAttraction parkAttraction;
		
		try {
			parkVisitor = ParkVisitorDAO.selectParkVisitor(aRB.getUserID());
			parkAttraction = ParkAttractionDAO.selectAttractionByName(aRB.getAttractionName());
		} catch (ParkVisitorNotFoundException e) {
			mB.setMessage("ParkVisitor not found");
			mB.setType(false);
			return mB;
		} catch (ParkAttractionNotFoundException e) {
			mB.setMessage("ParkAttraction not found");
			mB.setType(false);
			return mB;
		}
		
		//controlli
		VerifyConditionReportControl vCR = new VerifyConditionReportControl();
		boolean b1;
		boolean b2;
		
		//This should be two threads
		b1 = vCR.verifyDistance(aRB.getPositionBean(), parkAttraction.getPosition());
		b2 = vCR.verifyCountDown(parkVisitor);
		
		if(b1 && b2) {
			calculateWaitingTime(parkAttraction, aRB.getQueueLen());
		} else if(!b1){
			mB.setMessage("Non sei molto vicino all'attrazione");
			mB.setType(false);
		}  else {
			mB.setMessage("Aspetta ancora un po'");
			mB.setType(false);
		}
		
		//update Attraction
		
		
		//logic videoAds
		
		
		//ritorno l'esito al GuiController
		mB.setMessage("Inserimento completato");
		mB.setType(true);
		return mB;
	}
	
	protected void calculateWaitingTime(ParkAttraction attraction, int queuLEN) {
		//dummy method
	}
	
}
