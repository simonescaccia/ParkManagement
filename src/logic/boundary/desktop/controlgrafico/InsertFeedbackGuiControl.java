package logic.boundary.desktop.controlgrafico;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

import javafx.stage.Stage;
import logic.boundary.desktop.view.InsertFeedbackView;
import logic.control.bean.FeedbackBean;
import logic.control.bean.MessageBean;
import logic.control.bean.ReportBean;
import logic.control.bean.UserBean;
import logic.control.controlapplicativo.InsertFeedbackControl;
import logic.control.controlapplicativo.ShowVideoAdsControl;
import logic.exception.NullAttractionNameException;
import logic.exception.NullLoginException;
import logic.exception.ParkAttractionNotFoundException;
import logic.exception.ParkVisitorNotFoundException;
import logic.exception.QueueNotFoundException;
import logic.exception.ReportNotFoundException;

public class InsertFeedbackGuiControl extends GenericGuiControl{
	
	public InsertFeedbackGuiControl(InsertFeedbackView iRV) {
		super.gV = iRV;
	}
	
	public void showVideoAds() {
		UserBean vAB= new UserBean();
		try {
			vAB.setUserID(super.lGC.getLoginControl().getLoginBean().getUserID());
		} catch (NullLoginException e) {
			MessageBean mB = new MessageBean();
			mB.setMessage(e.getMessage());
			mB.setType(false);
			showMessage(mB);
			return;
		}
		
		ShowVideoAdsControl sVAC = new ShowVideoAdsControl();
		sVAC.loadVideoAds(vAB.getUserID());
	}
	
	public void showReports() {
		
		MessageBean mB = new MessageBean();
		
		try {
			UserBean uB = new UserBean();
			uB.setUserID(super.lGC.getLoginControl().getLoginBean().getUserID());
			
			InsertFeedbackControl iFC = new InsertFeedbackControl();
			List<ReportBean> listOfReports = iFC.showReports(uB);
			
			//show list of reports
			((InsertFeedbackView)super.gV).showReports(listOfReports);
			
			
		} catch (NullLoginException | ReportNotFoundException | ParkAttractionNotFoundException e) {
			mB.setMessage(e.getMessage());
			mB.setType(false);
			super.showMessage(mB);
		}
		
	}
	
	public void insertFeedback(String feedback, String attrName, String date) {
		
		MessageBean mB = new MessageBean();
		FeedbackBean fB = new FeedbackBean();
		
		try {
			//insert park attraction name into the bean
			fB.setAttrName(attrName);
			
			//convert feedback String to int and to Time	
			int feedbackI = Integer.parseInt(feedback);
			if((Integer.signum(feedbackI) == -1) || feedbackI>200) {
				throw new NumberFormatException();
			}
			long milliseconds = (long)((feedbackI*60*1000)-(60*60*1000));
			Time feedbackTime = new Time(milliseconds);
			fB.setTimeFeedback(feedbackTime);
			
			fB.setDate(Timestamp.valueOf(date));
			
			//insert userID into the bean
			fB.setUserID(super.lGC.getLoginControl().getLoginBean().getUserID());
			
			InsertFeedbackControl iFC = new InsertFeedbackControl();
			mB = iFC.insertFeedback(fB);
			
			//load new info updated
			Stage stage = super.gV.getStage();
			InsertFeedbackView iFV = new InsertFeedbackView(super.lGC);
			this.gV = iFV;
			iFV.showScene(stage);
			
			super.showMessage(mB);
			
		} catch (NumberFormatException e) {
			//return failure convertion
			mB.setMessage("Valore non valido");
			mB.setType(false);
			super.showMessage(mB);
		} catch (NullAttractionNameException | NullLoginException | ReportNotFoundException | QueueNotFoundException | ParkAttractionNotFoundException | ParkVisitorNotFoundException e) {
			mB.setMessage(e.getMessage());
			mB.setType(false);
			super.showMessage(mB);
		} 

		//videoAds
		if(mB.getType()) {
			((InsertFeedbackView)super.gV).showButtonVideoAds();
		}
		
	}
	
}
