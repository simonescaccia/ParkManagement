package logic.boundary.desktop.controlgrafico;

import java.util.List;

import logic.boundary.desktop.view.InsertFeedbackView;
import logic.control.bean.MessageBean;
import logic.control.bean.ReportBean;
import logic.control.bean.UserBean;
import logic.control.controlapplicativo.InsertFeedbackControl;
import logic.exception.NullLoginException;
import logic.exception.ParkAttractionNotFoundException;
import logic.exception.ReportNotFoundException;

public class InsertFeedbackGuiControl extends GenericGuiControl{
	
	public InsertFeedbackGuiControl(InsertFeedbackView iRV) {
		super.gV = iRV;
	}
	
	public void showReports() {
		
		MessageBean mB = new MessageBean();
		
		try {
			UserBean uB = new UserBean();
			uB.setUserID(super.lGC.getLoginControl().getLoginBean().getUserID());
			
			InsertFeedbackControl iFC = new InsertFeedbackControl();
			List<ReportBean> listOfReports = iFC.showReports(uB);
			
			//show list of reports
			
			
		} catch (NullLoginException | ReportNotFoundException | ParkAttractionNotFoundException e) {
			mB.setMessage(e.getMessage());
			mB.setType(false);
			super.showMessage(mB);
		}
		
		
	}
	
}
