package logic.boundary.desktop.controlgrafico;

import logic.control.bean.AddReportBean;
import logic.control.bean.MessageBean;
import logic.boundary.desktop.view.AddReportView;
import logic.control.controlapplicativo.AddReportControl;
import logic.exception.NullLoginException;

public class AddReportGuiControl extends GenericGuiControl{

	private AddReportControl aRC;
	
	public AddReportGuiControl(AddReportView aRV) {
		super.gV = aRV;
		aRC = new AddReportControl(); 
	}

	public void insertQueueLenght(String attractionName, String queueLenS, boolean isLast) {
		
		int queueLen;
		MessageBean mB = new MessageBean();
		AddReportBean aRB = new AddReportBean();
		aRB.setAttractionName(attractionName);
		aRB.setIsLast(isLast);
		
		//convert String to int		
		try {
			
			//controll login
			if(super.loginBean.getUserID() != null) {
				aRB.setUserID(super.loginBean.getUserID());
			} else {
				throw new NullLoginException("Add Report need a not null idUser");
			}
			
			
			queueLen= Integer.parseInt(queueLenS);
			aRB.setQueueLen(queueLen);
			
			//call the controller
			MessageBean res = aRC.tryToUpdate(aRB);
			
			//comunico l'esito
			mB.setMessage(res.getMessage());
			mB.setType(res.getType());
			
		} catch (NumberFormatException e) {
			//return failure
			mB.setMessage("Inserire un numero da 0 a 100");
			mB.setType(false);
		} catch (NullLoginException e) {
			//return failure
			mB.setMessage("Login richiesto");
			mB.setType(false);			
		}
		
		super.showMessage(mB);
		
	}
	
}
