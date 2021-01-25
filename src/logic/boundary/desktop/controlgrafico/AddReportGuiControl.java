package logic.boundary.desktop.controlgrafico;

import logic.control.bean.AddReportBean;
import logic.control.bean.MessageBean;
import logic.control.bean.PositionBean;
import logic.control.bean.UserBean;
import logic.boundary.desktop.view.AddReportView;
import logic.boundary.desktop.view.PositionGoogleMapsView;
import logic.control.controlapplicativo.AddReportControl;
import logic.exception.NullAttractionNameException;
import logic.exception.NullLoginException;
import logic.exception.PositionNotFoundException;

public class AddReportGuiControl extends GenericGuiControl{

	private AddReportControl aRC;
	
	public AddReportGuiControl(AddReportView aRV) {
		super.gV = aRV;
		aRC = new AddReportControl(); 
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
		
		aRC.showVideoAds(vAB);
	}
	
	public void insertQueueLenght(String attractionName, String queueLenS, boolean isLast) {
		
		int queueLen;
		MessageBean mB = new MessageBean();
		AddReportBean aRB = new AddReportBean();
		aRB.setIsLast(isLast);
		
			
		try {
			
			//insert park attraction name into the bean
			aRB.setAttractionName(attractionName);
			
			//convert queueLen String to int		
			queueLen= Integer.parseInt(queueLenS);
			aRB.setQueueLen(queueLen);
			
			//insert userID into the bean
			aRB.setUserID(super.lGC.getLoginControl().getLoginBean().getUserID());
			
			//get the position of user
			PositionBean pB = new PositionBean();
			PositionGoogleMapsView pGM = new PositionGoogleMapsView();
			pGM.getPosition(pB);
			aRB.setPositionBean(pB);
			
			//call the controller
			MessageBean res = aRC.addQueueReport(aRB);
			
			//comunico l'esito
			mB.setMessage(res.getMessage());
			mB.setType(res.getType());
			
		} catch (NumberFormatException e) {
			//return failure convertion
			mB.setMessage("Inserire un numero da 0 a 150");
			mB.setType(false);
		} catch (NullLoginException | PositionNotFoundException | NullAttractionNameException e) {
			//return failure login null
			mB.setMessage(e.getMessage());
			mB.setType(false);			
		}
		
		super.showMessage(mB);
		
		if(mB.getType()) {
			((AddReportView)super.gV).showButtonVideoAds();
		}
		
	}
	
}
