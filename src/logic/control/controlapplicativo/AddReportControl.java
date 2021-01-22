package logic.control.controlapplicativo;

import java.sql.Timestamp;

import logic.control.bean.AddReportBean;
import logic.control.bean.MessageBean;
import logic.control.bean.UserBean;
import logic.entities.dao.ParkAttractionDAO;
import logic.entities.dao.ParkVisitorDAO;
import logic.entities.factory.Factory;
import logic.entities.model.ParkAttraction;
import logic.entities.model.ParkVisitor;
import logic.entities.model.Report;
import logic.exception.DBFailureException;
import logic.exception.ParkAttractionNotFoundException;
import logic.exception.ParkVisitorNotFoundException;
import logic.exception.ReportNotFoundException;

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
		try {
			b2 = vCR.verifyCountDown(parkVisitor,parkAttraction);
		} catch (DBFailureException e) {
			mB.setMessage("DB failure");
			mB.setType(false);
			return mB;
		}
		
		//if the conditions are true then i can commit the report
		if(b1 && b2) {
			
			CommitReportControl cRC = new CommitReportControl(); 
			Report report = Factory.getReport();
			//fill Report
			report.setIsLast(aRB.getIsLast());
			report.setLengthQueue(aRB.getQueueLen());
			Timestamp now = new Timestamp(System.currentTimeMillis());
			report.setDate(now);
			report.setParkVisitor(parkVisitor);
			report.setParkAttraction(parkAttraction);
			//commit the report
			try {
				cRC.commitTheReport(report);
			} catch (ParkAttractionNotFoundException | ReportNotFoundException | ParkVisitorNotFoundException e) {
				mB.setMessage(e.getMessage());
				mB.setType(false);
				return mB;
			}
			
		} else if(!b1){
			mB.setMessage("Non sei molto vicino all'attrazione");
			mB.setType(false);
			return mB;
		}  else {
			mB.setMessage("Aspetta ancora un po'");
			mB.setType(false);
			return mB;
		}
		
		//ritorno l'esito al GuiController
		mB.setMessage("Inserimento completato");
		mB.setType(true);
		return mB;
	}

	public void showVideoAds(UserBean vAB) {
		ShowVideoAdsControl sVAC = new ShowVideoAdsControl();
		sVAC.loadVideoAds(vAB.getUserID());
	}
}
